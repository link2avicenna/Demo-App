package com.netag.app.ui.homeUI

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.netag.app.R
import com.netag.app.model.User

@Composable
fun UserInfoCard(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        TopBar(modifier)
        InfoCard(modifier)
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Welcome" ,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1.3f))

        Image(
            modifier = Modifier
                .size(20.dp)
                .clickable {
                },
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.notification),
            contentDescription = null
        )
    }
}



@Composable
fun InfoCard(
    modifier: Modifier
) {
    // Dummy Data
    val dummyUserList = listOf(
        User(
            id = "1",
            name = "John Doe",
            email = "john.doe@example.com",
            designation = "Software Engineer",
            companyname = "TechCorp",
            phone = "+1234567890",
            profileUrl = "https://www.example.com/profile.jpg"
        ),
        User(
            id = "2",
            name = "Jane Smith",
            email = "jane.smith@example.com",
            designation = "Product Manager",
            companyname = "Innovate Inc",
            phone = "+0987654321",
            profileUrl = "https://www.example.com/profile2.jpg"
        )
    )

    val context = LocalContext.current
    val loggedInUserId = "1"
    val currentUser = dummyUserList.filter { it.id == loggedInUserId }

    Card(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clickable {
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF0445A6), Color(0xFF000000)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    currentUser?.map { item ->
                        CardItem(modifier, item.name, R.drawable.ic_nav_profile)
                        CardItem(modifier, item.email, R.drawable.icon_email)
                        CardItem(modifier, item.designation, R.drawable.ic_designation)
                        CardItem(modifier, item.companyname, R.drawable.building)
                        CardItem(modifier, item.phone, R.drawable.ic_phone_number)
                    }
                }
                Column {
                    currentUser?.map { item ->
                        val painter =
                            rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = item.profileUrl)
                                    .build()
                            )
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null,
                        Modifier
                            .size(50.dp)
                            .padding(end = 20.dp)
                            .clickable {
                                currentUser?.map { item ->
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, "https://example.com/${item.id}")
                                        type = "text/plain"
                                    }

                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    context.startActivity(shareIntent)
                                }
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier,
    text: String,
    imgResource: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        Image(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = imgResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = modifier.size(10.dp))
        Text(text = text, modifier = modifier.padding(vertical = 10.dp), style = TextStyle(color = Color.White))
    }
}

