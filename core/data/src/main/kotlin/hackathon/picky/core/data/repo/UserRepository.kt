package hackathon.picky.core.data.repo

interface UserRepository {
    /**
     * 사용자 소득분위 조회
     * @param userId 사용자 ID
     * @return Result<Int> 성공 시 소득분위 값, 실패 시 에러
     */
    suspend fun getUserIncomeBracket(userId: Long): Result<Int>
}