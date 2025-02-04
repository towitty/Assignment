package com.twitty.assignment.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    var selectedTabRoute by remember { mutableStateOf<TabRoute>(TabRoute.Search) }
    val onTabSelected = { tabRoute: TabRoute -> selectedTabRoute = tabRoute }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainTab(
                selectedTabRoute = selectedTabRoute,
                onTabSelected = onTabSelected
            )
            selectedTabRoute.content()
        }
    }
}

@Composable
fun MainTab(selectedTabRoute: TabRoute, onTabSelected: (TabRoute) -> Unit) {
    TabRow(
        selectedTabIndex = TabRoute.values().indexOf(selectedTabRoute),
        modifier = Modifier.fillMaxWidth()
    ) {
        TabRoute.values().forEach { tabRoute ->
            Tab(
                selected = tabRoute == selectedTabRoute,
                onClick = { onTabSelected(tabRoute) },
                icon = {
                    Icon(
                        imageVector = tabRoute.icon,
                        contentDescription = stringResource(tabRoute.title),
                        modifier = Modifier.size(24.dp)
                    )
                },
            )
        }
    }
}