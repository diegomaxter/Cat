package com.example.myapplication.domain

interface CatsRepository {
    suspend fun fetchCats(): List<Cat>
}
