package com.emreozcelik.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emreozcelik.littlelemon.ui.theme.LittleLemonColor
import com.emreozcelik.littlelemon.ui.theme.Typography
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.rememberAsyncImagePainter


@Composable
fun Home(navController: NavHostController, database: AppDatabase) {

    val menuItemsDatabase by database.menuItemDao().getAll().observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.width(50.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "little lemon logo",
                Modifier
                    .width(200.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { navController.navigate(Profile.route)}) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }

        }
        Hero(menuItemsDatabase)
    }
}



@Composable
fun Hero(menuItemsDatabase: List<MenuItemRoom>) {

    var menuItems = menuItemsDatabase
    var selectedCategory by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column() {

                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 24.sp,
                    color = LittleLemonColor.cloud
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.body1,
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .padding(bottom = 28.dp, end = 20.dp)
                        .fillMaxWidth(0.6f)
                )

            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "hero image",
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

        }

        var searchPhrase by remember {
            mutableStateOf("")
        }

        TextField(
            label = { Text(text = "Enter search phrase") },
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LittleLemonColor.cloud),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.Search, contentDescription = "search", tint = Color.Black
                )
            },
        )

        if (searchPhrase.isNotEmpty()) {
            menuItems = menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }


    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "ORDER FOR DELIVERY !",
            modifier = Modifier.padding(top = 30.dp),
            style = Typography.h2
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 10.dp)
        ) {
            Button(
                onClick = {
                    selectedCategory = "starters"
                },
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Starters",
                    style = Typography.button
                )
            }

            Button(
                onClick = {
                    selectedCategory = "mains"
                },
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Mains",
                    style = Typography.button
                )
            }

            Button(
                onClick = {
                    selectedCategory = "desserts"
                },
                modifier = Modifier.height(40.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Desserts",
                    style = Typography.button
                )
            }

            Button(
                onClick = {
                    selectedCategory = "drinks"
                },
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonColor.cloud
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Drinks",
                    style = Typography.button
                )
            }
        }
    }

    if (selectedCategory.isNotEmpty()) {
        menuItems = menuItems.filter { it.category.contains(selectedCategory) }
    }

    MenuItems(menuItemsList = menuItems)

}

@Composable
fun MenuItems(menuItemsList: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(items = menuItemsList, itemContent = { menuItem -> MenuItem(menuItem) })
    }
}

@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Divider(
        thickness = 5.dp,
        color = Color(0xFFDDDDDD),
        modifier = Modifier.padding(vertical = 15.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = menuItem.title,
            style = Typography.h2
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = menuItem.description,
                    fontSize = 15.sp,
                    color = LittleLemonColor.charcoal)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "$${menuItem.price}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = LittleLemonColor.charcoal
                )
            }
            Image(
                painter = rememberAsyncImagePainter(menuItem.image),
                modifier = Modifier
                    .padding(start = 15.dp)
                    .size(100.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "image",
            )
        }
    }
}

