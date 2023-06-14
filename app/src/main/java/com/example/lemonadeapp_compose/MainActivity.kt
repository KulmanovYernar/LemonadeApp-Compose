package com.example.lemonadeapp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Lemonade", fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
    ) { innerPadding ->
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.lemon_tree,
                        descriptionId = R.string.lemon_tree_desc,
                        imageId = R.drawable.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.lemon,
                        descriptionId = R.string.lemon_desc,
                        imageId = R.drawable.lemon_squeeze,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.glass_of_lemonade,
                        descriptionId = R.string.glass_of_lemonade_desc,
                        imageId = R.drawable.lemon_drink,
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }

                4 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.empty_glass,
                        descriptionId = R.string.empty_glass_desc,
                        imageId = R.drawable.lemon_restart,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelId: Int,
    imageId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors =
                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(id = imageId), contentDescription = stringResource(
                        id = descriptionId
                    ), modifier = Modifier
                        .width(128.dp)
                        .height(160.dp)
                        .padding(24.dp)
                )
            }
            Spacer(modifier = modifier.height(32.dp))
            Text(
                text = stringResource(descriptionId),
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeAppComposeTheme {
        LemonadeApp()
    }
}