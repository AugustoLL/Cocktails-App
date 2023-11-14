package com.example.cocktails.ui.test

import com.example.cocktails.data.FakeDataSource
import com.example.cocktails.data.FakeRepository
import com.example.cocktails.ui.home.HomeViewModel
import org.junit.Test

class HomeViewModelTest {
    private val homeViewModel = HomeViewModel(FakeRepository())

    @Test
    fun homeViewModel_deleteCocktail() {
//        homeViewModel.deleteCocktail(FakeDataSource.fakeCocktail1)
    }
}