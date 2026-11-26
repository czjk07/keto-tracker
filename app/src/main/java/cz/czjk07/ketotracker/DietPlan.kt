package cz.czjk07.ketotracker

import android.graphics.Color

data class Meal(
    val name: String,
    val box: Int,
    val liquidMl: Int?
)

object DietPlan {

    private fun bar(name: String) = Meal(name, box = 0, liquidMl = null)
    private fun soup(name: String, box: Int) = Meal(name, box = box, liquidMl = 400)
    private fun yogurt(name: String, box: Int) = Meal(name, box = box, liquidMl = 200)
    private fun protein(name: String, box: Int) = Meal(name, box = box, liquidMl = 200)

    val days: List<List<Meal>> = listOf(
        listOf(bar("Tyčinka smetana"), soup("Houbová polévka", 4), protein("Protein vanilka", 8)),
        listOf(yogurt("Jogurt broskev", 6), soup("Kuřecí polévka", 2), protein("Protein jahoda", 9)),
        listOf(bar("Tyčinka čoko"), soup("Zeleninová polévka", 1), bar("Tyčinka kapučíno")),
        listOf(bar("Tyčinka smetana"), yogurt("Jogurt cookies", 5), protein("Protein vanilka", 8)),
        listOf(bar("Tyčinka kokos"), soup("Hrachová polévka", 3), yogurt("Jogurt čokoláda s pomerančem", 7)),
        listOf(bar("Tyčinka jogurt"), soup("Houbová polévka", 4), protein("Protein banán", 10)),
        listOf(yogurt("Jogurt broskev", 6), soup("Kuřecí polévka", 2), protein("Protein jahoda", 9)),
        listOf(bar("Tyčinka kokos"), soup("Zeleninová polévka", 1), yogurt("Jogurt cookies", 5)),
        listOf(bar("Tyčinka kapučíno"), yogurt("Jogurt cookies", 5), bar("Tyčinka smetana")),
        listOf(bar("Tyčinka jogurt"), soup("Hrachová polévka", 3), protein("Protein banán", 10)),
        listOf(bar("Tyčinka čoko"), soup("Zeleninová polévka", 1), protein("Protein vanilka", 8)),
        listOf(bar("Tyčinka jogurt"), soup("Houbová polévka", 4), protein("Protein jahoda", 9)),
        listOf(yogurt("Jogurt čokoláda s pomerančem", 7), soup("Kuřecí polévka", 2), bar("Tyčinka kokos")),
        listOf(yogurt("Jogurt broskev", 6), bar("Tyčinka čoko"), protein("Protein banán", 10)),
        listOf(yogurt("Jogurt čokoláda s pomerančem", 7), soup("Hrachová polévka", 3), bar("Tyčinka kapučíno"))
    )

    val dayCount: Int get() = days.size

    private val palette = intArrayOf(
        0xFFEF5350.toInt(), // red
        0xFFEC407A.toInt(), // pink
        0xFFAB47BC.toInt(), // purple
        0xFF7986CB.toInt(), // indigo
        0xFF5C6BC0.toInt(), // deep purple/blue
        0xFF42A5F5.toInt(), // blue
        0xFF26C6DA.toInt(), // cyan
        0xFF26A69A.toInt(), // teal
        0xFF66BB6A.toInt(), // green
        0xFF9CCC65.toInt(), // lime
        0xFFFFA726.toInt(), // orange
        0xFFFF7043.toInt(), // deep orange
        0xFF8D6E63.toInt(), // brown
        0xFF78909C.toInt(), // blue grey
        0xFFFFCA28.toInt()  // amber
    )

    private val foodOrder: List<String> = days.flatten().map { it.name }.distinct()

    fun colorFor(foodName: String): Int {
        val index = foodOrder.indexOf(foodName)
        return palette[index.coerceAtLeast(0) % palette.size]
    }

    fun tintOf(color: Int): Int =
        Color.argb(0x2E, Color.red(color), Color.green(color), Color.blue(color))

    fun darkOf(color: Int): Int = Color.rgb(
        (Color.red(color) * 0.55f).toInt(),
        (Color.green(color) * 0.55f).toInt(),
        (Color.blue(color) * 0.55f).toInt()
    )
}
