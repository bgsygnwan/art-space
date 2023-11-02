package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentArtwork by remember {
        mutableStateOf(1)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(currentArtwork) {
            1 -> {
                ArtworkWall(
                    drawableResourceId = R.drawable.artwork_1
                )
                ArtworkDescriptor(
                    textTitleResourceId = R.string.artwork_1_title,
                    textArtistResourceId = R.string.artwork_1_artist,
                    textYearResourceId = R.string.artwork_1_year
                )
            }
            2 -> {
                ArtworkWall(
                    drawableResourceId = R.drawable.artwork_2
                )
                ArtworkDescriptor(
                    textTitleResourceId = R.string.artwork_2_title,
                    textArtistResourceId = R.string.artwork_2_artist,
                    textYearResourceId = R.string.artwork_2_year
                )
            }
            3 -> {
                ArtworkWall(
                    drawableResourceId = R.drawable.artwork_3
                )
                ArtworkDescriptor(
                    textTitleResourceId = R.string.artwork_3_title,
                    textArtistResourceId = R.string.artwork_3_artist,
                    textYearResourceId = R.string.artwork_3_year
                )
            }
        }
        DisplayController(
            onButtonPreviousClick = {
                if (currentArtwork == 1) {
                    currentArtwork = 1
                } else {
                    currentArtwork--
                }
            },
            onButtonNextClick = {
                if (currentArtwork == 3) {
                    currentArtwork = 1
                } else {
                    currentArtwork++
                }
            },
            currentArtwork = currentArtwork
        )
    }
}

@Composable
fun ArtworkWall(
    drawableResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .height(400.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = null,
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun ArtworkDescriptor(
    textTitleResourceId: Int,
    textArtistResourceId: Int,
    textYearResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(
                1.dp,
                Color.LightGray,
                RoundedCornerShape(16.dp)
            ),
    ) {
        Column(modifier = modifier
            .padding(16.dp)
        ) {
            Text(
                text = stringResource(textTitleResourceId),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(textArtistResourceId),
                    fontSize = 14.sp,
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = stringResource(textYearResourceId),
                    fontSize = 14.sp,
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onButtonPreviousClick: () -> Unit,
    onButtonNextClick: () -> Unit,
    currentArtwork: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onButtonPreviousClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Previous")
            }
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "$currentArtwork of 3")
            }
            Button(
                onClick = onButtonNextClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}