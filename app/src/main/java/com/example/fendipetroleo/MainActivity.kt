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
import com.example.fendipetroleo.data.DMDatabase
import com.example.fendipetroleo.data.DMRepository
import com.example.fendipetroleo.ui.theme.FendipetroleoTheme

class MainActivity : ComponentActivity() {

    private lateinit var database1: DMDatabase
    private lateinit var repository1: DMRepository
    private lateinit var viewModelFactory: DMViewModelFactory
    private lateinit var viewModel: DMViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the database

        database1 = Room.databaseBuilder(
            applicationContext,
            DMDatabase::class.java, "agentes"
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("informacion.db")
            .build()

        // Initialize the DAO
        val agenteDao = database1.agenteDao()
        val despachoDao = database1.saleDao()
        // Initialize the repository
        repository1 = DMRepository(agenteDao, despachoDao)
        // Initialize the factory
        viewModelFactory = DMViewModelFactory(repository1)
        // Initialize the viewmodel
        viewModel = ViewModelProvider(this, viewModelFactory)[DMViewModel::class.java]

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