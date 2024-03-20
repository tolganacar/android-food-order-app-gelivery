package com.tolganacar.gelivery.data.repo

import com.tolganacar.gelivery.data.datasource.FoodsDataSource
import com.tolganacar.gelivery.data.entity.Foods

class FoodsRepository(var foodsDataSource: FoodsDataSource) {

    suspend fun loadFoods(): List<Foods> = foodsDataSource.loadFoods()
}