package com.example.presentation_common.navigation

abstract class NavRoutes(
    val route: String,
) {
    object Products : NavRoutes("/products")
    object ProductDetails : NavRoutes("/products/details")

}