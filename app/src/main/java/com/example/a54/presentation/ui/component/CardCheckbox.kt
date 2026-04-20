package com.example.a54.presentation.ui.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag


@Composable
fun CardCheckbox(
    checked: Boolean,
    onChecked: () -> Unit
) {
    Checkbox(
        modifier = Modifier.testTag("card_checkbox"),
        checked = checked,
        onCheckedChange = { onChecked() },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Blue,
            uncheckedColor = Color.Gray,
            checkmarkColor = Color.Green
        )
    )
}

