package com.example.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette


@Composable
fun ImageLoader(id: Int, name:@Composable () -> Unit) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    var imgUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(palette?.dominantSwatch?.rgb ?: 0))
    )
    {
        Column {
            CoilImage(
                imageModel = imgUrl,
                contentDescription = null,
                modifier = Modifier
                    //.fillMaxWidth()
                    .size(150.dp)
                    .padding(10.dp),
                bitmapPalette = BitmapPalette {
                    palette = it
                },
            )
            name()
        }

    }
}
