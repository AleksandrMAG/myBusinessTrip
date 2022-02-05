package com.example.mybusinesstrip.db.repository

import androidx.lifecycle.LiveData
import com.example.mybusinesstrip.db.dao.VisitsDao
import com.example.mybusinesstrip.model.VisitsModel

class RepositoryRealization(private val visitsDao: VisitsDao): VisitRepository {

    override val allVisits: LiveData<List<VisitsModel>>
        get() = visitsDao.getAllVisits()

    override suspend fun addVisit(visit: VisitsModel, onSuccess: () -> Unit) {
        visitsDao.addVisit(visit)
        onSuccess()
    }

    override suspend fun deleteVisit(visit: VisitsModel, onSuccess: () -> Unit) {
        visitsDao.deleteVisit(visit)
        onSuccess()
    }

    override suspend fun updateVisit(visit: VisitsModel, onSuccess: () -> Unit) {
        visitsDao.updateVisit(visit)
        onSuccess()
    }
//
//    override val visitsToExcell: List<VisitsModel>
//        get() = visitsDao.getAllVisitsForExcell()
}