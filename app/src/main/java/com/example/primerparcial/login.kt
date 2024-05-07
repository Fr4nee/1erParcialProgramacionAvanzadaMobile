package com.example.primerparcial
import HomePage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primerparcial.ui.theme.PrimerParcialTheme

@Composable
fun Main(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    Scaffold(
        modifier = Modifier
    ) {
        MainNavHost(
            modifier = Modifier.padding(it),
            navHostController = navHostController,
        )
    }
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(navHostController, modifier = modifier)
        }
        composable("home") {
            HomePage(
                usuario = "Pedro Pe"
            )
        }
    }
}
@Composable
fun Login(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val usuarioIngresado = remember { mutableStateOf("") }
    val contraseñaIngresada = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login Page",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = usuarioIngresado.value,
            onValueChange = { usuarioIngresado.value = it },
            label = { Text("Usuario") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = contraseñaIngresada.value,
            onValueChange = { contraseñaIngresada.value = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                val usuario = usuarioIngresado.value
                val contraseña = contraseñaIngresada.value
                if (ValidarLogin(usuario, contraseña)) {
                    error.value = false
                    navHostController.navigate("home")
                } else {
                    error.value = true
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Iniciar sesión")
        }
        if (error.value) {
            Text(
                text = "Usuario o contraseña incorrectos!",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

fun ValidarLogin(usuario: String, contraseña: String): Boolean {
    val usuarioAValidar = "pedro@pe.com.ar"
    val contraseñaAValidar = "abc123"
    return usuario == usuarioAValidar && contraseña == contraseñaAValidar
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    PrimerParcialTheme {
        Main()
    }
}





