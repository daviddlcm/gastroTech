package com.example.gastrotech.home.domain

import com.example.gastrotech.home.data.model.ComidaRequest
import com.example.gastrotech.home.data.repository.ComidaRepository

class GetComidasUseCase {
    private val  repository = ComidaRepository()

    suspend operator fun invoke(): Result<List<ComidaRequest>> {
        return repository.getComidas()
    }
}