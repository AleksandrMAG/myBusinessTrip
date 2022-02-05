package com.example.mybusinesstrip.workingwithfiles

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.model.VisitsModel
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream



class Exchange(private val applicationContext: Context,
               private val dstUri: Uri? = null,
               private val downloadDir: File? = null) {

    companion object {
        const val FILE_NAME = "BusinessTRIP.xlsx"
    }

    suspend fun createTable() {

        val columnNames = applicationContext.resources.getStringArray(R.array.table_columns)

        val xlWb = XSSFWorkbook()
        val xlWs = xlWb.createSheet()

//        xlWs.createRow(0).createCell(0).setCellValue(message)

        //Filling the header-------------------------------------------
        xlWs.createRow(0).let {
            (columnNames.indices).forEach { column ->
                it.createCell(column).setCellValue(columnNames[column])
            }
        }


        try {
            dstUri?.let {
                val dst = applicationContext.contentResolver.openOutputStream(it)
                xlWb.write(dst)
                dst?.close()
            }
            downloadDir?.let {
                val dst = FileOutputStream(File(it, FILE_NAME))
                xlWb.write(dst)
                dst?.close()
            }
        } catch (e: Exception) {
            Log.e("MAIN", "file not created", e)
        }

        xlWb.close()
    }
}