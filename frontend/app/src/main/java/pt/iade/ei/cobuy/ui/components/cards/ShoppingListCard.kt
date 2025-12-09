package pt.iade.ei.cobuy.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@Composable
fun ShoppingListCard(
    list: ShoppingList,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(0.8f)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.bg_list_card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, bottom = 10.dp, end = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(22.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.spiral_rings),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(0.7f),
                        contentScale = ContentScale.FillHeight
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp, bottom = 30.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = list.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OrangePrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    list.createdAt?.let {
                        Text(
                            text = "Criada em: ${it.substring(0, 10)}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextDark.copy(alpha = 0.9f),
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_list_delete), // cria este drawable
                    contentDescription = "Apagar lista",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListCardPreview() {
    COBUYTheme {
        ShoppingListCard(
            list = ShoppingList(
                id = 1,
                title = "Churrasco Rapazes",
                createdAt = "2025-12-05"
            ),
            onClick = {},
            onDelete = {}
        )
    }
}
