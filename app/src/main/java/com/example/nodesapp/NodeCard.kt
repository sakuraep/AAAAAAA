package com.example.nodesapp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.StiffnessHigh
import androidx.compose.animation.core.Spring.StiffnessMedium
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.nodesapp.data.Node
import com.example.nodesapp.ui.theme.NodesViewModel

@Composable
fun NodeCard(modifier: Modifier = Modifier,
             label: String,
             node: String,
             viewModel: NodesViewModel = NodesViewModel(),
             onRemoveButtonClick: () -> Unit = {}
){
    Card(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp, 20.dp, 0.dp, 20.dp)) {
        Column(modifier = modifier
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = DampingRatioMediumBouncy,
                    stiffness = StiffnessMedium
                )
            )) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "$label$",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    overflow= TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {viewModel.removeNode();
                onRemoveButtonClick()}) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }
            }
                Row(
                    modifier = modifier

                ) {
                    Text(
                        text = "Category: $node",
                        fontSize = 20.sp
                    )
            }
        }
    }
}