package com.lastbullet.fishingdiary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lastbullet.fishingdiary.ui.theme.BackgrounColor
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

@Composable
fun FeedScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgrounColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "타임라인 공사중",
            color = Color.Black,
            fontSize = 48.sp
            )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FishingDiaryTheme {
        FeedScreen()
    }
}