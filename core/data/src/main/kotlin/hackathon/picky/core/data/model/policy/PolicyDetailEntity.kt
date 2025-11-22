package hackathon.picky.core.data.model.policy

import java.time.LocalDate

data class PolicyDetailEntity(
    val id: Long,
    val title: String,
    val imgUrl: String,
    val department: String,
    val startDate: LocalDate?,  // nullable - 상시 모집이면 null
    val closingDate: LocalDate?,  // nullable - 상시 모집이면 null
    val eligibility: List<String>,
    val description: String,
    val bookmarked: Boolean
)