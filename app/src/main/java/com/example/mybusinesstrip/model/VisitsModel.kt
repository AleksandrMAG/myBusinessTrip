package com.example.mybusinesstrip.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = VisitsModel.TABLENAME)
class VisitsModel : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    var employer : String = ""
    var region : String = ""
    var locality : String = ""
    var person : String = ""
    var address : String = ""

    @ColumnInfo(name = "name_of_the_sign")
    var signName : String = ""
    @ColumnInfo(name = "name")
    var contactName : String = ""
    @ColumnInfo(name = "phone")
    var contactPhone : String = ""
    @ColumnInfo(name = "phone_2")
    var contactPhoneSecond : String = ""
    @ColumnInfo(name = "email")
    var contactEmail : String = ""
    @ColumnInfo(name = "site")
    var contactSite : String = ""
    @ColumnInfo(name = "info_before")
    var infoBefore : String = ""
    @ColumnInfo(name = "info_after")
    var infoAfter : String = ""
    @ColumnInfo(name = "todo")
    var infoTodo : String = ""

    var photos : String = ""

    companion object {
        const val TABLENAME = "visits_data"
    }
}