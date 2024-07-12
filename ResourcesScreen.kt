package com.plcoding.navigationdrawercompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ResourceItem(val title: String, val content: String)

val resourceItems = listOf(
    ResourceItem("COVID-19 Helpline", "Phone: 123-456-7890"),
    ResourceItem("Emergency Services", "Phone: 911"),
    ResourceItem("Mental Health Support", "Phone: 800-273-8255"),
    ResourceItem("Vaccination Information", "Website: www.vaccineinfo.com"),
    ResourceItem("Local Health Department", "Website: www.localhealthdept.com"),
    ResourceItem("COVID-19 Testing Centers", "Website: www.covidtesting.com"),
    ResourceItem("World Health Organization", "Website: www.who.int"),
    ResourceItem("Centers for Disease Control", "Website: www.cdc.gov"),
    ResourceItem("Local Food Banks", "Website: www.foodbanks.com"),
    ResourceItem("Community Support Groups", "Website: www.supportgroups.com")
)

@Composable
fun ResourcesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "COVID-19 Resources",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        resourceItems.forEach { resourceItem ->
            ResourceCard(resourceItem)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ResourceCard(resourceItem: ResourceItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = resourceItem.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = resourceItem.content,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}
