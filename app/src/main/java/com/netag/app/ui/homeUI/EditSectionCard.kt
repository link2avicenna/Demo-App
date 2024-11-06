package com.netag.app.ui.homeUI

import com.netag.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditCard(
    modifier: Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }
    // Use dummy data for selected colors instead of fetching from Firebase
    var selectedProfileColor by remember { mutableStateOf(Color(0xFF6200EA)) }
    var selectedButtonColor by remember { mutableStateOf(Color(0xFFFF0000)) }
    var selectedTextColor by remember { mutableStateOf(Color(0xFFFFFFFF)) }

    Spacer(modifier = Modifier.size(10.dp))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Red)
    ) {
        Column {
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = null,
                    modifier = modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Edit & Create Card", style = TextStyle(color = Color.White))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CreateCardItem(
                    Modifier,
                    "Add Info",
                    R.drawable.addcontact,
                    CardDimensions(width = 100, height = 100)
                ) {}

                Spacer(modifier = modifier.size(10.dp))

                CreateCardItem(
                    Modifier,
                    "Theme Color",
                    R.drawable.ic_color_picker,
                    CardDimensions(width = 100, height = 100)
                ) {}

                Spacer(modifier = modifier.size(10.dp))

                CreateCardItem(
                    Modifier,
                    "Add Location",
                    R.drawable.ic_location,
                    CardDimensions(width = 100, height = 100)
                ) {}
            }
            Spacer(modifier = modifier.size(30.dp))
        }
    }

    // Show color selection dialog when theme color option is clicked
    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Choose Colors") },
//            text = {
//                ThemeColorDialogContent(
//                    initialProfileColor = selectedProfileColor,
//                    initialProfileButtonColor = selectedButtonColor,
//                    initialProfileTextColor = selectedTextColor,
//                    onColorChange = { profileColor, buttonColor, textColor ->
//                        selectedProfileColor = profileColor
//                        selectedButtonColor = buttonColor
//                        selectedTextColor = textColor
//                    },
//                    onDismiss = { showDialog = false } // Dismiss the dialog
//                )
//            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.black)
                    ),
                    onClick = {
                        showDialog = false
//                        UpdateThemeColors(
//                            selectedButtonColor = selectedButtonColor.toHex(),
//                            selectedTextColor = selectedTextColor.toHex(),
//                            selectedProfileColor = selectedProfileColor.toHex()
//                        )
                    }
                ) {
                    Text("Ok")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

}

fun Color.toHex(): String {
    val alpha = (this.alpha * 255).toInt().toString(16).padStart(2, '0')
    val red = (this.red * 255).toInt().toString(16).padStart(2, '0')
    val green = (this.green * 255).toInt().toString(16).padStart(2, '0')
    val blue = (this.blue * 255).toInt().toString(16).padStart(2, '0')
    return "#$alpha$red$green$blue"
}

@Composable
fun CreateCardItem(
    modifier: Modifier,
    txt: String,
    resourceId: Int,
    dimensions: CardDimensions,
    onCheckChanged: OnCheckChanged
) {
    Card(
        modifier = modifier
            .width(dimensions.width.dp)
            .height(dimensions.height.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.size(20.dp))
            Image(
                modifier = modifier.size(40.dp),
                painter = painterResource(id = resourceId),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(10.dp))
            Text(text = txt,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = modifier.size(20.dp))
        }
    }
}
