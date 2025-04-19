package com.example.fendipetroleo.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fendipetroleo.AgenteViewModel


@Composable
fun HomePage(inputViewModel: AgenteViewModel, modifier: Modifier = Modifier) {

    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())

    val y = if(allAgents.value.isEmpty()){
        "empty"
    }else{
        allAgents.value[1].razonSocial
    }

    Thread {
        //Do your database´s operations here
    }.start()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Thread {
            //Do your database´s operations here

        }.start()
        Text(
            text = "create from assets table 3 $y",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}