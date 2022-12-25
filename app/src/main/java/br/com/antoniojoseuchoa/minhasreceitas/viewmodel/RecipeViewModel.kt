package br.com.antoniojoseuchoa.minhasreceitas.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.antoniojoseuchoa.minhasreceitas.data.repository.RepositoryRecipeImpl
import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain
import br.com.antoniojoseuchoa.minhasreceitas.domain.usecases.UseCaseAddRecipe
import br.com.antoniojoseuchoa.minhasreceitas.domain.usecases.UseCaseGetAllRecipe
import br.com.antoniojoseuchoa.minhasreceitas.extensions.db
import kotlinx.coroutines.launch

class RecipeViewModel(private val useCaseGetAllRecipe: UseCaseGetAllRecipe, private val useCaseAddRecipe: UseCaseAddRecipe): ViewModel() {

    val states: LiveData<States> = liveData {
        emit(States.Loader)

        val state = try {
            val list = useCaseGetAllRecipe()
            if (list.isEmpty()) {
                States.Empty
            } else {
                States.OnSucess(list)
            }
        } catch (ex: Exception) {
            States.OnErro("Erro ao retornar as receitas")
        }

        emit(state)
    }

    fun insert(name: String) = viewModelScope.launch {
        useCaseAddRecipe(RecipeDomain(name = name))
    }

    class Factory: ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repositoryRecipeImpl =  RepositoryRecipeImpl(application.db.dao())
            return RecipeViewModel(
                useCaseGetAllRecipe = UseCaseGetAllRecipe(repositoryRecipeImpl),
                useCaseAddRecipe = UseCaseAddRecipe(repositoryRecipeImpl)
            ) as T
        }
    }
}