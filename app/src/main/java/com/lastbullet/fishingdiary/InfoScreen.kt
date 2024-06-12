package com.lastbullet.fishingdiary

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Image(painter = painterResource(id = R.drawable.sebastes_inermis), contentDescription = "예시",
            modifier = Modifier.size(240.dp))
        Text(text = "눈은 주둥이보다 길고, 앞쪽 밑에 예리한 가시가 두 개 있다." +
                "꼬리지느러미의 뒷가장자리는 둥글거나 뭉툭하다. 몸은 회갈색이고," +
                "5-6줄의 검은색의 불명료한 가로띠가 있다. 난태생 물고기로 1년이면" +
                "8-9cm, 2년이면 13cm, 5년이 되면 19-20cm가 되고," +
                "가장 큰 것은 30cm 정도가 된다.")

    }
}

@Preview(showBackground = true)
@Composable
private fun InfoScreenPreview() {
    FishingDiaryTheme {
        InfoScreen()
    }
}