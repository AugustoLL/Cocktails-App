package com.example.cocktails.ui.home.cocktailcard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cocktails.R

@Composable
fun CocktailCardInfo(
    cocktailName: String,
    cocktailDescription: String,
    cocktailStars: Int,
    cocktailPreparationTime: Double,
    cocktailPreparation: List<String>,
    cocktailIngredients: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(8f)) {
                Text(
                    text = cocktailName,
                    style = MaterialTheme.typography.displaySmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    if (cocktailStars > 0) {
                        for (star in 1..cocktailStars) {
                            CocktailCardInfoIcon(imageVector = Icons.Filled.Star)
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    }
                    if (cocktailPreparationTime > 0) {
                        CocktailCardInfoIcon(
                            imageVector = Icons.Filled.DateRange,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Text(
                            text = "$cocktailPreparationTime mins.",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            CocktailCardInfoButton(
                expanded = expanded,
                onClick = { expanded = !expanded },
                modifier = Modifier.weight(1f)
            )
        }
        if (!expanded) Text(
            text = cocktailDescription,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(top = 20.dp)
        )
        else CocktailCardInfoText(
            ingredients = cocktailIngredients,
            preparations = cocktailPreparation,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Composable
private fun CocktailCardInfoText(
    ingredients: List<String>,
    preparations: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 5.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        for (ingredient in ingredients)
            Row {
                Text(
                    text = "• $ingredient",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = stringResource(id = R.string.preparation),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 5.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        for (step in preparations)
            Row {
                Text(
                    text = "${preparations.indexOf(step)} - $step",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
    }
}

@Composable
private fun CocktailCardInfoIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String? = null
){
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier.size(15.dp)
    )
}

@Composable
private fun CocktailCardInfoButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(!expanded) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}