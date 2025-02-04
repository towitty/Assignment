package com.twitty.assignment.ui.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.twitty.assignment.R
import com.twitty.assignment.ui.favorite.FavoritesScreen
import com.twitty.assignment.ui.search.SearchScreen
import com.twitty.assignment.ui.theme.AppIcons

sealed interface TabRoute {
    val icon: ImageVector

    @get:StringRes
    val title: Int
    val content: @Composable () -> Unit

    companion object {
        fun values(): List<TabRoute> = listOf(Search, Favorite)
    }

    data object Search : TabRoute {
        override val icon: ImageVector = AppIcons.Search
        override val title: Int = R.string.search
        override val content: @Composable () -> Unit = { SearchScreen() }
    }

    data object Favorite : TabRoute {
        override val icon: ImageVector = AppIcons.Favorite
        override val title: Int = R.string.favorites
        override val content: @Composable () -> Unit = { FavoritesScreen() }
    }
}