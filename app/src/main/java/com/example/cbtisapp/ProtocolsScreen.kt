package com.example.cbtisapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cbtisapp.ThemeManager

data class Protocol(
    val type: String,
    val icono: ImageVector,
    val colorAcento: Color,
    val actions: List<String>,
)

@Composable
fun ProtocolItem(protocol: Protocol, isDarkMode: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
        ),
        elevation = CardDefaults.cardElevation(if (isDarkMode) 0.dp else 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 🎯 Se agregó contentAlignment para centrar el icono en el círculo
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = protocol.colorAcento, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = protocol.icono,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = protocol.type,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122)
                )

                Spacer(modifier = Modifier.height(4.dp))

                protocol.actions.forEach { accion ->
                    Text(
                        text = "• $accion",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = if (isDarkMode) Color.White.copy(alpha = 0.7f) else Color.DarkGray,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProtocolsScreen() {
    val context = LocalContext.current
    val themeManager = remember { ThemeManager(context) }
    val isDarkMode by themeManager.isDarkModeFlow.collectAsState(initial = false)

    val protocolsList = listOf(
        Protocol(
            type = "Incendio",
            icono = Icons.Default.Whatshot,
            colorAcento = Color(0xFF830122),
            actions = listOf(
                "Active la alarma manual más cercana inmediatamente.",
                "Evacue por las rutas señaladas, no use elevadores.",
                "Diríjase al punto de reunión exterior y reporte su estado."
            )
        ),
        Protocol(
            type = "Terremoto",
            icono = Icons.Default.Warning,
            colorAcento = Color(0xFF005691),
            actions = listOf(
                "Agáchese, cúbrase debajo de una mesa y sujétese.",
                "Aléjese de ventanas, estanterías y objetos colgantes.",
                "Permanezca en el interior hasta que cese el movimiento."
            )
        ),
        Protocol(
            type = "Tiroteo Activo",
            icono = Icons.Default.Shield,
            colorAcento = Color(0xFFB30000),
            actions = listOf(
                "Corra si hay una ruta de escape segura disponible.",
                "Escóndase en un lugar seguro, bloquee puertas y silencie móviles.",
                "Luche solo como último recurso si su vida corre peligro."
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color(0xFF121212) else Color(0xFFF8F9FA))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Protocolos de emergencia",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = if (isDarkMode) Color(0xFFE63946) else Color(0xFF830122)
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Actúe con calma y rapidez según las siguientes directrices institucionales.",
            fontSize = 14.sp,
            color = if (isDarkMode) Color.White.copy(alpha = 0.6f) else Color.Gray,
        )

        Spacer(Modifier.height(20.dp))

        // 🎯 Corrección: Se eliminó el bucle duplicado
        protocolsList.forEach { item ->
            ProtocolItem(protocol = item, isDarkMode = isDarkMode)
            Spacer(Modifier.height(16.dp))
        }

        /* 🏢 COMENTADO POR EL MOMENTO: Banner de "CAMPUS SEGURO"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.campus_image),
                contentDescription = "Vista del campus",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Surface(
                color = Color(0xCC830122),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "CAMPUS SEGURO",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 2.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        */
    }
}
