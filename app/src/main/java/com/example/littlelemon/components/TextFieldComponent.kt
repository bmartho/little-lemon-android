package com.example.littlelemon.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.markaziFontFamily

@Composable
fun TextFieldComponent(
    label: String,
    text: String?,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    Text(
        modifier = Modifier
            .padding(top = 16.dp),
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.W400,
        color = Black,
        fontSize = 16.sp,
        text = label
    )

    TextField(
        value = text ?: "",
        onValueChange = onValueChange,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}