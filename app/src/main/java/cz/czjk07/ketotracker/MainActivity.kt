package cz.czjk07.ketotracker

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            DietWidgetState(this).reset()
            val appWidgetManager = AppWidgetManager.getInstance(this)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(this, DietWidgetProvider::class.java)
            )
            for (appWidgetId in appWidgetIds) {
                DietWidgetProvider.updateWidget(this, appWidgetManager, appWidgetId)
            }
            Toast.makeText(this, R.string.reset_done, Toast.LENGTH_SHORT).show()
        }
    }
}
