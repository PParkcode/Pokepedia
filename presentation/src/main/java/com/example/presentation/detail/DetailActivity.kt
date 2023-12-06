package com.example.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonStats
import com.example.presentation.common.ImageLoader
import com.example.presentation.detail.ui.theme.PokepediaTheme
import kotlinx.coroutines.launch


@Composable
fun SetDetailActivity(id: Int, name: String, viewModel: DetailViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val pokemonTypes by viewModel.pokemonTypes.collectAsState()
    val pokemonStats by viewModel.pokemonStats.collectAsState()
    val pokemonFlavorText by viewModel.pokemonFlavorText.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()

    viewModel.setPokemonNameId(id, name)
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            viewModel.getPokemonStats(id)
            viewModel.getPokemonTypes(id)
            viewModel.getPokemonFlavorText(id)

        }
    }

    DetailActivity(pokemon, pokemonStats, pokemonTypes, pokemonFlavorText)

}

@Composable
fun DetailActivity(
    pokemon: PokemonCover,
    pokemonStats: PokemonStats,
    pokemonTypes: List<String>,
    pokemonFlavorText: PokemonFlavorText
) {

    LazyColumn {
        item {
            ImageArea(id = pokemon.id)
        }
        item {
            NameArea(name = pokemon.name)
        }
        item{
            TypeArea(types = pokemonTypes)
        }
        item {
            PhysicalArea(weight = pokemonStats.weight, height = pokemonStats.height)
        }
    }


}

@Composable
fun ImageArea(id: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
            .background(Color.Gray)

    ) {
        Column {
            Text(
                text = "NO.$id",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 7.dp, end = 12.dp)
                    .align(alignment = Alignment.End)
            )
            ImageLoader(id = id)
        }

    }
}

@Composable
fun NameArea(name: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
    }

}

@Composable
fun TypeArea(types: List<String>) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .sizeIn(minWidth = 50.dp, minHeight = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
        ) {
            Text(
                text = "type",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(7.dp),

            )
        }
    }
}

@Composable
fun PhysicalArea(weight:String ="112.3", height:String = "11.0") {
    Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier.fillMaxWidth()) {

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp)){
            Text(
                text = "$weight KG",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Weight",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

        }
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp)) {
            Text(
                text = "$height M",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Height",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

}
@Composable
fun PokemonStatsArea(pokemonStats: PokemonStats) {

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    PokepediaTheme {
        PhysicalArea()
    }
}