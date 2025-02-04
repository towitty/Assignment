package com.twitty.assignment.ui.favorite

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.twitty.assignment.R

@Composable
fun FavoritesScreen() {
    Surface {
        Text(stringResource(id = R.string.favorites))
    }
}