package br.com.antoniojoseuchoa.minhasreceitas.domain.usecases

import br.com.antoniojoseuchoa.minhasreceitas.data.repository.RepositoryRecipeImpl
import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain

class UseCaseAddRecipe constructor(val repositoryRecipeImpl: RepositoryRecipeImpl) {
    suspend operator fun invoke(recipeDomain: RecipeDomain) = repositoryRecipeImpl.addRecipe(recipeDomain)
}