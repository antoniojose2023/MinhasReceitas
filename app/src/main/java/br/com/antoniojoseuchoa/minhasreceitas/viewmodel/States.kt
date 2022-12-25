package br.com.antoniojoseuchoa.minhasreceitas.viewmodel

import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain

sealed interface States {
    object Loader: States
    object Empty: States
    class OnSucess(val list: List<RecipeDomain>): States
    class OnErro(val erro: String): States
}