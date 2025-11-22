package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.UserIncomeBracketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    /**
     * 사용자 소득분위 조회
     * @param userId 사용자 식별 ID (default = 1)
     */
    @GET("/api/user/{userId}")
    suspend fun getUserIncomeBracket(
        @Path("userId") userId: Long = 1
    ): Response<UserIncomeBracketResponse>
}