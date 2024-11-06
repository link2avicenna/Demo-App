package com.netag.app.ui.homeUI

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.netag.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PhotosVideosSection(
    modifier: Modifier
) {
    val photosVidosList = listOf(
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        PhotoVideoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1hZxkl7aLUy170veFH3FI9uDbkqoSBjMY2A&s",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

    )

    val context = LocalContext.current
    var vUrl by remember { mutableStateOf("") }
    var thumbnail: Bitmap? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Extract image URLs and video URL for the first item
    val aList: MutableList<String> = mutableListOf()
    photosVidosList.forEach { item ->
        aList.add(item.imageUrl)
        vUrl = item.videoUrl ?: vUrl
    }

    LaunchedEffect(vUrl) {
        isLoading = true
        thumbnail = getVideoThumbnail(vUrl)
        isLoading = false
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        // Display gallery of photos
        PhotoGallery(modifier = Modifier, aList)

        // Display video thumbnail or placeholder
        if (isLoading) {
            Image(
                painter = painterResource(id = R.drawable.ic_vid_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
//                        SessionManager.putvideouri(context, vUrl)
                        // You can add the navigation to video here
                    }
            )
        } else {
            thumbnail?.asImageBitmap()?.let {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
//                                SessionManager.putvideouri(context, vUrl)
                                // You can add the navigation to video here
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icvideo),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                            .height(50.dp)
                            .clickable {
//                                SessionManager.putvideouri(context, vUrl)
                                // You can add the navigation to video here
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        // Button to navigate to photo/video page
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Red), RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp).clickable { /* Add navigation here */ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp).clickable { /* Add navigation here */ }
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { /* Add navigation here */ },
                    text = "Photos & Videos"
                )
            }
        }
    }
}

@Composable
fun PhotoGallery(
    modifier: Modifier,
    aList: MutableList<String>
) {
    LazyRow(
        modifier = modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(aList) { item ->
            PhotoVideoItemCard(item, modifier)
        }
    }
}

@Composable
fun PhotoVideoItemCard(
    item: String,
    modifier: Modifier
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder
                (LocalContext.current).data(data = item).build()
        )
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(80.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                // Add navigation logic for photo view here
            }
    )
    Spacer(modifier = modifier.size(10.dp))
}

// Data class for photos and videos
data class PhotoVideoItem(val imageUrl: String, val videoUrl: String)

suspend fun getVideoThumbnail(videoUrl: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(videoUrl, HashMap())
            retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            retriever.release()
        }
    }
}
