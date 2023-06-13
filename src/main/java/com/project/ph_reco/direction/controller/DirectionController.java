package com.project.ph_reco.direction.controller;

import com.project.ph_reco.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";


    @GetMapping("/dir/{encodeId}")
    public String searchDirection(@PathVariable("encodeId") String encodeId) {
        String result = directionService.findDirectionUrlById(encodeId);

        log.info("[DirectionController searchDirection] direction url : {}", result);

        return "redirect:" + result;
    }

}
