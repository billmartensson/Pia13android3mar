package se.magictechnology.pia13android3mar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ShoppingScreen(shopviewmodel : ShopViewModel) {

    val shoplist by shopviewmodel.allshopping.collectAsState()

    var addshoptitle by remember { mutableStateOf("") }
    var addshopamount by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        shopviewmodel.loadshopping()
    }

    Column {
        Text("SHOP")

        TextField(value = addshoptitle, onValueChange = { addshoptitle = it })
        TextField(value = addshopamount, onValueChange = { addshopamount = it })

        Button(onClick = {
            shopviewmodel.addshop(addshoptitle, addshopamount.toInt())
        }) {
            Text("ADD SHOP")
        }


        LazyColumn {
            items(shoplist) { shopitem ->
                Row(modifier = Modifier.clickable {
                    //shopviewmodel.deleteshop(shopitem)
                }) {
                    Text(shopitem.shopname)
                    Text("${shopitem.amount}")

                    if(shopitem.isbought) {
                        Text("KLAR")
                    } else {
                        Text("Ej klar")
                    }
                }
            }
        }
    }
}