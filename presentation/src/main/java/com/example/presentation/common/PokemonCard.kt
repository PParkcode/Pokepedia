package com.example.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.PokemonCover
import com.example.presentation.common.ui.theme.PokepediaTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.launch



@Composable
fun PokemonCard(
    item: PokemonCover,
    getKoreanName: suspend (Int) -> String,
    viewModel: CommonViewModel = hiltViewModel()
) {

    var pokemonName by remember { mutableStateOf(item.name) }
    val coroutineScope = rememberCoroutineScope()


    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .size(210.dp)
            .padding(8.dp)
            .clickable {
                //Log.d("확인", "${item.id} ${item.name}")
            }

    ) {

        Column {
            ImageLoader(id = item.id)
            // 이름은 비동기로 가져와서 업데이트
            LaunchedEffect(key1 = item.id) {
                pokemonName = getKoreanName(item.id)
            }

            // 이름이 로드되면 텍스트 표시
            Text(
                text = pokemonName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .align(CenterHorizontally)
            )

        }
    }
}


