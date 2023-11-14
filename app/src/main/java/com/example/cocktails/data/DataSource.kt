package com.example.cocktails.data

import com.example.cocktails.R
import com.example.cocktails.model.FakeCocktail

object DataSource {
    val cocktails = listOf<FakeCocktail>(
        FakeCocktail(
            name = R.string.cocktail_bloody_mary,
            description = R.string.cocktail_bloody_mary_description,
            image = R.drawable.bloody_mary_johann_trasch_m_7sqr2pdf8_unsplash_,
            preparationMinutes = 3,
            ingredients = listOf(
                "1 1/2 ounces vodka",
                "3 ounces tomato juice, store-bought or homemade",
                "1/2 ounce freshly squeezed lemon juice",
                "1 dash Worcestershire sauce",
                "1 dash hot sauce, or to taste",
                "1 pinch celery salt",
                "1 pinch freshly ground black pepper",
                "1 pinch freshly ground black pepper",
                "Lemon wedge, for garnish",
                "Celery stalk, or pickle spear, for garnish"
            ),
            preparation = listOf(
                "Gather the ingredients",
                "In a highball glass filled with ice cubes, pour the vodka, tomato juice, lemon juice, and Worcestershire sauce.",
                "Add the hot sauce, celery salt, black pepper, and horseradish (if using).",
                "Stir well, or try rolling the drink. Taste and adjust the seasonings to your liking.",
                "Garnish with a lemon wedge, celery stalk, and/or pickle.",
                "Serve and enjoy"
            ),
            stars = 5,
        ),
        FakeCocktail(
            name = R.string.cocktail_negroni,
            description = R.string.cocktail_negroni_description,
            image = R.drawable.negroni_vlady_nykulyak_rkbkss_z2_w_unsplash_,
            preparationMinutes = 5,
            ingredients = listOf(),
            preparation = listOf(),
            stars = 4,
        ),
        FakeCocktail(
            name = R.string.cocktail_sangria,
            description = R.string.cocktail_sangria_description,
            image = R.drawable.sangria_luis_gonzalez_sosa_7p90roow_iw_unsplash_,
            preparationMinutes = 7,
            ingredients = listOf(),
            preparation = listOf(),
            stars = 2,
        ),
        FakeCocktail(
            name = R.string.cocktail_cosmopolitan,
            description = R.string.cocktail_cosmopolitan_description,
            image = R.drawable.cosmopolitan_jenny_pace_ybz_o25m2zy_unsplash_,
            preparationMinutes = 3,
            ingredients = listOf(),
            preparation = listOf(),
            stars = 1,
        )
    )
}