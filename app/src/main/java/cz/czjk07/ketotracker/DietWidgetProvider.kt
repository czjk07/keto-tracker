package cz.czjk07.ketotracker

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikeThroughSpan
import android.widget.RemoteViews

class DietWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_TOGGLE_MEAL) {
            val mealIndex = intent.getIntExtra(EXTRA_MEAL_INDEX, -1)
            if (mealIndex in 0 until DietWidgetState.MEALS_PER_DAY) {
                val state = DietWidgetState(context)
                val day = state.currentDay
                state.toggle(day, mealIndex)
                if (state.allConsumed(day)) {
                    if (day < DietPlan.dayCount) {
                        state.currentDay = day + 1
                    } else {
                        state.finished = true
                    }
                }
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val appWidgetIds = appWidgetManager.getAppWidgetIds(
                    ComponentName(context, DietWidgetProvider::class.java)
                )
                for (appWidgetId in appWidgetIds) {
                    updateWidget(context, appWidgetManager, appWidgetId)
                }
            }
        }
    }

    companion object {
        const val ACTION_TOGGLE_MEAL = "cz.czjk07.ketotracker.action.TOGGLE_MEAL"
        const val EXTRA_MEAL_INDEX = "cz.czjk07.ketotracker.extra.MEAL_INDEX"

        private val rowIds = intArrayOf(R.id.meal_row_0, R.id.meal_row_1, R.id.meal_row_2)
        private val nameIds = intArrayOf(R.id.meal_name_0, R.id.meal_name_1, R.id.meal_name_2)
        private val boxIds = intArrayOf(R.id.meal_box_0, R.id.meal_box_1, R.id.meal_box_2)
        private val liquidIds = intArrayOf(R.id.meal_liquid_0, R.id.meal_liquid_1, R.id.meal_liquid_2)

        fun updateWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val state = DietWidgetState(context)
            val views = RemoteViews(context.packageName, R.layout.widget_diet)

            val day = state.currentDay
            val meals = DietPlan.days[day - 1]

            views.setTextViewText(
                R.id.widget_day,
                if (state.finished) "Hotovo ✓" else "Den $day/${DietPlan.dayCount}"
            )

            for (i in 0 until DietWidgetState.MEALS_PER_DAY) {
                val meal = meals[i]
                val consumed = state.isConsumed(day, i)
                val color = DietPlan.colorFor(meal.name)

                views.setInt(rowIds[i], "setBackgroundColor", DietPlan.tintOf(color))
                views.setFloat(rowIds[i], "setAlpha", if (consumed) 0.45f else 1.0f)

                views.setTextViewText(nameIds[i], strike(meal.name, consumed))
                views.setTextColor(nameIds[i], DietPlan.darkOf(color))

                views.setTextViewText(boxIds[i], strike(meal.box.toString(), consumed))
                views.setTextColor(boxIds[i], COLOR_GREY)

                val liquidText = meal.liquidMl?.let { "$it ml" } ?: "–"
                views.setTextViewText(liquidIds[i], strike(liquidText, consumed))
                views.setTextColor(liquidIds[i], COLOR_GREY)

                val toggleIntent = Intent(context, DietWidgetProvider::class.java).apply {
                    action = ACTION_TOGGLE_MEAL
                    putExtra(EXTRA_MEAL_INDEX, i)
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    i,
                    toggleIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(rowIds[i], pendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private const val COLOR_GREY = 0xFF424242.toInt()

        private fun strike(text: String, strike: Boolean): CharSequence {
            if (!strike) return text
            return SpannableString(text).apply {
                setSpan(StrikeThroughSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }
}
