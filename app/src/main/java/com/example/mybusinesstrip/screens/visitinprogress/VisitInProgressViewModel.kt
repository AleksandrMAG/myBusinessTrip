package com.example.mybusinesstrip.screens.visitinprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusinesstrip.REPOSITORY
import com.example.mybusinesstrip.model.VisitsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisitInProgressViewModel : ViewModel() {
    var currentVisit = VisitsModel()

    fun updateVisit(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateVisit(currentVisit, onSuccess)
        }
    }
}