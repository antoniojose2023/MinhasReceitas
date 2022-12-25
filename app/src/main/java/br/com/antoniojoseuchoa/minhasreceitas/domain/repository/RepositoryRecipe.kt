package br.com.antoniojoseuchoa.minhasreceitas.domain.repository

import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain

interface RepositoryRecipe {
    suspend fun getAll(): List<RecipeDomain>
    suspend fun addRecipe(recipeDomain: RecipeDomain)
}