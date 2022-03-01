package com.example.mybusinesstrip.screens.exchange

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentResolverCompat
import androidx.core.content.ContextCompat
import com.example.mybusinesstrip.MainActivity
import com.example.mybusinesstrip.RESOLVER
import com.example.mybusinesstrip.databinding.DataExchangeFragmentBinding
import com.example.mybusinesstrip.workingwithfiles.ExportDataToExcel
import com.example.mybusinesstrip.workingwithfiles.ImportDataFromExcel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val OPEN_DOCUMENT_REQUEST_CODE = 77

class DataExchangeFragment : Fragment() {

    private lateinit var viewModel: DataExchangeViewModel
    private lateinit var binding: DataExchangeFragmentBinding
    private lateinit var exchange: ExportDataToExcel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataExchangeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                var downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                exchange = ExportDataToExcel(requireContext(), downloadDir = downloadDir)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.also { documentUri ->
                RESOLVER.takePersistableUriPermission(
                    documentUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                openDocument(documentUri)
            }
        }
    }

    private fun init() {

        viewModel = ViewModelProvider(this)[DataExchangeViewModel::class.java]


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, ExportDataToExcel.FILE_NAME)
            }
            var dstUri = requireContext().contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            exchange = ExportDataToExcel(requireContext(), dstUri = dstUri)


        } else {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    100)
            }
        }

        binding.apply {
            btnExchangeExport.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    exchange.createTable(viewModel.getAllVisitsForExcell())
                }
            }

            btnExchangeImport.setOnClickListener {
                openDocumentPicker()
            }
        }
    }

    private fun openDocumentPicker() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/*"
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

    private fun openDocument(documentUri: Uri) {
        CoroutineScope(Dispatchers.IO).launch {
            ImportDataFromExcel(documentUri).readTable()
        }
    }
}