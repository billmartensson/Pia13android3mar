package se.magictechnology.pia13android3mar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DoneScreen(shopviewmodel : ShopViewModel) {

    val shoplist by shopviewmodel.doneshopping.collectAsState()

    LaunchedEffect(true) {
        shopviewmodel.loadDoneShopping()
        shopviewmodel.setNavbarTitle("DONE")
    }

    Column {
        Text("DONE DONE DONE")

        LazyColumn {
            items(shoplist) { shopitem ->
                Row(modifier = Modifier
                    .height(50.dp)
                ) {
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