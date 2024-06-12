package com.lastbullet.fishingdiary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.lastbullet.fishingdiary.ui.theme.BackgroundColor
import com.lastbullet.fishingdiary.ui.theme.FeedBox
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

@Composable
fun FeedScreen() {
    val db = Firebase.firestore
    val fishName = remember {
        mutableListOf("")
    }

    val upLoadDate = remember {
        mutableListOf("")
    }

    var rememberImg1  by remember {
        mutableStateOf("".toUri())
    }
    var rememberImg2  by remember {
        mutableStateOf("".toUri())
    }
    var rememberImg3  by remember {
        mutableStateOf("".toUri())
    }
    val storage = Firebase.storage("gs://sparta-f5aee.appspot.com")
    val storageRef = storage.reference
    storageRef.child("Images/2024.06.11.16.36.52.1000016905").downloadUrl.addOnSuccessListener {
        rememberImg1 = it
    }
    storageRef.child("Images/2024.06.11.16.41.46.1000018250").downloadUrl.addOnSuccessListener {
        rememberImg2 = it
    }
    storageRef.child("Images/2024.06.11.16.41.56.1000018252").downloadUrl.addOnSuccessListener {
        rememberImg3 = it
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {
            val fishes = db.collection("fishNameList")
            fishes
                .get()
                .addOnSuccessListener {documents ->
                    for (document in documents) {
                        fishName.add(document.data.getValue("fishname").toString())
                        upLoadDate.add(document.data.getValue("imageUri").toString())
                    }
                }
//            for (i in 0..fishName.size) {
                FeedBox(rememberImg1, "광어", "2024.06.11")
                FeedBox(rememberImg2, "참돔", "2024.06.10")
                FeedBox(rememberImg3, "우럭(조피볼락)", "2024.06.01")
//            }


//                 TODO : 올린 데이터를 받아서 보여주기(사진,어종,날짜)
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