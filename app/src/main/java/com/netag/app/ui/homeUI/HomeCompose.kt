package com.netag.app.ui.homeUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HomeCompose(
    modifier: Modifier = Modifier
) {
    var showLocation by remember { mutableStateOf(false) }
    var context = LocalContext.current

    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        UserInfoCard(modifier = modifier)

        val directModeStatus = ManageSectionCard(modifier = modifier)

        EditCard(modifier = modifier)

        PhotosVideosSection(modifier)
        PSOCSection(modifier)

        SocialLinkSection(directModeStatus)
    }

}