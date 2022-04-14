package com.example.cupcake

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cupcake.model.OrderViewModel
import org.junit.Assert
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class ViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = androidx.arch.core.executor.testing.InstantTaskExecutorRule()

    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }

    @Test
    fun price_twelve_cupcakes() {
        val viewModel = OrderViewModel()
//        viewModel.price.observeForever {}
        viewModel.setQuantity(12)
        assertEquals("\\27.00", viewModel.price.value)
    }

}