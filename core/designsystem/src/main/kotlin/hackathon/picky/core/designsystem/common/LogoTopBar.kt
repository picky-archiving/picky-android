package hackathon.picky.core.designsystem.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray50

@Composable
fun LogoTopBar(
    onClickSearch: () -> Unit,
    onClickMy: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 로고 (왼쪽)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.weight(1f))

        // Search 아이콘
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(), // Material3 ripple
                    onClick = { onClickSearch() }
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        // My 아이콘
        Image(
            painter = painterResource(id = R.drawable.mypage),
            contentDescription = "My",
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = { onClickMy() }
                )
        )
    }
}

@Composable
@Preview
fun LogoTopBarPreview() {
    LogoTopBar(
        onClickSearch = {},
        onClickMy = {}
    )
}
