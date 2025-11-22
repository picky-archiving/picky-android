package hackathon.picky.feature.home.model

import java.time.LocalDate

data class PolicyDetail(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val department: String,
    val startDate: LocalDate?,  // nullable - 상시 모집이면 null
    val closingDate: LocalDate?,  // nullable - 상시 모집이면 null
    val eligibility: List<String>,
    val description: String
)