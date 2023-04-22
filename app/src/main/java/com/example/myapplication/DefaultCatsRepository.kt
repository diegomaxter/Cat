package com.example.myapplication

import com.example.myapplication.data.remote.CatsApi
import com.example.myapplication.data.remote.CatsResponse
import com.example.myapplication.domain.Cat
import com.example.myapplication.domain.CatsRepository

private fun List<CatsResponse>.toCats(): List<Cat> = this.map {
    Cat(
        id = it.id,
        breedName = it.breedName,
        origin = it.origin,
        referenceId = it.referenceId
    )
}

class DefaultCatsRepository(
    private val api: CatsApi
) : CatsRepository {
    override suspend fun fetchCats(): List<Cat> {
        return try {
            api.fetchCats().toCats()
        } catch (ex: Exception) {
            emptyList()
        }
    }

}