package com.lastbullet.fishingdiary

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lastbullet.fishingdiary.ui.theme.BackgroundColor
import com.lastbullet.fishingdiary.ui.theme.FeedBox
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

@Composable
fun FeedScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {
            FeedBox(fishName = "광어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
            FeedBox(fishName = "숭어", date = "2024-06-11")
                // TODO : 올린 데이터를 받아서 보여주기(사진,어종,날짜)
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "광어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "광어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
//            FeedBox(fishName = "숭어", date = "2024-06-11")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FishingDiaryTheme {
        FeedScreen()
    }
}