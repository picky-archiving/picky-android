package hackathon.picky.core.network.datasource.user

import hackathon.picky.core.network.api.UserApi
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.request.UpdateIncomeBracketRequest
import hackathon.picky.core.network.model.response.UserIncomeBracketResponse
import hackathon.picky.core.network.util.runRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitUserDatasource @Inject constructor(
    private val userApi: UserApi
) : UserDatasource {
    override suspend fun getUserIncomeBracket(userId: Long): ApiResponse<UserIncomeBracketResponse> {
        return runRemote { userApi.getUserIncomeBracket(userId) }
    }

    override suspend fun updateUserIncomeBracket(
        userId: Long,
        incomeBracket: Int
    ): ApiResponse<UserIncomeBracketResponse> {
        return runRemote {
            userApi.updateUserIncomeBracket(
                userId = userId,
                request = UpdateIncomeBracketRequest(incomeBracket = incomeBracket)
            )
        }
    }
}