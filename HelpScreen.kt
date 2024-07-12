package com.plcoding.navigationdrawercompose

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.*
import com.google.android.gms.location.*
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HelpScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }
    var address by remember { mutableStateOf("") }

    val assumedMedicalData = listOf(
        listOf("Facility", "Type", "Distance", "Vaccines Available"),
        listOf("Central Hospital", "Hospital", "2 km", "Pfizer, Moderna"),
        listOf("Downtown Clinic", "Clinic", "1.5 km", "AstraZeneca, J&J"),
        listOf("Westside GP", "GP", "3 km", "Moderna, Pfizer"),
        listOf("Eastside Medical Center", "Medical Center", "4 km", "Pfizer, AstraZeneca")
    )

    val assumedCasesData = listOf(
        listOf("Region", "Active Cases", "Recovered", "Deaths", "Total Cases"),
        listOf("Downtown", "1200", "8000", "50", "9250"),
        listOf("Uptown", "950", "6700", "30", "7680"),
        listOf("Westside", "700", "5400", "40", "6140"),
        listOf("Eastside", "1150", "8200", "60", "9410"),
        listOf("Northside", "850", "6000", "35", "6885"),
        listOf("Southside", "1050", "7500", "45", "8595"),
        listOf("Midtown", "1300", "8700", "55", "10055"),
        listOf("Oldtown", "900", "6200", "25", "7125"),
        listOf("Newtown", "780", "5800", "20", "6600"),
        listOf("Suburbs", "670", "5300", "15", "5985"),
        listOf("Industrial Area", "560", "4900", "10", "5470"),
        listOf("Countryside", "440", "4500", "5", "4945"),
        listOf("Riverside", "650", "5200", "30", "5880"),
        listOf("Seaside", "530", "4600", "20", "5150"),
        listOf("Hilltop", "600", "4800", "25", "5425")
    )

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(locationPermissionState.hasPermission) {
        if (locationPermissionState.hasPermission) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@LaunchedEffect
            }
            fusedLocationClient.lastLocation.addOnSuccessListener { loc: Location? ->
                location = loc
                loc?.let {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    if (addresses != null && addresses.isNotEmpty()) {
                        val addressObj = addresses[0]
                        address = """
                            City: ${addressObj.locality}
                            Post Code: ${addressObj.postalCode}
                            Country: ${addressObj.countryName}
                            County: ${addressObj.subAdminArea}
                        """.trimIndent()
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "This is the help screen", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        when {
            locationPermissionState.hasPermission -> {
                location?.let {
                    Text(text = "Latitude: ${it.latitude}", fontSize = 18.sp)
                    Text(text = "Longitude: ${it.longitude}", fontSize = 18.sp)
                    Text(text = "Altitude: ${it.altitude}", fontSize = 18.sp)
                    Text(text = "Accuracy: ${it.accuracy}", fontSize = 18.sp)
                    Text(text = "Provider: ${it.provider}", fontSize = 18.sp)
                } ?: Text(text = "Retrieving location...", fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                if (address.isNotEmpty()) {
                    Text(text = "Location Information:", fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
                    Text(text = address, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Nearby Medical Facilities:", fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
                MedicalFacilitiesTable(assumedMedicalData)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Nearby Active Cases:", fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
                ActiveCasesTable(assumedCasesData)
            }
            locationPermissionState.shouldShowRationale || !locationPermissionState.permissionRequested -> {
                Column {
                    Text("Location permission is needed to show your current location.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                        Text("Request Permission")
                    }
                }
            }
            else -> {
                Text("Location permission denied. Please enable it in the app settings.")
            }
        }
    }
}

@Composable
fun MedicalFacilitiesTable(data: List<List<String>>) {
    Column {
        data.forEach { row ->
            Row(modifier = Modifier.padding(8.dp)) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Divider()
        }
    }
}

@Composable
fun ActiveCasesTable(data: List<List<String>>) {
    Column {
        data.forEach { row ->
            Row(modifier = Modifier.padding(8.dp)) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Divider()
        }
    }
}
