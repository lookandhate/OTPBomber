package com.example.b0mb3r

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.b0mb3r.services.Services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.Checkbox
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.get
import com.example.b0mb3r.models.NumberToAttackViewModel
import com.example.b0mb3r.models.ServiceViewModel
import com.example.b0mb3r.services.BaseService


import com.example.b0mb3r.ui.theme.B0mb3rTheme

// TODO: Implement data shring using ViewModels https://discord.com/channels/464822298537623562/466302297237094400/874281646017376266

class MainActivity : ComponentActivity() {

    lateinit var Services: Services
    lateinit var Provider: ViewModelProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Address of activity", "This address ${System.identityHashCode(this)} this")

        Services = Services(context = this)
        Provider = ViewModelProvider(this)

        setContent {
            B0mb3rTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    B0mb3rTheme {
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            CheckBoxes()
            NumberInput()
            StartAttackButton()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreen(
    services: Services = Services(context = LocalContext.current),
    numberToAttack: String = "null"
) {
    B0mb3rTheme {
        Column(Modifier.padding(8.dp)) {
            NumberInput()
            Column(modifier = Modifier.padding(vertical = 30.dp)) {
                CheckBoxes()
                Column(modifier = Modifier.padding(vertical = 30.dp)) {
                    StartAttackButton()
                }
            }
        }

    }
}

@Composable
fun CheckBoxes() {

    val provider = (LocalContext.current as MainActivity).Provider
    val services = provider.get<ServiceViewModel>()
    val values = services.mapOfServices.observeAsState().value

    Column(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        values!!.keys.toList().forEach { item ->

            Row(modifier = Modifier.padding()) {
                val isChecked = remember { mutableStateOf(values.getOrDefault(item, true)) }
                Checkbox(
                    checked = isChecked.value,

                    onCheckedChange = {
                        isChecked.value = it;
                        services.mapOfServices.value!!.set(item, isChecked.value)

                    },
                )
                Text(text = item.serviceName)
            }
        }


    }
}


@Composable
fun NumberInput() {
    val provider = (LocalContext.current as MainActivity).Provider
    val viewModel = provider.get(NumberToAttackViewModel::class.java)

    val state = viewModel.Number.observeAsState()

    TextField(
        value = state.value!!,
        onValueChange = { value -> viewModel.Number.value = value },
        label = { Text("Pass phoine number to initiate an attack") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),


        )
}

@Composable
fun StartAttackButton() {
    val provider = (LocalContext.current as MainActivity).Provider
    val numberToAttackViewModel = provider[NumberToAttackViewModel::class.java]
    val servicesViewModel = provider[ServiceViewModel::class.java]

    val currentContext: Context = LocalContext.current

    val numberToAttackState = numberToAttackViewModel.Number.observeAsState()
    val servicesState = servicesViewModel.mapOfServices.observeAsState()

    fun onClick(context: Context) {
        Log.d("OnClick", "Number to attack is ${numberToAttackState.value}")
        Log.d("OnClick", servicesViewModel.GetCheckedServices().toString())

        // TODO Change Placholder 5 to more meaningful number
        numberToAttackState.value?.let {
            startAttack(servicesViewModel.GetCheckedServices(), it, 2,
                { num -> Toast.makeText(context,  "$num attack of 5 completed", Toast.LENGTH_SHORT) },
                { Toast.makeText(context, "All attacks completed", Toast.LENGTH_LONG) }
            )
        }

    }

    Column(modifier = Modifier.wrapContentSize(Alignment.BottomCenter)) {
        Button(
            modifier = Modifier.wrapContentSize(),
            onClick = { onClick(currentContext) }) {
            Text(text = "Start Attack")
        }
    }

}


fun startAttack(
    services: List<BaseService>?,
    numberToAttack: String,
    attacks: Int,
    attackCompletedCallback: (attackNumber: Int) -> Unit,
    allAttacksCompletedCallback: () -> Unit
) {
    for (i in 1..attacks) {
        services!!.forEach { it.execute(numberToAttack);
            Log.d("startAttack", "Sleepinkg a bit");
            Thread.sleep(1_000) }

        attackCompletedCallback(i)
    }
    allAttacksCompletedCallback()

}