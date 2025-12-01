package pt.iade.ei.cobuy.storage.utils

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pt.iade.ei.cobuy.storage.model.SavedPlace

@Dao
interface SavedPlacesDao {

    @Query("SELECT * FROM saved_places")
    fun getAll(): Flow<List<SavedPlace>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: SavedPlace)

    @Delete
    suspend fun delete(place: SavedPlace)
}