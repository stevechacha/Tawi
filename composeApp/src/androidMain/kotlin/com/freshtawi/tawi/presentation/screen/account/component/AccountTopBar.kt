package com.freshtawi.tawi.presentation.screen.account.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.resources.Resources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 30.sp
            )
        },
        actions = {
             IconButton(onClick = { /*TODO*/ }) {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_settings) ,
                     contentDescription = "Settings",
                     modifier = Modifier.size(25.dp)
                 )
             }
        },
        navigationIcon = {
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Image(
                        painter = painterResource(id = Resources.images.vunaLogo),
                        contentDescription = "Vuna Logo",
                        modifier = Modifier.size(25.dp)
                    )
                }

        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )

}