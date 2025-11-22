package hackathon.picky.core.model

import androidx.annotation.DrawableRes
import com.example.core.designsystem.R

enum class Category(val label: String, @DrawableRes val iconResId: Int) {
    FINANCE("금융", R.drawable.ic_money),
    EDUCATION("교육", R.drawable.ic_book),
    RESIDENCE("주거", R.drawable.ic_fire),
    EMPLOYMENT("취업", R.drawable.ic_document),
    TOP("탑 배너", R.drawable.logo)
}
