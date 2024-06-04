package com.lastbullet.fishingdiary

import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.inappmessaging.internal.Logging.TAG
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
        val context = LocalContext.current // context 초기화
        var imgUri by remember { // 이미지의 uri 저장하기 위한 변수 지정
            mutableStateOf<Uri?>(null)
        }
        val pickMedia = // photopicker 초기화. 아래는 uri로 불러올 때 사용
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                imgUri = uri
            }
        Image( // uri로 불러온 이미지 출력
            painter = rememberAsyncImagePainter(imgUri),
            contentDescription = "default image",
            modifier = Modifier.size(240.dp)
        )
        var fishName by remember { mutableStateOf("") } // 어종(이름) 저장할 변수 지정
        TextField( //어종을 입력하는 입력창 TODO 1: 검색창으로 바꾸기
            value = fishName,        // TODO 2: 키워드 검색으로 선택만 할 수 있게 바꾸기
            onValueChange = { fishName = it },
            label = { Text(text = "잡은 어종을 입력하세요") })
        Row {
            Button(onClick = { // 이미지'만' 선택할 수 있게 만드는 버튼.
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) {
                Text(text = "사진 선택")
            }
            Button(onClick = { // onClick에 들어가는 것들이 너무 많아짐에 따라 변수로 빼둘까 생각 중
                uploadButtonOnClick(fishName = fishName, imgUri = imgUri, context = context)
            }) {
                Text(text = "업로드")
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

private fun uploadButtonOnClick(fishName: String, imgUri: Uri?, context: android.content.Context) {
    val db = Firebase.firestore // firestore 데이터베이스 초기화
    val storage = Firebase.storage("gs://sparta-f5aee.appspot.com") // firebase storage 초기화
    val storageRef = storage.reference // firebase storage reference 지정. 초기화랑 한묶음인듯
    val riversRef = // 이미지를 storage에 넣을 때 경로 지정하기
        storageRef.child("Images/${imgUri!!.lastPathSegment}") //TODO 사진 선택 안하고 업로드 에러 nullsafety 필요
    val uploadTask = riversRef.putFile(imgUri) // 이미지를 storage에 넣는 작업. nullsafety 필수
    uploadTask.addOnSuccessListener { _ -> // 이미지를 성공적으로 넣었을 경우,
        // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
        // ...
        val tempName = hashMapOf(  // firestore에 넣을 "키" 와 값(value). 여기선 위에 작성한 어종(이름)이 들어간다.
            "fishname" to fishName
        )
        //firestore에 자료 보내기
        db.collection("fishNameList") // 컬렉션 이름; firestore에 먼저 생성 후 작성
            .document("XyfudoakHUL4XHuin7ZK") // 폴더 이름; firestore에 먼저 생성 후 작성
            .set(tempName) // 키와 값(여기선 mutableMap으로 설정)
            .addOnSuccessListener { // 성공시 나타내는 로그
                Log.d(
                    TAG,
                    "DocumentSnapshot successfully written!"
                )
            } // 실패시 나타내는 로그
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        Toast.makeText(context, "업로드에 성공했습니다.", Toast.LENGTH_SHORT) // 성공시의 toast 메시지
            .show() // TODO 출력이 늦다
    }.addOnFailureListener {
        Toast.makeText(context, "업로드에 실패했습니다.", Toast.LENGTH_SHORT) // 실패시의 toast 메시지
            .show() // TODO 출력되기 전에 nullpointexception
        // Handle unsuccessful uploads
    }
}
