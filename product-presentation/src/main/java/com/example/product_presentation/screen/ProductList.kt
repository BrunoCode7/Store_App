package com.example.product_presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation_common.navigation.NavRoutes
import com.example.presentation_common.screen.CommonScreen
import com.example.product_presentation.list.ProductsListViewModel
import com.example.product_presentation.model.ProductUi
import com.example.products_domain.entity.product.Product

@Composable
fun ProductListView(viewModel: ProductsListViewModel, navController: NavController) {
    viewModel.loadProducts()
    viewModel.productListFlow.collectAsState().value.let {
        state ->
        CommonScreen(state = state, onSuccess = {
            list ->
            productList(productList = list
                , onClick = {
                product->
                    navController.currentBackStackEntry?.savedStateHandle?.set("selectedProduct",viewModel.convertToUiModel(product))
                    navController.navigate(NavRoutes.ProductDetails.route)
            })
        })
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun productList(productList:List<Product>,onClick:(product:Product)->Unit) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)){

        items(productList){
            product->
            Card(
                modifier = Modifier
                    .clickable {
                        onClick(product)
                    }
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15),
                elevation = 10.dp
            ) {
                Row(
                verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(110.dp)
                        .padding(8.dp)){
                        GlideImage(model = product.image, contentDescription = null, modifier = Modifier.fillParentMaxSize())
                    }
                    Column {
                        Text(text = product.title)
                        Text(text = "Price:${product.price}")
                    }
                }
            }
        }
        }
    }

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetails(product: ProductUi?) {

    if (product!=null){
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)){
                GlideImage(model = product.image, contentDescription = null, modifier = Modifier.fillMaxSize())
                Box(
                    Modifier
                        .size(60.dp)
                        .align(Alignment.TopEnd)){
                    Icon(Icons.Filled.Star,
                        "",
                        tint = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxSize())
                    Text(text  = product.rating.rate.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
            }

            Text(
                text  = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text  = "Price:${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text  = "Description",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text  = product.description,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }else{
        Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Text(text = "Something wrong happened")
        }
    }


}