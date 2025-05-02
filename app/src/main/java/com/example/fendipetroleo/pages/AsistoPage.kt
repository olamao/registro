package com.example.fendipetroleo.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
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
import com.example.fendipetroleo.DMViewModel
import com.example.fendipetroleo.data.DMRepository
import com.example.fendipetroleo.data.despachos.Despacho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

@Composable
fun AsistoPage(inputViewModel: DMViewModel, inputRepository: DMRepository, modifier: Modifier = Modifier) {

    var clickedButton by remember { mutableIntStateOf(0) }
    var displayRegistro by remember { mutableStateOf(false) }
    var typedCode by remember { mutableStateOf("") }
    val allAgents = inputViewModel.allAgents.collectAsState(initial = emptyList())
    var despachoData by remember { mutableStateOf<Despacho?>(null) }

    suspend fun getDespachoByIdSuspend(id: Int): Despacho {
        return withContext(Dispatchers.IO) {
            inputRepository.getDespachoById(id)
        }
    }

    LaunchedEffect(key1 = Unit) {
        despachoData = getDespachoByIdSuspend(11001001)
    }



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
            clickedButton = singleChoiceSegmentedButton()
        }
        displayRegistro = if(clickedButton == 1){
            true
        }else{
            false
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            DisplayText(displayRegistro)
            typedCode = displayTextField(displayRegistro)
        }

        val myDigit = transformContent(typedCode)
        val eds = if(myDigit == -1){
            ""
        }else{
            allAgents.value[myDigit].razonSocial.toString()
        }

        val xx = despachoData?.m202101.toString()
       val despachoMunicipio = listOf(
           despachoData?.m202101,
           despachoData?.m202102,
           despachoData?.m202103,
           despachoData?.m202104,
           despachoData?.m202105,
           despachoData?.m202106,
           despachoData?.m202107,
           despachoData?.m202108,
           despachoData?.m202109,
           despachoData?.m202110,
           despachoData?.m202111,
           despachoData?.m202112,

           despachoData?.m202201,
           despachoData?.m202202,
           despachoData?.m202203,
           despachoData?.m202204,
           despachoData?.m202205,
           despachoData?.m202206,
           despachoData?.m202207,
           despachoData?.m202208,
           despachoData?.m202209,
           despachoData?.m202210,
           despachoData?.m202211,
           despachoData?.m202212,

           despachoData?.m202301,
           despachoData?.m202302,
           despachoData?.m202303,
           despachoData?.m202304,
           despachoData?.m202305,
           despachoData?.m202306,
           despachoData?.m202307,
           despachoData?.m202308,
           despachoData?.m202309,
           despachoData?.m202310,
           despachoData?.m202311,
           despachoData?.m202312,

           despachoData?.m202401,
           despachoData?.m202402,
           despachoData?.m202403,
           despachoData?.m202404,
           despachoData?.m202405,
           despachoData?.m202406,
           despachoData?.m202407,
           despachoData?.m202408,
           despachoData?.m202409,
           despachoData?.m202410,
           despachoData?.m202411,
           despachoData?.m202412,

           despachoData?.m202501,
           despachoData?.m202502,
           despachoData?.m202503,
           despachoData?.m202504,

           )
        Text(text = eds + xx)
        LazyColumn (){
            despachoMunicipio.forEach {
                item(it){
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Composable
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
        text = "código SICOM del aportante \t",
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = displayColor
    )}

@Composable
fun displayTextField(display: Boolean): String {
    var displayAlpha = if(display){
        1f
    }else{
        0.4f
    }

    var typedText by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .alpha(displayAlpha),
        value = typedText,
        onValueChange = { typedText = it },
        enabled = display,
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


/*    val y = if(allAgents.value.isEmpty()){
        "empty"
    }else{
        allAgents.value[1].razonSocial
    }*/