package dev.sheengo.weatherservice.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import dev.sheengo.weatherservice.dto.auth.RefreshTokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import dev.sheengo.weatherservice.service.AuthService;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    /**
     * Method under test: {@link AuthController#generateToken(TokenRequest)}
     */
    @Test
    void testGenerateToken() throws Exception {
        when(authService.generateToken(Mockito.<TokenRequest>any())).thenReturn(new TokenResponse());

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setPassword("iloveyou");
        tokenRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(tokenRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/access/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accessToken\":null,\"accessTokenExpiry\":null,\"refreshToken\":null,\"refreshTokenExpiry\":null}"));
    }

    /**
     * Method under test: {@link AuthController#refreshToken(RefreshTokenRequest)}
     */
    @Test
    void testRefreshToken() throws Exception {
        when(authService.refreshToken(Mockito.<RefreshTokenRequest>any())).thenReturn(new TokenResponse());

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(refreshTokenRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/refresh/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accessToken\":null,\"accessTokenExpiry\":null,\"refreshToken\":null,\"refreshTokenExpiry\":null}"));
    }

    /**
     * Method under test: {@link AuthController#update(AuthUserUpdateDTO)}
     */
    @Test
    void testUpdate() throws Exception {
        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        when(authService.update(Mockito.<AuthUserUpdateDTO>any())).thenReturn(authUser);

        AuthUserUpdateDTO authUserUpdateDTO = new AuthUserUpdateDTO();
        authUserUpdateDTO.setEmail("jane.doe@example.org");
        authUserUpdateDTO.setId(1L);
        authUserUpdateDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authUserUpdateDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/auth/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"createdAt\":[1970,1,1,0,0],\"updateAt\":[1970,1,1,0,0],\"createdBy\":1,\"updatedBy\":1,\"deleted"
                                        + "\":true,\"state\":\"ACTIVE\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"role\":\"USER\",\"active"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link AuthController#register(AuthUserCreateDTO)}
     */
    @Test
    void testRegister() throws Exception {
        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        when(authService.create(Mockito.<AuthUserCreateDTO>any())).thenReturn(authUser);

        AuthUserCreateDTO authUserCreateDTO = new AuthUserCreateDTO();
        authUserCreateDTO.setEmail("jane.doe@example.org");
        authUserCreateDTO.setPassword("iloveyou");
        authUserCreateDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authUserCreateDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"createdAt\":[1970,1,1,0,0],\"updateAt\":[1970,1,1,0,0],\"createdBy\":1,\"updatedBy\":1,\"deleted"
                                        + "\":true,\"state\":\"ACTIVE\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"role\":\"USER\",\"active"
                                        + "\":true}"));
    }
}

