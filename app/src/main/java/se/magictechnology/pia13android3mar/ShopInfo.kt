package se.magictechnology.pia13android3mar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ShopInfo(shopviewmodel : ShopViewModel) {

    LaunchedEffect(true) {
        shopviewmodel.setNavbarTitle("INFO")
    }

    Column {
        Text("INFO OM APPEN")
    }
}