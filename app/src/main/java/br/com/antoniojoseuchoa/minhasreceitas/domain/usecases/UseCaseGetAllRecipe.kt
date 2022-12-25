package br.com.antoniojoseuchoa.minhasreceitas.domain.usecases

import br.com.antoniojoseuchoa.minhasreceitas.data.repository.RepositoryRecipeImpl

class UseCaseGetAllRecipe constructor(val repositoryRecipeImpl: RepositoryRecipeImpl) {

    suspend operator fun invoke() = repositoryRecipeImpl.getAll()
}