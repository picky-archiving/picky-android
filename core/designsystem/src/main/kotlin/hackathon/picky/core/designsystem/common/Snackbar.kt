package hackathon.picky.core.designsystem.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.White

@Composable
fun PickySnackbar(
    message: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 13.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = if (isError) R.drawable.error_with_circle
                    else R.drawable.check_with_circle
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = message,
                fontFamily = PretendardFontFamily,
                fontSize = 15.sp,
                color = Gray800
            )
        }
    }
}