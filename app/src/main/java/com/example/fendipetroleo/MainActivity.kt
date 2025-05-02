package com.example.fendipetroleo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.room.Room
import com.example.fendipetroleo.data.DMDatabase
import com.example.fendipetroleo.data.DMRepository
import com.example.fendipetroleo.data.despachos.Despacho
import com.example.fendipetroleo.ui.theme.FendipetroleoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var database1: DMDatabase
    private lateinit var repository1: DMRepository
    private lateinit var viewModelFactory: DMViewModelFactory
    private lateinit var viewModel: DMViewModel

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        database1 = Room.databaseBuilder(
            applicationContext,
            DMDatabase::class.java, "agentes"
        )
            .createFromAsset("informacion.db")
            .build()

        // Initialize the database
        val agenteDao = database1.agenteDao()
        // Initialize the DAO
        val despachoDao = database1.despachoDao()
        // Initialize the repository
        repository1 = DMRepository(agenteDao, despachoDao)
        // Initialize the factory
        viewModelFactory = DMViewModelFactory(repository1)
        // Initialize the viewmodel
        viewModel = ViewModelProvider(this, viewModelFactory)[DMViewModel::class.java]


/*        suspend fun getDespachoByIdSuspend(id: Int): Despacho {
            return withContext(Dispatchers.IO) {
                repository1.getDespachoById(id)
            }
        }
        var despacho = "xx"
        lifecycleScope.launch{
            val ñ = viewModel.getDespachoByIdSuspend(5001001).m202102.toString()
            despacho = getDespachoByIdSuspend(5001001).m202101.toString() + " " + ñ
            println(despacho)
            Log.d("mao", despacho + " "  + ñ)
        }*/

        setContent {
            FendipetroleoTheme {
                MainScreen(viewModel, repository1)

            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FendipetroleoTheme {
        Greeting("Android")
    }
}