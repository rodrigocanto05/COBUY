package pt.iade.ei.cobuy.storage.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.iade.ei.cobuy.storage.model.SavedPlace

@Database(
    entities = [SavedPlace::class],
    version = 1,
    exportSchema = false
)
abstract class SavedPlacesDatabase : RoomDatabase() {
    abstract fun savedPlacesDao(): SavedPlacesDao
}