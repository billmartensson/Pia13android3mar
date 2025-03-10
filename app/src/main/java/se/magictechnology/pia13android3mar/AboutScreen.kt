package se.magictechnology.pia13android3mar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("About Shopping App")
        Text("To use the app add thing to buy")
        Text("The app is very fun")
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}