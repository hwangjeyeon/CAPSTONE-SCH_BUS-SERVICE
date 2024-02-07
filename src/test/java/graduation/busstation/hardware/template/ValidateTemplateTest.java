package graduation.busstation.hardware.template;

import graduation.busstation.hardware.entity.BusStation;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ValidateTemplateTest {

    @InjectMocks
    ValidateTemplate validateTemplate;

    @Mock
    BusStation busStation;

    @DisplayName("정류장명,mac주소가 틀릴 때, 예외를 잘 던지는가")
    @Test
    void stationValidateTest(){
        HttpServletRequest request = new MockHttpServletRequest();

        assertThatThrownBy(
                () -> validateTemplate.validateTemplate(null, true, request))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("차량번호판이 틀릴 때, 예외를 잘 던지는가")
    @Test
    void licenseValidateTest(){
        HttpServletRequest request = new MockHttpServletRequest();

        assertThatThrownBy(
                () -> validateTemplate.validateTemplate(new BusStation(), false, request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 검증 모두 통과했을 때, 찾은 정류장 정보를 잘 반환하는가")
    @Test
    void validatePassTest(){
        //given
        HttpServletRequest request = new MockHttpServletRequest();
        busStation = new BusStation();
        busStation.setId(1L);

        BusStation findBusStation = validateTemplate.validateTemplate(busStation, true, request);

        assertThat(findBusStation).isEqualTo(busStation);
        assertThat(findBusStation.getId()).isEqualTo(busStation.getId());
    }

}