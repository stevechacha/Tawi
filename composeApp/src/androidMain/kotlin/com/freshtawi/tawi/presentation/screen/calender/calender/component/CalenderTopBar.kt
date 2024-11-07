package com.freshtawi.tawi.presentation.screen.calender.calender.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.resources.Resources


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderTopBar(
    title: String,
    showSearchBar: Boolean = false,
    initialValue: String,
    onSearchParamChange: (searchParam: String) -> Unit,
    showMenuBar: Boolean = false,
    showBackArrow: Boolean = false
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
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 30.sp,
                    )

                },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(Resources.images.vunaIcon),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(24.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                },

                actions = {
                    if (showMenuBar) {
                        IconButton(onClick = { }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_shopping_cart),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
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
                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
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
                            .border(2.dp, Color(0xffD9D9D9))
                            .clip(RoundedCornerShape(8.dp))
                            .focusRequester(focusRequester = focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Product Name / ID",
                                color = Color.Cyan
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = MaterialTheme.colorScheme.onBackground ,
                            errorBorderColor = Color.Red,
                            errorTextColor = Color.Red,
                            focusedBorderColor = Color(0xffD9D9D9),
                            cursorColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary
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