# keto-tracker

Jednoduchý Android home-screen widget pro 15denní dietní plán (3 jídla denně).

## Funkce

- Widget zobrazuje aktuální den a 3 jídla dne: **jídlo – číslo krabice – množství tekutiny**.
- Každé jídlo má vlastní barvu.
- Klepnutím na řádek označíš jídlo jako snědené (přeškrtnuté + zesvětlené); dalším klepnutím značku zrušíš.
- Jakmile jsou všechna 3 jídla dne označená, widget se automaticky přepne na další den (v pravém horním rohu je vidět „Den X/15"). Po 15. dni se zobrazí „Hotovo ✓".
- Množství tekutiny: polévka 400 ml, proteinový koktejl 200 ml, jogurtový krém 200 ml, proteinová tyčinka bez tekutiny (zobrazí se „–"). Čísla krabic odpovídají inventáři (tyčinky mají krabici 0).
- Aplikace obsahuje jednoduchou obrazovku s tlačítkem pro reset diety na 1. den.

## Sestavení

Projekt používá Gradle (AGP 8.7.3, Kotlin 2.0.21, Gradle 8.10.2), minSdk 26, žádné externí závislosti.

Pokud nemáš vygenerovaný Gradle wrapper, vytvoř ho jednou příkazem:

```bash
gradle wrapper --gradle-version 8.10.2
```

Poté:

```bash
./gradlew assembleDebug
```

nebo projekt otevři v Android Studiu a nainstaluj na zařízení. Widget přidej na plochu jako „Keto denní plán".

## Struktura

- `app/src/main/java/cz/czjk07/ketotracker/DietPlan.kt` – 15denní itinerář, čísla krabic, množství tekutin, barvy potravin
- `.../DietWidgetState.kt` – stav widgetu (aktuální den, označená jídla) v SharedPreferences
- `.../DietWidgetProvider.kt` – AppWidgetProvider, RemoteViews, klepnutí = přepnutí stavu jídla, automatický posun na další den
- `.../MainActivity.kt` – obrazovka aplikace s tlačítkem resetu
- `app/src/main/res/layout/widget_diet.xml` – layout widgetu
- `app/src/main/res/xml/diet_widget_info.xml` – konfigurace widgetu
