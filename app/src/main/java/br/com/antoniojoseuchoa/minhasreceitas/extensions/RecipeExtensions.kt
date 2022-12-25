package br.com.antoniojoseuchoa.minhasreceitas.extensions

import android.content.Context
import androidx.room.Room
import br.com.antoniojoseuchoa.minhasreceitas.data.AppDatabase

val Context.db: AppDatabase
            get() = Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "recipe.db"
            ).build()
