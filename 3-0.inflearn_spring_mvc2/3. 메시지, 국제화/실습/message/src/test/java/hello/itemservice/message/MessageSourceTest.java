package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    // MessageSourceTest 추가 - 기본
    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("hello");
    }

    // MessageSourceTest 추가 - 메시지가 없는 경우, 기본 메시지
    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    // MessageSourceTest 추가 - 매개변수 사용
    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("hello Spring");
    }

    // MessageSourceTest 추가 - 국제화 파일 선택1
    // Locale 정보가 없는 경우 Locale.getDefault()을 호출해서 시스템의 기본 로케일 사용
    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("hello");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕하세요");
    }

    // MessageSourceTest 추가 - 국제화 파일 선택2
    @Test
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
