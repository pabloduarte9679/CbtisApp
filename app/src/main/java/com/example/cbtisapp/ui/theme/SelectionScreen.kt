package com.example.cbtisapp.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.SportsBasketball
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cbtisapp.ThemeManager
//import kotlinx.coroutines.launch


data class Edificio(
    val nombre: String,
    val id: String
)

data class ZonaEvacuacion(
    val puntoReunion: String,
    val icono: ImageVector,
    val edificios: List<Edificio>
)

@Composable
fun EdificioItem(nombre: String, isDarkMode: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(if (isDarkMode) 0.dp else 2.dp),
        border = BorderStroke(
            1.dp,
            if (isDarkMode) Color.White.copy(alpha = 0.1f) else Color.LightGray.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = nombre,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkMode) Color.White.copy(alpha = 0.9f) else Color.DarkGray
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun SelectionScreen(onEdificioSelected: (String) -> Unit) {
    val context = LocalContext.current
    val themeManager = remember { ThemeManager(context) }
    //val scope = rememberCoroutineScope()
    val isDarkMode by themeManager.isDarkModeFlow.collectAsState(initial = false)

    val zonas = listOf(
        ZonaEvacuacion(
            "Punto de Reunión: Canchas", Icons.Default.SportsBasketball, listOf(
                Edificio("Edificio A", "A"), Edificio("Edificio B", "B"),
                Edificio("Electromecánica", "EM"), Edificio("Robótica", "RB"),
                Edificio("EBC", "EBC")
            )
        ),
        ZonaEvacuacion(
            "Punto de Reunión: Bicéfalo", Icons.Default.Groups, listOf(
                Edificio("Electrónica", "EL"), Edificio("Edificio J", "J"),
                Edificio("Cómputo", "CP"), Edificio("Edificio S", "S")
            )
        )
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color(0xFF121212) else Color(0xFFFDFDFD))
            .verticalScroll(rememberScrollState())
    ) {
        /*HeaderSection(
            isDarkMode = isDarkMode,
            onToggleDarkMode = {
                scope.launch {
                    themeManager.setDarkMode(!isDarkMode)
                }
            }
        ) */

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Seleccione su ubicación",
            color = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Seleccione el edificio actual",
            fontSize = 13.sp,
            color = if (isDarkMode) Color.White.copy(alpha = 0.7f) else Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        zonas.forEach { zona ->
            ZonaSection(zona, isDarkMode, onEdificioSelected)
        }

        WarningBox(isDarkMode)

        Spacer(modifier = Modifier.height(80.dp))
    }
}

/*@Composable
fun HeaderSection(isDarkMode: Boolean, onToggleDarkMode: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isDarkMode) Color(0xFF1E1E1E) else Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Seleccion de ruta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkMode) Color.White else Color.Black
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onToggleDarkMode) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = "Modo oscuro",
                    tint = if (isDarkMode) Color.White else Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { /* SOS */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text("SOS", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
*/
@Composable
fun ZonaSection(zona: ZonaEvacuacion, isDarkMode: Boolean, onEdificioSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                zona.icono,
                contentDescription = null,
                tint = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = zona.puntoReunion,
                fontWeight = FontWeight.Bold,
                color = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        zona.edificios.chunked(2).forEach { fila ->
            Row(modifier = Modifier.fillMaxWidth()) {
                fila.forEach { edificio ->
                    Box(modifier = Modifier.weight(1f)) {
                        EdificioItem(edificio.nombre, isDarkMode) { onEdificioSelected(edificio.id) }
                    }
                }
                if (fila.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun WarningBox(isDarkMode: Boolean) {
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) Color(0xFF331111) else Color(0xFFFFF1F1)
        ),
        border = BorderStroke(1.dp, Color.Red.copy(alpha = 0.3f))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Mantenga la calma. Al seleccionar su ubicación, se mostrarán las flechas rojas específicas para su salida.",
                fontSize = 12.sp,
                color = if (isDarkMode) Color(0xFFFF8888) else Color.Red
            )
        }
    }
}