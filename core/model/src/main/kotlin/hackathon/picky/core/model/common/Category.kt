package hackathon.picky.core.model.common

import androidx.annotation.DrawableRes
import com.example.core.designsystem.R

enum class Category(val label: String, @DrawableRes val iconResId: Int, val description: String) {
    FINANCE("금융", R.drawable.ic_money, description = "사회초년생들을 위한 금융 지원 모음"),
    EDUCATION("교육", R.drawable.ic_book, description = "직업부터 창업까지 필요한 교육 정책 모음"),
    RESIDENCE("주거", R.drawable.ic_fire, description = "내 집 마련을 위한 관련 지원 정책 모음"),
    EMPLOYMENT("취업", R.drawable.ic_document, description = "청년 취준생들을 위한 취업 관련 정보 모음"),
    TOP("Picky's 소득분위별 추천", R.drawable.logo, description = "");

    companion object {
        fun fromLabel(label: String): Category {
            return values().firstOrNull { it.label == label } ?: TOP
        }
    }
}
