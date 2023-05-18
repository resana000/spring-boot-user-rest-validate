package com.example.demo;

import com.example.demo.dto.UserCriteriaDto;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class UserControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void mailGroup_CreateUser_correctRequest() throws Exception {
        User user = new User();
        user.setName("Johnn");
        user.setEmail("Doe@mail.com");

        mvc.perform(post("/user/create").header("x-Source", "mail")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void mailGroup_CreateUser_emptyEmail_shouldFail() throws Exception {
        User user = new User();
        user.setName("Johnn");
        user.setEmail("");

        mvc.perform(post("/user/create").header("x-Source", "mail")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void mobileGroup_CreateUser_onlyPhone_shouldCorrect() throws Exception {
        User user = new User();
        user.setPhoneNumber("70000000000");
        user.setName("");
        user.setBankId(null);
        user.setEmail("");

        mvc.perform(post("/user/create").header("x-Source", "mobile")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void checkJPAQueryMethod_searchUser_byOnlyPhone_shouldCorrect() throws Exception {
        User user = new User();
        user.setPhoneNumber("70000000000");
        user.setName("Eric");

        mvc.perform(post("/user/create").header("x-Source", "mobile")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        UserCriteriaDto dto = new UserCriteriaDto();
        dto.setPhoneNumber("70000000000");
        mvc.perform(post("/user/search")
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Eric")));
    }
}