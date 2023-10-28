package com.emreozcelik.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emreozcelik.littlelemon.ui.theme.LittleLemonColor
import com.emreozcelik.littlelemon.ui.theme.Typography

@Composable
fun Profile(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemonPreferences", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""


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
            text = "Personal information",
            modifier = Modifier.padding(vertical = 80.dp, horizontal = 20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )



        OutlinedTextField(
            value = firstName,
            onValueChange = {},
            label = { Text(text = "First name") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled = false
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = {},
            label = { Text(text = "Last name") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled = false
        )

        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text(text = "Email") },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled = false
        )




        Button(
            onClick = {

            val sharedPreferences2 = context.getSharedPreferences("LittleLemonPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences2.edit()

            editor.remove("firstName")
            editor.remove("lastName")
            editor.remove("email")

            editor.apply()

            navController.navigate(Onboarding.route)

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
                text = "Log out",
                style = Typography.button,
                color = LittleLemonColor.charcoal
            )
        }


    }
}
