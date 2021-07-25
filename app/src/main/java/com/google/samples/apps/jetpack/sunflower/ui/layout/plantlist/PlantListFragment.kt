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

import android.os.Bundle
import android.view.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.samples.apps.jetpack.sunflower.HomeViewPagerFragmentDirections
import com.google.samples.apps.jetpack.sunflower.R
import com.google.samples.apps.jetpack.sunflower.utilities.Injector
import com.google.samples.apps.jetpack.sunflower.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        Injector.providePlantListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return ComposeView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUi()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi() {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            (view as? ComposeView)?.setContent {
                PlantListPage(plants) {
                    val direction =
                        HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                            it.plantId
                        )
                    findNavController().navigate(direction)
                }
            }
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
}
