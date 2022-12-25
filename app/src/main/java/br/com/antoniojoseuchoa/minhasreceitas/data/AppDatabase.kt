package br.com.antoniojoseuchoa.minhasreceitas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.antoniojoseuchoa.minhasreceitas.data.dao.RecipeDao
import br.com.antoniojoseuchoa.minhasreceitas.data.entity.RecipeEntity

@Database(
    entities = [ RecipeEntity::class ], version = 1
)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun dao(): RecipeDao
}