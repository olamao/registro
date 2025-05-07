package com.example.work.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.Point
import com.example.work.DMViewModel
import com.example.work.charts.LineChart
import com.example.work.data.DMRepository
import com.example.work.data.volumen.Despacho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun DespachosPage(inputViewModel: DMViewModel, inputRepository: DMRepository, modifier: Modifier = Modifier) {

    //var clickedButton by remember { mutableIntStateOf(0) }
    //var displayRegistro by remember { mutableStateOf(false) }
    var codDes by remember { mutableIntStateOf(0) }
    var typedCode by remember { mutableStateOf("") }
    var despachoData by remember { mutableStateOf<Despacho?>(null) }
    var points = remember { mutableListOf<Point>() }

    suspend fun getDespachoByIdSuspend(id: Int): Despacho {
        return withContext(Dispatchers.IO) {
            inputRepository.getDespachoById(id)
        }
    }

    codDes = inputViewModel.municipio*1000+1

    LaunchedEffect(key1 = Unit) {
        despachoData = getDespachoByIdSuspend(codDes)}

    if(despachoData != null){
        generarPuntos2(despachoData, inputViewModel)
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
            DisplayText()
            typedCode = displayTextField()
        }

        val pointsSize = points.size.toString()
        Text( codDes.toString() + " " + pointsSize)


        val nn = inputViewModel.viewModelPoints.size

        inputViewModel.viewModelPoints.forEach { Log.e("mao",it.toString()) }

        Text(text = nn.toString())
        if(inputViewModel.viewModelPoints.isNotEmpty()){
            LineChart(inputViewModel.viewModelPoints)

        }
   // LineChart(points)


/*        LazyColumn (){
            despachoMunicipio.forEach {
                item(it){
                    Text(text = it.toString())
                }
            }
        }*/
    }
}

fun generarPuntos2(despachoData: Despacho?, inputViewModel: DMViewModel, ): Int{

    val points =  if(despachoData == null){
        mutableListOf(Point(x=0f, y= 0f))
    }
    else{
        mutableListOf(
            Point(x=1f, y= despachoData?.m202101.toString().toFloat()),
            Point(x=2f, y= despachoData?.m202102.toString().toFloat()),
            Point(x=3f, y= despachoData?.m202103.toString().toFloat()),
            Point(x=4f, y= despachoData?.m202104.toString().toFloat()),
            Point(x=5f, y= despachoData?.m202105.toString().toFloat()),
            Point(x=6f, y= despachoData?.m202106.toString().toFloat()),
            Point(x=7f, y= despachoData?.m202107.toString().toFloat()),
            Point(x=8f, y= despachoData?.m202108.toString().toFloat()),
            Point(x=9f, y= despachoData?.m202109.toString().toFloat()),
            Point(x=10f, y= despachoData?.m202110.toString().toFloat()),
            Point(x=11f, y= despachoData?.m202111.toString().toFloat()),
            Point(x=12f, y= despachoData?.m202112.toString().toFloat()),

            Point(x=13f, y= despachoData?.m202201.toString().toFloat()),
            Point(x=14f, y= despachoData?.m202202.toString().toFloat()),
            Point(x=15f, y= despachoData?.m202203.toString().toFloat()),
            Point(x=16f, y= despachoData?.m202204.toString().toFloat()),
            Point(x=17f, y= despachoData?.m202205.toString().toFloat()),
            Point(x=18f, y= despachoData?.m202206.toString().toFloat()),
            Point(x=19f, y= despachoData?.m202207.toString().toFloat()),
            Point(x=20f, y= despachoData?.m202208.toString().toFloat()),
            Point(x=21f, y= despachoData?.m202209.toString().toFloat()),
            Point(x=22f, y= despachoData?.m202210.toString().toFloat()),
            Point(x=23f, y= despachoData?.m202211.toString().toFloat()),
            Point(x=24f, y= despachoData?.m202212.toString().toFloat()),

            Point(x=25f, y= despachoData?.m202301.toString().toFloat()),
            Point(x=26f, y= despachoData?.m202302.toString().toFloat()),
            Point(x=27f, y= despachoData?.m202303.toString().toFloat()),
            Point(x=28f, y= despachoData?.m202304.toString().toFloat()),
            Point(x=29f, y= despachoData?.m202305.toString().toFloat()),
            Point(x=30f, y= despachoData?.m202306.toString().toFloat()),
            Point(x=31f, y= despachoData?.m202307.toString().toFloat()),
            Point(x=32f, y= despachoData?.m202308.toString().toFloat()),
            Point(x=33f, y= despachoData?.m202309.toString().toFloat()),
            Point(x=34f, y= despachoData?.m202310.toString().toFloat()),
            Point(x=35f, y= despachoData?.m202311.toString().toFloat()),
            Point(x=36f, y= despachoData?.m202312.toString().toFloat()),


            Point(x=37f, y= despachoData?.m202401.toString().toFloat()),
            Point(x=38f, y= despachoData?.m202402.toString().toFloat()),
            Point(x=39f, y= despachoData?.m202403.toString().toFloat()),
            Point(x=40f, y= despachoData?.m202404.toString().toFloat()),
            Point(x=41f, y= despachoData?.m202405.toString().toFloat()),
            Point(x=42f, y= despachoData?.m202406.toString().toFloat()),
            Point(x=43f, y= despachoData?.m202407.toString().toFloat()),
            Point(x=44f, y= despachoData?.m202408.toString().toFloat()),
            Point(x=45f, y= despachoData?.m202409.toString().toFloat()),
            Point(x=46f, y= despachoData?.m202410.toString().toFloat()),
            Point(x=47f, y= despachoData?.m202411.toString().toFloat()),
            Point(x=48f, y= despachoData?.m202412.toString().toFloat()),

            Point(x=49f, y= despachoData?.m202501.toString().toFloat()),
            Point(x=50f, y= despachoData?.m202502.toString().toFloat()),
            Point(x=51f, y= despachoData?.m202503.toString().toFloat()),
            Point(x=52f, y= despachoData?.m202504.toString().toFloat()),
        )
    }
    inputViewModel.viewModelPoints = points
    val size = points.size
    return size
}
