package com.freshtawi.tawi.presentation.common.bottomNav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String? = null,
    contentDescription: String? = null,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onBackground,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null && alertCount < 0) {
        throw IllegalArgumentException("Alert count can't be negative")
    }

    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(25.dp),
                    tint = if (selected) selectedColor else unselectedColor,
                )
            }
        },
        label = {
            if (title != null) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                    color = if (selected) selectedColor else unselectedColor,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
        },
        alwaysShowLabel = true
    )
}