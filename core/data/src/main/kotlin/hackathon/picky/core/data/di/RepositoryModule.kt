package hackathon.picky.core.data.di

import hackathon.picky.core.data.repo.AuthRepository
import hackathon.picky.core.data.repo.AuthRepositoryImpl
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.data.repo.PolicyRepositoryImpl
import hackathon.picky.core.data.repo.UserRepository
import hackathon.picky.core.data.repo.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindPolicyRepository(
        policyRepository: PolicyRepositoryImpl
    ): PolicyRepository

    @Binds
    abstract fun bindUserRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository
}