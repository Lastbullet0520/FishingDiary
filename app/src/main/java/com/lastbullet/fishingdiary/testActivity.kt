package com.lastbullet.fishingdiary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//@Preview(showBackground = true)
//@Composable
//fun NavigationBar(screenChange : ()->Unit = {} ) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(color = Color(0x00000000))
//    ) {
//        IconButton(
//            onClick = { screenChange() }, modifier = Modifier
//                .align(BiasAlignment(0.9f, 0.7f))
//                .clip(CircleShape)
//                .size(80.dp),
//            colors = IconButtonDefaults.iconButtonColors(Color.Red)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_add_a_photo_24),
//                contentDescription = null,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//        BottomNavigationBar(modifier = Modifier
//            .fillMaxHeight(0.1f)
//            .align(Alignment.BottomStart))
//    }
//}





