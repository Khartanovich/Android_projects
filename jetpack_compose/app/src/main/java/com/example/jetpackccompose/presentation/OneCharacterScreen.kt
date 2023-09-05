package com.example.jetpackccompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun OneCharacterScreen(id: Int, viewModel: MyViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getInfoAboutOneCharacter(id)
    }
    val character by viewModel.oneCharacter.collectAsState()


    var listNumberEpisode = mutableListOf<Int>()
    val char = "/"
    character?.episode?.onEachIndexed { index, it ->
        listNumberEpisode.add(it.split(char).last().toInt())
        if (index == character?.episode?.lastIndex) {
            LaunchedEffect(Unit) {
                viewModel.getEpisode(listNumberEpisode)
            }
        }
    }

    val episodes by viewModel.episode.collectAsState()

    val gradient = Brush.horizontalGradient(
        0.0f to Color.White,
        1.0f to Color.Black,
        startX = 0.0f,
        endX = 1000.0f
    )
    if (episodes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        ) {
            item {
                GlideImageWithPreview(
                    data = character?.image,
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = character?.name.toString(),
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(gradient)
                ) {}
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Live status:",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = character?.status.toString(),
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Species and gender",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = character?.species.toString() + " (${character?.gender.toString()})",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Last known location",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = character?.location?.name.toString(),
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "First seen in:",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = episodes.first().name,
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Episodes:",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

            items(episodes.count()) { index ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF747070))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = episodes[index].name,
                            modifier = Modifier.padding(start = 16.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                        Text(
                            text = episodes[index].airDate,
                            modifier = Modifier.padding(start = 16.dp),
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        text = episodes[index].episode,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }

            }

        }
    }
}