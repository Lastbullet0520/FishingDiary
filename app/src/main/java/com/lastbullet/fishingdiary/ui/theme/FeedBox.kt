package com.lastbullet.fishingdiary.ui.theme

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

import com.lastbullet.fishingdiary.R


@Composable
fun FeedBox(rememberImg : Uri,fishName: String,upLoadDate: String) {
    Column(
        modifier = Modifier
            .background(BottomButtonColor)
            .fillMaxWidth(0.95f)
            .height(320.dp)
            .border(
                BorderStroke(
                    2.dp,
                    Brush.sweepGradient(
                        listOf(
                            Color.Blue,
                            Color.Green
                        )
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Spacer(modifier = Modifier.weight(0.05f))
            Image(
                modifier = Modifier
                    .fillMaxWidth((2 / 3f))
                    .fillMaxHeight(3 / 4f)
                    .weight(0.5f),
                painter = rememberAsyncImagePainter(rememberImg), contentDescription = null
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Icon(
                painter = painterResource(id = R.drawable.baseline_density_medium_24),
                contentDescription = null,
            )
        }
        Row(
            Modifier
                .weight(0.2f)
                .padding(10.dp), Arrangement.Center
        )
        {

            Text(
                text = fishName, modifier = Modifier,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(0.1f))
            val tempDate = upLoadDate/*.substring(7,18)*/
            Text(
                text = tempDate, modifier = Modifier,
                fontSize = 16.sp
            )
        }

    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun OnCreate() {
    FishingDiaryTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}
