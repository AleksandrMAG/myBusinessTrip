package com.example.mybusinesstrip.db.repository

import androidx.lifecycle.LiveData
import com.example.mybusinesstrip.model.VisitsModel

interface VisitRepository {
    val allVisits: LiveData<List<VisitsModel>>
    suspend fun addVisit(visit: VisitsModel, onSuccess: () -> Unit)
    suspend fun deleteVisit(visit: VisitsModel, onSuccess: () -> Unit)
    suspend fun updateVisit(visit: VisitsModel, onSuccess: () -> Unit)

//    val visitsToExcell: List<VisitsModel>
}