package com.example.fendipetroleo.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fendipetroleo.AgenteViewModel
import java.io.IOException

@Composable
fun AsistoPage(inputViewModel: AgenteViewModel, modifier: Modifier = Modifier) {

    var clickedButton by remember { mutableIntStateOf(0) }

    var displayRegistro by remember { mutableStateOf(false) }
    var typedCode by remember { mutableStateOf("") }


    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())
    val codigosList = if(allAgents.value.isEmpty()){
        listOf("empty")
    }else{
        allAgents.value.map { it.codigoSicom }
    }

    val brush = Brush.verticalGradient(listOf(Color.LightGray, Color.White))  //.horizontalGradient(listOf(Color.Red, Color.Blue))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Asisto\t \t",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            clickedButton = SingleChoiceSegmentedButton()
        }
        displayRegistro = if(clickedButton == 1){
            true
        }else{
            false
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            DisplayText(displayRegistro)
            typedCode = DisplayTextField(displayRegistro)
        }

        val myDigit = transformContent(typedCode)
        val eds = if(myDigit == -1){
            ""
        }else{
            allAgents.value[myDigit].razonSocial.toString()
        }

        Text(text = eds)
    }
}

@Composable
fun SingleChoiceSegmentedButton(modifier: Modifier = Modifier) : Int{
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("No", "Si")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
    return selectedIndex
}

@Composable
fun DisplayText(display: Boolean) {
    var displayColor = if (display) {
        Color.Black
    }else{
        Color.LightGray
    }

    Text(
        modifier = Modifier
            .padding(top = 10.dp),
        text = "Digite código SICOM \t",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = displayColor
    )}

@Composable
fun DisplayTextField(display: Boolean): String {
    var displayAlpha = if(display){
        1f
    }else{
        0.4f
    }

    var typedText by remember { mutableStateOf("") }
    TextField(modifier = Modifier
        .alpha(displayAlpha),
        value = typedText,
        onValueChange = { typedText = it },
        enabled = display,
        // label = { Text("Label") }
    )
    return typedText
}

fun transformContent(input: String): Int {
    var xx = -1
    if(input == ""){
        Log.e("mao","string is empty")
    }else{
        try {
            // Code that may throw an exception
            xx = input.toInt()
            val numbers = input.split(",").mapNotNull { it.toIntOrNull() }
        } catch (e: IOException) {
            // Code for handling the exception
            Log.e("mao","error de lectura")
        }
    }


    return xx
}


/*    val y = if(allAgents.value.isEmpty()){
        "empty"
    }else{
        allAgents.value[1].razonSocial
    }*/