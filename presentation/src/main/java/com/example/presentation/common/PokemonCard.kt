package com.example.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.PokemonCover



@Composable
fun PokemonCard(
    item: PokemonCover,
    getKoreanName: suspend (Int) -> String,
    navigateToDetail:(Int,String) ->Unit
) {

    var pokemonName by remember { mutableStateOf(item.name) }
    LaunchedEffect(key1 = item.id) {
        pokemonName = getKoreanName(item.id)
    }


    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .size(230.dp)
            .padding(8.dp)
            .clickable {
                navigateToDetail(item.id,item.name)
            }


    ) {
        Column {
            ImageLoader(id = item.id) {
                Text(
                    text = pokemonName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(CenterHorizontally)
                )
            }

            /*
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
             */

        }
    }
}


