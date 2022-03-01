package com.example.mybusinesstrip.workingwithfiles

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.mybusinesstrip.RESOLVER
import com.example.mybusinesstrip.model.VisitsModel
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ImportDataFromExcel(private val dstUri: Uri) {

    suspend fun readTable(): List<VisitsModel> {
        var list: MutableList<VisitsModel> = mutableListOf()

        Log.d("IMPORT", "start readTable")

        RESOLVER.openInputStream(dstUri)?.use { inputStream ->

            Log.d("IMPORT", "got inputStream")
            Log.d("IMPORT", inputStream.toString())
            try {
                val workbook = XSSFWorkbook(inputStream)

                Log.d("IMPORT", "WorkBookCreated")

                val sheet = workbook.getSheetAt(0)
                for (row in sheet) {
                    if (row.rowNum > 0) {
                        val visit = VisitsModel()
                        val cellIterator = row.cellIterator()

                        while (cellIterator.hasNext()) {
                            val cell = cellIterator.next()
                            when(cell.columnIndex) {
                                0 -> visit.employer = cell.stringCellValue
                                1 -> visit.region = cell.stringCellValue
                                2 -> visit.locality = cell.stringCellValue
                                3 -> visit.person = cell.stringCellValue
                                4 -> visit.signName = cell.stringCellValue
                                5 -> visit.contactName = cell.stringCellValue
                                6 -> {

                                }
                                7 -> visit.contactEmail = cell.stringCellValue
                                8 -> visit.contactSite = cell.stringCellValue
                                9 -> visit.infoBefore = cell.stringCellValue
                                10 -> visit.infoAfter = cell.stringCellValue
                                11 -> visit.infoTodo = cell.stringCellValue
                                else -> break
                            }
                        }
                        list[row.rowNum] = visit
                    }
                }
            } catch (ioException: Exception) {
                Log.e("IMPORT", ioException.toString())
            }
        }

        return list
    }

}