package br.com.antoniojoseuchoa.minhasreceitas.data.mapper

import br.com.antoniojoseuchoa.minhasreceitas.data.entity.RecipeEntity
import br.com.antoniojoseuchoa.minhasreceitas.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity(
    id=id,
    name=name
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id=id,
    name=name
)

