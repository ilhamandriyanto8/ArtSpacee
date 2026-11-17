
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtSpaceTheme {
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

data class Artwork(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val animeRes: Int,
    @StringRes val descriptionRes: Int
)

private val artworks = listOf(
    Artwork(
        R.drawable.mrn,
        R.string.artwork_1_name,
        R.string.artwork_1_anime,
        R.string.artwork_1_desc
    ),
    Artwork(
        R.drawable.itsuki,
        R.string.artwork_2_name,
        R.string.artwork_2_anime,
        R.string.artwork_2_desc
    ),
    Artwork(
        R.drawable.mitsuha,
        R.string.artwork_3_name,
        R.string.artwork_3_anime,
        R.string.artwork_3_desc
    ),
    Artwork(
        R.drawable.anzu,
        R.string.artwork_4_name,
        R.string.artwork_4_anime,
        R.string.artwork_4_desc
    ),
    Artwork(
        R.drawable.isla,
        R.string.artwork_5_name,
        R.string.artwork_5_anime,
        R.string.artwork_5_desc
    )
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var currentIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val lastIndex = artworks.lastIndex
    val artwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtworkWall(
            artwork = artwork,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ArtworkDescriptor(artwork = artwork)

        Spacer(modifier = Modifier.height(24.dp))

        DisplayController(
            onPreviousClick = {
                currentIndex = if (currentIndex == 0) {
                    lastIndex
                } else {
                    currentIndex - 1
                }
            },
            onNextClick = {
                currentIndex = if (currentIndex == lastIndex) {
                    0
                } else {
                    currentIndex + 1
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ArtworkWall(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
            .shadow(elevation = 8.dp),
        color = Color.White
    ) {
        Image(
            painter = painterResource(artwork.imageRes),
            contentDescription = stringResource(artwork.nameRes),
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(20.dp)
    ) {

        Text(
            text = stringResource(artwork.nameRes),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(artwork.animeRes),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tentang Karakter",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(artwork.descriptionRes),
            fontSize = 15.sp,
            lineHeight = 23.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.previous))
        }

        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
