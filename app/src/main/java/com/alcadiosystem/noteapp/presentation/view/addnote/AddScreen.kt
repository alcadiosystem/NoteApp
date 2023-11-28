package com.alcadiosystem.noteapp.presentation.view.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alcadiosystem.noteapp.presentation.view.component.MyAppBar
import com.alcadiosystem.noteapp.presentation.view.viewmodel.CRUDViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController, viewModel: CRUDViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            MyAppBar(viewModel.titleBar, true, navController)
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Titulo")},
                value = viewModel.title,
                onValueChange = {viewModel.title = it}
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Descripcion")},
                value = viewModel.description,
                onValueChange = {viewModel.description = it}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(modifier = Modifier.fillMaxWidth(),onClick = {
                if(viewModel.isUpdate) viewModel.updateNote()
                else viewModel.addNote()
                navController.popBackStack()
            }) {
                Text(text = viewModel.titleButton)
            }
        }
    }
}