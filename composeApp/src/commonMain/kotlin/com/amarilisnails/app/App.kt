package com.amarilisnails.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.amarilisnails.app.navigation.NavigationWrapper
import com.amarilisnails.app.presentation.theme.AmarilisNailsTheme

@Composable
@Preview
fun App() {
    AmarilisNailsTheme {
        NavigationWrapper()
    }
}