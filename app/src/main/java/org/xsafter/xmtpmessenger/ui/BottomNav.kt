package org.xsafter.xmtpmessenger.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Routing.Main.BottomBar(
    currentRouting: Routing.Main.BottomNav,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    onSelected: (routing: Routing.Main.BottomNav) -> Unit = { }
) {
    BottomNavigation(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = if (isSystemInDarkTheme()) 0.dp else 4.dp
    ) {
        bottomNavRoutings.forEach { routing ->
            val selected = routing == currentRouting

            BottomNavigationItem(
                label = { Text(text = routing.label) },
                icon = { Icon(routing.icon, contentDescription = null) },
                selected = selected,
                onClick = { onSelected(routing) },
                //unselectedContentColor = AmbientContentColor.current.copy(alpha = ContentAlpha.disabled)
            )
        }
    }
}

