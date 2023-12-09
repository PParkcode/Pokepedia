package com.example.presentation.detail


import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonStats
import com.example.domain.model.PokemonType
import com.example.domain.model.Stat
import com.example.presentation.detail.ui.theme.PokepediaTheme
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette
import kotlinx.coroutines.launch

/**
 * SetDetailActivity
 * ViewModel을 생성하고
 * 네트워크 작업을 통해 포켓몬 정보를 불러와 ViewModel에 데이터를 저장한다.
 * 이후 받아온 데이터를 통해 DetailActivity 호출
 */
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
    pokemonTypes: List<PokemonType>,
    pokemonFlavorText: PokemonFlavorText,
    modifier: Modifier = Modifier.padding(start = 18.dp)
) {

    LazyColumn {
        item {
            ImageArea(id = pokemon.id)

        }
        item {
            NameAndTypeArea(name = pokemon.name, types = pokemonTypes, modifier)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
        }
        item {
            PokemonFlavorTextArea(flavorText = pokemonFlavorText.flavorText, modifier)
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(color = Color(0xFFF0F0F0))
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            PokemonStatsArea(pokemonStats = pokemonStats, modifier)
        }
        item {
            PhysicalArea(weight = pokemonStats.weight, height = pokemonStats.height)
        }
    }


}
/**
 *ImageArea
 * 상세화면에서 상단의 이미지 뷰를 책임
 */
@Composable
fun ImageArea(id: Int) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val imgUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = Color(palette?.dominantSwatch?.rgb ?: 0).toArgb()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
            .background(color = Color(palette?.dominantSwatch?.rgb ?: 0))

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "NO.$id",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 7.dp, end = 12.dp)
                    .align(alignment = Alignment.End)
            )
            CoilImage(
                imageModel = imgUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(10.dp),
                bitmapPalette = BitmapPalette {
                    palette = it
                },
            )

        }

    }
}

/**
 * NameAndTypeArea
 * 포켓몬 이름과 타입을 책임
 */
@Composable
fun NameAndTypeArea(name: String, types: List<PokemonType>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .then(modifier)

    ) {

        NameArea(name = name)
        Spacer(modifier = Modifier.width(8.dp))
        TypeArea(types = types)
    }
}

@Composable
fun NameArea(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 29.sp,


        )
}

/**
 * TypeArea
 * 포켓몬은 1개이상 2개 이하의 타입을 가진다.
 * 받아온 타입을 Row로 보여준다.
 */
@Composable
fun TypeArea(types: List<PokemonType>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)


    ) {
        items(types) { type ->
            Type(type)
        }
    }
}

/**
 * Type
 * PokemonType의 데이터를 통해
 * 미리 저장해둔 {type}_type.png 파일을 불러온다.
 * 그리고 이를 아이콘과 한글 이름을 ㅗ매칭
 */
@Composable
fun Type(type: PokemonType, modifier: Modifier = Modifier) {
    val resources = LocalContext.current.resources
    val imgName = "${type.type}_type"

    val resourceId = resources.getIdentifier(imgName, "drawable", LocalContext.current.packageName)

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
            .padding(start = 8.dp)

    ) {

        Image(
            painter = painterResource(id = resourceId),
            contentDescription = type.type,
            modifier = Modifier.size(28.dp)

        )
        Text(
            text = type.koreanType,
            fontSize = 9.sp,
            fontWeight = FontWeight.Light,
            color = Color.Gray,
            modifier = Modifier.padding(start = 3.dp)
        )
    }
}

/**
 * PokemonFlavorTextArea
 * 포켓몬 설명을 보여주는 영역
 */
@Composable
fun PokemonFlavorTextArea(flavorText: String, modifier: Modifier = Modifier) {
    val text = flavorText.replace("\n", " ")
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 15.dp)
            .then(modifier)
    ) {
        Text(
            text = text, fontSize = 13.sp, modifier = Modifier.fillMaxWidth()
        )
    }

}

/**
 * PokemonStatsArea
 * 포켓몬이 가진 6개의 초기 스탯을 보여주는 영역
 */

@Composable
fun PokemonStatsArea(pokemonStats: PokemonStats, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "기본 스탯",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = modifier.padding(bottom = 11.dp)
        )

        PokemonStat(pokemonStats.hp, modifier.padding(bottom = 9.dp))
        PokemonStat(pokemonStats.atk, modifier.padding(bottom = 9.dp))
        PokemonStat(pokemonStats.def, modifier.padding(bottom = 9.dp))
        PokemonStat(pokemonStats.specialAtk, modifier.padding(bottom = 9.dp))
        PokemonStat(pokemonStats.specialDef, modifier.padding(bottom = 9.dp))
        PokemonStat(pokemonStats.speed, modifier.padding(bottom = 9.dp))


    }
}

/**
 * PokemonStat
 * 하나의 포켓몬 스탯을 보여주는 영역
 * 이름과 커스텀 바로 이루어짐
 */
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
        CustomBar(
            stat = stat, maxStat = 300,
            Modifier
                .weight(4f)
                .padding(start = 7.dp)
        )
    }
}

/**
 * CustomBar
 *
 * 상태를 가로 막대 바로 표현
 * 애니메이션을 적용했다.
 */
@Composable
fun CustomBar(stat: Stat, maxStat: Int, modifier: Modifier = Modifier) {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(stat.name) {
        progress = stat.value.toFloat() / maxStat
    }
    val size by animateFloatAsState(
        targetValue = progress, animationSpec = tween(
            durationMillis = 500,
            delayMillis = 200,
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
        // 수치를 Text로 나타내기 위한 Box
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

/**
 * PhysicalArea
 * 최 하단의 무게와 신장을 나타내는 영역
 */
@Composable
fun PhysicalArea(weight: String, height: String, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(top = 7.dp)) {

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

/*
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
            ImageLoader(id = id, { Text("") })
        }

    }
}

 */


@Preview(showBackground = true)
@Composable
fun Preview4() {
    PokepediaTheme {
        Type(PokemonType("fire", "불꽃"))
    }
}

@Preview
@Composable
fun Preview5() {
    PokepediaTheme {
        NameAndTypeArea(name = "리자몽", types = listOf(PokemonType("fire", "불꽃")))
    }
}
