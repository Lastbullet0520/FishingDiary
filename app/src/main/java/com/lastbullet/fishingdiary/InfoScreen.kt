package com.lastbullet.fishingdiary

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lastbullet.fishingdiary.ui.theme.BackgroundColor
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

@Composable
fun InfoScreen() {
    val context = LocalContext.current
    var fishName by remember { mutableStateOf("") }
    val lists = mutableSetOf( // TODO 리스트 늘리기
        "광어", "우럭(조피볼락)", "도다리", "넙치", "참돔", "벵어돔", "쥐치", "볼락", "돌돔", "연어", "장어"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        SearchableExpandedDropDownMenu(listOfItems = lists.toMutableList(),
            modifier = Modifier.fillMaxWidth(),
            onDropDownItemSelected = { item ->
                fishName = item
            },
            dropdownItem = { test ->
                Text(text = test)
            },
            defaultItem = {
                Log.e("DEFAULT_ITEM", it)
            },
            onSearchTextFieldClicked = {
                keyboardController?.show()
            })

        Text(
            text = fishName,
            color = Color.Black,
            fontSize = 48.sp
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun InfoScreenPreview() {
    FishingDiaryTheme {
        InfoScreen()
    }
}