package com.xita.games.higherlower.pages

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xita.games.higherlower.R
import com.xita.games.higherlower.viewModels.HomePageViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomePage(
    navController: NavController,
    homePageViewModel: HomePageViewModel = viewModel()
) {
    homePageViewModel.playerOneName =
        navController.currentBackStackEntry?.arguments?.getString("playerOneName") ?: "Player 1"
    homePageViewModel.playerTwoName =
        navController.currentBackStackEntry?.arguments?.getString("playerTwoName") ?: "Player 2"
    homePageViewModel.playerOneGender =
        navController.currentBackStackEntry?.arguments?.getString("playerOneGender") ?: "male"
    homePageViewModel.playerTwoGender =
        navController.currentBackStackEntry?.arguments?.getString("playerTwoGender") ?: "female"

    if (homePageViewModel.playerOneName.isEmpty()) {
        homePageViewModel.playerOneName = "Player 1"
    }
    if (homePageViewModel.playerTwoName.isEmpty()) {
        homePageViewModel.playerTwoName = "Player 2"
    }

    BackHandler {

    }
    Scaffold { innerPadding ->

        Box {

            Image(
                painter = painterResource(R.drawable.game_background),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier.padding(innerPadding)
            ) {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(1) {
                        Text(
                            "Cards Remaining: " +
                                    "${
                                        if (homePageViewModel.drawCardResponse == null) ""
                                        else homePageViewModel.drawCardResponse?.remaining
                                    }",
                            modifier = Modifier.padding(bottom = 15.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold, fontSize = 25.sp
                            )
                        )

                        Row(
                            modifier = Modifier

                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {


                            GlideImage(
                                model = if (homePageViewModel.currentCard == null) {
                                    R.drawable.back_of_card // Resource ID for the placeholder
                                } else {
                                    homePageViewModel.currentCard!!.images.png // URL of the card image
                                },
//                            if (homePageViewModel.currentCard == null)  R.drawable.back_of_card
//                            else homePageViewModel.currentCard!!.images.png,
                                contentDescription = "",
                                modifier = Modifier.size(170.dp)
//                        .background(Color.Red)
                            )



                            Spacer(modifier = Modifier.width(20.dp))
                            GlideImage(
                                if (homePageViewModel.nextCard == null) R.drawable.back_of_card
                                else homePageViewModel.nextCard!!.images.png,
                                contentDescription = "",
                                modifier = Modifier.size(170.dp) // Ensures consistent item size
//                        .background(Color.Red)
                            )
                        }
                        if (homePageViewModel.currentRoundMessage.isEmpty()) {
                            Spacer(Modifier.height(50.dp))
                            Text(
                                if (homePageViewModel.currentPlayer == 1)
                                    "${homePageViewModel.playerOneName}'s turn"
                                else "${homePageViewModel.playerTwoName}'s turn",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                                )
                            )

                            Row(Modifier.padding(top = 10.dp)) {
                                Button(modifier = Modifier.padding(end = 10.dp), onClick = {
                                    homePageViewModel.playerGuess = "higher"
                                    homePageViewModel.drawCard()
                                }) {
                                    Text("Higher")
                                }

                                Button(onClick = {
                                    homePageViewModel.playerGuess = "lower"
                                    homePageViewModel.drawCard()


                                }) {
                                    Text("Lower")
                                }
                            }
//
                        } else {
                            Spacer(Modifier.height(20.dp))
                            Row(
                                modifier = Modifier
                                    .height(70.dp)
                                    .padding(end = 10.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val composition by rememberLottieComposition(
                                    LottieCompositionSpec.RawRes(
                                        if (homePageViewModel.currentRoundMessage == "Correct")
                                            R.raw.lottie_correct
                                        else R.raw.lottie_wrong
                                    )
                                )
                                val progress by animateLottieCompositionAsState(
                                    composition = composition,
                                    iterations = LottieConstants.IterateForever
                                )

//                            Box(
//                                contentAlignment = Alignment.CenterStart,
//                                modifier = Modifier.background(Color.Gray.copy(alpha = 0.2f))
//                            ) {
//                                LottieAnimation(
//                                    composition = composition,
//                                    progress = progress,
//                                )
//                            }

                                LottieAnimation(
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp),
                                    composition = composition,
                                    progress = progress,
                                )
                                Text(
                                    homePageViewModel.currentRoundMessage, style = TextStyle(
                                        fontWeight = FontWeight.Bold, fontSize = 24.sp
                                    )
                                )
                            }


                            Button(modifier = Modifier.padding(top = 10.dp), onClick = {
                                homePageViewModel.setNextRound()
                            }) {
                                Text("Next Round")
                            }
                        }


                    }

                }






                Row(
                    modifier = Modifier
                        .weight(0.3f)
                        .background(Color.White.copy(alpha = 0.7f))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    LazyColumn {
                        item {

                            GlideImage(
                                if (homePageViewModel.playerOneGender == "male") R.drawable.boy
                                else R.drawable.girl,
                                contentDescription = "Boy",
                                Modifier.size(100.dp)
                            )
                            Text(
                                homePageViewModel.playerOneName,
//                            if (homePageViewModel.playerOneName.isEmpty()) "Player 1"
//                            else homePageViewModel.playerOneName,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                                )
                            )
                            Text(
                                "Score: ${homePageViewModel.playerOneScore}", style = TextStyle(
                                    fontWeight = FontWeight.Bold, fontSize = 18.sp
                                )
                            )
                        }
                    }

                    Spacer(Modifier.width(50.dp))
                    LazyColumn {
                        item {
                            GlideImage(
                                if (homePageViewModel.playerTwoGender == "male") R.drawable.boy
                                else R.drawable.girl,
                                contentDescription = "Girl",
                                Modifier.size(100.dp)
                            )
                            Text(
                                homePageViewModel.playerTwoName,
//                            if (homePageViewModel.playerTwoName.isEmpty()) "Player 2"
//                            else homePageViewModel.playerTwoName,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                                )
                            )
                            Text(
                                "Score: ${homePageViewModel.playerTwoScore}", style = TextStyle(
                                    fontWeight = FontWeight.Bold, fontSize = 18.sp
                                )
                            )
                        }
                    }
                }

            }


            if (homePageViewModel.gameSetupLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(0.7f))
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(70.dp)
                                .height(70.dp),
                            strokeWidth = 7.dp
                        )
                        Text(
                            "Setting up game",
                            modifier = Modifier.padding(top = 10.dp),
                            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        )
                    }

                }
            } else {

            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    HomePage(navController = rememberNavController())
}