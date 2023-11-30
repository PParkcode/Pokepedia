package com.example.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.PokemonCover
import com.example.presentation.common.PokemonCard
import com.example.presentation.home.ui.theme.PokepediaTheme
import kotlinx.coroutines.CoroutineScope
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
fun SetHomeActivity(viewModel: HomeViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            viewModel.getPokemons()
        }
    }
    PokemonGrid(viewModel.pokemons, { viewModel.getPokemons() }, coroutineScope)
}

@Composable
fun PokemonGrid(
    pokemons: List<PokemonCover>,
    getPokemons: suspend () -> Unit,
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
            PokemonCard(item = pokemon)
        }

        /*

        if (gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == pokemons.size - 1) {

            // 리스트의 끝에 도달하면 다음 페이지 로드
            coroutineScope.launch {
                Log.d("확인","코루틴 스코프 안에 진행")
                getPokemons()
            }
        }

         */

        if (gridState.firstVisibleItemIndex == pokemons.size - 10) {
            coroutineScope.launch {
                getPokemons()
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokepediaTheme {

    }
}