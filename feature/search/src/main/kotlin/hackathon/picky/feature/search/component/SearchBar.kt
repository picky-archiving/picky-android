package hackathon.picky.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray50
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.PretendardFontFamily

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onChangeQuery: (String) -> Unit,
    onClickSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onChangeQuery,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp) // 전체 TextField 높이를 유지하면서 여유 공간 확보
            .clip(RoundedCornerShape(8.dp)),
        placeholder = { Text("검색어를 입력하세요") },
        leadingIcon = { Icon(painterResource(R.drawable.search), contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onChangeQuery("") }) {
                    Icon(painterResource(R.drawable.cancel), contentDescription = null)
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray50,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(fontSize = 14.sp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onClickSearch() }
        )
    )
}


@Composable
@Preview
fun SearchBarPreview() {
    SearchBar(
        "Sample Search",
        onChangeQuery = { },
        onClickSearch = {},
    )
}