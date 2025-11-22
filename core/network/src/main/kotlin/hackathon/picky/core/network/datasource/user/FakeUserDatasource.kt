package hackathon.picky.core.network.datasource.user

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.UserIncomeBracketData
import hackathon.picky.core.network.model.response.UserIncomeBracketResponse
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FakeUserDatasource @Inject constructor() : UserDatasource {
    private var currentIncomeBracket = 2 // Mock 상태 저장

    override suspend fun getUserIncomeBracket(userId: Long): ApiResponse<UserIncomeBracketResponse> {
        delay(500) // 네트워크 지연 시뮬레이션

        return ApiResponse.Success(
            UserIncomeBracketResponse(
                data = UserIncomeBracketData(
                    incomeBracket = currentIncomeBracket
                ),
                message = "success",
                success = true,
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            )
        )
    }

    override suspend fun updateUserIncomeBracket(
        userId: Long,
        incomeBracket: Int
    ): ApiResponse<UserIncomeBracketResponse> {
        delay(500) // 네트워크 지연 시뮬레이션

        // Mock 상태 업데이트
        currentIncomeBracket = incomeBracket

        return ApiResponse.Success(
            UserIncomeBracketResponse(
                data = UserIncomeBracketData(
                    incomeBracket = incomeBracket
                ),
                message = "success",
                success = true,
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            )
        )
    }
}