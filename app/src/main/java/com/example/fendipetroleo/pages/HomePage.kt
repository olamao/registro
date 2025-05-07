package com.example.fendipetroleo.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fendipetroleo.DMViewModel
import com.example.fendipetroleo.R

@Composable
fun HomePage(inputViewModel: DMViewModel, modifier: Modifier = Modifier) {

    var codigoMun by remember { mutableIntStateOf(154) }
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val widthPx = displayMetrics.widthPixels
    val heightPx = displayMetrics.heightPixels
    val widthDp = with(LocalDensity.current) { widthPx.toDp() }
    val heightDp = with(LocalDensity.current) { (heightPx/3).toDp() }
    val secondHeight = with(LocalDensity.current) { (heightPx/6f).toDp() }

    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())
    val newAgentList = allAgents.value.toList()

    Thread {
        //Do your database´s operations here
    }.start()


    val startAngle = 0F
    val sweepAngle = 180F
    val rect = Rect(
        left = -(widthPx.toFloat()/8f),
        top = -(widthPx.toFloat()/6),
        right = (widthPx.toFloat()*9/8),
        bottom = (widthPx.toFloat()/6)
    )
    val path = Path()
    path.apply {
        arcTo(rect = rect, startAngleDegrees = startAngle, sweepAngleDegrees = sweepAngle, forceMoveTo = false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFffffff)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Thread {
            //Do your database´s operations here
        }.start()

        Image(
            modifier = Modifier
                .padding(top = 150.dp),
            painter = painterResource(id = R.drawable.logo_soldicom_3),
            contentDescription = "logo"
        )

        val brush = Brush.verticalGradient(listOf(Color.LightGray, Color.White))  //.horizontalGradient(listOf(Color.Red, Color.Blue))
        Column(
            modifier = modifier
                .background(brush),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Canvas(
                    modifier = Modifier
                        .height(secondHeight)
                        .fillMaxWidth()
                ) {
                    drawPath(path, Color.White)
                }
            }



            Box(contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Administrado por:",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF004877))
                    Image(
                        painter = painterResource(id = R.drawable.logo_fendipetroleo),
                        contentDescription = "logo"
                    )
                }
            }
            Box(contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                //.height(secondHeight)
            ) {}


        }
    }
}

@Composable
fun Registro() {
    Button(onClick = { /* Handle button click */ }) {
        Text("Filled")
    }
}