package com.plcoding.navigationdrawercompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    var selectedSymptom by remember { mutableStateOf("Select a symptom") }
    val symptoms = listOf("Fever", "Cough", "Fatigue", "Loss of Taste or Smell")
    val recoveryRateData = listOf(95, 90, 92, 88)
    val symptomData = mapOf(
        "Fever" to listOf(100, 80, 60, 40),
        "Cough" to listOf(90, 70, 50, 30),
        "Fatigue" to listOf(80, 60, 40, 20),
        "Loss of Taste or Smell" to listOf(70, 50, 30, 10)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = "This is the settings screen", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        DropdownMenuDemo(selectedSymptom, symptoms) { symptom ->
            selectedSymptom = symptom
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedSymptom != "Select a symptom") {
            Text(text = "Selected Symptom: $selectedSymptom", fontSize = 18.sp, modifier = Modifier.padding(8.dp))

            Text(text = "Symptom Data", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            SymptomBarChart(symptomData[selectedSymptom] ?: listOf())

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Recovery Rate Data", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            RecoveryRateLineChart(recoveryRateData)

            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(text = "COVID-19 Data Table", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        CovidDataTable()
    }
}

@Composable
fun DropdownMenuDemo(selectedSymptom: String, symptoms: List<String>, onSymptomSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            OutlinedTextField(
                value = selectedSymptom,
                onValueChange = { },
                label = { Text("Select a symptom") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                readOnly = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                symptoms.forEach { symptom ->
                    DropdownMenuItem(onClick = {
                        onSymptomSelected(symptom)
                        expanded = false
                    }) {
                        Text(text = symptom)
                    }
                }
            }
        }
    }
}

@Composable
fun SymptomBarChart(data: List<Int>) {
    Column {
        data.forEachIndexed { index, value ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Week ${index + 1}: ", modifier = Modifier.weight(1f))
                LinearProgressIndicator(
                    progress = value / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                )
                Text(text = "$value%", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun RecoveryRateLineChart(data: List<Int>) {
    Column {
        data.forEachIndexed { index, value ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Week ${index + 1}: ", modifier = Modifier.weight(1f))
                LinearProgressIndicator(
                    progress = value / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                )
                Text(text = "$value%", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CovidDataTable() {
    val covidData = listOf(
        listOf("Country", "Cases", "Recoveries", "Deaths"),
        listOf("USA", "33M", "29M", "600K"),
        listOf("India", "30M", "29M", "400K"),
        listOf("Brazil", "20M", "18M", "550K"),
        listOf("UK", "5M", "4.5M", "128K")
    )

    Column {
        covidData.forEach { row ->
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
