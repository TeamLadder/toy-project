package team.radder.toychattingproject.controller;

import org.springframework.security.test.context.support.WithMockUser;
import team.radder.toychattingproject.domain.User;
import team.radder.toychattingproject.domain.dto.AddUserRequest;
import team.radder.toychattingproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest            // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc    // MockMvc 생성 및 자동 구성
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void signup() throws Exception{
        // given : 회원가입에 필요한 정보 초기화
        AddUserRequest request = new AddUserRequest();
        request.setEmail("mock_email");
        request.setPassword("mock_pw");
        request.setNickname("mock_nickname");

        // when : POST /user
        ResultActions response = mockMvc.perform(post("/user")
                .param("email", request.getEmail())
                .param("password", request.getPassword())
                .param("nickname", request.getNickname()));

        // then : 호출 결과 HTTP Status code 200
        response.andExpect(status().is3xxRedirection());

        User byEmail = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Assertions.assertNotNull(byEmail);
    }

    @Test
    void login() throws Exception {
        // when : GET /login
        ResultActions response = mockMvc.perform(get("/login"));

        // then : 호출 결과 HTTP Status code 200
        response.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
        // 인증된 사용자를 시뮬레이션
    void loginAuthenticated() throws Exception {
        // when : GET /login
        ResultActions response = mockMvc.perform(get("/login").with(csrf()));

        // then : 호출 결과 리다이렉트 됨
        response.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/chatting"));
    }
}