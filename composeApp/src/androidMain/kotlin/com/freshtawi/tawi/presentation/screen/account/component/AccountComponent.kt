package com.freshtawi.tawi.presentation.screen.account.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.domain.model.AppSetting

@Composable
fun AccountComponent(
    modifier: Modifier = Modifier,
    appSetting: AppSetting,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick.invoke(appSetting.title) }
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = appSetting.icon),
                contentDescription = appSetting.title,
                modifier = modifier.size(24.dp)
            )
            Column(
                modifier.padding(start = 16.dp, end = 12.dp)
            ) {
                Text(
                    text = appSetting.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                appSetting.subTitle?.let { subTitle ->
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = appSetting.title,
                modifier = modifier.size(24.dp)
            )

        }

    }

}