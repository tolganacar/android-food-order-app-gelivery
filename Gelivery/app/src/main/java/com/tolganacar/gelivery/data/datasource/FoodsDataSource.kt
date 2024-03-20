package com.tolganacar.gelivery.data.datasource

import com.tolganacar.gelivery.data.entity.Foods
import com.tolganacar.gelivery.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource(var foodsDao: FoodsDao) {

    suspend fun loadFoods(): List<Foods> = withContext(Dispatchers.IO){
        return@withContext foodsDao.loadFoods().yemekler
    }
}