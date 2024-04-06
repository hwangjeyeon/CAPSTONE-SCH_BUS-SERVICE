package graduation.busstation.hardware.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class HardwareControllerDevTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;


    @DisplayName("정류장 도착 컨트롤러 기능 테스트")
    @Test
    void arrivedControllerTest() throws Exception {

        Map<String, String> input = new HashMap<>();
        input.put("stationName","후문");
        input.put("macAddress", "FC-AA-14-44-4F-81");
        input.put("license","12가4519");

        mockMvc.perform(MockMvcRequestBuilders.patch("/arrived/receive/station")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }


    @DisplayName("정류장 출발 컨트롤러 기능 테스트")
    @Test
    void departedControllerTest() throws Exception{
        Map<String, String> input = new HashMap<>();
        input.put("stationName","후문");
        input.put("macAddress", "FC-AA-14-44-4F-81");
        input.put("license","12가4519");

        mockMvc.perform(MockMvcRequestBuilders.patch("/departed/receive/station")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @DisplayName("마지막 정류장 출발 컨트롤러 기능 테스트")
    @Test
    void lastDepartedControllerTest() throws Exception{
        Map<String, String> input = new HashMap<>();
        input.put("stationName","인문대앞");
        input.put("macAddress", "FC-AA-14-44-4F-85");
        input.put("license","12가4519");

        mockMvc.perform(MockMvcRequestBuilders.patch("/departed/receive/station")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }


}