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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fendipetroleo.AgenteViewModel
import com.example.fendipetroleo.R

@Composable
fun HomePage(inputViewModel: AgenteViewModel, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val widthPx = displayMetrics.widthPixels
    val heightPx = displayMetrics.heightPixels
    val widthDp = with(LocalDensity.current) { widthPx.toDp() }
    val heightDp = with(LocalDensity.current) { (heightPx/3).toDp() }
    val secondHeight = with(LocalDensity.current) { (heightPx/6f).toDp() }

    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())

    val y = if(allAgents.value.isEmpty()){
        "empty"
    }else{
        allAgents.value[1].razonSocial
    }

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
       // moveTo(0f, 100f)
       // lineTo(widthPx.toFloat(), y = 100f)
        arcTo(rect = rect, startAngleDegrees = startAngle, sweepAngleDegrees = sweepAngle, forceMoveTo = false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFffffff)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Thread {
            //Do your database´s operations here

        }.start()

        Image(
            painter = painterResource(id = R.drawable.logo_soldicom_3),
            contentDescription = "logo"
        )


        Box(modifier = Modifier
            .fillMaxWidth()
            .height(secondHeight)
            //.size(200.dp)
            .background(Color.Gray),
            contentAlignment = Alignment.TopCenter ){

            Canvas(
                modifier = Modifier
                    .height(secondHeight)
                    .fillMaxWidth()
                    //.size(Size(width = widthPx.toFloat(), height = widthPx.toFloat()))
                    //.size(mySizeDp)
            )
            {
                drawPath(path, Color.Yellow, alpha = 0.5f)
            }

        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(heightDp)
            //.size(200.dp)
            .background(Color.Red)){
            Text(
                text = "68 Asamblea $y",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Registro()
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(heightDp)
            //.size(200.dp)
            .background(Color.LightGray)){
            Row(){
                Text(
                    text = "Organizada por:",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.logo_fendipetroleo),
                    contentDescription = "logo"
                )
            }
        }
    }
}

@Composable
fun Registro() {
    Button(onClick = { /* Handle button click */ }) {
        Text("Filled")
    }
}