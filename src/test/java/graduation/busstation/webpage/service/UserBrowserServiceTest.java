package graduation.busstation.webpage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserBrowserServiceTest {
    private final String[] mobilePattern = {"ANDROID", "TABLET", "IPAD", "MOBILE", "IPHONE"};
    @InjectMocks
    UserBrowserService userBrowserService;

    @DisplayName("모바일 접속 유저 테스트")
    @Test
    void mobileUserConnect(){
        List<String> result = new ArrayList<>();
        for (String s : mobilePattern) {
            result.add(userBrowserService.userBrowserCheck(s));
        }

        for (String s : result) {
            assertThat(s).isEqualTo("mobile");
        }
    }

    @DisplayName("웹 접속 유저 테스트")
    @Test
    void webUserConnect(){
        List<String> result = new ArrayList<>();
        result.add(userBrowserService.userBrowserCheck("Chrome"));
        assertThat(result.get(0)).isEqualTo("web");
    }




}