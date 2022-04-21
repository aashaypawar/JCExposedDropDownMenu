package com.geeksforgeeks.jcexposeddropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Creating a Simple Scaffold Layout for the application
            Scaffold(

                // Creating a Top Bar
                topBar = { TopAppBar(title = { Text("GFG | Multiple TextField Colors", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },

                // Creating Content
                content = {

                    // Creating a Column Layout
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                        var text by remember{ mutableStateOf("")}

                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            visualTransformation = ColorsTransformation()
                        )

                    }
                }
            )
        }
    }
}

class ColorsTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithColors(text.toString()),
            OffsetMapping.Identity)
    }
}

fun buildAnnotatedStringWithColors(text:String): AnnotatedString {
    val words: List<String> = text.split("\\s+".toRegex())// splits by whitespace
    val colors = listOf(Color.Red,Color.Black,Color.Yellow,Color.Blue)

    val builder = AnnotatedString.Builder()
    for ((count, word) in words.withIndex()) {
        builder.withStyle(style = SpanStyle(color = colors[count%4])) {
            append("$word ")
        }
    }
    return builder.toAnnotatedString()
}

/*
Package Name Wrong
 */
