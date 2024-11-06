package com.netag.app.ui.homeUI

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.netag.app.R
import com.netag.app.model.UserSelectedLink

@Composable
fun SocialLinkSection(
    directModeStatus: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SocialLinksList(directModeStatus)

        if (!directModeStatus) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Red),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = "Add Social Links"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Composable
fun SocialGallery(
    modifier: Modifier,
    aList: MutableList<UserSelectedLink>,
    selectedLinkId: Int?,
    onItemSelected: (Int?) -> Unit,
    directModeStatus: Boolean
) {
    LazyRow(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        items(aList) { item ->
            val isSelected = item.id == selectedLinkId
            val alphaValue = if (directModeStatus) {
                if (isSelected) 1f else 0.5f
            } else {
                1f
            }

            Column(
                modifier = Modifier
                    .clickable { onItemSelected(item.id) }
                    .alpha(alphaValue)
                    .padding(8.dp)
            ) {
                item.socialIcon?.let { iconResId ->
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                item.name?.let { name ->
                    Text(text = name, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun SocialLinksList(
    directModeStatus: Boolean
) {
    val socialLinks = getDummyLinks()
    var selectedLinkId by remember { mutableStateOf(socialLinks.firstOrNull()?.id) }

    SocialGallery(
        modifier = Modifier,
        aList = socialLinks,
        selectedLinkId = selectedLinkId,
        onItemSelected = { linkId ->
            selectedLinkId = linkId
        },
        directModeStatus = directModeStatus
    )
}

fun getDummyLinks(): MutableList<UserSelectedLink> {
    return mutableListOf(
        UserSelectedLink(id = 1, name = "Facebook", socialIcon = R.drawable.ic_facebook, value = "fb_link"),
        UserSelectedLink(id = 2, name = "Twitter", socialIcon = R.drawable.ic_twitter, value = "twitter_link"),
        UserSelectedLink(id = 3, name = "Instagram", socialIcon = R.drawable.ic_instagram, value = "insta_link"),
        UserSelectedLink(id = 4, name = "LinkedIn", socialIcon = R.drawable.ic_linkedin, value = "linkedin_link"),
        UserSelectedLink(id = 2, name = "Twitter", socialIcon = R.drawable.ic_twitter, value = "twitter_link"),
        UserSelectedLink(id = 3, name = "Instagram", socialIcon = R.drawable.ic_instagram, value = "insta_link"),
        )
}

