package hackathon.picky.feature.home.model

data class PolicyDetail(
    val id: String,
    val title: String,
    val department: String,
    val applicationPeriod: String,
    val eligibility: List<String>,
    val description: String
)