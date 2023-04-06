package com.example.product_presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation_common.navigation.NavRoutes
import com.example.presentation_common.screen.CommonScreen
import com.example.product_presentation.list.ProductsListViewModel
import com.example.products_domain.entity.product.Product

@Composable
fun productListView(viewModel: ProductsListViewModel, navController: NavController) {
    viewModel.loadProducts()
    viewModel.productListFlow.collectAsState().value.let {
        state ->
        CommonScreen(state = state, onSuccess = {
            list ->
            productList(productList = list
                , onClick = {
                product->
                navController.navigate(NavRoutes.ProductsDetails(product.id).route)
            })
        })
    }
}

@Composable
fun productList(productList:List<Product>,onClick:(product:Product)->Unit) {
    LazyColumn(modifier = Modifier.padding(16.dp)){

        items(productList){
            product->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onClick(product)
                    }
            ) {
                Text(text = product.title)
                Text(text = product.description)
            }
        }
        }
    }

@Composable
fun productDetails(viewModel: ProductsListViewModel,navController: NavController,productId:Int) {

}