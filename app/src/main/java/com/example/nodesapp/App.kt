package com.example.nodesapp

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nodesapp.ui.theme.NodesUiState
import com.example.nodesapp.ui.theme.NodesViewModel

enum class Pages{
    Start,
    End
}

@Composable
fun App(modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        viewModel: NodesViewModel = NodesViewModel()
){
    var valueLabel by remember {
        mutableStateOf("")
    }
    var valueNode by remember {
        mutableStateOf("")
    }
    NavHost(navController = navController, startDestination = Pages.Start.name){
        composable(route = Pages.Start.name){
            NodesScreen(onAddButtonClick = {
                navController.navigate(Pages.End.name)
            },
            onRemoveButtonClick = {
                navController.navigate(Pages.Start.name)
            })
        }
        composable(route = Pages.End.name){
            AddNewNodesScreen(
                onSaveNodeClick = {
                    navController.navigate(Pages.Start.name)
                },
                updatePage = {navController.navigate(Pages.End.name)}
            )
        }
    }

}