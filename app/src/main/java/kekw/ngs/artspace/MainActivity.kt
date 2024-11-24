package kekw.ngs.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kekw.ngs.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    ) {
    Column  (
        modifier = modifier
    ){
        Button(
            onClick = { onClick() },
            modifier = modifier
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun ArtWorkWall(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes contentDescription: Int,
) {
    Column  (
        modifier = modifier
            .fillMaxSize(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Surface  (
            modifier = modifier,
            shadowElevation = 4.dp,
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = contentDescription),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes subtitle: Int,
) {
    Card  (
        modifier = modifier,
    ){
        Column (
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(id = subtitle),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun DisplayController(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row  (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ){
        CustomButton(
            onClick = { onPreviousClick() },
            text = "Previous",
        )
        CustomButton(
            onClick = { onNextClick() },
            text = "Next",
        )
    }

}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier,
) {
    var artWorkNumber: Int by remember { mutableIntStateOf(1) }
    val image = when (artWorkNumber) {
        1 -> R.drawable.the_embarkation_of_the_queen_of_sheba
        2 -> R.drawable.self_portrait_by_salvator_rosa
        3 -> R.drawable.judith_slaying_holofernes
        4 -> R.drawable.martyrdom_of_st_philip
        else -> R.drawable.the_embarkation_of_the_queen_of_sheba
    }
    val title = when (artWorkNumber) {
        1 -> R.string.the_embarkation_of_the_queen_of_sheba
        2 -> R.string.self_portrait_by_salvator_rosa
        3 -> R.string.judith_slaying_holofernes
        4 -> R.string.martyrdom_of_st_philip
        else -> R.string.the_embarkation_of_the_queen_of_sheba
    }
    val description = when (artWorkNumber) {
        1 -> R.string.the_embarkation_of_the_queen_of_sheba_description
        2 -> R.string.self_portrait_by_salvator_rosa_description
        3 -> R.string.judith_slaying_holofernes_description
        4 -> R.string.martyrdom_of_st_philip_description
        else -> R.string.the_embarkation_of_the_queen_of_sheba_description
    }
    Column  (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        ArtWorkWall(
            image = image,
            contentDescription = title,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkDescriptor(
            title = title,
            subtitle = description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        DisplayController(
            onPreviousClick = {
                artWorkNumber -= 1
                if (artWorkNumber == 0) {
                    artWorkNumber = 4
            } },
            onNextClick = {
                artWorkNumber += 1
                if (artWorkNumber == 5) {
                    artWorkNumber = 1
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}