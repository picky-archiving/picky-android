package hackathon.picky.core.network.di

import hackathon.picky.core.network.datasource.auth.AuthDatasource
import hackathon.picky.core.network.datasource.auth.FakeAuthDatasource
import hackathon.picky.core.network.datasource.auth.RetrofitAuthDatasource
import hackathon.picky.core.network.datasource.policy.FakePolicyDatasource
import hackathon.picky.core.network.datasource.policy.PolicyDatasource
import hackathon.picky.core.network.datasource.policy.RetrofitPolicyDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatasourceModule {
    @Binds
    @FakeDataSource
    abstract fun bindFakeAuthDatasource(
        fakeAuthDatasource: FakeAuthDatasource
    ): AuthDatasource

    @Binds
    @RealDataSource
    abstract fun bindAuthDatasource(
        retrofitAuthDatasource: RetrofitAuthDatasource
    ): AuthDatasource

    @Binds
    @FakeDataSource
    abstract fun bindFakePolicyDatasource(
        fakePolicyDatasource: FakePolicyDatasource
    ): PolicyDatasource

    @Binds
    @RealDataSource
    abstract fun bindPolicyDatasource(
        retrofitPolicyDatasource: RetrofitPolicyDatasource
    ): PolicyDatasource
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeDataSource