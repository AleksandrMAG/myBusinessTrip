package com.example.mybusinesstrip.screens.exchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybusinesstrip.REPOSITORY
import com.example.mybusinesstrip.model.VisitsModel

class DataExchangeViewModel : ViewModel() {

    fun getAllVisitsForExcell(): List<VisitsModel> {
        return REPOSITORY.visitsToExcell
    }

}