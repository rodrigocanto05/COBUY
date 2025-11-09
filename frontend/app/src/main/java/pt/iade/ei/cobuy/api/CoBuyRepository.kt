package pt.iade.ei.cobuy.api

import pt.iade.ei.cobuy.model.Group
import pt.iade.ei.cobuy.model.ListItem

class CoBuyRepository {
    private val apiService = CoBuyAPIService.getInstance()

    suspend fun getUserGroups(userId: Int): List<Group> {
        return apiService.getUserGroups(userId)
    }

    suspend fun getGroupItems(groupId: Int): List<ListItem> {
        return apiService.getGroupItems(groupId)
    }
}
