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

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                _savedPlaces.value = repo.getSavedPlaces()
            } catch (e: Exception) {
                Log.e("SavedPlaceVM", "Erro ao carregar favoritos", e)
            }
        }
    }

    fun save(supermarketId: Int, onResult: (SaveResult) -> Unit) {
        viewModelScope.launch {
            try {
                repo.savePlace(supermarketId)
                onResult(SaveResult.ADDED)

            } catch (e: HttpException) {
                when (e.code()) {
                    409 -> {
                        Log.w("SavedPlaceVM", "Já está nos favoritos")
                        onResult(SaveResult.ALREADY_EXISTS)
                    }
                    404 -> {
                        Log.w("SavedPlaceVM", "Supermercado não encontrado")
                        onResult(SaveResult.ERROR)
                    }
                    else -> {
                        Log.e("SavedPlaceVM",
                            "Erro HTTP inesperado: ${e.code()}", e)
                        onResult(SaveResult.ERROR)
                    }
                }

            } catch (e: Exception) {
                Log.e("SavedPlaceVM", "Erro ao guardar favorito", e)
                onResult(SaveResult.ERROR)
            }

            load()
        }
    }

    fun remove(id: Int) {
        viewModelScope.launch {
            try {
                repo.deletePlace(id)

            } catch (e: HttpException) {
                when (e.code()) {
                    404 -> Log.w("SavedPlaceVM", "Favorito já não existia")
                    403 -> Log.w("SavedPlaceVM", "Não pertence ao utilizador")
                    else -> Log.e("SavedPlaceVM", "Erro ao remover favorito: ${e.code()}")
                }
            } catch (e: Exception) {
                Log.e("SavedPlaceVM", "Erro inesperado ao remover favorito", e)
            }

            load()
        }
    }
}