package com.example.tophair.app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType

@Preview(showBackground = true)
@Composable
fun CustomButton(
    text: String = "",
    typeText: TextType = TextType.LARGE,
    onClick: () -> Unit = {},
    color: Color = Color(0xFF26A69A),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = color
        ),
        modifier = modifier,
        shape = MaterialTheme.shapes.small
    ) {
        Text(text, fontSize = typeText.fontSize)
    }
}

@Composable
fun CustomIconButton(
    text: String = "",
    typeText: TextType = TextType.EXTRA_SMALL,
    onClick: () -> Unit = {},
    color: Color = Color(0xFF26A69A),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
    painter: Int,
    contentDescription: String? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = color
            ),
            modifier = modifier,
            shape = MaterialTheme.shapes.small
        ) {
            Image(
                painter = painterResource(id = painter),
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text, fontSize = typeText.fontSize,
                color = Color.White
            )
        }
    }
}

@Composable
fun CustomRowWithDividers(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.txt_condicional_inicial),
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text, color = Color.White)
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLogo() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val temaTopHair = Color(red = 4, green = 23, blue = 32)
    Column(
        modifier = Modifier
            .height(88.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = temaTopHair, shape = RectangleShape),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.mipmap.logo_mobile),
                contentDescription = "Logo Top Hair",
                modifier = Modifier
                    .heightIn(max = screenHeight * 10 / 100)
                    .width(240.dp)
            )
        }
    }
}

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEFEFEF),
        ),
        shape = shape
    ) {
        content()
    }
}

@Composable
fun OutlinedTextFieldBackground(
    color: Color,
    content: @Composable () -> Unit
) {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp)
                .background(
                    color,
                    shape = RoundedCornerShape(4.dp)
                )
        )
        content()
    }
}