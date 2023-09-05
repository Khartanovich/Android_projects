package com.example.jetpackccompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackccompose.ui.theme.JetpackCcomposeTheme
import com.example.jetpackccompose.entity.CharacterEntity
import com.example.jetpackccompose.entity.LocationEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    private val pageCharacters by lazy { CharacterPagingSource.pagerCharacters(viewModel) }
    private val pageLocations by lazy { LocationPagingSource.pagerLocations(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            JetpackCcomposeTheme {
                Surface {
                    Scaffold(bottomBar = { MyBottomAppBar(navController) }) {
                        Box(modifier = Modifier.padding(it)){
                            MyNavGraph(navController = navController)
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun ListCharacters(navController: NavHostController) {
        val characters: LazyPagingItems<CharacterEntity> =
            pageCharacters.flow.collectAsLazyPagingItems()
        LazyColumn {
            items(characters.itemCount) { character ->
                characters[character]?.let { it1 -> ItemCharacter(character = it1, navController) }
            }

            characters.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = characters.loadState.refresh as LoadState.Error
                        item {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                e.error.localizedMessage?.let {
                                    Text(text = it, color = Color.White)
                                }
                                Button(onClick = { retry() }) {
                                    Text(text = "Retry")
                                }
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = characters.loadState.append as LoadState.Error
                        item {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                e.error.localizedMessage?.let {
                                    Text(text = it, color = Color.White)
                                }
                                Button(onClick = { retry() }) {
                                    Text(text = "Retry")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ListLocations() {

        val locations: LazyPagingItems<LocationEntity> =
            pageLocations.flow.collectAsLazyPagingItems()
        LazyColumn {
            items(locations.itemCount) { location ->
                locations[location]?.let { ItemInfoLocation(infoLocation = it) }
            }
            locations.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = locations.loadState.refresh as LoadState.Error
                        item {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                e.error.localizedMessage?.let {
                                    Text(text = it, color = Color.White)
                                }
                                Button(onClick = { retry() }) {
                                    Text(text = "Retry")
                                }
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = locations.loadState.append as LoadState.Error
                        item {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                e.error.localizedMessage?.let {
                                    Text(text = it, color = Color.White)
                                }
                                Button(onClick = { retry() }) {
                                    Text(text = "Retry")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MyNavGraph(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "characters") {
            composable("characters") { ListCharacters(navController) }
            composable("location") { ListLocations() }
            composable(
                "one_character/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("id")
                    ?.let { OneCharacterScreen(id = it) }
            }
        }
    }
}

