package com.joohnq.propertiesrentalapp.view.screens

import UiState
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.commandiron.compose_loading.Circle
import com.google.android.gms.location.FusedLocationProviderClient
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.model.entities.Result
import com.joohnq.propertiesrentalapp.model.entities.Section
import com.joohnq.propertiesrentalapp.view.components.HomePropertiesCarousel
import com.joohnq.propertiesrentalapp.view.components.RadioGroupTwice
import com.joohnq.propertiesrentalapp.view.components.SearchBarWithFilter
import com.joohnq.propertiesrentalapp.view.components.p_13_normal_fs
import com.joohnq.propertiesrentalapp.view.components.p_14_medium_fs
import com.joohnq.propertiesrentalapp.view.components.p_14_normal_fs
import com.joohnq.propertiesrentalapp.view.components.p_18_semi_bold_fs
import com.joohnq.propertiesrentalapp.view.components.p_20_semi_bold_fs
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.GradientPurpleToPurple
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.Purple6246EA
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PropertyViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    rentOrBuy: String = Section.TO_RENT,
    location: UiState<String?> = UiState.None,
    nearYourLocation: UiState<List<Result>?> = UiState.None,
    topRated: UiState<List<Result>?> = UiState.None,
    autoCompleteLocation: UiState<AutoCompleteRequest> = UiState.None,
    context: Context? = null,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    fusedLocationClient: FusedLocationProviderClient? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    mainViewModel: MainViewModel = viewModel(modelClass = MainViewModel::class.java),
    userViewModel: UserViewModel = viewModel(modelClass = UserViewModel::class.java),
    propertyViewModel: PropertyViewModel = viewModel(modelClass = PropertyViewModel::class.java),
    locationViewModel: LocationViewModel = viewModel(modelClass = LocationViewModel::class.java),
    navController: NavController = rememberNavController(),
    onErrorAction: (String) -> Unit = {},
    onChangeRentOrBuy: (Int) -> Unit = {}
) {
    var search: String by remember { mutableStateOf("") }
    val onSearchChange = { s: String -> search = s }
    val paddingTop = padding.calculateTopPadding()
    val paddingHorizontal = padding.calculateRightPadding(layoutDirection = LayoutDirection.Rtl)
    val paddingBottom = padding.calculateBottomPadding()
    val rentOrBuySelection = if (rentOrBuy == Section.TO_RENT) 0 else 1

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(padding)
    ) {
        Button(onClick = { userViewModel.logout() }) {
            Text("Sair")
        }
        Text(
            stringResource(id = R.string.find_your_place_in),
            style = p_14_normal_fs.copy(color = Gray7D7F88)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_gradient),
                contentDescription = stringResource(id = R.string.filter),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(10.dp))
            when (location) {
                is UiState.Success -> {
                    location.data?.let {
                        Text(
                            text = it, style = p_20_semi_bold_fs.copy(color = Blue1A1E25)
                        )
                    }
                }

                is UiState.Failure -> {
                    location.error?.let { e ->
                        onErrorAction(e)
                    }
                }

                is UiState.Loading -> {
                    Text(
                        text = stringResource(id = R.string.loading),
                        style = p_20_semi_bold_fs.copy(color = Blue1A1E25)
                    )
                }

                else -> {
                    Text(
                        stringResource(id = R.string.error),
                        style = p_20_semi_bold_fs.copy(color = Blue1A1E25)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        SearchBarWithFilter(text = search,
            onTextChange = onSearchChange,
            onFilterButtonIsClicked = {},
            onFocusChange = { state: Boolean, hasFocus: Boolean -> })
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            stringResource(id = R.string.what_do_you_need),
            style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
        )
        Spacer(modifier = Modifier.height(15.dp))
        RadioGroupTwice(
            itemsList = mainViewModel.rentOrBuyList, initialSelectedIndex = rentOrBuySelection
        ) { index ->
            onChangeRentOrBuy(index)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            stringResource(id = R.string.near_your_location),
            style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                "234 " + stringResource(id = R.string._properties_in_) + " Suva, Fiji",
                style = p_13_normal_fs.copy(color = Gray7D7F88)
            )
            Box(modifier = Modifier
                .clickable {

                }
                .padding(vertical = 3.dp, horizontal = 5.dp)) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    style = p_14_medium_fs.copy(color = Gray7D7F88)
                        .copy(brush = GradientPurpleToPurple),
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        when (nearYourLocation) {
            is UiState.Success -> {
                nearYourLocation.data?.let { properties: List<Result> ->
                    HomePropertiesCarousel(
                        items = properties.take(5),
                        section = rentOrBuy
                    )
                }
            }

            is UiState.Loading -> {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .heightIn(min = 100.dp)
                        .fillMaxWidth()
                ) {
                    Circle(size = 30.dp, color = Purple6246EA)
                }
            }

            is UiState.Failure -> {
                nearYourLocation.error?.let { e ->
                    onErrorAction(e)
                }
            }

            else -> Unit
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            when (location) {
                is UiState.Success -> {
                    location.data?.let {
                        Text(
                            stringResource(id = R.string.top_rated_in, it),
                            style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                        )
                    }
                }

                is UiState.Failure -> {
                    location.error?.let { e ->
                        onErrorAction(e)
                    }
                }

                else -> {
                    Text(
                        stringResource(id = R.string.top_rated_near_your_location),
                        style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                    )
                }
            }
            Box(modifier = Modifier
                .clickable {

                }
                .padding(vertical = 3.dp, horizontal = 5.dp)) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    style = p_14_medium_fs.copy(color = Gray7D7F88)
                        .copy(brush = GradientPurpleToPurple),
                )
            }
        }
        when (topRated) {
            is UiState.Success -> {
                topRated.data?.let { properties: List<Result> ->
                    HomePropertiesCarousel(
                        items = properties.take(5),
                        section = rentOrBuy
                    )
                }
            }

            is UiState.Loading -> {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .heightIn(min = 100.dp)
                        .fillMaxWidth()
                ) {
                    Circle(size = 30.dp, color = Purple6246EA)
                }
            }

            is UiState.Failure -> {
                topRated.error?.let { e ->
                    onErrorAction(e)
                }
            }

            else -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}