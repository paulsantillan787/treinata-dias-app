package com.example.a30diasdebienestar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30diasdebienestar.model.Data.sugerencias
import com.example.a30diasdebienestar.model.Sugerencia
import com.example.a30diasdebienestar.ui.theme._30DiasDeBienestarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DiasDeBienestarTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { it ->
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            itemsIndexed(sugerencias) { index, sugerencia ->
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .shadow(
                            2.dp,
                            ambientColor = MaterialTheme.colorScheme.secondary,
                            spotColor = MaterialTheme.colorScheme.secondary
                        )
                        .background(MaterialTheme.colorScheme.primaryContainer),
                ) {
                    AppCard(index + 1, sugerencia = sugerencia)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 36.sp
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Composable
fun AppCard(dia: Int, sugerencia: Sugerencia, modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            )
            .padding(12.dp)
    ) {
        Text(
            text = "Day $dia",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = stringResource(sugerencia.frase),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Button(
            onClick = { clicked = !clicked },
            shape = RectangleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(sugerencia.image),
                contentDescription = null
            )
        }
        if (clicked) {
            Text(
                text = stringResource(sugerencia.descripcion),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
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
    _30DiasDeBienestarTheme {
        App()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewDark() {
    _30DiasDeBienestarTheme(darkTheme = true) {
        App()
    }
}