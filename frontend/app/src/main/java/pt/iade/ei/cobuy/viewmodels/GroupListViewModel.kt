package pt.iade.ei.cobuy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.api.CoBuyRepository
import pt.iade.ei.cobuy.model.Group

class GroupListViewModel(private val repository: CoBuyRepository = CoBuyRepository()) : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    fun fetchUserGroups(userId: Int) {
        viewModelScope.launch {
            _groups.value = repository.getUserGroups(userId)
        }
    }
}
