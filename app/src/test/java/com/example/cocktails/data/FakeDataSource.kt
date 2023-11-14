package com.example.cocktails.data

import com.example.cocktails.model.Cocktail

object FakeDataSource {
    val fakeCocktail1 =
        Cocktail(
            id = 0,
            name = "Bloody Mary",
            description = "The bloody mary is an icon in the cocktail world. Created in the 1920s, this vodka-spiked tomato cocktail developed into a favorite brunch drink and became well-known as a hangover remedy.",
            imageUrl = "",
            preparationTime = 3.0,
            stars = 3,
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
            )
        )

    val fakeCocktail2 =
        Cocktail(
            id = 1,
            name = "Negroni",
            description = "A sophisticated aperitif featuring equal parts of gin, Campari, and sweet vermouth and so easy that anyone can mix it up.",
            imageUrl = "https://www.thespruceeats.com/thmb/9MxcWgP9_tkMRQcK24SDJLstYWI=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/negroni-cocktail-recipe-759327-hero-01-3e157f628ade43f1969793447c5ff51d.jpg",
            preparationTime = 3.0,
            stars = 4,
            ingredients = listOf(
                "1 ounce of gin",
                "1 ounce of sweet vermouth",
                "1 ounce of Campari",
                "Orange twist or slice, for garnish"
            ),
            preparation = listOf(
                "Gather the ingredients",
                "In an old-fashioned glass filled with ice cubes, pour the gin, vermouth, and Campari.",
                "Stir well.",
                "Gently squeeze an orange twist or slice over the glass, then add it as a garnish.",
                "Serve and enjoy.",
            )
        )

    val fakeCocktail3 =
        Cocktail(
            id = 2,
            name = "Old-Fashioned",
            description = "This classic drink has been served since the mid-1800s and is as popular today as it was back then.",
            imageUrl = "https://www.thespruceeats.com/thmb/L2f6dIuBg-gGSHkrd0IiTf0x2OU=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/old-fashioned-cocktail-recipe-and-history-759328-hero-01-c9002b3aa6d24a3781befa19dc69eb0e.jpg",
            preparationTime = 3.5,
            stars = 4,
            ingredients = listOf(
                "1 sugar cube, or 1/2 teaspoon sugar",
                "3 dashes bitters ",
                "2 ounces of bourbon or rye whiskey",
                "Orange peel for garnish",
                "Cherry for garnish",
            ),
            preparation = listOf(
                "Gather the ingredients",
                "Place a sugar cube or sugar in an old-fashioned glass and saturate it with bitters. Muddle or stir to mix.",
                "Add the whiskey, fill the glass with ice, and stir well.",
                "Express the orange peel over the drink before dropping it into the glass: Twist up the peel and give it a good squeeze (directed toward the glass, not your eyes) and bits of citrus oil will spray into the drink. Add a cherry if you like.",
                "Serve and enjoy.",
            )
        )

    val cocktailList = listOf(fakeCocktail1, fakeCocktail2, fakeCocktail3)
}