package com.example.cocktails.ui.cocktail.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cocktails.R
import com.example.cocktails.ui.AppViewModelProvider
import com.example.cocktails.ui.cocktail.entry.CocktailDetails
import com.example.cocktails.ui.navigation.NavigationDestination
import com.example.cocktails.ui.theme.CocktailsTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object CocktailDetailsDestination : NavigationDestination {
    override val route = "cocktail_details"
    override val titleRes = 0
    const val cocktailIdArg = "cocktailId"
    val routeWithArgs = "$route/{$cocktailIdArg}"
}

@Composable
fun CocktailDetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CocktailDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {},
        floatingActionButton = {},
        modifier = modifier
    ) { innerPadding ->
        CocktailDetailsBody(
            cocktail = uiState.value.cocktail,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteCocktail()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun CocktailDetailsBody(
    cocktail: CocktailDetails,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        Column {
            CocktailDetailsImage(
                imageUrl = cocktail.imageUrl,
                cocktailName = cocktail.name
            )
            Text(text = cocktail.description)
        }
    }
}

@Composable
private fun CocktailDetailsImage(
    imageUrl: String,
    cocktailName: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(enable = true)
                .build(),
            placeholder = painterResource(id = R.drawable.default_drink_illustration),
            error = painterResource(id = R.drawable.default_drink_illustration),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = cocktailName,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun CocktailDetailsBodyPreview() {
    CocktailDetailsBody(
        cocktail = CocktailDetails(
            name = "Negroni Sbagliato",
            description = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "dsadaaaaaaaaaaaaaaaaaaaaaaaaaa"
        ),
        onDelete = {},
    )
}

//@Preview
//@Composable
//private fun CocktailDetailsBodyDarkPreview() {
//    CocktailsTheme(darkTheme = true) {
//        CocktailDetailsBody(
//            cocktail = CocktailDetails(
//                name = "Negroni Sbagliato"
//            ),
//            onDelete = {},
//        )
//    }
//}
