package com.netag.app.ui.homeUI

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.netag.app.R
import com.netag.app.model.Contacts
import com.netag.app.model.UserSelectedLink
import com.netag.app.model.UserSelectedLink1
import com.netag.app.model.Users1

val dummyUserData = listOf(
    Users1(id = "1", directMode = true, leadMode = false, profileOn = 1),
    Users1(id = "2", directMode = false, leadMode = true, profileOn = 0)
)

val dummyLeads = listOf(
    Contacts(id = "1", name = "Lead 1", imageview = "https://example.com/image1.jpg"),
    Contacts(id = "2", name = "Lead 2", imageview = "https://example.com/image2.jpg")
)

val dummySocialLinks = listOf(
    UserSelectedLink1(id = "1", linkId = "1", selectedForDirect = false),
    UserSelectedLink1(id = "2", linkId = "2", selectedForDirect = false)
)

@Composable
fun ManageSectionCard(
    modifier: Modifier
): Boolean {
    val context = LocalContext.current
    val loggedInUserId = "1" // Simulated logged-in user ID

    // Use dummy data instead of observing Firebase LiveData
    val userList = dummyUserData
    val currentUser = userList.find { it.id == loggedInUserId }

    val socialLinks = dummySocialLinks
    val leadsData = dummyLeads

//    val user =  Users1(id = "2", directMode = false, leadMode = true, profileOn = 0)
//
//    // Set the `selectedForDirect` flag for the user selected link
//    socialLinks.filter { it.id == user.direct?.linkId }
//        .map { it.selectedForDirect = true }

    var directModeStatus by remember {
        mutableStateOf(false)
    }

    var leadModeStatus by remember {
        mutableStateOf(false)
    }

    var profileModeStatus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(currentUser) {
        currentUser?.let {
            directModeStatus = it.directMode
            leadModeStatus = it.leadMode
            profileModeStatus = it.profileOn == 1
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardColors(containerColor = Color.LightGray,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.size(10.dp))
            Text(text = "Manage Card")
            Spacer(modifier = modifier.size(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Lead Mode switch
                SwitchBtn(modifier = modifier, bgColor = Color.Black, leadModeStatus, "Lead Mode") { status ->
                    leadModeStatus = status
                    Log.d("Lead Mode", "Status: $status")
                }
                Spacer(modifier = modifier.size(10.dp))

                // Direct Mode switch
                SwitchBtn(modifier = modifier, bgColor = Color.Blue, directModeStatus, "Direct Mode") { status ->
                    if (status && socialLinks.isEmpty()) {
                        val message = "Please add link first"
//                        ApplicationHandler.toast(message)
                        directModeStatus = false
                    } else {
                        directModeStatus = status
                        Log.d("Direct Mode", "Status: $status")
                    }
                }
                Spacer(modifier = modifier.size(10.dp))

                // Profile Mode switch
                SwitchBtn(modifier = modifier, bgColor = Color.Red, profileModeStatus, "Switch Mode") { status ->
                    profileModeStatus = status
                    Log.d("Profile Mode", "Status: $status")
                }
            }
            Spacer(modifier = modifier.size(10.dp))
            LeadList(leads = leadsData)
            Spacer(modifier = modifier.size(10.dp))
        }
    }
    return directModeStatus
}

// First LeadList (Uses passed leads data)
@Composable
fun LeadList(
    leads: List<Contacts>?) {
    val context = LocalContext.current

    leads?.let {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                items(it.toList()) { item ->
                    val imageUrl = item.imageview
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl.ifEmpty { R.drawable.ic_profile_placeholder })
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
//                                showBottomSheetDialog(item, context)
                            }
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
            Text(text = "Leads",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                }
            )
        }
    }
}

// Second LeadList (Uses dummy data)
@Composable
fun LeadListDummy(
    modifier: Modifier
) {
    val itemsList = listOf(
        Item(R.drawable.ic_snapchat),
        Item(R.drawable.ic_phone),
        Item(R.drawable.ic_x),
        Item(R.drawable.ic_apple)
    )
    LazyRow(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(itemsList) { item ->
            ItemCard(item, modifier, CardDimensions(50, 50))
        }
    }
}

@Composable
fun ItemCard(item: Item, modifier: Modifier, cardDimensions: CardDimensions) {
    Image(
        painter = painterResource(id = item.resourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(cardDimensions.width.dp)
            .height(cardDimensions.height.dp)
    )
    Spacer(modifier = modifier.size(10.dp))
}

@Composable
fun SwitchBtn(
    modifier: Modifier,
    bgColor: Color,
    checkedStatus: Boolean,
    itemTxt: String,
    onCheckChanged: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .border(BorderStroke(width = 1.dp, color = Color.Red), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Switch(
                checked = checkedStatus,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = bgColor,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Black,
                ),
                onCheckedChange = onCheckChanged
            )
            Text(
                text = itemTxt,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

data class Item(val resourceId: Int)

data class CardDimensions(
    val width: Int,
    val height: Int,
    val size: Int? = null
)

typealias OnCheckChanged = (status: Boolean) -> Unit