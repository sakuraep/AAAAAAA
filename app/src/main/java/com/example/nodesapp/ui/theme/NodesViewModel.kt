package com.example.nodesapp.ui.theme

import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nodesapp.data.DataSource
import com.example.nodesapp.data.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NodesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(NodesUiState())
    val uiState: StateFlow<NodesUiState> = _uiState.asStateFlow()

    fun saveNewNode(label: String, node: String){
        DataSource.NodesList.add(uiState.value.index, Node(label, node))
        _uiState.update { currentState ->
            currentState.copy(
                index =+ 1
            )
        }
    }

    fun newSelectedCategory(category: String){
        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category
            )
        }
    }

    fun removeCategory(category: String){
        DataSource.CategoryList.remove(category)
    }

    fun removeNode(){
        DataSource.NodesList.removeAt(_uiState.value.index)
        _uiState.update { currentState ->
            currentState.copy(
                index =- 1
            )
        }
    }
    fun addCategory(category: String){
        DataSource.CategoryList.add(category)
    }
}