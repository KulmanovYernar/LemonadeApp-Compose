package com.example.lemonadeapp_compose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp_compose.ui.theme.LemonadeAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppComposeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val imageResource = when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    description = R.string.lemon_tree_desc,
                    image = R.drawable.lemon_tree
                )
            }

            2 -> LemonTextAndImage(
                description = R.string.lemon_desc,
                image = R.drawable.lemon_squeeze
            )

            3 -> LemonTextAndImage(
                description = R.string.glass_of_lemonade_desc,
                image = R.drawable.lemon_drink
            )

            4 -> LemonTextAndImage(
                description = R.string.empty_glass_desc,
                image = R.drawable.lemon_restart
            )

            else -> {}
        }
    }
}

@Composable
fun LemonTextAndImage(description: Int, image: Int, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(stringResource(id = description))
        Spacer(modifier = modifier.height(32.dp))
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = description),
            modifier = Modifier
                .wrapContentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeAppComposeTheme {
        LemonadeApp()
    }
}