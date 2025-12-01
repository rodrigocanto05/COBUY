package pt.iade.ei.cobuy.network.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.repository.SavedPlacesRepository
import pt.iade.ei.cobuy.storage.model.SavedPlace

class SavedPlaceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SavedPlacesRepository(application)

    val savedPlaces = repository.getSavedPlaces()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun save(place: SavedPlace) {
        viewModelScope.launch {
            repository.save(place)
        }
    }

    fun remove(place: SavedPlace) {
        viewModelScope.launch {
            repository.remove(place)
        }
    }
}