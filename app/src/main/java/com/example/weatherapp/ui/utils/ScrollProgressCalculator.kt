package com.example.weatherapp.ui.utils

import androidx.compose.foundation.lazy.LazyListState

fun calculateScrollProgress(
    lazyListState: LazyListState,
    animationTriggerHeight: Float
): Float {
    val progress = if (lazyListState.firstVisibleItemIndex == 0) {
        0f
    } else if (lazyListState.firstVisibleItemIndex == 1) {
        (lazyListState.firstVisibleItemScrollOffset / animationTriggerHeight).coerceIn(
            0.0f,
            1f,
        )
    } else {
        1f
    }
    return progress
}
