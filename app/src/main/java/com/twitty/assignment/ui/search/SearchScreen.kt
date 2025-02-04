package com.twitty.assignment.ui.search

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.twitty.assignment.R

@Composable
fun SearchScreen() {
    Surface {
        Text(stringResource(id = R.string.search))
    }
}