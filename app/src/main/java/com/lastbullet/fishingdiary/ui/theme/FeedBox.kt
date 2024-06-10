package com.lastbullet.fishingdiary.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.lastbullet.fishingdiary.R
import com.lastbullet.fishingdiary.UploadScreen


@Composable
fun FeedBox(/*TODO 이미지 Url 불러오기?*/fishName : String, date : String) {
    val db = Firebase.firestore
    val storage = Firebase.storage("gs://sparta-f5aee.appspot.com")
    var storageRef = storage.reference
    Row(
        modifier = Modifier
            .background(BottomButtonColor)
            .fillMaxWidth(0.95f)
            .fillMaxHeight(1 / 8f)
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth((1 / 4f))
                .fillMaxHeight(1f)
        ) {
        }
        Column(
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
            Text(
                text = date, modifier = Modifier,
                fontSize = 16.sp
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_density_medium_24),
            contentDescription = null,
        )
    }
}