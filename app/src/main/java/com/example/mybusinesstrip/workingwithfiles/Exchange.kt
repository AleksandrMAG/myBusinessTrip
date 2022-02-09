package com.example.mybusinesstrip.workingwithfiles

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.model.VisitsModel
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream



class Exchange(private val applicationContext: Context,
               private val dstUri: Uri? = null,
               private val downloadDir: File? = null) {

    companion object {
        const val FILE_NAME = "BusinessTRIP.xlsx"
    }

    suspend fun createTable(listToExcellRows: List<VisitsModel>) {

        val columnNames = applicationContext.resources.getStringArray(R.array.table_columns)
        val columnWidth = applicationContext.resources.getStringArray(R.array.table_columns_width)

        val xlWb = XSSFWorkbook()
        val xlWs = xlWb.createSheet()

        //Create cell formatting styles
        val styleMain = xlWb.createCellStyle()
        styleMain.verticalAlignment = VerticalAlignment.CENTER
        styleMain.borderBottom = BorderStyle.MEDIUM
        styleMain.borderLeft = BorderStyle.MEDIUM
        styleMain.borderRight = BorderStyle.MEDIUM
        styleMain.borderTop = BorderStyle.MEDIUM
        styleMain.wrapText = true

        val styleHeader = styleMain.copy()
        styleHeader.fillBackgroundColor = IndexedColors.CORAL.index
        styleHeader.alignment = HorizontalAlignment.CENTER

        val styleCenter = styleMain.copy()
        styleCenter.alignment = HorizontalAlignment.CENTER

        //Filling the header-------------------------------------------
        xlWs.createRow(0).let {
            it.height = 4000
            (columnNames.indices).forEach { column ->
                xlWs.setColumnWidth(column, columnWidth[column].toInt())
                var cell = it.createCell(column)
                cell.setCellValue(columnNames[column])
                cell.cellStyle = styleHeader
            }
        }

        //Filling all rows
        var currentRowNumber = 1
        listToExcellRows.forEach {
            xlWs.createRow(currentRowNumber).let { xssfRow ->

                var cellEmployer = xssfRow.createCell(0)
                cellEmployer.setCellValue(it.employer)
                cellEmployer.cellStyle = styleMain

                var cellRegion = xssfRow.createCell(1)
                cellRegion.setCellValue(it.region)
                cellRegion.cellStyle = styleMain

                var cellLocality = xssfRow.createCell(2)
                cellLocality.setCellValue(it.locality)
                cellLocality.cellStyle = styleMain

                var cellPerson = xssfRow.createCell(3)
                cellPerson.setCellValue(it.person)
                cellPerson.cellStyle = styleMain

                var cellSign = xssfRow.createCell(4)
                cellSign.setCellValue(it.signName)
                cellSign.cellStyle = styleMain

                var cellContactsName = xssfRow.createCell(5)
                cellContactsName.setCellValue(it.contactName)
                cellContactsName.cellStyle = styleMain

                var cellPhones = xssfRow.createCell(6)
                cellPhones.setCellValue("${it.contactPhone}   ${it.contactPhoneSecond}")
                cellPhones.cellStyle = styleCenter

                var cellEmail = xssfRow.createCell(7)
                cellEmail.setCellValue(it.contactEmail)
                cellEmail.cellStyle = styleCenter

                var  cellCite = xssfRow.createCell(8)
                cellCite.setCellValue(it.contactSite)
                cellCite.cellStyle = styleCenter

                var cellBeforeVisit = xssfRow.createCell(9)
                cellBeforeVisit.setCellValue(it.infoBefore)
                cellBeforeVisit.cellStyle = styleMain

                var cellAfterVisit = xssfRow.createCell(10)
                cellAfterVisit.setCellValue(it.infoAfter)
                cellAfterVisit.cellStyle = styleMain

                var cellToDo = xssfRow.createCell(11)
                cellToDo.setCellValue(it.infoTodo)
                cellToDo.cellStyle = styleMain

                currentRowNumber++
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