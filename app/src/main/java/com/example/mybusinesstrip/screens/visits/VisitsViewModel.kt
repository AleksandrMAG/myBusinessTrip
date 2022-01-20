package com.example.mybusinesstrip.screens.visits

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mybusinesstrip.REPOSITORY
import com.example.mybusinesstrip.db.VisitsDatabase
import com.example.mybusinesstrip.db.repository.RepositoryRealization
import com.example.mybusinesstrip.model.VisitsModel

class VisitsViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase() {
        val daoVisits = VisitsDatabase.getInstance(context).getVisitsDao()
        REPOSITORY = RepositoryRealization(daoVisits)
    }

    fun getAllVisits() : LiveData<List<VisitsModel>> {
        return REPOSITORY.allVisits
    }

}