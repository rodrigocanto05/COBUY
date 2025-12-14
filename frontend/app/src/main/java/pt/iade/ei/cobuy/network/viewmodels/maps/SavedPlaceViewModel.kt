package pt.iade.ei.cobuy.network.viewmodels.maps

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.repository.SavedPlacesRepository
import pt.iade.ei.cobuy.storage.model.SavedPlace
import retrofit2.HttpException

enum class SaveResult {
    ADDED,
    ALREADY_EXISTS,
    ERROR
}

class SavedPlaceViewModel(
    private val repo: SavedPlacesRepository = SavedPlacesRepository()
) : ViewModel() {

    private val _savedPlaces = MutableStateFlow<List<SavedPlace>>(emptyList())
    val savedPlaces: StateFlow<List<SavedPlace>> = _savedPlaces

    fun load() {
        viewModelScope.launch {
            try {
                _savedPlaces.value = repo.getSavedPlaces()
            } catch (e: Exception) {
                _savedPlaces.value = emptyList()
            }
        }
    }

    fun clear() {
        _savedPlaces.value = emptyList()
    }

    fun save(supermarketId: Int, onResult: (SaveResult) -> Unit) {
        viewModelScope.launch {
            try {
                repo.savePlace(supermarketId)
                load()
                onResult(SaveResult.ADDED)
            } catch (e: HttpException) {
                when (e.code()) {
                    409 -> onResult(SaveResult.ALREADY_EXISTS)
                    else -> onResult(SaveResult.ERROR)
                }
            } catch (e: Exception) {
                onResult(SaveResult.ERROR)
            }
        }
    }

    fun remove(id: Int) {
        viewModelScope.launch {
            _savedPlaces.value = _savedPlaces.value.filterNot { it.id == id }
            try {
                repo.deletePlace(id)
            } catch (e: Exception) {
                load()
            }
        }
    }
}