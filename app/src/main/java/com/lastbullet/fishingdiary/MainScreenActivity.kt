package com.lastbullet.fishingdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

enum class ScreenName(val screenName: String, val description: String) {
    HOME("MAIN" ,"메인화면"),
    INFO("INFO","정보화면"),
    FEED("FEED","타임라인"),
    UPLOAD("UPLOAD","업로드")
}

class MainScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FishingDiaryTheme {
                Screen(routeName = ScreenName.HOME.screenName)
            }
        }
    }
}

@Composable
fun HomeScreen(uploadScreen : () -> Unit) {
//    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = ScrollableState { _ -> 1f }, orientation = Orientation.Vertical)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)
                .background(color = Color(0xffd3f0f0))
        ) {
            IconButton(
                onClick = { uploadScreen() }, modifier = Modifier
                    .align(BiasAlignment(0.9f, 0.95f))
                    .clip(CircleShape)
                    .size(80.dp),
                colors = IconButtonDefaults.iconButtonColors(Color.Red)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_a_photo_24),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Red)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.Center)
                ) {

                    Text(text = "Home", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Blue)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.Center)
                ) {

                    Text(text = "Info", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Yellow)
            ) {
                IconButton(
                    onClick = {
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {

                    Text(text = "Feed", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    FishingDiaryTheme {
        Screen(ScreenName.HOME.screenName)
    }
}

@Composable
fun Screen(routeName: String) {
    val navController = rememberNavController()

    NavHost(navController,routeName) {
        composable(ScreenName.HOME.screenName) {
            HomeScreen(
                uploadScreen = {navController.navigate(ScreenName.UPLOAD.screenName)}
            )
        }
        composable(ScreenName.FEED.screenName) {
            // TODO 타임라인 화면 만들고 연결
        }
        composable(ScreenName.INFO.screenName) {
            // TODO 정보 보여줄 화면 만들고 연결
        }
        composable(ScreenName.UPLOAD.screenName) {
            UploadScreen(
                navigateUp = {navController.navigateUp()},
                )
        }
    }
}