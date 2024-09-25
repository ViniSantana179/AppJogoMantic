package com.example.aula_25_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aula_25_09.ui.theme.Aula_25_09Theme

data class Jogador(
    var nome: String = "",
    var level: Int = 1,
    var equipamento: Int = 0,
    var modificadores: Int = 0
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game()
        }
    }
}

@Composable
fun Game() {
    var jogadores = remember {
        mutableStateListOf<Jogador>()
    }

    // Inicializando a lista de jogadores apenas uma vez
    if (jogadores.isEmpty()) {
        repeat(6) {
            jogadores.add(Jogador("", 1, 0, 0))
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(jogadores) { jogador ->
            JogadorCard(jogador = jogador)
        }
    }
}

@Composable
fun JogadorCard(jogador: Jogador) {
    var nome by remember { mutableStateOf(jogador.nome) }
    var level by remember { mutableStateOf(jogador.level) }
    var equipamento by remember { mutableStateOf(jogador.equipamento) }
    var modificadores by remember { mutableStateOf(jogador.modificadores) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = androidx.compose.ui.graphics.Color.LightGray)
            .padding(16.dp)
            .border(1.dp, androidx.compose.ui.graphics.Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Jogador: ") }
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Poder: ${equipamento + level + modificadores}")

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (level > 1) level -= 1
            }) {
                Text(text = "-")
            }

            Text(text = "Level: $level", modifier = Modifier.padding(horizontal = 16.dp))

            Button(onClick = {
                if (level < 10) level += 1
            }) {
                Text(text = "+")
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (equipamento > 0) equipamento -= 1
            }) {
                Text(text = "-")
            }

            Text(
                text = "Bônus Equipamento: $equipamento",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(onClick = {
                if (equipamento < 99) equipamento += 1
            }) {
                Text(text = "+")
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (modificadores > -10) modificadores -= 1
            }) {
                Text(text = "-")
            }

            Text(
                text = "Modificadores: $modificadores",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(onClick = {
                if (modificadores < 10) modificadores += 1
            }) {
                Text(text = "+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Game()
}
