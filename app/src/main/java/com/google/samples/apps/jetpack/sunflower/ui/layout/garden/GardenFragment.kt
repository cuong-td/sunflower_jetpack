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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.samples.apps.jetpack.sunflower.HomeViewPagerFragmentDirections
import com.google.samples.apps.jetpack.sunflower.R
import com.google.samples.apps.jetpack.sunflower.adapters.PLANT_LIST_PAGE_INDEX
import com.google.samples.apps.jetpack.sunflower.utilities.Injector
import com.google.samples.apps.jetpack.sunflower.viewmodels.GardenPlantingListViewModel

class GardenFragment : Fragment() {

    private val viewModel: GardenPlantingListViewModel by viewModels {
        Injector.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUi()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeUi() {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) {
            (view as? ComposeView)?.setContent {
                GardenPage(it,
                    itemClicked = {
                        val direction = HomeViewPagerFragmentDirections
                            .actionViewPagerFragmentToPlantDetailFragment(it)
                        findNavController().navigate(direction)
                    },
                    noItemClicked = {
                        navigateToPlantListPage()
                    }
                )
            }
        }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }
}
