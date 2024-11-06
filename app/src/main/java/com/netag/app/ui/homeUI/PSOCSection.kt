package com.netag.app.ui.homeUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.netag.app.R

@Composable
fun PSOCSection(
    modifier: Modifier) {
    val boxHeight = 110
    val boxWidth = 140

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CreateCardItem(
                Modifier,
                "Add Product",
                R.drawable.products,
                CardDimensions(width = boxWidth, height = boxHeight)
            ) {

            }
            Spacer(modifier = Modifier.size(20.dp))
            CreateCardItem(
                Modifier, "Add Services", R.drawable.services,
                CardDimensions(width = boxWidth, height = boxHeight)
            ) {

            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CreateCardItem(
                Modifier,
                "Manage Orders",
                R.drawable.manageorders,
                CardDimensions(width = boxWidth, height = boxHeight)
            ) {

            }
            Spacer(modifier = Modifier.size(20.dp))
            CreateCardItem(
                Modifier, "Add Calendar", R.drawable.ic_calendar,
                CardDimensions(width = boxWidth, height = boxHeight)
            ) {

            }
        }
        Spacer(modifier = Modifier.size(30.dp))
    }
}