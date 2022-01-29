package com.example.mybusinesstrip.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mybusinesstrip.model.VisitsModel

@Dao
interface VisitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVisit(visit: VisitsModel)

    @Query("SELECT * FROM ${VisitsModel.TABLENAME} ORDER BY region ASC")
    fun getAllVisits(): LiveData<List<VisitsModel>>

    @Delete
    suspend fun deleteVisit(visit: VisitsModel)

    @Update
    suspend fun updateVisit(visit: VisitsModel)

}