package com.example.presentation.detail


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonStats
import com.example.domain.model.Stat
import com.example.presentation.common.ImageLoader
import com.example.presentation.detail.ui.theme.ATKColor
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
    pokemonFlavorText: PokemonFlavorText,
    modifier: Modifier = Modifier.padding(start = 15.dp)
) {

    LazyColumn {
        item {
            ImageArea(id = pokemon.id)
        }
        item {
            NameArea(name = pokemon.name, modifier)
        }
        item {
            PokemonFlavorTextArea(flavorText = pokemonFlavorText.flavorText, modifier)
        }
        item {
            TypeArea(types = pokemonTypes)
        }
        item {
            PokemonStatsArea(pokemonStats = pokemonStats, modifier)
        }
        item {
            PhysicalArea(weight = pokemonStats.weight, height = pokemonStats.height)
        }
    }


}

@Composable
fun ImageArea(id: Int, modifier: Modifier = Modifier) {
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
fun NameArea(name: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(
            text = name, fontWeight = FontWeight.Bold, fontSize = 27.sp
        )
    }

}

@Composable
fun PokemonFlavorTextArea(flavorText: String, modifier: Modifier = Modifier) {
    val text = flavorText.replace("\n", " ")
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, end = 15.dp)
            .then(modifier)
    ) {
        Text(
            text = text, fontSize = 13.sp, modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun TypeArea(types: List<String>, modifier: Modifier = Modifier) {
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
                text = types.toString(),
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
fun PhysicalArea(weight: String , height: String , modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp)) {
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
fun PokemonStatsArea(pokemonStats: PokemonStats, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "기본 스탯", fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
            modifier = modifier
        )

        PokemonStat(pokemonStats.hp, modifier.padding(bottom = 5.dp))
        PokemonStat(pokemonStats.atk, modifier.padding(bottom = 5.dp))
        PokemonStat(pokemonStats.def, modifier.padding(bottom = 5.dp))
        PokemonStat(pokemonStats.specialAtk, modifier.padding(bottom = 5.dp))
        PokemonStat(pokemonStats.specialDef, modifier.padding(bottom = 5.dp))
        PokemonStat(pokemonStats.speed, modifier.padding(bottom = 5.dp))


    }
}

@Composable
fun PokemonStat(stat: Stat, modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stat.name,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            fontSize = 11.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .weight(1f)
                .then(modifier)

        )
        CustomBar(stat = stat, maxStat = 300, Modifier.weight(4f).padding(start = 7.dp))
    }
}

@Composable
fun CustomBar(stat: Stat, maxStat: Int, modifier: Modifier = Modifier) {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(stat.name) {
        progress = stat.value.toFloat() / maxStat
    }
    val size by animateFloatAsState(
        targetValue = progress, animationSpec = tween(
            durationMillis = 700,
            delayMillis = 700,
            easing = EaseInOutCubic,
        ), label = ""
    )
    //ProgressBar
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(19.dp)
            .then(modifier)
    ) {
        //바의 바탕
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
                .then(modifier)
        )
        //진행도
        Box(
            modifier = Modifier
                .fillMaxWidth(size)
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .background(Color(stat.color))
                .animateContentSize()
                .then(modifier)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .then(modifier)
        ) {
            Row(
                modifier = Modifier
                    .widthIn(min = 30.dp)
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${stat.value}/$maxStat",
                    fontSize = 11.sp,
                )
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PokepediaTheme {
        PokemonFlavorTextArea(flavorText = "태어났을 때부터 등에 이상한 씨앗이 심어져 있으며 몸과 함께 자란다고 한다.")
    }
}

@Preview(showBackground = true) 
@Composable
fun Preview3() {
    PokepediaTheme {
        PokemonStat(stat = Stat("SP-ATK",120,0xFF000011))
    }
}