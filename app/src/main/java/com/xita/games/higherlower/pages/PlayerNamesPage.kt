package com.xita.games.higherlower.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xita.games.higherlower.R
import com.xita.games.higherlower.ui.theme.Purple40
import com.xita.games.higherlower.viewModels.PlayerNamesViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerNamesPage(
    navController: NavHostController, playerNamesViewModel: PlayerNamesViewModel = viewModel()
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Text(
                "Customize players",
                modifier = Modifier.padding(bottom = 20.dp),
                style = TextStyle(
                    fontSize = 25.sp, fontWeight = FontWeight.Bold
                )
            )
        }
        item {
            Row(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.7f))

                    .fillMaxSize(),

                horizontalArrangement = Arrangement.Center

            ) {

                Column(
                    Modifier.weight(0.45f), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GlideImage(if (playerNamesViewModel.playerOneGender == "male") R.drawable.boy
                    else R.drawable.girl,
                        contentDescription = "Boy",
                        Modifier
                            .size(100.dp)
                            .clickable {
                                if (playerNamesViewModel.playerOneGender == "male") {
                                    playerNamesViewModel.playerOneGender = "female"
                                } else {
                                    playerNamesViewModel.playerOneGender = "male"
                                }
                            })

                    OutlinedTextField(
                        value = playerNamesViewModel.playerOneName,
//                visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
                        onValueChange = { value -> playerNamesViewModel.playerOneNameChange(value) },
                        placeholder = { Text("Player one name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                            .background(Color.Gray.copy(alpha = 0.3f))
                    )
//            Text(
//                homePageViewModel.playerOneName, style = TextStyle(
//                    fontWeight = FontWeight.Bold, fontSize = 20.sp
//                )
//            )
//            Text(
//                "Score: ${homePageViewModel.playerOneScore}", style = TextStyle(
//                    fontWeight = FontWeight.Bold, fontSize = 18.sp
//                )
//            )
                }

                Spacer(Modifier.weight(0.1f))
                Column(
                    Modifier.weight(0.45f), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GlideImage(if (playerNamesViewModel.playerTwoGender == "male") R.drawable.boy
                    else R.drawable.girl,
                        contentDescription = "Girl",
                        Modifier
                            .size(100.dp)
                            .clickable {
                                if (playerNamesViewModel.playerTwoGender == "male") {
                                    playerNamesViewModel.playerTwoGender = "female"
                                } else {
                                    playerNamesViewModel.playerTwoGender = "male"
                                }
                            })
                    OutlinedTextField(
                        value = playerNamesViewModel.playerTwoName,
//                visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
                        onValueChange = { value -> playerNamesViewModel.playerTwoNameChange(value) },
                        placeholder = { Text("Player two name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                            .background(Color.Gray.copy(alpha = 0.3f))
                    )
//            Text(
//                homePageViewModel.playerTwoName, style = TextStyle(
//                    fontWeight = FontWeight.Bold, fontSize = 20.sp
//                )
//            )
//            Text(
//                "Score: ${homePageViewModel.playerTwoScore}", style = TextStyle(
//                    fontWeight = FontWeight.Bold, fontSize = 18.sp
//                )
//            )
                }
            }
        }

        item {

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
                        navController.navigate("home/${playerNamesViewModel.playerOneName}/${playerNamesViewModel.playerTwoName}/${playerNamesViewModel.playerOneGender}/${playerNamesViewModel.playerTwoGender}")
                    }),
            ) {
//                if (isLoading) CircularProgressIndicator(
//                    modifier = Modifier
//                        .align(alignment = Alignment.Center), color = Color.White
//                )
//                else
                Text(
                    "Continue to Game",
                    color = Color.White,
//                    color = if (transparent) Purple40 else Color.White,
                    modifier = Modifier.align(alignment = Alignment.Center),
                    style = TextStyle(fontSize = 25.sp)
                )
            }
//            Button(onClick = {}) {
//                Text("Continue to Game")
//            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerNamesPage() {
    PlayerNamesPage(rememberNavController())
}