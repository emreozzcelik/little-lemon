package com.emreozcelik.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.emreozcelik.littlelemon.ui.theme.Typography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emreozcelik.littlelemon.ui.theme.LittleLemonColor


@Composable
fun Onboarding(navController: NavHostController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "little lemon logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
                    .height(50.dp)
                    .width(200.dp)
            )


        Text(
            text = "Let's get to know you",
            modifier = Modifier
                .background(color = LittleLemonColor.green)
                .padding(50.dp)
                .fillMaxWidth(),
            fontSize = 30.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Personal information",
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = {firstName = it},
            label = { Text(text = "First name") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName = it},
            label = { Text(text = "Last name") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)

        )
        Button(
            onClick = {
                        if(firstName.isBlank() || lastName.isBlank() || email.isBlank() ) {
                            Toast.makeText(
                                context,
                                "Registration unsuccessful. Please enter all data.",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                        val sharedPreferences = context.getSharedPreferences("LittleLemonPreferences", Context.MODE_PRIVATE)

                        val editor = sharedPreferences.edit()

                        editor.putString("firstName", firstName)
                        editor.putString("lastName", lastName)
                        editor.putString("email", email)


                        editor.apply()

                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Home.route)

                        }

                      },


            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 80.dp),
            shape = RoundedCornerShape(10.dp)


        ) {
            Text(
                text = "Register",
                style =Typography.button,
                color = LittleLemonColor.charcoal
            )
        }

    }

}


