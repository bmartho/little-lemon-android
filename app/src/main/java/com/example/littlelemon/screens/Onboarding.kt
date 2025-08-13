package com.example.littlelemon.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Yellow
import com.example.littlelemon.ui.theme.karlaFontFamily
import com.example.littlelemon.ui.theme.markaziFontFamily

@Composable
fun Onboarding(modifier: Modifier, onNavigation: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preferences = PreferencesManager(context)

    Column(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(vertical = 16.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(102.dp)
                .background(color = Green),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                fontFamily = karlaFontFamily,
                color = Color.White,
                fontSize = 24.sp,
                text = "Let\'s get to know you"
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .padding(start = 16.dp),
            fontFamily = markaziFontFamily,
            fontWeight = FontWeight.W600,
            color = Black,
            fontSize = 24.sp,
            text = "Personal information"
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TextFieldComponent(label = "First name", text = firstName) { text ->
                firstName = text
            }

            TextFieldComponent(label = "Last name", text = lastName) { text ->
                lastName = text
            }

            TextFieldComponent(label = "E-mail", text = emailName) { text ->
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
                if (firstName.isBlank() || lastName.isBlank() || emailName.isBlank()) {
                    Toast.makeText(
                        context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    preferences.saveString(USER_FIRST_KEY, firstName)
                    preferences.saveString(USER_LAST_NAME_KEY, lastName)
                    preferences.saveString(USER_EMAIL_KEY, emailName)
                    onNavigation()
                }
            }) {

            Text(
                fontFamily = karlaFontFamily,
                fontWeight = FontWeight.W600,
                color = Black,
                fontSize = 18.sp,
                text = "Register"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(Modifier) {

    }
}