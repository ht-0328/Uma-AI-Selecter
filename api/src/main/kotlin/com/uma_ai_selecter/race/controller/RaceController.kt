package com.uma_ai_selecter.race.controller

import com.uma_ai_selecter.api.RaceApi
import com.uma_ai_selecter.model.RaceDetail
import com.uma_ai_selecter.race.service.RaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

/**
 * レース予測情報を提供するコントローラー
 */
@RestController
@CrossOrigin
class RaceController(
    private val raceService: RaceService
) : RaceApi {

    /**
     * レース予測情報を取得する
     */
    override fun getRacePrediction(): ResponseEntity<RaceDetail> {
        val raceDetail = raceService.getRacePrediction()
        return ResponseEntity.ok(raceDetail)
    }
}
