package hackathon.picky.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hackathon.picky.core.designsystem.common.CommonListItem
import hackathon.picky.core.designsystem.common.CommonListType
import hackathon.picky.feature.home.model.HomeListItem
import hackathon.picky.feature.home.model.HomeUiTest

@Composable
fun ListScroll(
    list: List<HomeListItem>,
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
                CommonListItem(
                    type = CommonListType.DYNAMIC,
                    imageRes = item.imageRes,
                    title = item.title,
                    closingDate = item.closingDate,
                    onClickDetail = onClickDetail,
                    id = item.id,
                )

        }
    }
}

@Composable
@Preview
fun ListScrollPreview() {
    ListScroll(
        list = HomeUiTest.infoSectionList[0].infoList,
        onClickDetail = {}
    )
}