package pt.iade.ei.cobuy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.api.LocationRepository
import pt.iade.ei.cobuy.model.Location

class LocationViewModel(private val repository: LocationRepository = LocationRepository()) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations

    fun fetchSavedLocations() {
        viewModelScope.launch {
            _locations.value = repository.getSavedLocations()
        }
    }
}
