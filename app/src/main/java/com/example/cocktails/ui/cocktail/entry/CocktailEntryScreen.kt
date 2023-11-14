package com.example.cocktails.ui.cocktail.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktails.CocktailsTopAppBar
import com.example.cocktails.ui.AppViewModelProvider
import com.example.cocktails.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object CocktailEntryDestination : NavigationDestination {
    override val route = "cocktail_entry"
    override val titleRes = 0
}

private const val TAG = "COCKTAIL_ENTRY_SCREEN"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: CocktailEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CocktailsTopAppBar(
                title = "Add Cocktail",
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        },
    ) { innerPadding ->
        CocktailEntryBody(
            uiState = viewModel.uiState,
            onCocktailValueChange = viewModel::updateUiState,
            validateList = viewModel::listElementsAreValid,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveCocktail()
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
private fun CocktailEntryBody(
    uiState: CocktailUiState,
    onCocktailValueChange: (CocktailDetails) -> Unit,
    validateList: (List<String>) -> Boolean,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CocktailInputForm(
            cocktailDetails = uiState.cocktailDetails,
            onValueChange = onCocktailValueChange,
            validateList = {
                validateList(it)
            },
            modifier = Modifier.fillMaxSize()
        )
        Button(
            onClick = { onSaveClick() },
            enabled = uiState.isEntryValid,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun CocktailInputForm(
    cocktailDetails: CocktailDetails,
    onValueChange: (CocktailDetails) -> Unit,
    validateList: (List<String>) -> Boolean,
    modifier: Modifier = Modifier
) {
    val ingredientsFieldCount = remember { mutableIntStateOf(-1) }
    val preparationStepsFieldCount = remember { mutableIntStateOf(-1) }
    val ingredientList = remember { mutableStateListOf<String>() }
    val preparationSteps = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CocktailInput(
            value = cocktailDetails.name,
            onValueChange = { onValueChange(cocktailDetails.copy(name = it)) },
            label = "Name of the Cocktail"
        )
        CocktailInput(
            value = cocktailDetails.description,
            onValueChange = { onValueChange(cocktailDetails.copy(description = it)) },
            label = "Description",
            singleLine = false
        )
        CocktailInput(
            value = cocktailDetails.imageUrl,
            onValueChange = { onValueChange(cocktailDetails.copy(imageUrl = it)) },
            label = "Image Url"
        )
        CocktailInput(
            value = cocktailDetails.preparationTime,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = { onValueChange(cocktailDetails.copy(preparationTime = it)) },
            label = "Preparation Time"
        )
        /**
         * Dynamic Ingredient Inputs
         * When the user clicks the Add Ingredient Button, a new
         * OutlinedTextField is created, each field has an icon button companion
         * that the user can click to delete said field
         */
        Button(
            onClick = {
                ingredientList.add("")
                ingredientsFieldCount.intValue++
            },
            enabled = validateList(ingredientList)
        ) {
            Text(text = "Add Ingredient")
        }
        DynamicInputList(
            name = "Ingredient",
            listCount = ingredientsFieldCount.intValue,
            list = ingredientList,
            onValueChange = { index, value ->
                ingredientList[index] = value
                onValueChange(cocktailDetails.copy(ingredients = ingredientList))
            },
            onElementRemove = { index ->
                ingredientList.remove(ingredientList[index])
                onValueChange(cocktailDetails.copy(ingredients = ingredientList))
                ingredientsFieldCount.intValue--
            }
        )
        /**
         * Dynamic Preparation Steps Inputs
         * When the user clicks the Add Instruction Button, a new
         * OutlinedTextField is created, each field has an icon button companion
         * that the user can click to delete said field
         */
        Button(
            onClick = {
                preparationSteps.add("")
                preparationStepsFieldCount.intValue++
            },
            enabled = validateList(preparationSteps)
        ) {
            Text(text = "Add Instruction")
        }
        DynamicInputList(
            name = "Preparation Step",
            listCount = preparationStepsFieldCount.intValue,
            list = preparationSteps,
            onValueChange = { index, value ->
                preparationSteps[index] = value
                onValueChange(cocktailDetails.copy(preparation = preparationSteps))
            },
            onElementRemove = { index ->
                preparationSteps.remove(preparationSteps[index])
                onValueChange(cocktailDetails.copy(preparation = preparationSteps))
                preparationStepsFieldCount.intValue--
            }
        )
    }
}

@Composable
private fun DynamicInputList(
    name: String,
    listCount: Int,
    list: MutableList<String>,
    onValueChange: (index: Int, value: String) -> Unit,
    onElementRemove: (index: Int) -> Unit,
    enabled: Boolean = true
) {
    if (listCount >= 0) {
        for (element in 0..listCount) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = list[element],
                    onValueChange = { onValueChange(element, it) },
                    label = { Text(text = "$name #${element}") },
                    modifier = Modifier.padding(bottom = 16.dp).widthIn(max = 220.dp),
                    enabled = enabled,
                    singleLine = true
                )
                IconButton(onClick = { onElementRemove(element) }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Delete $name",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun CocktailInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
        imeAction = ImeAction.Next
    ),
    enabled: Boolean = true,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions,
        label = { Text(text = label) },
        modifier = modifier.padding(bottom = 16.dp),
        enabled = enabled,
        singleLine = singleLine
    )
}

@Preview
@Composable
private fun CocktailEntryBodyPreview() {
    CocktailEntryBody(
        uiState = CocktailUiState(),
        onCocktailValueChange = {},
        onSaveClick = {},
        validateList = { true }
    )
}