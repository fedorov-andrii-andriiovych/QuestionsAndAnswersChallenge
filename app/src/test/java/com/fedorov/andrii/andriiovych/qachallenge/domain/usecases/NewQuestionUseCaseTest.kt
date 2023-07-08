package com.fedorov.andrii.andriiovych.qachallenge.domain.usecases

import com.fedorov.andrii.andriiovych.qachallenge.domain.models.QuestionModel
import com.fedorov.andrii.andriiovych.qachallenge.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.qachallenge.presentation.viewmodels.ResultOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.kotlin.mock

class NewQuestionUseCaseTest {
    companion object{
        private const val ERROR = "Error"
        private const val HARD = "Hard"
        private const val BOOLEAN = "Boolean"
        private const val ONE = "One"

    }

    private val networkRepository = mock<NetworkRepository>()

    @Test
     fun `should return the same data from repository`()  = runBlocking {

        val testData = ResultOf.Success(
            value = QuestionModel(
                category = ONE,
                difficulty = HARD,
                type = BOOLEAN
            )
        )
        Mockito.`when`(networkRepository.getNewQuestion(anyInt(), anyString(), anyString())).thenReturn(testData)

        val useCase = NewQuestionUseCase(networkRepository = networkRepository)

        val result = useCase.getNewQuestion(anyInt(), anyString(), anyString()) as ResultOf.Success
        val actual = result.value
        val expected = QuestionModel(category = ONE, difficulty = HARD, type = BOOLEAN)

        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `should return error from repository`()  = runBlocking {

        val testData = ResultOf.Failure(message = ERROR)
        Mockito.`when`(networkRepository.getNewQuestion(anyInt(), anyString(), anyString())).thenReturn(testData)

        val useCase = NewQuestionUseCase(networkRepository = networkRepository)

        val result = useCase.getNewQuestion(anyInt(), anyString(), anyString()) as ResultOf.Failure
        val actual = result.message
        val expected = ERROR

        Assertions.assertEquals(actual, expected)
    }
}