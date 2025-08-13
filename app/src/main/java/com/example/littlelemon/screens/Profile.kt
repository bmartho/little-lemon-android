package com.example.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.components.TextFieldComponent
import com.example.littlelemon.sharedpreference.PreferencesManager
import com.example.littlelemon.sharedpreference.USER_EMAIL_KEY
import com.example.littlelemon.sharedpreference.USER_FIRST_KEY
import com.example.littlelemon.sharedpreference.USER_LAST_NAME_KEY
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.Yellow
import com.example.littlelemon.ui.theme.karlaFontFamily
import com.example.littlelemon.ui.theme.markaziFontFamily

@Composable
fun Profile(modifier: Modifier, onNavigation: () -> Unit) {
    val context = LocalContext.current
    val preferences = PreferencesManager(context)

    var firstName by remember { mutableStateOf(preferences.getString(USER_FIRST_KEY)) }
    var lastName by remember { mutableStateOf(preferences.getString(USER_LAST_NAME_KEY)) }
    var emailName by remember { mutableStateOf(preferences.getString(USER_EMAIL_KEY)) }

    Column(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .padding(top = 16.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )

        Text(
            modifier = Modifier
                .padding(top = 84.dp, start = 16.dp, bottom = 24.dp),
            fontFamily = markaziFontFamily,
            fontWeight = FontWeight.W800,
            color = Black,
            fontSize = 24.sp,
            text = "Personal information"
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TextFieldComponent(
                label = "First name",
                enabled = false,
                text = firstName
            ) { text ->
                firstName = text
            }

            TextFieldComponent(
                label = "Last name",
                enabled = false,
                text = lastName
            ) { text ->
                lastName = text
            }

            TextFieldComponent(
                label = "E-mail",
                enabled = false,
                text = emailName
            ) { text ->
                emailName = text
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Yellow),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                preferences.clearPreferences()
                onNavigation()
            }) {

            Text(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.W600,
                color = Black,
                fontSize = 18.sp,
                text = "Log out"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(Modifier) {

    }
}