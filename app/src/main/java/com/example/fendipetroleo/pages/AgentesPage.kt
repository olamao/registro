package com.example.fendipetroleo.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import co.yml.charts.common.model.Point
import com.example.fendipetroleo.DMViewModel
import com.example.fendipetroleo.data.DMRepository
import com.example.fendipetroleo.data.agente.Agent
import com.example.fendipetroleo.data.volumen.Despacho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

@Composable
fun AgentesPage(inputViewModel: DMViewModel, inputRepository: DMRepository, modifier: Modifier = Modifier) {

    var codigoMun by remember { mutableIntStateOf(44430) }
    var codigoPro by remember { mutableIntStateOf(1) }
    var codigoDes by remember { mutableIntStateOf(44430001) }
    var size by remember { mutableIntStateOf(-1) }
    var municipio by remember { mutableStateOf("") }
    var departamento by remember { mutableStateOf("") }
    var typedCode by remember { mutableStateOf("") }
    var despachoData by remember { mutableStateOf<Despacho?>(null) }
    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())
    val newAgentList = allAgents.value.toList()
    val brush = Brush.verticalGradient(listOf(Color.LightGray, Color.White))
    var points = remember { mutableListOf<Point>() }
    var despachoList = remember { mutableListOf<Despacho>() }


    codigoMun = inputViewModel.municipio
    codigoDes = inputViewModel.despacho



    suspend fun getDespachoByIdSuspend(id: Int): Despacho {
        return withContext(Dispatchers.IO) {
            inputRepository.getDespachoById(id)
        }
    }

    LaunchedEffect(key1 = Unit) {
        despachoData = getDespachoByIdSuspend(codigoDes)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = modifier
            //.padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(200.dp)) {
            itemsIndexed(items = newAgentList) { index, name ->

                ProductItem(index = index, agent = name, onItemClick = { selectedAgent ->
                    codigoMun = selectedAgent.codigoMun
                    codigoDes = codigoMun*1000+1
                    departamento = selectedAgent.departamento
                    municipio = selectedAgent.municipio

                    inputViewModel.municipio = codigoMun
                    inputViewModel.despacho = codigoDes


                    generarPuntos(despachoData, inputViewModel)


                    // Handle item click
                    // You can navigate to a detail screen, show a dialog, etc.
                })
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            DisplayText()
            typedCode = displayTextField()
        }
        Text(text = codigoMun.toString() + " " + municipio + " " + departamento)

        val myDigit = transformContent(typedCode)
        val eds = if(myDigit == -1){
            ""
        }else{
            allAgents.value[myDigit].razonSocial.toString()
        }
        Text(text = eds)
        size = generarPuntos(despachoData, inputViewModel)
        val dd = despachoData?.m202101.toString()
        Text(text = dd + ", " + size.toString())
    }
}


@Composable
fun ProductItem(index : Int ,agent: Agent, onItemClick: (Agent) -> Unit) {
    val backgroundColor = if (index % 2 == 0) {Color.White} else {Color.LightGray}
    // Your item UI code here
    // Detect click and invoke the onItemClick lambda
    Box(
        modifier = Modifier
            .clickable { onItemClick(agent) }
            //.padding(16.dp)
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Row{
            Text(text = agent.codigoSicom.toString(), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "\t ${agent.razonSocial}", fontSize = 12.sp)
        }
    }
}



@Composable
fun DisplayText() {
    Text(
        modifier = Modifier
            .padding(top = 10.dp),
        text = "código SICOM del aportante \t",
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )}

@Composable
fun displayTextField(): String {

    var typedText by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .alpha(1f),
        value = typedText,
        onValueChange = { typedText = it },
        //enabled = display,
        singleLine = true,
        label = { Text(
            text="Solo ingresar 6 primeros números",
            fontSize = 10.sp,) }
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







fun generarPuntos(despachoData: Despacho?, inputViewModel: DMViewModel, ): Int{

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



    /*val y = if(allAgents.value.isEmpty()){
        "empty"
    }else{
        allAgents.value[1].razonSocial
    }*/

/*@Composable
fun singleChoiceSegmentedButton(modifier: Modifier = Modifier) : Int{
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
}*/
