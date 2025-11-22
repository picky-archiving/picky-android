package hackathon.picky.core.data.mapper.policiy

import hackathon.picky.core.data.model.policy.CategoryGroupEntity
import hackathon.picky.core.data.model.policy.HomeDataEntity
import hackathon.picky.core.data.model.policy.PolicyInfoEntity
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.network.model.response.CategoryGroup
import hackathon.picky.core.network.model.response.HomeData
import hackathon.picky.core.network.model.response.PolicyInfo
import java.time.LocalDate

fun HomeData.toEntity(): HomeDataEntity {
    return HomeDataEntity(
        categories = this.categories.map { it.toEntity() },
        incomePolicies = this.incomePolicies.map { it.toEntity() }
    )
}

fun CategoryGroup.toEntity(): CategoryGroupEntity {
    return CategoryGroupEntity(
        category = Category.fromLabel(this.category),
        policies = this.policies.map { it.toEntity() }
    )
}

fun PolicyInfo.toEntity(): PolicyInfoEntity {
    return PolicyInfoEntity(
        id = this.id,
        category = this.category,
        title = this.title,
        imageUrl = this.imageUrl,
        always = this.always,
        endDate = if (!this.always) {
            LocalDate.parse(this.endDate)
        } else null, // null 처리 포함,
        viewCount = this.viewCount,
    )
}
