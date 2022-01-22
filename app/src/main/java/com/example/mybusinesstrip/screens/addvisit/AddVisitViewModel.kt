package com.example.mybusinesstrip.screens.addvisit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusinesstrip.REPOSITORY
import com.example.mybusinesstrip.model.VisitsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddVisitViewModel: ViewModel() {

    var newVisit = VisitsModel()

    fun saveVisit(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.addVisit(newVisit, onSuccess)
        }
    }

}