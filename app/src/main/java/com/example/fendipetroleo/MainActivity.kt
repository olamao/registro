package com.example.fendipetroleo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fendipetroleo.data.agente.AgenteDatabase
import com.example.fendipetroleo.data.agente.AgenteRepository
import com.example.fendipetroleo.data.volumen.SaleDatabase
import com.example.fendipetroleo.data.volumen.SaleRepository
import com.example.fendipetroleo.ui.theme.FendipetroleoTheme

class MainActivity : ComponentActivity() {

    private lateinit var database1: AgenteDatabase
    private lateinit var database2: SaleDatabase
    private lateinit var repository1: AgenteRepository
    private lateinit var repository2: SaleRepository
    private lateinit var viewModelFactory: AgenteViewModelFactory
    private lateinit var viewModel: AgenteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // Initialize the database


            database1 = Room.databaseBuilder(
                applicationContext,
                AgenteDatabase::class.java, "agentes"
            )
                .createFromAsset("sqlitedatabase.db")
                .build()

            database2 = Room.databaseBuilder(
                applicationContext,
                SaleDatabase::class.java, "volumen"
            )
                .createFromAsset("sqlitedatabase.db")
                .build()

            // Initialize the DAO
            val agenteDao = database1.agenteDao()
            val saleDao = database2.saleDao()
            // Initialize the repository
            repository1 = AgenteRepository(agenteDao)
            repository2 = SaleRepository(saleDao)
            // Initialize the factory
            viewModelFactory = AgenteViewModelFactory(repository1, repository2)
            // Initialize the viewmodel
            viewModel = ViewModelProvider(this, viewModelFactory)[AgenteViewModel::class.java]


            FendipetroleoTheme {
                MainScreen(viewModel)

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