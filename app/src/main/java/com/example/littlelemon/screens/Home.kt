package com.example.littlelemon.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.R
import com.example.littlelemon.database.AppDatabase
import com.example.littlelemon.database.MenuItemRoom
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.Gray
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Yellow
import com.example.littlelemon.ui.theme.markaziFontFamily
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Home(modifier: Modifier, database: AppDatabase, onNavigation: () -> Unit) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var itemBreakDown by remember { mutableStateOf("") }
    var searchPhrase by remember { mutableStateOf("") }

    val menuItems = databaseMenuItems
        .sortedBy { it.title }
        .filter {
            it.title.contains(
                other = searchPhrase,
                ignoreCase = true
            )
        }.filter {
            it.category.contains(
                other = itemBreakDown,
                ignoreCase = true
            )
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .height(126.dp)
                    .width(240.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .size(56.dp)
                    .clickable {
                        onNavigation()
                    },
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Logo"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Green),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    fontFamily = markaziFontFamily,
                    color = Yellow,
                    fontSize = 52.sp,
                    text = "Little Lemon"
                )

                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            modifier = Modifier,
                            fontFamily = markaziFontFamily,
                            color = Gray,
                            fontSize = 36.sp,
                            text = "Chicago"
                        )

                        Text(
                            modifier = Modifier,
                            fontFamily = markaziFontFamily,
                            color = Gray,
                            fontSize = 22.sp,
                            text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist."
                        )
                    }

                    Image(
                        modifier = Modifier
                            .size(145.dp),
                        painter = painterResource(id = R.drawable.hero),
                        contentDescription = "Hero"
                    )
                }

                TextField(
                    value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    label = {
                        Text(
                            text = "Enter search phrase"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp)),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedContainerColor = Gray,
                        focusedContainerColor = Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.W800,
            text = "ORDER TO DELIVERY!"
        )

        BreakDownMenu(
            list = databaseMenuItems.map { it.category }.distinct(),
            itemBreakDown = itemBreakDown
        ) {
            itemBreakDown = it
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            color = Color.Gray,
            thickness = 1.dp
        )


        MenuItems(menuItems)
    }
}

@Composable
fun BreakDownMenu(list: List<String>, itemBreakDown: String, onClick: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
            .horizontalScroll(scrollState)
    ) {
        list.forEach { item ->
            Box(
                Modifier
                    .padding(8.dp)
                    .background(
                        if (itemBreakDown != item) {
                            Gray
                        } else {
                            Yellow
                        }, shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .clickable {
                        if (item == itemBreakDown) {
                            onClick("")
                        } else {
                            onClick(item)
                        }
                    }
            ) {
                Text(
                    modifier = Modifier,
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    text = item
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(list: List<MenuItemRoom>) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        list.forEach { item ->
            Column {
                Text(
                    modifier = Modifier,
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    text = item.title
                )

                Row {
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            modifier = Modifier,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W300,
                            text = item.description
                        )
                        Text(
                            modifier = Modifier,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            text = doubleToCurrencyString(item.price)
                        )
                    }

                    GlideImage(
                        modifier = Modifier.size(82.dp),
                        model = item.image,
                        contentDescription = item.title
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
        }
    }
}

fun doubleToCurrencyString(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(amount)
}
