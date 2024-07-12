package com.plcoding.navigationdrawercompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NewsItem(val icon: ImageVector, val headline: String)

val newsItems = listOf(
    NewsItem(Icons.Default.Info, "COVID-19 Vaccination Drive Accelerates"),
    NewsItem(Icons.Default.Info, "New COVID-19 Variants Emerging"),
    NewsItem(Icons.Default.Info, "Global COVID-19 Case Numbers Decline"),
    NewsItem(Icons.Default.Info, "COVID-19 Vaccine Booster Shots Approved"),
    NewsItem(Icons.Default.Info, "Schools Reopen Amid COVID-19 Safety Measures"),
    NewsItem(Icons.Default.Info, "COVID-19 Travel Restrictions Lifted"),
    NewsItem(Icons.Default.Info, "New Research on COVID-19 Long-Term Effects"),
    NewsItem(Icons.Default.Info, "Healthcare Workers' Efforts in the COVID-19 Pandemic"),
    NewsItem(Icons.Default.Info, "COVID-19 and Mental Health: Coping Strategies"),
    NewsItem(Icons.Default.Info, "COVID-19 Vaccines: Myths vs Facts")
)

@Composable
fun NewsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "COVID-19 News",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        newsItems.forEach { newsItem ->
            NewsCard(newsItem)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NewsCard(newsItem: NewsItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = newsItem.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = newsItem.headline,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
