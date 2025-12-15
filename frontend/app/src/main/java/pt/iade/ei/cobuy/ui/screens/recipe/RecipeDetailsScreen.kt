package pt.iade.ei.cobuy.ui.screens.recipe

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.iade.ei.cobuy.network.api.recipe.RecipeApiProvider
import pt.iade.ei.cobuy.network.models.RecipeIngredientDto
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.network.viewmodels.lists.UserListsViewModel
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.dialogs.SelectListDialog
import pt.iade.ei.cobuy.ui.components.dialogs.ShoppingListUi
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.screens.previews.ListItemsScreenUi
import pt.iade.ei.cobuy.ui.screens.previews.RecipeDetailScreenUi
import pt.iade.ei.cobuy.viewmodel.recipe.RecipeIngredientsViewModel
import pt.iade.ei.cobuy.viewmodel.recipe.RecipeIngredientsViewModelFactory

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipeId: Int
) {
    val context = LocalContext.current

    val userId = SessionViewModel.currentUserId
    if (userId == null) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Sessão inválida. Faz login novamente.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
        return
    }

    val vm: RecipeIngredientsViewModel = viewModel(
        factory = RecipeIngredientsViewModelFactory(RecipeApiProvider.service)
    )

    val listsVm: UserListsViewModel = viewModel()
    val listsState by listsVm.uiState.collectAsState()

    val extras = remember(recipeId) { recipeExtras(recipeId) }
    val ingredients by vm.ingredients.collectAsState(initial = emptyList())
    val error by vm.error.collectAsState(initial = null)

    LaunchedEffect(recipeId) { vm.load(recipeId) }
    LaunchedEffect(userId) { listsVm.loadUserLists(userId) }

    val userListsUi: List<ShoppingListUi> = remember(listsState.lists) {
        listsState.lists.map { sl ->
            val groupName = sl.group?.name ?: "Sem grupo"
            ShoppingListUi(
                id = sl.id,
                title = "$groupName • ${sl.title}"
            )
        }
    }

    val title = remember(recipeId) { "Receita #$recipeId" }

    var showListDialog by remember { mutableStateOf(false) }
    var selectedIngredient by remember { mutableStateOf<RecipeIngredientDto?>(null) }

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = title,
                navController = navController,
                showBackButton = true
            )
        },
        bottomBar = { CoBuyBottomBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = extras.imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (error != null) {
                    Text(text = error ?: "", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (ingredients.isEmpty()) {
                    Text(text = "Sem ingredientes.")
                } else {
                    ingredients.forEach { ing ->
                        IngredientRow(
                            ing = ing,
                            onAddClick = {
                                when {
                                    listsState.isLoading -> {
                                        Toast.makeText(context, "A carregar listas...", Toast.LENGTH_SHORT).show()
                                        return@IngredientRow
                                    }
                                    listsState.error != null -> {
                                        Toast.makeText(context, listsState.error ?: "Erro ao carregar listas", Toast.LENGTH_SHORT).show()
                                        return@IngredientRow
                                    }
                                    userListsUi.isEmpty() -> {
                                        Toast.makeText(context, "Ainda não tens listas.", Toast.LENGTH_SHORT).show()
                                        return@IngredientRow
                                    }
                                }

                                selectedIngredient = ing
                                showListDialog = true
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Modo de preparação",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                extras.preparationSteps.forEachIndexed { index, step ->
                    Text(text = "${index + 1}. $step")
                }
            }
        }
    }

    SelectListDialog(
        show = showListDialog,
        lists = userListsUi,
        onDismiss = {
            showListDialog = false
            selectedIngredient = null
        },
        onSelect = { list ->
            val ing = selectedIngredient ?: return@SelectListDialog
            showListDialog = false
            selectedIngredient = null

            vm.addOneIngredient(
                recipeId = recipeId,
                listId = list.id,
                userId = userId,
                ingredientRgiId = ing.id,
                onSuccess = {
                    Toast.makeText(
                        context,
                        "${ing.ingredient} adicionado à lista \"${list.title}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onFailure = { msg ->
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            )
        }
    )
}

@Composable
private fun IngredientRow(
    ing: RecipeIngredientDto,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = ing.ingredient)
            Text(text = "${ing.qty} ${ing.unit}")
        }

        IconButton(onClick = onAddClick) {
            Icon(
                imageVector = Icons.Default.AddShoppingCart,
                contentDescription = "Adicionar"
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenUiPreview() {
    RecipeDetailScreenUi()
}

