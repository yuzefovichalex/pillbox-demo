package com.alexyuzefovich.pillbox.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import com.alexyuzefovich.pillbox.PillBoxApplication
import com.alexyuzefovich.pillbox.ui.model.Pill
import com.alexyuzefovich.pillbox.ui.components.PillCard
import com.alexyuzefovich.pillbox.ui.theme.PillBoxTheme
import com.alexyuzefovich.pillbox.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.Factory((application as PillBoxApplication).pillRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PillBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Pills(mainViewModel)
                }
            }
        }
        mainViewModel.loadPills()
    }

}

@Composable
fun Pills(mainViewModel: MainViewModel) {
    val pills = mainViewModel.pills.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(8.dp, 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pills.value) { pill ->
            PillCard(pill)
        }
    }
}