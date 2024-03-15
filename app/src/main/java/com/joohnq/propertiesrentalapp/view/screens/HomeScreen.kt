package com.joohnq.propertiesrentalapp.view.screens

import UiState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.model.entities.ContactInfo
import com.joohnq.propertiesrentalapp.model.entities.DetailedType
import com.joohnq.propertiesrentalapp.model.entities.Features
import com.joohnq.propertiesrentalapp.model.entities.Highlight
import com.joohnq.propertiesrentalapp.model.entities.Multimedia
import com.joohnq.propertiesrentalapp.model.entities.Operation
import com.joohnq.propertiesrentalapp.model.entities.Phone1
import com.joohnq.propertiesrentalapp.model.entities.PropertyItem
import com.joohnq.propertiesrentalapp.model.entities.SuggestedTexts
import com.joohnq.propertiesrentalapp.view.components.CustomSnackBarHost
import com.joohnq.propertiesrentalapp.view.components.HomePropertiesCarousel
import com.joohnq.propertiesrentalapp.view.components.MainBottomNavigation
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
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC
import com.joohnq.propertiesrentalapp.view.theme.PropertiesRentalAppTheme
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel?,
    userViewModel: UserViewModel?,
    locationViewModel: LocationViewModel?,
    navController: NavController,
    selectedItemBottomBar: Int,
    onChangeBottomBar: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var search: String by remember { mutableStateOf("") }
    val onSearchChange = { s: String -> search = s }

    val location = locationViewModel?.locationName?.collectAsState()?.value

    val rentOrBuy: Int = mainViewModel?.rentOrBuy?.collectAsState()?.value ?: 0
    val onChangeRentOrBuy = { i: Int -> mainViewModel?.setRentOrBuy(i) }

    val list = listOf(
        PropertyItem(
            address = "arcu",
            bathrooms = 4169,
            contactInfo = ContactInfo(
                agencyLogo = "potenti",
                commercialName = "Danny Sargent",
                contactMethod = "solum",
                contactName = "Marco Harrison",
                micrositeShortName = "Bessie Kramer",
                phone1 = Phone1(
                    formattedPhone = "(289) 756-6337",
                    nationalNumber = false,
                    phoneNumber = "(123) 883-7333",
                    phoneNumberForMobileDialing = "(623) 288-5158",
                    prefix = "utinam"
                ),
                totalAds = 3870,
                userType = "luptatum"
            ),
            country = "South Africa",
            description = "magnis",
            detailedType = DetailedType(typology = "primis"),
            district = "vivendo",
            exterior = false,
            externalReference = "esse",
            favourite = false,
            features = Features(hasAirConditioning = false),
            floor = "nec",
            has360 = false,
            has3DTour = false,
            hasLift = false,
            hasPlan = false,
            hasStaging = false,
            hasVideo = false,
            highlight = Highlight(groupDescription = "tale"),
            labels = listOf(),
            latitude = 10.11,
            locationId = "fusce",
            longitude = 12.13,
            multimedia = Multimedia(images = listOf()),
            municipality = "ludus",
            neighborhood = "persius",
            newDevelopment = false,
            newProperty = false,
            numPhotos = 7275,
            operation = Operation.rent,
            preferenceHighlight = false,
            price = 14.15,
            priceByArea = 16.17,
            propertyCode = "dolores",
            propertyType = "brute",
            province = "tamquam",
            rooms = 3298,
            showAddress = false,
            size = 18.19,
            status = "posse",
            suggestedTexts = SuggestedTexts(
                subtitle = "sonet",
                title = "natoque"
            ),
            thumbnail = "appareat",
            topHighlight = false,
            topNewDevelopment = false,
            topPlus = false,
            urgentVisualHighlight = false,
            url = "http://www.bing.com/search?q=quis",
            visualHighlight = false
        ),
        PropertyItem(
            address = "arcu",
            bathrooms = 4169,
            contactInfo = ContactInfo(
                agencyLogo = "potenti",
                commercialName = "Danny Sargent",
                contactMethod = "solum",
                contactName = "Marco Harrison",
                micrositeShortName = "Bessie Kramer",
                phone1 = Phone1(
                    formattedPhone = "(289) 756-6337",
                    nationalNumber = false,
                    phoneNumber = "(123) 883-7333",
                    phoneNumberForMobileDialing = "(623) 288-5158",
                    prefix = "utinam"
                ),
                totalAds = 3870,
                userType = "luptatum"
            ),
            country = "South Africa",
            description = "magnis",
            detailedType = DetailedType(typology = "primis"),
            district = "vivendo",
            exterior = false,
            externalReference = "esse",
            favourite = false,
            features = Features(hasAirConditioning = false),
            floor = "nec",
            has360 = false,
            has3DTour = false,
            hasLift = false,
            hasPlan = false,
            hasStaging = false,
            hasVideo = false,
            highlight = Highlight(groupDescription = "tale"),
            labels = listOf(),
            latitude = 10.11,
            locationId = "fusce",
            longitude = 12.13,
            multimedia = Multimedia(images = listOf()),
            municipality = "ludus",
            neighborhood = "persius",
            newDevelopment = false,
            newProperty = false,
            numPhotos = 7275,
            operation = Operation.rent,
            preferenceHighlight = false,
            price = 14.15,
            priceByArea = 16.17,
            propertyCode = "dolores",
            propertyType = "brute",
            province = "tamquam",
            rooms = 3298,
            showAddress = false,
            size = 18.19,
            status = "posse",
            suggestedTexts = SuggestedTexts(
                subtitle = "sonet",
                title = "natoque"
            ),
            thumbnail = "appareat",
            topHighlight = false,
            topNewDevelopment = false,
            topPlus = false,
            urgentVisualHighlight = false,
            url = "http://www.bing.com/search?q=quis",
            visualHighlight = false
        )
    )
    val padding = 16.dp

    PropertiesRentalAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GrayFCFCFC),
            snackbarHost = { CustomSnackBarHost(snackBarHostState) },
            bottomBar = {
                MainBottomNavigation(
                    navController = navController,
                    selectedItem = selectedItemBottomBar,
                    onChangeBottomBar = { onChangeBottomBar(it) })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Box(modifier = Modifier.padding(padding)) {
                    Column {
                        Button(onClick = { userViewModel?.logout() }) {
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
                                            text = it,
                                            style = p_20_semi_bold_fs.copy(color = Blue1A1E25)
                                        )
                                    }
                                }

                                is UiState.Failure -> {
                                    location.error?.let { error ->
                                        scope.launch {
                                            snackBarHostState
                                                .showSnackbar(
                                                    message = error,
                                                    duration = SnackbarDuration.Short
                                                )
                                        }
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
                        SearchBarWithFilter(
                            text = search,
                            onTextChange = onSearchChange,
                            onFilterButtonIsClicked = {},
                            onFocusChange = { state: Boolean, hasFocus: Boolean -> }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            stringResource(id = R.string.what_do_you_need),
                            style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        mainViewModel?.rentOrBuyList?.let { rentOrBuyList ->
                            RadioGroupTwice(
                                itemsList = rentOrBuyList,
                                initialSelectedIndex = rentOrBuy
                            ) { index ->
                                onChangeRentOrBuy(index)
                            }
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
                                "234" +
                                        stringResource(id = R.string._properties_in_) + "Suva, Fiji",
                                style = p_13_normal_fs.copy(color = Gray7D7F88)
                            )
                            Box(
                                modifier = Modifier
                                    .clickable {

                                    }
                                    .padding(vertical = 3.dp, horizontal = 5.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.see_all),
                                    style = p_14_medium_fs.copy(color = Gray7D7F88)
                                        .copy(brush = GradientPurpleToPurple),
                                )
                            }
                        }
                    }
                }
                HomePropertiesCarousel(
                    items = list
                )
                Spacer(modifier = Modifier.height(40.dp))
                Box(modifier = Modifier.padding(horizontal = padding)) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        when (location) {
                            is UiState.Success -> {
                                location.data?.let { locationName ->
                                    Text(
                                        stringResource(id = R.string.top_rated_in) + locationName,
                                        style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                                    )
                                }
                            }

                            is UiState.Failure -> {
                                location.error?.let { error ->
                                    scope.launch {
                                        snackBarHostState
                                            .showSnackbar(
                                                message = error,
                                                duration = SnackbarDuration.Short
                                            )
                                    }
                                }
                            }

                            is UiState.Loading -> {
                                Text(
                                    stringResource(id = R.string.top_rated_near_your_location),
                                    style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                                )
                            }

                            else -> {
                                Text(
                                    stringResource(id = R.string.top_rated_near_your_location),
                                    style = p_18_semi_bold_fs.copy(color = Blue1A1E25)
                                )

                            }
                        }
                        Box(
                            modifier = Modifier
                                .clickable {

                                }
                                .padding(vertical = 3.dp, horizontal = 5.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.see_all),
                                style = p_14_medium_fs.copy(color = Gray7D7F88)
                                    .copy(brush = GradientPurpleToPurple),
                            )
                        }
                    }
                }
                HomePropertiesCarousel(
                    items = list
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(null, null, null, navController, 1, {})
}