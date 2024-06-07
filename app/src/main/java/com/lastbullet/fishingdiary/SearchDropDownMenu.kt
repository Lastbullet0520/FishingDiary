package com.lastbullet.fishingdiary

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> SearchableExpandedDropDownMenu(
    modifier: Modifier = Modifier,
    listOfItems: List<T>,
    enable: Boolean = true,
    readOnly: Boolean = true,
    placeholder: @Composable (() -> Unit) = { Text(text = "Select Option") },
    openedIcon: ImageVector = Icons.Outlined.KeyboardArrowUp,
    closedIcon: ImageVector = Icons.Outlined.KeyboardArrowDown,
    parentTextFieldCornerRadius: Dp = 12.dp,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
    ),
    onDropDownItemSelected: (T) -> Unit = {},
    dropdownItem: @Composable (T) -> Unit,
    isError: Boolean = false,
    showDefaultSelectedItem: Boolean = false,
    defaultItemIndex: Int = 0,
    defaultItem: (T) -> Unit,
    onSearchTextFieldClicked: () -> Unit,
) {
    var selectedOptionText by rememberSaveable { mutableStateOf("") }
    var searchedOption by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val baseHeight = 530.dp
    val density = LocalDensity.current

    if (showDefaultSelectedItem) {
        selectedOptionText = selectedOptionText.ifEmpty { listOfItems[defaultItemIndex].toString() }

        defaultItem(
            listOfItems[defaultItemIndex],
        )
    }

    val maxHeight = remember(itemHeights.toMap()) {
        if (itemHeights.keys.toSet() != listOfItems.indices.toSet()) {
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }

        var sum = with(density) { DropdownMenuVerticalPadding.toPx().toInt() } * 2
        for ((_, itemSize) in itemHeights.toSortedMap()) {
            sum += itemSize
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - itemSize / 2).toDp() }
            }
        }
        baseHeight
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                colors = colors,
                textStyle = TextStyle(Color.Black),
                value = selectedOptionText,
                readOnly = readOnly,
                enabled = enable,
                onValueChange = { selectedOptionText = it },
                placeholder = placeholder,
                trailingIcon = {
                    IconToggleButton(
                        checked = expanded,
                        onCheckedChange = {
                            expanded = it
                        },
                    ) {
                        if (expanded) {
                            Icon(
                                imageVector = openedIcon,
                                contentDescription = null,
                            )
                        } else {
                            Icon(
                                imageVector = closedIcon,
                                contentDescription = null,
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(parentTextFieldCornerRadius),
                isError = isError,
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            keyboardController?.show()
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    expanded = !expanded
                                }
                            }
                        }
                    },
            )
        }
        if (expanded) {
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredSizeIn(maxHeight = maxHeight)
                    .absoluteOffset(y = 56.dp), // Adjust offset as needed
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .focusRequester(focusRequester),
                        value = searchedOption,
                        onValueChange = { selectedSport ->
                            searchedOption = selectedSport
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                        },
                        placeholder = {
                            Text(text = "Search")
                        },
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    focusRequester.requestFocus()
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            onSearchTextFieldClicked()
                                        }
                                    }
                                }
                            },
                    )

                    listOfItems.filter {
                        it.toString().contains(
                            searchedOption,
                            ignoreCase = true,
                        )
                    }.forEach { selectedItem ->
                        DropdownMenuItem(
                            onClick = {
                                keyboardController?.hide()
                                selectedOptionText = selectedItem.toString()
                                onDropDownItemSelected(selectedItem)
                                searchedOption = ""
                                expanded = false
                            },
                            text = {
                                dropdownItem(selectedItem)
                            }
                        )
                    }
                }
            }
        }
    }
}

private val DropdownMenuVerticalPadding = 8.dp