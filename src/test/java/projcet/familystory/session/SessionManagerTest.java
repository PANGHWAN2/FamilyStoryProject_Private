package projcet.familystory.session;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import projcet.familystory.domain.User;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();


    @Test
    void sessionTest(){

        MockHttpServletResponse response = new MockHttpServletResponse();
        User member = new User();
        sessionManager.createSession(member, response);
        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        //세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);
        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }
}
