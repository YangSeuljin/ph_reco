package com.project.ph_reco.direction.controller

import com.project.ph_reco.direction.dto.OutputDto
import com.project.ph_reco.pharmacy.service.PharmacyRecommendationService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class FormControllerTest extends Specification {

    private MockMvc mockMvc
    private PharmacyRecommendationService pharmacyRecommendationService = Mock()
    private List<OutputDto> outputDtoList

    def setup() {
        // FormController MockMvc 객체로 만든다.
        mockMvc = MockMvcBuilders.standaloneSetup(new FormController(pharmacyRecommendationService))
                .build();
        outputDtoList = new ArrayList<>()
        outputDtoList.addAll(OutputDto.builder()
                .pharmacyName("pharmacy1")
                .build(),
                OutputDto.builder()
                        .pharmacyName("pharmacy2")
                        .build())
    }

    def "GET /"() {
        expect:
        // FormController 의 "/" URI를 get 방식으로 호출
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.handler().handlerType(FormController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("main"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main"))
                .andDo(MockMvcResultHandlers.log())
    }

    def "POST /search"() {
        given:
        String inputAddress = "서울 성북구 종암동"

        when:
        def resultActions = mockMvc.perform(post("/search")
                .param("address", inputAddress))

        then:
        1 * pharmacyRecommendationService.recommendPharmacyList(argument -> {
            assert argument == inputAddress //mock 객체의 argument 검증
        }) >> outputDtoList

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("output"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("outputFormList")) //model에 outputFormList라는 key가 존재하는지 확인
                .andExpect(MockMvcResultMatchers.model().attribute("outputFormList", outputDtoList))
                .andDo { MockMvcResultHandlers.log() }
    }
}
