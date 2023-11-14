package com.example.cocktails.ui.home.cocktailcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cocktails.R

@Composable
fun CocktailCardImage(
    cocktailImg: String,
    onDeleteCocktail: () -> Unit,
    onEditCocktail: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(cocktailImg)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.default_drink_illustration),
            error = painterResource(id = R.drawable.default_drink_illustration),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(modifier = Modifier.padding(end = 5.dp)) {
            CocktailCardImageIcon(
                onClick = { expanded = !expanded },
                modifier = Modifier.padding(8.dp)
            )
            CardDropDownMenu(
                expanded = expanded,
                onDismiss = { expanded = false },
                onDeleteCocktail = onDeleteCocktail,
                onEditCocktail = onEditCocktail
            )
        }
    }
}

@Composable
private fun CocktailCardImageIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    imageVector: ImageVector = Icons.Outlined.MoreVert,
    imageSize: Dp = 35.dp,
    description: String = ""
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
private fun CardDropDownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onDeleteCocktail: () -> Unit,
    onEditCocktail: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    CocktailCardImageIcon(
                        imageVector = Icons.Outlined.Delete,
                        imageSize = 20.dp
                    )
                    Text(text = "Delete Cocktail")
                }
            },
            onClick = onDeleteCocktail
        )
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    CocktailCardImageIcon(
                        imageVector = Icons.Outlined.Edit,
                        imageSize = 20.dp
                    )
                    Text(text = "Edit Cocktail")
                }
            },
            onClick = onEditCocktail
        )
    }
}

@Preview
@Composable
private fun CocktailCardImagePreview() {
    CocktailCardImage(
        cocktailImg = "",
        onDeleteCocktail = {},
        onEditCocktail = {}
    )
}