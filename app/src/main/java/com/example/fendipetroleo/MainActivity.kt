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
import com.example.fendipetroleo.ui.theme.FendipetroleoTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: AgenteDatabase
    private lateinit var repository: AgenteRepository
    private lateinit var viewModelFactory: AgenteViewModelFactory
    private lateinit var viewModel: AgenteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // Initialize the database


            database = Room.databaseBuilder(
                applicationContext,
                AgenteDatabase::class.java, "agentes"
            )
                .createFromAsset("sqlitedatabase.db")
                .build()


            // Initialize the DAO
            val agenteDao = database.agenteDao()
            // Initialize the repository
            repository = AgenteRepository(agenteDao)
            // Initialize the factory
            viewModelFactory = AgenteViewModelFactory(repository)
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