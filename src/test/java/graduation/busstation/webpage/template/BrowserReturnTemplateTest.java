package graduation.busstation.webpage.template;

import graduation.busstation.hardware.repository.StationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrowserReturnTemplateTest {

    @Mock
    StationRepository stationRepository;

    @InjectMocks
    BrowserReturnTemplate browserReturnTemplate;

    @DisplayName("사용자 페이지 html을 잘 가져오는지 테스트")
    @Test
    void getBrowserPageTest(){
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        }; // 모델 생성
        String resultMobile = browserReturnTemplate.getBrowserPage("mobile", model);
        String resultWeb = browserReturnTemplate.getBrowserPage("web", model);
        Assertions.assertThat(resultMobile).isEqualTo("webpage.html");
        Assertions.assertThat(resultWeb).isEqualTo("webpage.html");

    }



}