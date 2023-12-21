package com.example.nodesapp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.nodesapp.data.DataSource
import com.example.nodesapp.data.Node
import com.example.nodesapp.ui.theme.NodesUiState
import com.example.nodesapp.ui.theme.NodesViewModel
import java.time.format.TextStyle

@Composable
fun AddNewNodesScreen(modifier: Modifier = Modifier,
                      onSaveNodeClick: () -> Unit,
                      viewModel: NodesViewModel = NodesViewModel(),
                      updatePage: () -> Unit = {}
                      ){
    var valueLabel by remember {
        mutableStateOf("")
    }
    var valueCategory by remember {
        mutableStateOf("")

    }
    val gameUiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier .padding(10.dp)) {
        Row {
            TextButton(onClick = onSaveNodeClick) {
                Text(text = "Cancel", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {viewModel.saveNewNode(valueLabel, valueCategory);
            onSaveNodeClick()}) {
                Text(text = "Add", fontWeight = FontWeight.Bold)
            }
        }
        Column(modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(value = valueLabel, onValueChange = {valueLabel = it},
                modifier = modifier
                    .fillMaxWidth(),
                label = { Text(text = "label")},
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = true,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(value = valueCategory, onValueChange = {valueCategory = it},
                modifier = modifier
                    .fillMaxWidth(),
                readOnly = true,
                enabled = false,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                ),
                singleLine = true
            )
        }
        var status by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { status = !status }
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )) {
            Text(text = stringResource(R.string.select_category),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            if (status){
                CategoryList(selectedCategory = {
                    valueCategory = it;
                },
                    updatePage = {updatePage()})
            }
        }
    }
}

@Composable
fun CategoryList(modifier: Modifier = Modifier,
                 selectedCategory: (String) -> Unit,
                 viewModel: NodesViewModel = NodesViewModel(),
                 updatePage: () -> Unit = {}
){
    var status by remember {
        mutableStateOf(false)
    }
    var newCategory by remember {
        mutableStateOf("")
    }
    Column {
        LazyColumn(modifier = modifier
            .padding(0.dp, 10.dp, 10.dp, 10.dp)){
            items(DataSource.CategoryList){ item ->
                Row {
                    Text(text = item, modifier = modifier
                        .clickable { selectedCategory(item) }
                        .padding(top = 6.dp),
                        fontSize = 24.sp)
                    Spacer(modifier.weight(1f))
                    IconButton(onClick = { viewModel.removeCategory(item);
                    updatePage()}) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
        IconButton(onClick = { status = !status}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
        if (status){
            Row(verticalAlignment = Alignment.CenterVertically){
                OutlinedTextField(value = newCategory,
                    onValueChange = {newCategory = it},
                    label = { Text(text = "New category")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                IconButton(onClick = {
                viewModel.addCategory(newCategory);
                    updatePage()
                }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}