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

package com.google.samples.apps.jetpack.sunflower.ui.layout.plantlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.google.samples.apps.jetpack.sunflower.R
import com.google.samples.apps.jetpack.sunflower.data.Plant

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantListPage(plants: List<Plant>, itemClicked: (Plant) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.card_side_margin))
            .padding(top = dimensionResource(R.dimen.header_margin))
    ) {
        items(plants.size) {
            ItemPlant(plants[it], itemClicked)
        }
    }
}