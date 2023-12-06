package com.example.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.PokemonCover
import com.example.presentation.common.CommonViewModel
import com.example.presentation.common.PokemonCard
import com.example.presentation.home.ui.theme.PokepediaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokepediaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun SetHomeActivity(
    navigateToDetail: (Int, String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val pokemons by viewModel.pokemons.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            viewModel.getPokemons()
        }
    }
    PokemonGrid(
        //viewModel.pokemons,
        pokemons,
        { viewModel.getPokemons() },
        { viewModel.getKoreanName(it) },
        { id, name -> navigateToDetail(id, name) },
        coroutineScope
    )
}

@Composable
fun PokemonGrid(
    pokemons: List<PokemonCover>,
    getPokemons: suspend () -> Unit,
    getKoreanName: suspend (Int) -> String,
    navigateToDetail: (Int, String) -> Unit,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.SpaceEvenly,
        state = gridState,
        modifier = Modifier.padding(7.dp)
    ) {
        items(pokemons) { pokemon ->

            PokemonCard(
                item = pokemon,
                { getKoreanName(it) },
                { id, name -> navigateToDetail(id, name) })
        }

        if (gridState.firstVisibleItemIndex == pokemons.size - 50) {
            coroutineScope.launch {
                getPokemons()
            }
        }
    }
}
