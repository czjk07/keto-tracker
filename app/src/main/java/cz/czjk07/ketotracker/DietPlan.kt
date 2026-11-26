package cz.czjk07.ketotracker

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
}
