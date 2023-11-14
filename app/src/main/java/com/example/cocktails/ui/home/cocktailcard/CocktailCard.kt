package com.example.cocktails.ui.home.cocktailcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktails.model.Cocktail
import com.example.cocktails.ui.AppViewModelProvider
import com.example.cocktails.ui.home.HomeViewModel
import kotlinx.coroutines.launch

private const val TAG = "COCKTAIL_CARD"

@Composable
fun CocktailCard(
    cocktail: Cocktail,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CocktailCardImage(
                cocktailImg = cocktail.imageUrl,
                onDeleteCocktail = {
                    coroutineScope.launch {
                        viewModel.deleteCocktail(cocktail)
                    }
                },
                onEditCocktail = {},
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )
            CocktailCardInfo(
                cocktailName = cocktail.name,
                cocktailDescription = cocktail.description,
                cocktailStars = cocktail.stars,
                cocktailPreparationTime = cocktail.preparationTime,
                cocktailIngredients = cocktail.ingredients,
                cocktailPreparation = cocktail.preparation,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CocktailCardPreview() {
    CocktailCard(
        cocktail = Cocktail(
            id = 0,
            name = "Test Test Test Test Test",
            description = "\nThis is a test description \nThis is a test descriptionThis is a test description This is a test description This is a test description This is a test description This is a test descriptionThis is a test description",
            imageUrl = "",
            preparationTime = 4.0,
            stars = 5,
            ingredients = listOf(),
            preparation = listOf()
        )
    )
}