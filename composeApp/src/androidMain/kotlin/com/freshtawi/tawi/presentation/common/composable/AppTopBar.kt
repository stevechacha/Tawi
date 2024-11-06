package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    showSearchBar: Boolean = false,
    initialValue: String,
    onSearchParamChange: (searchParam: String) -> Unit,
    showMenuBar: Boolean = false,
    showBackArrow: Boolean = false,
    titleSearch: String = ""
) {
    Surface(
       color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },

                actions = {
                    if (showMenuBar) {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
            AnimatedVisibility(visible = showSearchBar) {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    var searchParam: String by remember { mutableStateOf(initialValue) }
                    val focusRequester = remember { FocusRequester() }
                    val focusManager = LocalFocusManager.current

                    OutlinedTextField(
                        value = searchParam,
                        onValueChange = { newValue ->
                            searchParam = if (newValue.trim().isNotEmpty()) newValue else ""
                            onSearchParamChange(newValue)
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .border(2.dp,Color(0xffD9D9D9))
                            .clip(RoundedCornerShape(8.dp))
                            .focusRequester(focusRequester = focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = titleSearch,
                                color = colorScheme.onSurface.copy(alpha = .32F)
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedBorderColor = Color(0xffD9D9D9),
                            focusedBorderColor = Color(0xffD9D9D9),
                            cursorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearchParamChange(searchParam)
                            }
                        ),
                        trailingIcon = {
                            Row {
                                AnimatedVisibility(visible = searchParam.trim().isNotEmpty()) {
                                    IconButton(onClick = {
                                        focusManager.clearFocus()
                                        searchParam = ""
                                        onSearchParamChange(searchParam)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                }

                                IconButton(onClick = {
                                    onSearchParamChange(searchParam)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewAppTopBar() {
    AppTopBar(
        title = "Search",
        initialValue = "",
        onSearchParamChange = {},
        showMenuBar = true,
        showBackArrow = true,
        showSearchBar = true
    )
}