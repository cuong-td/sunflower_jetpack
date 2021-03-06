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

package com.google.samples.apps.jetpack.sunflower.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.google.samples.apps.jetpack.sunflower.R

@Composable
fun CardShape() = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = dimensionResource(R.dimen.card_corner_radius),
    bottomEnd = 0.dp,
    bottomStart = dimensionResource(R.dimen.card_corner_radius)
)

@Composable
fun ButtonAddShape() = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = dimensionResource(R.dimen.button_corner_radius),
    bottomEnd = 0.dp,
    bottomStart = dimensionResource(R.dimen.button_corner_radius)
)