package com.lastbullet.fishingdiary

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.lastbullet.fishingdiary.ui.theme.FishingDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FishingDiaryTheme {
                Greeting()


            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        var imgUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val storage = Firebase.storage("gs://sparta-f5aee.appspot.com")
        val storageRef = storage.reference
        val pickMedia =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                imgUri = uri
            }
        Image(
            painter = rememberAsyncImagePainter(imgUri),
            contentDescription = "default image",
            modifier = Modifier.size(240.dp)
        )
        var fishName by remember{ mutableStateOf("") }
        TextField(value = fishName, onValueChange = {fishName = it})
        Row {
            Button(onClick = {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) {
                Text(text = "사진 선택")
            }
            Button(onClick = { /*TODO : firebase 적용*/
                val riversRef = storageRef.child("images/${imgUri!!.lastPathSegment}")
                var uploadTask = riversRef.putFile(imgUri!!)

// Register observers to listen for when the download is done or if it fails
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    // ...
                    taskSnapshot.metadata
                }.addOnFailureListener {
                    Toast.makeText(context, "업로드에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    // Handle unsuccessful uploads
                }
            }) {
                Text(text = "사진 업로드")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FishingDiaryTheme {
        Greeting()
    }
}