package com.example.storeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation_common.navigation.NavRoutes
import com.example.product_presentation.model.ProductUi
import com.example.product_presentation.screen.ProductDetails
import com.example.product_presentation.screen.ProductListView
import com.example.storeapp.ui.theme.StoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController =
                        rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(
        navController, startDestination = NavRoutes.Products.route
    ) {
        composable(route = NavRoutes.Products.route) {
            ProductListView(viewModel = hiltViewModel(), navController = navController)
        }

        composable(route = NavRoutes.ProductDetails.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<ProductUi>("selectedProduct")
            ProductDetails(result)
        }
    }
}