package hackathon.picky.core.model

import java.time.LocalDateTime

data class CommonListItem (
    val id: Int,
    val title: String,
    val imageUrl: String,
    val closingDate: LocalDateTime // null일 경우 상시
)

val CommonListItemTest = CommonListItem(
    id = 1,
    imageUrl = "https://sosal.kr/1144?pidx=0",
    title = "정책 이름 예시입니다. 길어질 시 예시입니다. 길어질 시 예시입니다.",
    closingDate = LocalDateTime.now().plusDays(5)
)