package hackathon.picky.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackathon.picky.core.designsystem.common.CommonListItemBox
import hackathon.picky.core.designsystem.common.CommonListType
import hackathon.picky.core.model.common.CommonListItem


@Composable
fun SearchListVerticalGridScroll(
    list: List<CommonListItem>,
    onClickDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(list) { item ->
            CommonListItemBox(
                type = CommonListType.DYNAMIC,
                imageUrl = item.imageUrl,
                title = item.title,
                closingDate = item.closingDate,
                onClickDetail = onClickDetail,
                id = item.id,
            )

        }
    }
}