package com.example.nodesapp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nodesapp.data.DataSource
import com.example.nodesapp.data.Node
import com.example.nodesapp.ui.theme.NodesUiState
import com.example.nodesapp.ui.theme.NodesViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NodesScreen(modifier: Modifier = Modifier,
                onAddButtonClick: () -> Unit = {},
                onRemoveButtonClick: () -> Unit = {},
                viewModel: NodesViewModel = NodesViewModel()
){
    val UiState by viewModel.uiState.collectAsState()
    var status by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier
        .padding(vertical = 6.dp)) {
        Row(modifier = modifier
            .padding(horizontal = 10.dp)) {
            Text(text = "None",
                modifier = modifier
                    .clickable { viewModel.newSelectedCategory("")},
                fontSize = 20.sp)
            LazyRow() {
                items(DataSource.CategoryList) { items ->
                    Text(
                        text = items,
                        modifier = modifier
                            .clickable { viewModel.newSelectedCategory(items) }
                            .padding(horizontal = 10.dp),
                        fontSize = 20.sp,
                    )
                }
            }
        }
        LazyColumn() {
            items(DataSource.NodesList) { items ->
                if (items.node == UiState.selectedCategory){
                    NodeCard(
                        label = items.label,
                        node = items.node,
                        onRemoveButtonClick = onRemoveButtonClick
                    )
                }
                if (UiState.selectedCategory == ""){
                    NodeCard(
                        label = items.label,
                        node = items.node,
                        onRemoveButtonClick = onRemoveButtonClick
                    )
                }
            }
        }
    }
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = modifier
            .fillMaxWidth()) {
            Button(onClick = onAddButtonClick,
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)){
                Text(text = "Add new post")
            }
        }
    }
}