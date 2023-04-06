package com.example.product_presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation_common.state.UiState
import com.example.product_presentation.model.ProductUi
import com.example.product_presentation.model.RatingUi
import com.example.products_domain.entity.Result
import com.example.products_domain.entity.Result.Error
import com.example.products_domain.entity.product.Product
import com.example.products_domain.usecase.GetProductsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val useCase:GetProductsListUseCase,
):ViewModel() {
    private val _productListFlow = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val productListFlow = _productListFlow

    fun loadProducts(){
        viewModelScope.launch {
            useCase.execute(GetProductsListUseCase.Request)
                .map {
                    convert(it)
                }.collect { _productListFlow.value = it }
        }
    }

    private fun convert(result:Result<GetProductsListUseCase.Response>):UiState<List<Product>>{
        return when (result){
            is Error -> {
                UiState.Error(result.Exception.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UiState.Success(result.data.data)
            }
        }
    }

    fun convertToUiModel(product: Product): ProductUi {
        return ProductUi(product.category,product.description,product.id,product.image,product.price,
            RatingUi(product.rating.count,product.rating.rate),product.title
        )
    }
}