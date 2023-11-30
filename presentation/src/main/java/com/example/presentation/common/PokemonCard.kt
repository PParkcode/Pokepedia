package com.example.presentation.common

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.PokemonCover
import com.example.presentation.common.ui.theme.PokepediaTheme


@Composable
fun PokemonCard(item: PokemonCover) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(4.dp)
            .clickable {
            Log.d("확인","${item.id} ${item.name}")
        }

    ) {
        Column {
            ImageLoader(id = item.id)
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PokepediaTheme {
        PokemonCard(item = PokemonCover(1,"이상해씨"))
    }
}