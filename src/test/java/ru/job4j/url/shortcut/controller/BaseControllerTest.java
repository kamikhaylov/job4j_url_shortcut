package ru.job4j.url.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.url.shortcut.Application;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;

import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.HEADER_AUTHORIZATION;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    protected MvcResult registration(String login, String password,
                                     String site, String name) throws Exception {
        RegistrationRequestDto request = new RegistrationRequestDto(
                login, password, site, name);
        return mockMvc.perform(
                        post("/registration/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                       .andDo(print())
                       .andExpect(status().isCreated())
                       .andExpect(jsonPath("site").value(site))
                       .andReturn();
    }

    protected MvcResult authorization(String login, String password) throws Exception {
        Map<String, String> user = Map.of("login", login, "password", password);
        return mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))
                       .andDo(print())
                       .andExpect(status().is2xxSuccessful())
                       .andReturn();
    }

    protected MvcResult convert(String url, String token) throws Exception {
        LinkRequestDto request = new LinkRequestDto(url);
        return mockMvc.perform(
                        post("/link/convert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                                .header(HEADER_AUTHORIZATION, token))
                       .andDo(print())
                       .andExpect(status().isCreated())
                       .andExpect(jsonPath("code").isNotEmpty())
                       .andReturn();
    }

    protected MvcResult redirect(String code, String url) throws Exception {
        return mockMvc.perform(get("/redirect/" + code))
                       .andDo(print())
                       .andExpect(status().isFound())
                       .andExpect(header().stringValues("Location", url))
                       .andReturn();
    }

    protected MvcResult statistic() throws Exception {
        return mockMvc.perform(
                        get("/statistic/list"))
                       .andDo(print())
                       .andExpect(status().isFound())
                       .andExpect(jsonPath("[0].url").isNotEmpty())
                       .andExpect(jsonPath("[0].total").isNumber())
                       .andReturn();
    }

    protected static String generateString() {
        return UUID.randomUUID().toString();
    }
}
