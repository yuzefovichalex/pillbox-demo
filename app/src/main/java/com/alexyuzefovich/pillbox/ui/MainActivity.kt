package com.alexyuzefovich.pillbox.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alexyuzefovich.pillbox.PillBoxApplication
import com.alexyuzefovich.pillbox.R
import com.alexyuzefovich.pillbox.ui.components.MedicineParametersSheet
import com.alexyuzefovich.pillbox.ui.components.PillColumn
import com.alexyuzefovich.pillbox.ui.extensions.hide
import com.alexyuzefovich.pillbox.ui.extensions.showFull
import com.alexyuzefovich.pillbox.ui.model.BottomSheetVisibilityState
import com.alexyuzefovich.pillbox.ui.theme.BlackAlpha12
import com.alexyuzefovich.pillbox.ui.theme.BottomSheetShape
import com.alexyuzefovich.pillbox.ui.theme.PillBoxTheme
import com.alexyuzefovich.pillbox.ui.viewmodel.MainViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.Factory((application as PillBoxApplication).pillRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which means we need to through handling
        // insets
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()

            SideEffect {
                // Update all of the system bar colors to be transparent, and use
                // dark icons if we're in light theme
                systemUiController.setSystemBarsColor(
                    color = BlackAlpha12
                )
            }

            PillBoxTheme {
                Surface(color = MaterialTheme.colors.primary) {
                    ProvideWindowInsets {
                        PillBoxContent(mainViewModel)
                    }
                }
            }
        }
        mainViewModel.loadPills()
    }

    override fun onBackPressed() {
        if (mainViewModel.isBottomSheetShown) {
            mainViewModel.closeBottomSheet()
        } else {
            super.onBackPressed()
        }
    }

}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun PillBoxContent(mainViewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()

    val systemInsets = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.systemBars)
    val imeInsets = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.ime)

    val keyboardController = LocalSoftwareKeyboardController.current

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val bottomSheetVisibilityState by remember { mainViewModel.bottomSheetVisibilityState }
    val medicineState by remember { mainViewModel.currentMedicineState }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = BottomSheetShape,
        sheetContent = {
            MedicineParametersSheet(
                medicineState = medicineState,
                onSave = {
                    keyboardController?.hide()
                    with(mainViewModel) {
                        saveMedicine(it)
                        closeBottomSheet()
                    }
                }
            )

            when (bottomSheetVisibilityState) {
                is BottomSheetVisibilityState.Opened -> {
                    modalBottomSheetState.showFull(coroutineScope)
                }
                is BottomSheetVisibilityState.Closed -> {
                    modalBottomSheetState.hide(coroutineScope)
                }
            }
        },
        content = {
            BackdropScaffold(
                appBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.h5
                        )
                    }
                },
                backLayerContent = { },
                frontLayerShape = BottomSheetShape,
                frontLayerContent = {
                    PillColumn(
                        pillFlow = mainViewModel.pills,
                        onMedicineCreate = {
                            mainViewModel.startMedicineCreation()
                        },
                        onMedicineDetails = { pill ->
                            mainViewModel.openMedicineDetails(pill)
                        }
                    )
                },
                gesturesEnabled = false
            )
        },
        modifier = Modifier.padding(
            top = systemInsets.calculateTopPadding(),
            bottom = imeInsets.calculateBottomPadding()
        )
    )
}