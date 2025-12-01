package pt.iade.ei.cobuy.network.repository

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import pt.iade.ei.cobuy.storage.utils.SavedPlacesDatabase
import pt.iade.ei.cobuy.storage.model.SavedPlace

class SavedPlacesRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        SavedPlacesDatabase::class.java,
        "saved_places.db"
    ).build()

    private val dao = db.savedPlacesDao()

    fun getSavedPlaces(): Flow<List<SavedPlace>> = dao.getAll()

    suspend fun save(place: SavedPlace) = dao.insert(place)

    suspend fun remove(place: SavedPlace) = dao.delete(place)
}