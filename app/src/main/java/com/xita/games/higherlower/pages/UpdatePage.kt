package com.xita.games.higherlower.pages

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xita.games.higherlower.R
import com.xita.games.higherlower.ui.theme.Purple40

@Composable
fun UpdatePage() {
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_app_update))
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever
    )

    Scaffold { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .weight(0.7f)
//            .fillMaxWidth()
//            .fillMaxSize()
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = progress,
//            modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .weight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "New Update Available", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )

                Text(
                    "The current version of this application is no longer supported. " +
                            "To continue using all features and ensure security and stability, please update to the latest version. Thank you for understanding!",
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clip(RoundedCornerShape(40))
//                    .background(if (transparent) Color.Transparent else Purple40)
                        .background(Purple40)
                        .border(3.dp, Purple40, RoundedCornerShape(40))
                        .padding(12.dp)
                        .clickable(onClick = {
                            try {
                                // Attempt to open the Play Store app
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=com.xita.games.higherlower")
                                )
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // Fallback: Open in a web browser
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.xita.games.higherlower")
                                )
                                context.startActivity(intent)
                            }

//                                https://play.google.com/store/apps/details?id=com.xita.chadventmpcs.android
//                            navController.navigate("home/${playerNamesViewModel.playerOneName}/${playerNamesViewModel.playerTwoName}/${playerNamesViewModel.playerOneGender}/${playerNamesViewModel.playerTwoGender}")
                        }),
                ) {
//                if (isLoading) CircularProgressIndicator(
//                    modifier = Modifier
//                        .align(alignment = Alignment.Center), color = Color.White
//                )
//                else
                    Text(
                        "Update",
                        color = Color.White,
//                    color = if (transparent) Purple40 else Color.White,
                        modifier = Modifier.align(alignment = Alignment.Center),
                        style = TextStyle(fontSize = 25.sp)
                    )
                }


            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun PreviewUpdatePage() {
    UpdatePage()
}