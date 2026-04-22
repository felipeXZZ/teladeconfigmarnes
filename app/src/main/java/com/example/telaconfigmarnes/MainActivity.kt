package com.example.telaconfigmarnes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    background = Color(0xFFECE8F1)
                )
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFECE8F1)
                ) { innerPadding ->
                    SettingsContainer(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

val HeaderTextStyle = TextStyle(
    fontSize = 28.sp,
    fontWeight = FontWeight.ExtraBold,
    color = Color.Black
)

@Composable
fun SettingsContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFECE8F1))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileTopCircle()
        Spacer(modifier = Modifier.height(8.dp))
        SettingsHeader()
        Spacer(modifier = Modifier.height(18.dp))
        SettingsImage()
        Spacer(modifier = Modifier.height(12.dp))
        SettingsCheckbox()
        Spacer(modifier = Modifier.height(12.dp))
        SettingsSwitch()
        Spacer(modifier = Modifier.height(12.dp))
        SettingsSlider()
        Spacer(modifier = Modifier.height(12.dp))
        SettingsRadioButtons()
        Spacer(modifier = Modifier.height(20.dp))
        SettingsAlertDialog()
    }
}

@Composable
fun ProfileTopCircle() {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color.White)
    )
}

@Composable
fun SettingsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = HeaderTextStyle
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.settings_icon_description),
            tint = Color.Black
        )
    }
}

@Composable
fun SettingsImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_profile_image),
            fontSize = 18.sp,
            color = Color.Black
        )

        Image(
            painter = painterResource(id = R.drawable.perfilfoto),
            contentDescription = stringResource(id = R.string.settings_profile_image),
            modifier = Modifier
                .size(34.dp)
                .clickable { }
                .clip(RoundedCornerShape(2.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SettingsCheckbox() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_consent),
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Composable
fun SettingsSwitch() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_mobile_data),
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Composable
fun SettingsSlider() {
    var sliderValue by remember { mutableFloatStateOf(0.2f) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.settings_text_size),
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..1f,
            steps = 3,
            modifier = Modifier.width(140.dp)
        )
    }
}

@Composable
fun SettingsRadioButtons() {
    var selectedPaymentMethod by remember { mutableStateOf("Paypal") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.payment_method),
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        val options = listOf(
            stringResource(id = R.string.paypal),
            stringResource(id = R.string.credit_card),
            stringResource(id = R.string.bank_transfer)
        )

        options.forEach { paymentMethod ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                RadioButton(
                    selected = selectedPaymentMethod == paymentMethod,
                    onClick = { selectedPaymentMethod = paymentMethod },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF18245C)
                    )
                )

                Text(
                    text = paymentMethod,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SettingsAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF18245C),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(120.dp)
            .height(50.dp)
    ) {
        Text(text = stringResource(id = R.string.sign_out))
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = stringResource(id = R.string.alert_title))
            },
            text = {
                Text(text = stringResource(id = R.string.alert_message))
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color(0xFFECE8F1)
        )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFECE8F1)
        ) { innerPadding ->
            SettingsContainer(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }
}