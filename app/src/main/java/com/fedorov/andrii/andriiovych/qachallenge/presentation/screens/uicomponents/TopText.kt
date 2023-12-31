package com.fedorov.andrii.andriiovych.qachallenge.presentation.screens.uicomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedorov.andrii.andriiovych.qachallenge.R
import com.fedorov.andrii.andriiovych.qachallenge.ui.theme.PrimaryBackgroundPink

@Composable
fun TopText(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = dimensionResource(id = R.dimen.shapeLarge),
                    bottomEnd = dimensionResource(id = R.dimen.shapeLarge)
                )
            )
            .background(color = PrimaryBackgroundPink)
            .fillMaxWidth()
            .border(
                BorderStroke(dimensionResource(id = R.dimen.spaceXXXSmall), Color.Black),
                shape = RoundedCornerShape(
                    bottomStart = dimensionResource(id = R.dimen.shapeLarge),
                    bottomEnd = dimensionResource(id = R.dimen.shapeLarge)
                )
            )

    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.spaceMedium)),
            textAlign = TextAlign.Center
        )
    }
}