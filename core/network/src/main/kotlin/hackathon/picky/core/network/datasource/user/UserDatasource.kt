package hackathon.picky.core.network.datasource.user

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.UserIncomeBracketResponse

interface UserDatasource {
    /**
     * 사용자 소득분위 조회
     * @param userId 사용자 식별 ID
     */
    suspend fun getUserIncomeBracket(userId: Long): ApiResponse<UserIncomeBracketResponse>

    /**
     * 사용자 소득분위 수정
     * @param userId 사용자 식별 ID
     * @param incomeBracket 변경할 소득분위
     */
    suspend fun updateUserIncomeBracket(userId: Long, incomeBracket: Int): ApiResponse<UserIncomeBracketResponse>
}