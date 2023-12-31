package com.fedorov.andrii.andriiovych.qachallenge.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.qachallenge.R
import com.fedorov.andrii.andriiovych.qachallenge.data.mappers.exceptions.*
import com.fedorov.andrii.andriiovych.qachallenge.domain.models.CategoryModel
import com.fedorov.andrii.andriiovych.qachallenge.domain.models.QuestionModel
import com.fedorov.andrii.andriiovych.qachallenge.presentation.screens.uicomponents.TopText
import com.fedorov.andrii.andriiovych.qachallenge.presentation.viewmodels.BooleanViewModel
import com.fedorov.andrii.andriiovych.qachallenge.presentation.viewmodels.Buttons
import com.fedorov.andrii.andriiovych.qachallenge.presentation.viewmodels.QuestionType
import com.fedorov.andrii.andriiovych.qachallenge.presentation.viewmodels.ScreenState

@Composable
fun BooleanQuizScreen(
    booleanViewModel: BooleanViewModel = hiltViewModel(),
    modifier: Modifier,
    category: CategoryModel
) {
    LaunchedEffect(Unit) {
        booleanViewModel.questionType = QuestionType.BOOLEAN
        booleanViewModel.categoryState = category
        booleanViewModel.getNewQuestion()
    }
    val screenState by booleanViewModel.screenState.collectAsState()
    val buttonTrueColorState by booleanViewModel.buttonTrueColorState.collectAsState()
    val buttonFalseColorState by booleanViewModel.buttonFalseColorState.collectAsState()
    when (screenState) {
        is ScreenState.Success<QuestionModel> -> {
            val questionModel = (screenState as ScreenState.Success<QuestionModel>).value
            BooleanSuccessScreen(
                questionModel = questionModel,
                onTrueClicked = { booleanViewModel.checkCorrectAnswer(Buttons.BUTTON_0.numberButton) },
                onFalseClicked = { booleanViewModel.checkCorrectAnswer(Buttons.BUTTON_1.numberButton) },
                buttonTrueColorState,
                buttonFalseColorState
            )
        }
        is ScreenState.Error -> {
            val message = (screenState as ScreenState.Error).value.message()
            FailureScreen(
                message = message,
                onClickRetry = { booleanViewModel.getNewQuestion() })
        }
        is ScreenState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun BooleanSuccessScreen(
    questionModel: QuestionModel,
    onTrueClicked: () -> Unit,
    onFalseClicked: () -> Unit,
    buttonTrueColorState: Color,
    buttonFalseColorState: Color,
) {
    TopText(text = questionModel.category)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.spaceXXMedium)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Text(
                text = questionModel.question,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        ButtonWithTextBoolean(
            buttonText = stringResource(R.string.tru),
            buttonColorState = buttonTrueColorState,
            onButtonClicked = onTrueClicked
        )

        ButtonWithTextBoolean(
            buttonText = stringResource(R.string.fals),
            buttonColorState = buttonFalseColorState,
            onButtonClicked = onFalseClicked
        )
    }
}

@Composable
fun ButtonWithTextBoolean(
    buttonText: String,
    buttonColorState: Color,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColorState),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.shapeMedium)),
        border = BorderStroke(dimensionResource(id = R.dimen.spaceXXXSmall), Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.spaceXXMedium))
    ) {
        Text(
            text = buttonText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}