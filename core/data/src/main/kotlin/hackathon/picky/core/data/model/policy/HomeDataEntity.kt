package hackathon.picky.core.data.model.policy

import hackathon.picky.core.model.common.Category
import java.time.LocalDate

data class HomeDataEntity(
    val categories: List<CategoryGroupEntity>,
    val incomePolicies: List<PolicyInfoEntity>
)

data class CategoryGroupEntity(
    val category: Category,
    val policies: List<PolicyInfoEntity>
)

data class PolicyInfoEntity(
    val id: Long,
    val category: String,
    val title: String,
    val imageUrl: String,
    val always: Boolean,
    val endDate: LocalDate?,
    val viewCount: Long?
)
