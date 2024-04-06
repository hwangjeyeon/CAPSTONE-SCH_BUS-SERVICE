package graduation.busstation.webpage.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class WebPageControllerTest {

    @Autowired
    MockMvc mockMvc;


    @DisplayName("리다이렉트 테스트")
    @Test
    void redirectControllerTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isMovedTemporarily())
                .andDo(print());
    }

    @DisplayName("버스정류장 페이지 테스트")
    @Test
    void busStationControllerTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/sch/station/page")
                .header(HttpHeaders.USER_AGENT,"android"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/sch/station/page")
                        .header(HttpHeaders.USER_AGENT,"chrome"))
                .andExpect(status().isOk());
    }

    @DisplayName("버스정류장 페이지 테스트")
    @Test
    void timeTableControllerTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/sch/station/timetable")
                        .header(HttpHeaders.USER_AGENT,"android"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/sch/station/timetable")
                        .header(HttpHeaders.USER_AGENT,"chrome"))
                .andExpect(status().isOk());
    }

}