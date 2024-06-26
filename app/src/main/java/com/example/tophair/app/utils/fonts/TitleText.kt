package com.example.tophair.app.utils.fonts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun TitleComposable(
    textTitle: String,
    typeTitle: TitleType,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = textTitle,
        modifier = modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = typeTitle.fontSize,
            color = textColor,
            fontWeight = fontWeight,
            textAlign = textAlign
        )
    )
}

@Preview
@Composable
fun TitleComposablePreview() {
    TopHairTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.lorem_ipsum).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H2,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H3,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H4,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H5,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H6,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

