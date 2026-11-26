package cz.czjk07.ketotracker

import android.content.Context
import android.content.SharedPreferences

class DietWidgetState(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var currentDay: Int
        get() = prefs.getInt(KEY_CURRENT_DAY, 1).coerceIn(1, DietPlan.dayCount)
        set(value) = prefs.edit().putInt(KEY_CURRENT_DAY, value.coerceIn(1, DietPlan.dayCount)).apply()

    var finished: Boolean
        get() = prefs.getBoolean(KEY_FINISHED, false)
        set(value) = prefs.edit().putBoolean(KEY_FINISHED, value).apply()

    fun isConsumed(day: Int, mealIndex: Int): Boolean =
        prefs.getBoolean(key(day, mealIndex), false)

    fun toggle(day: Int, mealIndex: Int): Boolean {
        val key = key(day, mealIndex)
        val now = !prefs.getBoolean(key, false)
        prefs.edit().putBoolean(key, now).apply()
        return now
    }

    fun allConsumed(day: Int): Boolean = (0 until MEALS_PER_DAY).all { isConsumed(day, it) }

    fun reset() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "diet_widget"
        private const val KEY_CURRENT_DAY = "current_day"
        private const val KEY_FINISHED = "finished"
        const val MEALS_PER_DAY = 3

        private fun key(day: Int, mealIndex: Int) = "consumed_${day}_$mealIndex"
    }
}
