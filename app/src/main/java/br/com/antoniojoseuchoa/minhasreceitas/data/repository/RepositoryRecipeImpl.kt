package br.com.antoniojoseuchoa.minhasreceitas.data.repository

import br.com.antoniojoseuchoa.minhasreceitas.data.dao.RecipeDao
import br.com.antoniojoseuchoa.minhasreceitas.data.mapper.toDomain
import br.com.antoniojoseuchoa.minhasreceitas.data.mapper.toEntity
import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain
import br.com.antoniojoseuchoa.minhasreceitas.domain.repository.RepositoryRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryRecipeImpl(val dao: RecipeDao): RepositoryRecipe {
    override suspend fun getAll(): List<RecipeDomain> = withContext(Dispatchers.IO){
         dao.getRepice().map {
             it.toDomain()
         }
    }


    override suspend fun addRecipe(recipeDomain: RecipeDomain) = withContext(Dispatchers.IO){
        dao.addRecipe(recipeDomain.toEntity())
    }
}