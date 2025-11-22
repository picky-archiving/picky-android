package hackathon.picky.core.data.mapper.policiy

import hackathon.picky.core.data.model.policy.PolicyEntity
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.network.model.response.PolicyIncomeResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun PolicyIncomeResponse.toEntity(): List<PolicyEntity> = this.data.map {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    PolicyEntity(
        id = it.id,
        title = it.title ?: "",
        category = Category.fromLabel(it.category ?: ""),
        endDate =  if (!it.always && !it.endDate.isNullOrEmpty()) {
            LocalDate.parse(it.endDate, formatter)
        } else null,
        imageUrl = "http://54.180.92.121:8080" + it.imageUrl ?: "",
        viewCount = it.viewCount ?: 0,

    )
}