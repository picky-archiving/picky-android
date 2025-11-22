package hackathon.picky.core.network.datasource.user

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.UserIncomeBracketResponse

interface UserDatasource {
    /**
     * 사용자 소득분위 조회
     * @param userId 사용자 식별 ID
     */
    suspend fun getUserIncomeBracket(userId: Long): ApiResponse<UserIncomeBracketResponse>
}