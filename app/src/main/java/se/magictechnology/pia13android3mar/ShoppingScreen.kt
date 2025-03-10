package se.magictechnology.pia13android3mar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShoppingScreen(shopviewmodel : ShopViewModel, goInfo : () -> Unit) {

    val shoplist by shopviewmodel.allshopping.collectAsState()

    var addshoptitle by remember { mutableStateOf("") }
    var addshopamount by remember { mutableStateOf("") }

    var shopfilter by remember { mutableStateOf("all") }

    LaunchedEffect(true) {

        shopviewmodel.setNavbarTitle("SHOPPING")
    }

    LaunchedEffect(shopfilter) {
        if(shopfilter == "all") {
            shopviewmodel.loadshopping()
        }
        if(shopfilter == "bought") {
            shopviewmodel.loadBoughtShopping()
        }
        if(shopfilter == "notbought") {
            shopviewmodel.loadNotBoughtShopping()
        }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("SHOPPING LIST")
        Text(shopfilter)
        Button(onClick = {
            goInfo()
        }) {
            Text("INFO")
        }

        TextField(value = addshoptitle, onValueChange = { addshoptitle = it })
        TextField(value = addshopamount, onValueChange = { addshopamount = it })

        Button(onClick = {
            shopviewmodel.addshop(addshoptitle, addshopamount.toInt())
        }) {
            Text("ADD SHOP")
        }


        Row {
            Button(onClick = {
                shopfilter = "all"
            }) {
                Text("ALLA")
            }
            Button(onClick = {
                shopfilter = "bought"
            }) {
                Text("KLARA")
            }
            Button(onClick = {
                shopfilter = "notbought"
            }) {
                Text("EJ KLARA")
            }
        }


        LazyColumn {
            items(shoplist) { shopitem ->
                Row(modifier = Modifier
                    .height(50.dp)
                    .clickable {
                    //shopviewmodel.deleteshop(shopitem)
                    shopviewmodel.changeBought(shopitem)
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


@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {

    var shopviewmodel = ShopViewModel()
    shopviewmodel.isPreview = true
    ShoppingScreen(shopviewmodel, goInfo = {})
}