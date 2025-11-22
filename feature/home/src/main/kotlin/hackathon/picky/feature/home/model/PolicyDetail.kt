package hackathon.picky.feature.home.model

import java.time.LocalDate

data class PolicyDetail(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val department: String,
    val startDate: LocalDate,
    val closingDate: LocalDate?,
    val eligibility: List<String>,
    val description: String,
    val webUrl: String
)