package edu.programmingclasses2024winter

import android.app.Application
import androidx.room.Room
import edu.programmingclasses2024winter.persistence.Database

class MyApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    database = Room.databaseBuilder(this, Database::class.java, "database.db")
      .fallbackToDestructiveMigration()
      .fallbackToDestructiveMigrationOnDowngrade()
      .build()
  }

  companion object {
    lateinit var database: Database
  }
}
