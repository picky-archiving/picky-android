package hackathon.picky.feature.home.model

sealed class PolicyDetailUiState {
    data object Loading : PolicyDetailUiState()

    data class Success(
        val policyDetail: PolicyDetail,
        val daysRemaining: Int,
        val isBookmarked: Boolean = false
    ) : PolicyDetailUiState()

    data class Error(
        val message: String
    ) : PolicyDetailUiState()
}