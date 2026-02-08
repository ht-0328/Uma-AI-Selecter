package com.uma_ai_selecter.race.service

import com.uma_ai_selecter.race.client.JraHorseResponse
import com.uma_ai_selecter.race.client.JraRaceResponse
import com.uma_ai_selecter.race.client.RaceClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RaceServiceTest {

    @Mock
    private lateinit var raceClient: RaceClient

    @InjectMocks
    private lateinit var raceService: RaceService

    @Test
    fun `getRacePrediction returns mapped race details`() {
        val mockResponse = JraRaceResponse(
            raceId = "123",
            raceName = "Test Race",
            courseInfo = "Test Course",
            trackCondition = "Good",
            distance = 2000,
            trackType = "Turf",
            horseList = listOf(
                JraHorseResponse(
                    horseId = 1,
                    name = "Horse 1",
                    gate = 1,
                    number = 1,
                    jockey = "Jockey 1",
                    last3f = 33.0,
                    passingOrder = "1-1",
                    positionScore = 10.0,
                    gateScore = 5.0,
                    courseScore = 5.0,
                    jockeyScore = 5.0
                )
            )
        )

        `when`(raceClient.fetchRacePrediction()).thenReturn(mockResponse)

        val result = raceService.getRacePrediction()

        assertEquals("Test Race", result.race.name)
        assertEquals("Test Course", result.race.course)
        assertEquals(1, result.horses.size)
        assertEquals("Horse 1", result.horses[0].name)
        assertEquals(33.0, result.horses[0].last3f)
    }
}
