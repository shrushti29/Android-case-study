package com.target.targetcasestudy.data.di

import com.target.targetcasestudy.domain.repo.DealRepository
import com.target.targetcasestudy.data.repo.DealRepositoryImpl
import com.target.targetcasestudy.domain.usecase.ExecuteDealApiUseCaseImpl
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ApplicationModule {

    @Singleton
    @Binds
    abstract fun bindDealRepository(dealRepositoryImpl: DealRepositoryImpl): DealRepository

    @Binds
    abstract fun bindDealUseCase(executeDealApiUseCaseImpl: ExecuteDealApiUseCaseImpl): ExecuteDealUseCase

}
