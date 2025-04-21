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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.fendipetroleo.R

@Composable
fun InformacionPage(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val widthPx = displayMetrics.widthPixels
    val heightPx = displayMetrics.heightPixels
    val widthDp = with(LocalDensity.current) { widthPx.toDp() }
    val heightDp = with(LocalDensity.current) { (heightPx/3).toDp() }
    val secondHeight = with(LocalDensity.current) { (heightPx/6f).toDp() }



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
                //.height(secondHeight)
            ) {
                Text(modifier = Modifier
                    .padding(top = 10.dp),
                    text = "ASAMBLEA EXTRAORDINARIA 68°\n",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004877)  //004877
                )
                //Registro()
            }
            Text(
                text =  "Día y fecha: Miércoles, 30 de abril de 2025\n" +
                        "Ciudad: \t\t\t\t Bogotá, D.C.\n" +
                        "Lugar:\t\t\t\t\t\t Hotel Wyndham Bogotá \n" +
                        "Dirección:\t\t Av. Esperanza N° 51-40\n" +
                        "Hora:\t\t\t\t\t\t 9:30 a.m.",
                fontSize = 15.sp,
                color = Color(0xFF004877)  //004877
            )
            Box(contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Organizada por:",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF004877))
                    Image(
                        painter = painterResource(id = R.drawable.logo_fendipetroleo),
                        contentDescription = "logo"
                    )
                }
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