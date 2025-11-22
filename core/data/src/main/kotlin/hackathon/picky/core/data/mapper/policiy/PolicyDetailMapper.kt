package hackathon.picky.core.data.mapper.policiy

import hackathon.picky.core.data.model.policy.PolicyDetailEntity
import hackathon.picky.core.network.model.response.PolicyDetailData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun PolicyDetailData.toEntity(): PolicyDetailEntity {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return PolicyDetailEntity(
        id = this.id,
        title = this.title,
        imgUrl = "http://54.180.92.121:8080"+this.imageUrl,
        department = this.host,
        startDate = LocalDate.parse(this.startDate, formatter),
        closingDate = if (!this.always) {
            LocalDate.parse(this.endDate, formatter)
        } else null,
        eligibility = this.qualifications,
        description = this.content,
        bookmarked = this.bookmarked,
        webUrl = this.url
    )
}