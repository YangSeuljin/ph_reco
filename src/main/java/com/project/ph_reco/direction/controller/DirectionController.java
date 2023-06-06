package com.project.ph_reco.direction.controller;

import com.project.ph_reco.direction.entity.Direction;
import com.project.ph_reco.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    @GetMapping("/dir/{encodeId}")
    public String searchDirection(@PathVariable("encodeId") String encodeId) {
        Direction resultDirection = directionService.findById(encodeId);

        String params = String.join(",", resultDirection.getTargetPharmacyName(),
                String.valueOf(resultDirection.getTargetLatitude()), String.valueOf(resultDirection.getTargetLongitude()));

        //은혜약국, 38.11, 128.11
        String result = UriComponentsBuilder.fromHttpUrl(DIRECTION_BASE_URL + params).toUriString();

        log.info("direction params: {}, url: {}", params, result);

        return "redirect:" + result;
    }

}
