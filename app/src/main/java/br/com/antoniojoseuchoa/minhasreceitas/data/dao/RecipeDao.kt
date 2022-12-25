package br.com.antoniojoseuchoa.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import br.com.antoniojoseuchoa.minhasreceitas.data.entity.RecipeEntity


@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    fun getRepice() : List<RecipeEntity>

    @Insert
    fun addRecipe(recipe: RecipeEntity)
}