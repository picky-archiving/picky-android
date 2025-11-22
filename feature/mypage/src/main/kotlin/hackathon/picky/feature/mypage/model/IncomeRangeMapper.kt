package hackathon.picky.feature.mypage.model

object IncomeRangeMapper {
    fun getIncomeRange(rank: String): String {
        return when (rank) {
            "1분위" -> "중위소득 30% 이하"
            "2분위" -> "중위소득 30% ~ 50%"
            "3분위" -> "중위소득 50% ~ 70%"
            "4분위" -> "중위소득 70% ~ 90%"
            "5분위" -> "중위소득 90% ~ 100%"
            "6분위" -> "중위소득 110% ~ 130%"
            "7분위" -> "중위소득 130% ~ 150%"
            "8분위" -> "중위소득 150% ~ 200%"
            "9분위" -> "중위소득 200% ~ 300%"
            "10분위" -> "중위소득 300% 이상"
            else -> "중위소득 000% ~ 000%"
        }
    }
}
