package se.magictechnology.pia13android3mar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import se.magictechnology.pia13android3mar.ui.theme.Pia13android3marTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        var shopviewmodel = ShopViewModel()
        shopviewmodel.appdatabase = db

        setContent {
            mainscaffold(shopviewmodel)
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Shopping",
        icon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Default.ShoppingCart,
        route = "shopping"
    ),
    NavigationItem(
        title = "Done",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Default.Person,
        route = "done"
    ),
    NavigationItem(
        title = "About",
        icon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Default.ShoppingCart,
        route = "about"
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainscaffold(shopViewModel: ShopViewModel) {

    var selectedNavigationIndex by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    val navtitle by shopViewModel.navbartitle.collectAsState()

    Pia13android3marTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(navtitle)
                    },
                    navigationIcon = {
                        /*
                        if(navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                         */
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            bottomBar = { NavigationBar(
                containerColor = Color.LightGray
            ) {

                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex == index,
                        onClick = {
                            selectedNavigationIndex = index
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = if(index == selectedNavigationIndex) item.selectedIcon else item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                item.title,
                                color = if(index == selectedNavigationIndex)
                                    Color.Magenta
                                else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            //selectedIconColor = MaterialTheme.colorScheme.surface,
                            selectedIconColor = Color.Magenta,
                            //indicatorColor = MaterialTheme.colorScheme.primary
                            indicatorColor = Color.Transparent
                        )

                    )
                }

            } }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = navigationItems.first().route,
                Modifier.padding(innerPadding)) {
                composable(navigationItems[0].route) {
                    ShoppingNav(shopViewModel)
                }
                composable(navigationItems[1].route) {
                    DoneScreen(shopViewModel)
                }
                composable(navigationItems[2].route) {
                    AboutScreen()
                }
            }

        }
    }
}


@Composable
fun ShopBottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Bottom app bar",
        )
    }
}


@Preview
@Composable
fun mainpreview() {
    mainscaffold(viewModel())
}
