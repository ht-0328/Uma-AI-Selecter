package com.uma_ai_selecter.race.service

import com.uma_ai_selecter.model.HorseDetail
import com.uma_ai_selecter.model.RaceDetail
import com.uma_ai_selecter.model.RaceInfo
import com.uma_ai_selecter.race.client.RaceClient
import org.springframework.stereotype.Service

/**
 * レース予測に関するビジネスロジックを提供するサービス
 */
@Service
class RaceService(
    private val raceClient: RaceClient
) {
    /**
     * レース予測情報を取得する
     *
     * WireMock(JRA-VAN)からデータを取得し、OpenAPI定義のDTOに変換して返却する
     */
    fun getRacePrediction(): RaceDetail {
        val jraResponse = raceClient.fetchRacePrediction()

        return RaceDetail(
            race = RaceInfo(
                name = jraResponse.raceName,
                course = jraResponse.courseInfo,
                trackCondition = jraResponse.trackCondition,
                distance = jraResponse.distance,
                track = jraResponse.trackType
            ),
            horses = jraResponse.horseList.map { horse ->
                HorseDetail(
                    id = horse.horseId,
                    name = horse.name,
                    gateNumber = horse.gate,
                    horseNumber = horse.number,
                    jockeyName = horse.jockey,
                    last3f = horse.last3f,
                    passingOrder = horse.passingOrder,
                    positionScore = horse.positionScore,
                    gateScore = horse.gateScore,
                    courseScore = horse.courseScore,
                    jockeyScore = horse.jockeyScore
                )
            }
        )
    }
}
