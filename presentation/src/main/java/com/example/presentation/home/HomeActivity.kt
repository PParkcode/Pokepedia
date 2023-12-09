package com.example.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.PokemonCover
import com.example.presentation.common.PokemonCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



/**
 * viewModel을 생성하고 PokemonGrid를 생성
 */
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

/**
 * PokemonGrid
 *
 * 포켓몬 도감 번호 순으로 N * 2 형태로 보여준다.
 * */
@Composable
fun PokemonGrid(
    pokemons: List<PokemonCover>,
    getPokemons: suspend () -> Unit,
    getKoreanName: suspend (Int) -> String,
    navigateToDetail: (Int, String) -> Unit,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    // 현재 스크롤의 상태를 저장
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
        // 처음으로 보이는 아이템의 Index가 불러온 포켓몬 사이즈의 - 50이라면 다음 포켓몬 리스트를 불러온다.
        if (gridState.firstVisibleItemIndex == pokemons.size - 50) {
            coroutineScope.launch {
                getPokemons()
            }
        }
    }
}
