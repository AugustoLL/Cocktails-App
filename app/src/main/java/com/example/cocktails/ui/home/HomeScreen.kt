package com.example.cocktails.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktails.CocktailsTopAppBar
import com.example.cocktails.model.Cocktail
import com.example.cocktails.ui.AppViewModelProvider
import com.example.cocktails.ui.home.cocktailcard.CocktailCard
import com.example.cocktails.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = 0
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToCocktailEntry: () -> Unit,
    navigateToCocktailUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.homeUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CocktailsTopAppBar(
                title = "Home",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToCocktailEntry,
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            cocktailList = uiState.value.cocktailsList,
            onCocktailClick = navigateToCocktailUpdate,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    cocktailList: List<Cocktail>,
    onCocktailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onCocktailExpand: (Int) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (cocktailList.isEmpty()) {
            Text(
                text = "Oops! No cocktails found in the inventory.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            CocktailList(
                cocktailList = cocktailList,
                onCocktailClick = { onCocktailClick(it.id) },
                modifier = Modifier.fillMaxSize().padding(12.dp)
            )
        }
    }
}

@Composable
private fun CocktailList(
    cocktailList: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(254.dp),
        modifier = modifier
    ) {
        items(items = cocktailList, key = { it.id }) {
            CocktailCard(
                cocktail = it,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CocktailListPreview() {
    CocktailList(
        cocktailList = listOf(
            Cocktail(
                id = 0,
                name = "Test Test Test Test Test",
                description = "\nThis is a test description \nThis is a test descriptionThis is a test description This is a test description This is a test description This is a test description This is a test descriptionThis is a test description",
                imageUrl = "",
                preparationTime = 4.0,
                stars = 5,
                ingredients = listOf(),
                preparation = listOf()
            ),
            Cocktail(
                id = 1,
                name = "Test",
                description = "This is a test description",
                imageUrl = "",
                preparationTime = 4.0,
                stars = 0,
                ingredients = listOf(),
                preparation = listOf()
            ),
            Cocktail(
                id = 2,
                name = "Test",
                description = "This is a test description",
                imageUrl = "",
                preparationTime = 4.0,
                stars = 3,
                ingredients = listOf(),
                preparation = listOf()
            ),
            Cocktail(
                id = 3,
                name = "Test",
                description = "This is a test description",
                imageUrl = "",
                preparationTime = 4.0,
                stars = 2,
                ingredients = listOf(),
                preparation = listOf()
            )
        ),
        onCocktailClick = {},
    )
}