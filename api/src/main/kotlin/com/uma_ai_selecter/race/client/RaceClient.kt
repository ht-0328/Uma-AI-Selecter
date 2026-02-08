package com.uma_ai_selecter.race.client

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

/**
 * JRA-VAN (WireMock) との通信を行うクライアント
 */
@Component
class RaceClient(
    private val wireMockRestClient: RestClient
) {
    /**
     * レース予測情報をWireMockから取得する
     */
    fun fetchRacePrediction(): JraRaceResponse {
        return wireMockRestClient.get()
            .uri("/jra/races/prediction")
            .retrieve()
            .body(JraRaceResponse::class.java)
            ?: throw RuntimeException("Failed to fetch race data from WireMock")
    }
}

data class JraRaceResponse(
    @JsonProperty("race_id") val raceId: String,
    @JsonProperty("race_name") val raceName: String,
    @JsonProperty("course_info") val courseInfo: String,
    @JsonProperty("track_condition") val trackCondition: String,
    val distance: Int,
    @JsonProperty("track_type") val trackType: String,
    @JsonProperty("horse_list") val horseList: List<JraHorseResponse>
)

data class JraHorseResponse(
    @JsonProperty("horse_id") val horseId: Int,
    val name: String,
    val gate: Int,
    val number: Int,
    val jockey: String,
    @JsonProperty("last_3f") val last3f: Double,
    @JsonProperty("passing_order") val passingOrder: String,
    @JsonProperty("position_score") val positionScore: Double,
    @JsonProperty("gate_score") val gateScore: Double,
    @JsonProperty("course_score") val courseScore: Double,
    @JsonProperty("jockey_score") val jockeyScore: Double
)
