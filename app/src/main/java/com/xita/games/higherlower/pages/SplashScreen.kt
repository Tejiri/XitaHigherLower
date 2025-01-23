package com.xita.games.higherlower.pages

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xita.games.higherlower.R
import com.xita.games.higherlower.services.FirebaseServices
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: androidx.navigation.NavController,activity: Activity) {

    LaunchedEffect(Unit) {
        delay(4000) // 4-second delay

        FirebaseServices().validateAppVersion(activity = activity, onUpToDate = {
            navController.navigate("playerNames") {
                popUpTo("splash") { inclusive = true }
            }
        }, onUpdate = {
            navController.navigate("updatePage") {
                popUpTo("splash") { inclusive = true }
            }
        })


    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.05f))
//                .background(Color.Blue.copy(alpha = 0.2f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
//                    .alpha(alpha)
//                    .scale(scale)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.xita_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
//                Column {
//                    Text(
//                        text = "Brought by",
//                        fontSize = 22.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 24.dp), // Ensures margin from screen edges
//                        maxLines = 2, // Ensures text doesn't overflow into multiple lines unnecessarily
//                        softWrap = true // Wraps text automatically
//                    )
//
//                    Text(
//                        text = "Xita Limited",
//                        fontSize = 22.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 24.dp), // Ensures margin from screen edges
//                        maxLines = 2, // Ensures text doesn't overflow into multiple lines unnecessarily
//                        softWrap = true // Wraps text automatically
//                    )
//                }

            }
        }
    }
}

