package pt.iade.ei.cobuy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.api.CoBuyRepository
import pt.iade.ei.cobuy.model.ListItem

class GroupDetailViewModel(private val repository: CoBuyRepository = CoBuyRepository()) : ViewModel() {

    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items: StateFlow<List<ListItem>> = _items

    fun fetchGroupItems(groupId: Int) {
        viewModelScope.launch {
            _items.value = repository.getGroupItems(groupId)
        }
    }
}
