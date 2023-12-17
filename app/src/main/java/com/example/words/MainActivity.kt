@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import java.io.FileOutputStream;
import java.io.InputStream;
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.words.useCases.countScore
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

               

        setContent {


            fun GetWord():String{
                val file = File(filesDir, "words.txt")
                val data = FileInputStream(file).use {
                    String(it.readBytes())
                }
                return data
            }


            val countUsecase = countScore()
            val text = countUsecase.getRandomWord(GetWord())
            val darkTheme = isSystemInDarkTheme()
            val systemUiController = rememberSystemUiController()
            if(darkTheme){
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent
                )
            }else{
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent
                )
            }

            val navController = rememberNavController()
            NavHost(navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController) }
                composable("secondScreen") { SecondScreen(navController) }
                composable("2P") { TwoPlayersScreen(text) }
                composable("3P") { ThreePlayersScreen(text) }
                composable("4P") { FourPlayersScreen(text) }
            }
        }
    }
}



@Composable
fun WelcomeScreen(navController: NavController) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val fontFamily = FontFamily(
        Font(R.font.pixelifysans_regular, FontWeight.Normal),
    )
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "WORDS",
            modifier = Modifier
                .padding(top = 78.dp),
            fontSize = 86.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold)
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center){
                Box(modifier = Modifier
                    .clip(CircleShape)){
                    Image(
                        painter = painterResource(id = R.drawable.play_now),
                        contentDescription = "Play Now",
                        modifier = Modifier
                            .size(150.dp)
                            .clickable(
                                indication = null,
                                interactionSource = interactionSource
                            ) {
                                navController.navigate("secondScreen")
                            },
                    )
                }

            }
        }
    }



@Composable
fun SecondScreen(navController: NavController) {

    
    
val interactionSource = remember {
    MutableInteractionSource()
}

val fontFamily = FontFamily(
    Font(R.font.pixelifysans_regular, FontWeight.Normal),
)
Column(modifier = Modifier
    .fillMaxHeight()
    .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally){
    Text(text = "CHOOSE",
        modifier = Modifier
            .padding(top = 78.dp),
        fontSize = 60.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold)

    Text(
        text = "NUMBER",
        fontSize = 60.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
    )

    Text(text = "OF PLAYERS",
        fontSize = 60.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold)
}


Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 125.dp),
    contentAlignment = Alignment.BottomCenter,
){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){

            Text(
                text = "2P",
                fontSize = 68.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .width(110.dp)
                    .border(3.dp, Color.Black)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(indication = null, interactionSource = interactionSource) {
                        navController.navigate("2P")
                    },
                textAlign = TextAlign.Center
            )

            Text(
                text = "3P",
                fontSize = 68.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .width(110.dp)
                    .border(3.dp, Color.Black)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(indication = null, interactionSource = interactionSource) {
                        navController.navigate("3P")
                    },
                textAlign = TextAlign.Center
            )

            Text(
                text = "4P",
                fontSize = 68.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .width(110.dp)
                    .border(3.dp, Color.Black)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(indication = null, interactionSource = interactionSource) {
                        navController.navigate("4P")
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}




@Composable
fun TwoPlayersScreen(text:String){



    val navController = rememberNavController()
    var score1 by remember {
        mutableStateOf(0)
    }
    var score2 by remember {
        mutableStateOf(0)
    }
    NavHost(navController = navController, startDestination = "currentScreen") {
        composable("currentScreen") {
            // Ваш текущий composable экран
            var colorBorder1: Color
            var colorBorder2: Color
            val countUseCase = countScore()

            var skipCount by remember {
                mutableStateOf(0)
            }

            var confirmCount by remember {
                mutableStateOf(0)
            }

            if(confirmCount %2 == 0){
                colorBorder1 = Color.Green
                colorBorder2 = Color.Black
            }
            else{
                colorBorder2 = Color.Green
                colorBorder1 = Color.Black
            }

            var editText by remember {
                mutableStateOf("")
            }



            val fontFamily = FontFamily(
                Font(R.font.pixelifysans_regular, FontWeight.Normal),
            )

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top){

                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder1),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "1P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = "$score1",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder2),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "2P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }

                    Text(
                        text = "$score2",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )

                }
            }


            Column(
                modifier = Modifier
                    .padding(top = 192.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = text,
                    fontSize = 42.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center,
                )

                TextField(value = editText, onValueChange ={ newText ->
                    editText = newText
                },
                    singleLine = true,
                    placeholder = { Text(text = "Write your word",
                        color = Color.Gray,
                        fontSize = 24.sp,
                        fontFamily = fontFamily)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 42.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Black,
                        disabledIndicatorColor = Color.Black,
                        errorIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                        ),
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp,
                        fontFamily = fontFamily)

                )



                var wordsList = mutableListOf<String>()

                Row(modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {

                    Box(modifier = Modifier
                        .height(64.dp)
                        .width(150.dp)
                        .border(3.dp, Color.Black)
                        .clickable {
                            if (editText == "") {

                            } else {
                                if (confirmCount % 2 == 0) {
                                    score1 += countUseCase.processWord(
                                        text,
                                        editText,
                                        wordsList = wordsList
                                    )
                                    wordsList.add(editText)
                                    editText = ""
                                } else {
                                    score2 += countUseCase.processWord(
                                        text,
                                        editText,
                                        wordsList = wordsList
                                    )
                                    wordsList.add(editText)
                                    editText = ""
                                }
                                confirmCount++
                                skipCount = 0
                            }
                        },
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Подтвердить слово",
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }




                    Box(modifier = Modifier
                        .height(64.dp)
                        .width(150.dp)
                        .border(3.dp, Color.Black)
                        .clickable {
                            confirmCount++
                            editText = ""
                            skipCount++
                            if (skipCount == 2) {
                                navController.navigate("winnerScreen")
                            }
                        },
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Пропустить ход",
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }


                }
            }
        }
        composable("winnerScreen") {
            Winner2(score1 = score1, score2 = score2)
        }
    }
    

    

}


@Composable
fun ThreePlayersScreen(text:String){
    var score1 by remember {
        mutableStateOf(0)
    }
    var score2 by remember {
        mutableStateOf(0)
    }
    var score3 by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "currentScreen") {
        composable("currentScreen") {
            // Ваш текущий composable экран

    var colorBorder1 = Color.Black
    var colorBorder2= Color.Black
    var colorBorder3= Color.Black
    val countUseCase = countScore()

    var skipCount by remember {
        mutableStateOf(0)
    }

    var confirmCount by remember {
        mutableStateOf(0)
    }

    if(confirmCount%3 == 0){
        colorBorder1 = Color.Green
        colorBorder2 = Color.Black
        colorBorder3 = Color.Black
    } else
        if(confirmCount%3 == 1){
            colorBorder1 = Color.Black
            colorBorder2 = Color.Green
            colorBorder3 = Color.Black
        }
    else {
        if(confirmCount%3 == 2){
            colorBorder1 = Color.Black
            colorBorder2 = Color.Black
            colorBorder3 = Color.Green
        }
    }




    var editText by remember {
        mutableStateOf("")
    }



    val fontFamily = FontFamily(
        Font(R.font.pixelifysans_regular, FontWeight.Normal),
    )

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top){

        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .size(64.dp)
                .border(3.dp, colorBorder1),
                contentAlignment = Alignment.Center){
                Text(
                    text = "1P",
                    fontSize = 36.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "$score1",
                fontSize = 36.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center
            )
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier
                .size(64.dp)
                .border(3.dp, colorBorder2),
                contentAlignment = Alignment.Center){
                Text(
                    text = "2P",
                    fontSize = 36.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "$score2",
                fontSize = 36.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center
            )

        }
    }


    Column(
        modifier = Modifier
            .padding(top = 192.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        TextField(value = editText, onValueChange ={ newText ->
            editText = newText
        },
            singleLine = true,
            placeholder = { Text(text = "Write your word",
                color = Color.Gray,
                fontSize = 24.sp,
                fontFamily = fontFamily)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 42.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Black,
                disabledIndicatorColor = Color.Black,
                errorIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                containerColor = Color.Transparent,
                cursorColor = Color.Black
                ),
            textStyle = TextStyle.Default.copy(fontSize = 24.sp,
                fontFamily = fontFamily)

        )



        var wordsList = mutableListOf<String>()

        Row(modifier = Modifier
            .padding(top = 48.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {

            Box(modifier = Modifier
                .height(64.dp)
                .width(150.dp)
                .border(3.dp, Color.Black)
                .clickable {
                    if (editText == "") {

                    } else {
                        if (confirmCount % 3 == 0) {
                            score1 += countUseCase.processWord(
                                text,
                                editText,
                                wordsList = wordsList
                            )
                            wordsList.add(editText)
                            editText = ""
                        } else {
                            if (confirmCount % 3 == 1) {
                                score2 += countUseCase.processWord(
                                    text,
                                    editText,
                                    wordsList = wordsList
                                )
                                wordsList.add(editText)
                                editText = ""
                            } else
                                if (confirmCount % 3 == 2) {
                                    score3 += countUseCase.processWord(
                                        text,
                                        editText,
                                        wordsList = wordsList
                                    )
                                    wordsList.add(editText)
                                    editText = ""
                                }

                        }
                        confirmCount++
                        skipCount = 0
                    }
                },
                contentAlignment = Alignment.Center){
                Text(
                    text = "Подтвердить слово",
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }

                    Box(modifier = Modifier
                        .height(64.dp)
                        .width(150.dp)
                        .border(3.dp, Color.Black)
                        .clickable {
                            confirmCount++
                            editText = ""
                            skipCount++
                            if (skipCount == 3) {
                                navController.navigate("winnerScreen3")
                            }
                        },
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Пропустить ход",
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }


                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 152.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = text,
                    fontSize = 42.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center,
                )
            }

            Row (modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom){

                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "$score3",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder3),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "3P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        composable("winnerScreen3") {
            Winner3(score1 = score1, score2 = score2, score3 = score3)
        }
    }


}



@Composable
fun FourPlayersScreen(text:String){
    var score4 by remember {
        mutableStateOf(0)
    }
    var score1 by remember {
        mutableStateOf(0)
    }
    var score2 by remember {
        mutableStateOf(0)
    }
    var score3 by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "currentScreen") {
        composable("currentScreen") {
            // Ваш текущий composable экран
            var colorBorder1 = Color.Black
            var colorBorder2= Color.Black
            var colorBorder3= Color.Black
            var colorBorder4= Color.Black
            val countUseCase = countScore()

            var skipCount by remember {
                mutableStateOf(0)
            }

            var confirmCount by remember {
                mutableStateOf(0)
            }

            if(confirmCount%4 == 0){
                colorBorder1 = Color.Green
                colorBorder2 = Color.Black
                colorBorder3 = Color.Black
                colorBorder4 = Color.Black
            } else
                if(confirmCount%4 == 1){
                    colorBorder1 = Color.Black
                    colorBorder2 = Color.Green
                    colorBorder3 = Color.Black
                    colorBorder4 = Color.Black
                }
                else {
                    if(confirmCount%4 == 2){
                        colorBorder1 = Color.Black
                        colorBorder2 = Color.Black
                        colorBorder3 = Color.Green
                        colorBorder4 = Color.Black
                    }
                    else {
                        if(confirmCount%4 == 3){
                            colorBorder1 = Color.Black
                            colorBorder2 = Color.Black
                            colorBorder3 = Color.Black
                            colorBorder4 = Color.Green
                        }
                    }
                }




            var editText by remember {
                mutableStateOf("")
            }



            val fontFamily = FontFamily(
                Font(R.font.pixelifysans_regular, FontWeight.Normal),
            )

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top){

                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder1),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "1P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = "$score1",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder2),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "2P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }

                    Text(
                        text = "$score2",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )

                }
            }


            Column(
                modifier = Modifier
                    .padding(top = 192.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                TextField(value = editText, onValueChange ={ newText ->
                    editText = newText
                },
                    singleLine = true,
                    placeholder = { Text(text = "Write your word",
                        color = Color.Gray,
                        fontSize = 24.sp,
                        fontFamily = fontFamily)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 42.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Black,
                        disabledIndicatorColor = Color.Black,
                        errorIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                        ),
                    textStyle = TextStyle.Default.copy(fontSize = 24.sp,
                        fontFamily = fontFamily)

                )



                var wordsList = mutableListOf<String>()

                Row(modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {

                    Box(modifier = Modifier
                        .height(64.dp)
                        .width(150.dp)
                        .border(3.dp, Color.Black)
                        .clickable {
                            if (editText == "") {

                            } else {
                                if (confirmCount % 4 == 0) {
                                    score1 += countUseCase.processWord(
                                        text,
                                        editText,
                                        wordsList = wordsList
                                    )
                                    wordsList.add(editText)
                                    editText = ""
                                } else {
                                    if(confirmCount % 4 == 1){
                                        score2 += countUseCase.processWord(
                                            text,
                                            editText,
                                            wordsList = wordsList
                                        )
                                        wordsList.add(editText)
                                        editText = ""
                                    }
                                    else
                                        if(confirmCount%4 == 2){
                                            score3 += countUseCase.processWord(
                                                text,
                                                editText,
                                                wordsList = wordsList
                                            )
                                            wordsList.add(editText)
                                            editText = ""
                                        }
                                    else {
                                            if(confirmCount%4 == 3){
                                                score4 += countUseCase.processWord(
                                                    text,
                                                    editText,
                                                    wordsList = wordsList
                                                )
                                                wordsList.add(editText)
                                                editText = ""
                                            }
                                        }

                                }
                                confirmCount++
                                skipCount = 0
                            }
                        },
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Подтвердить слово",
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }

                    Box(modifier = Modifier
                        .height(64.dp)
                        .width(150.dp)
                        .border(3.dp, Color.Black)
                        .clickable {
                            confirmCount++
                            skipCount++
                            editText = ""
                            if (skipCount == 4) {
                                navController.navigate("winnerScreen4")
                            }
                        },
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Пропустить ход",
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }


                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 152.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = text,
                    fontSize = 42.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center,
                )
            }

            Row (modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom){

                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "$score3",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder3),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "3P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "$score4",
                        fontSize = 36.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier
                        .size(64.dp)
                        .border(3.dp, colorBorder4),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "4P",
                            fontSize = 36.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        composable("winnerScreen4") {
            Winner4(score1 = score1, score2 = score2, score3 = score3, score4 = score4)
        }
    }


}


@Composable
fun Winner2(score1:Int, score2:Int) {

            val fontFamily = FontFamily(
                Font(R.font.pixelifysans_regular, FontWeight.Normal),
            )
            if (score1 > score2) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Победил первый игрок",
                        fontSize = 56.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (score2 > score1) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Победил второй игрок",
                        fontSize = 56.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (score1 == score2) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Ничья",
                        fontSize = 56.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }



@Composable
fun Winner3(score1:Int, score2:Int, score3:Int) {

    val fontFamily = FontFamily(
        Font(R.font.pixelifysans_regular, FontWeight.Normal),
    )
    if (score1 > score2 && score1 > score3) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Победил первый игрок",
                fontSize = 56.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center
            )
        }
    }
    else{
        if (score2 > score1 && score2 > score3) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Победил второй игрок",
                    fontSize = 56.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }
        }
        else {
            if (score3 > score1 && score3 > score2) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Победил третий игрок",
                        fontSize = 56.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }
            }  else {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Нет победителя",
                            fontSize = 56.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }


@Composable
fun Winner4(score1:Int, score2:Int, score3:Int, score4:Int) {

    val fontFamily = FontFamily(
        Font(R.font.pixelifysans_regular, FontWeight.Normal),
    )
    if (score1 > score2 && score1 > score3 && score1 > score4) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Победил первый игрок",
                fontSize = 56.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center
            )
        }
    }
    else{
        if (score2 > score1 && score2 > score3 && score2 > score4) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Победил второй игрок",
                    fontSize = 56.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }
        }
        else {
            if (score3 > score1 && score3 > score2 && score3 > score4) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Победил третий игрок",
                        fontSize = 56.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Center
                    )
                }
            } else{
                if (score4 > score1 && score4 > score2 && score4 > score3) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Победил четвёртый игрок",
                            fontSize = 56.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Нет победителя",
                            fontSize = 56.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Thin,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}



