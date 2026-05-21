package com.example.cbtisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.example.cbtisapp.ui.theme.CbtisAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CbtisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RoutesScreen()
                }
            }
        }
    }
}

@Composable
fun RoutesScreen() {
    // Componente padre ocupa toda la pantalla
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Barra superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF031A34))
                .padding(horizontal = 15.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //Icono de la barra superior de escudo
            Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = "Safe Icon",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
