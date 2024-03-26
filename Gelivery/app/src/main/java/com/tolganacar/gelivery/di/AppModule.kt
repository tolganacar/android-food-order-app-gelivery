package com.tolganacar.gelivery.di

import com.tolganacar.gelivery.data.datasource.FoodsDataSource
import com.tolganacar.gelivery.data.repo.FoodsRepository
import com.tolganacar.gelivery.retrofit.ApiUtils
import com.tolganacar.gelivery.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodsRepository(foodsDataSource: FoodsDataSource): FoodsRepository {
        return FoodsRepository(foodsDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodsDataSource(foodsDao: FoodsDao): FoodsDataSource {
        return FoodsDataSource(foodsDao)
    }

    @Provides
    @Singleton
    fun provideFoodsDao(): FoodsDao {
        return ApiUtils.getFoodsDao()
    }
}