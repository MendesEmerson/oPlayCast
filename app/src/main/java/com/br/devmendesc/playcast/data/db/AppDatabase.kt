package com.br.devmendesc.playcast.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.br.devmendesc.playcast.domain.models.entities.EpisodeEntity
import com.br.devmendesc.playcast.data.service.EpisodeDao
import com.br.devmendesc.playcast.data.service.PodcastDao
import com.br.devmendesc.playcast.domain.models.entities.PodcastEntity

@Database(entities = [EpisodeEntity::class, PodcastEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao
    abstract fun podcastDao(): PodcastDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
