/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.jetpack.sunflower.ui.layout.garden

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.samples.apps.jetpack.sunflower.R
import com.google.samples.apps.jetpack.sunflower.data.PlantAndGardenPlantings
import com.google.samples.apps.jetpack.sunflower.ui.theme.ButtonAddShape
import com.google.samples.apps.jetpack.sunflower.viewmodels.PlantAndGardenPlantingsViewModel

@Composable
fun GardenPage(
    gardenPlantings: List<PlantAndGardenPlantings>,
    itemClicked: (String) -> Unit,
    noItemClicked: () -> Unit
) {
    val hasPlantings = gardenPlantings.isNotEmpty()
    Box(Modifier.fillMaxSize()) {
        if (hasPlantings)
            GardenContent(gardenPlantings, itemClicked)
        else
            GardenNoContent(noItemClicked)
    }
}

@Composable
fun GardenNoContent(noItemClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.garden_empty),
            style = MaterialTheme.typography.h5
        )
        Button(
            onClick = { noItemClicked() },
            shape = ButtonAddShape(),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text(
                stringResource(R.string.add_plant),
                color = MaterialTheme.colors.primary,
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GardenContent(gardenPlantings: List<PlantAndGardenPlantings>, itemClicked: (String) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.card_side_margin))
            .padding(top = dimensionResource(R.dimen.header_margin))
    ) {
        items(gardenPlantings.size) {
            ItemGarden(PlantAndGardenPlantingsViewModel(gardenPlantings[it]), itemClicked)
        }
    }

}