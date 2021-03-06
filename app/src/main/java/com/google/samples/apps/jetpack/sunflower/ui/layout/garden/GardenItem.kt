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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import com.google.samples.apps.jetpack.sunflower.R
import com.google.samples.apps.jetpack.sunflower.ui.layout.ItemSunflowerCard
import com.google.samples.apps.jetpack.sunflower.viewmodels.PlantAndGardenPlantingsViewModel

@Composable
fun ItemGarden(viewModel: PlantAndGardenPlantingsViewModel, clicked: (String) -> Unit) =
    ItemSunflowerCard(
        content = { ItemGardenContent(viewModel) },
        clicked = { clicked(viewModel.plantId) }
    )

@Composable
fun ItemGardenContent(viewModel: PlantAndGardenPlantingsViewModel) {
    Column {
        Image(
            painter = rememberImagePainter(viewModel.imageUrl),
            contentDescription = stringResource(R.string.a11y_plant_item_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.plant_item_image_height))
        )
        Text(
            viewModel.plantName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.margin_normal)),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            stringResource(R.string.plant_date_header),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.margin_normal)),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
        Text(
            viewModel.plantDateString,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            stringResource(R.string.watered_date_header),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.margin_normal)),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
        Text(
            viewModel.waterDateString,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            LocalContext.current.resources
                .getQuantityString(
                    R.plurals.watering_next,
                    viewModel.wateringInterval,
                    viewModel.wateringInterval
                ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.margin_normal)),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
    }
}