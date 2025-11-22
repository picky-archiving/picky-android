package hackathon.picky.core.data.model.policy

import hackathon.picky.core.model.common.Category
import java.time.LocalDate

data class PolicyEntity(
    val id: Long,
    val title: String,
    val category: Category,
    val endDate: LocalDate?,    // 상시 모집이면 null
    val imageUrl: String,
    val viewCount: Long // null이면 0
)