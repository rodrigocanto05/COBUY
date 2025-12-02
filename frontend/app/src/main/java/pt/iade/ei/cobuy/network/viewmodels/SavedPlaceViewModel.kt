package pt.iade.ei.cobuy.network.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.repository.SavedPlacesRepository
import pt.iade.ei.cobuy.storage.model.SavedPlace

class SavedPlaceViewModel(
    private val repo: SavedPlacesRepository = SavedPlacesRepository()
) : ViewModel() {

    private val _savedPlaces = MutableStateFlow<List<SavedPlace>>(emptyList())
    val savedPlaces: StateFlow<List<SavedPlace>> = _savedPlaces

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _savedPlaces.value = repo.getSavedPlaces()
        }
    }

    fun save(supermarketId: Int) {
        viewModelScope.launch {
            repo.savePlace(supermarketId)
            load()
        }
    }

    fun remove(id: Int) {
        viewModelScope.launch {
            repo.deletePlace(id)
            load()
        }
    }
}