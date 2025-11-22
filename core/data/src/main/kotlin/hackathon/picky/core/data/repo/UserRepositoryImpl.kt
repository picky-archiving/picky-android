package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.network.datasource.user.UserDatasource
import hackathon.picky.core.network.di.RealDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @param:RealDataSource private val userDatasource: UserDatasource
) : UserRepository {
    override suspend fun getUserIncomeBracket(userId: Long): Result<Int> {
        return userDatasource.getUserIncomeBracket(userId).toResult(
            transform = { response ->
                response.data.incomeBracket
            }
        )
    }

    override suspend fun updateUserIncomeBracket(userId: Long, incomeBracket: Int): Result<Int> {
        return userDatasource.updateUserIncomeBracket(userId, incomeBracket).toResult(
            transform = { response ->
                response.data.incomeBracket
            }
        )
    }
}