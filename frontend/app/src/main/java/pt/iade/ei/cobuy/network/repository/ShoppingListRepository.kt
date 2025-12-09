package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.network.api.ShoppingListApi
import pt.iade.ei.cobuy.storage.model.ShoppingList

class ShoppingListRepository(
    private val api: ShoppingListApi
) {

    suspend fun getListsForGroup(groupId: Int, userId: Int): List<ShoppingList> {
        val response = api.getListsByGroup(groupId, userId)
        if (!response.isSuccessful) {
            throw Exception("Erro ao carregar listas: ${response.code()}")
        }
        return response.body() ?: emptyList()
    }

    suspend fun createList(
        groupId: Int,
        title: String,
        userId: Int
    ): ShoppingList {
        val body = ShoppingListApi.CreateListBody(
            title = title,
            group_id = groupId
        )

        val response = api.createList(body, userId)
        if (!response.isSuccessful) {
            throw Exception("Erro ao criar lista: ${response.code()}")
        }
        return response.body() ?: throw Exception("Resposta vazia ao criar lista")
    }

    suspend fun deleteList(listId: Int, userId: Int) {
        val response = api.deleteList(listId, userId)
        if (!response.isSuccessful) {
            throw Exception("Erro ao apagar lista: ${response.code()}")
        }
    }
}
