package dev.sheengo.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sheengo.weatherservice.config.security.JwtTokenUtil;
import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import dev.sheengo.weatherservice.dto.auth.RefreshTokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import dev.sheengo.weatherservice.enums.TokenType;
import dev.sheengo.weatherservice.repository.AuthUserRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private AuthUserRepository authUserRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SessionUser sessionUser;

    /**
     * Method under test: {@link AuthServiceImpl#generateToken(TokenRequest)}
     */
    @Test
    void testGenerateToken() throws AuthenticationException {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        TokenResponse tokenResponse = new TokenResponse();
        when(jwtTokenUtil.generateToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any())).thenReturn(tokenResponse);
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setPassword("iloveyou");
        tokenRequest.setUsername("janedoe");
        assertSame(tokenResponse, authServiceImpl.generateToken(tokenRequest));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
        verify(jwtTokenUtil).generateToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#generateToken(TokenRequest)}
     */
    @Test
    void testGenerateToken2() throws AuthenticationException {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtTokenUtil.generateToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any()))
                .thenReturn(new TokenResponse());
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenThrow(new CredentialsExpiredException("Msg"));

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setPassword("iloveyou");
        tokenRequest.setUsername("janedoe");
        assertThrows(CredentialsExpiredException.class, () -> authServiceImpl.generateToken(tokenRequest));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#generateToken(TokenRequest)}
     */
    @Test
    void testGenerateToken3() throws AuthenticationException {
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        when(jwtTokenUtil.generateToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any()))
                .thenReturn(new TokenResponse());
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setPassword("iloveyou");
        tokenRequest.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> authServiceImpl.generateToken(tokenRequest));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#create(AuthUserCreateDTO)}
     */
    @Test
    void testCreate() {
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
        when(authUserRepository.save(Mockito.<AuthUser>any())).thenReturn(authUser);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        AuthUserCreateDTO dto = new AuthUserCreateDTO();
        dto.setEmail("jane.doe@example.org");
        dto.setPassword("iloveyou");
        dto.setUsername("janedoe");
        assertSame(authUser, authServiceImpl.create(dto));
        verify(authUserRepository).save(Mockito.<AuthUser>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#create(AuthUserCreateDTO)}
     */
    @Test
    void testCreate2() {
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
        when(authUserRepository.save(Mockito.<AuthUser>any())).thenReturn(authUser);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new CredentialsExpiredException("Msg"));

        AuthUserCreateDTO dto = new AuthUserCreateDTO();
        dto.setEmail("jane.doe@example.org");
        dto.setPassword("iloveyou");
        dto.setUsername("janedoe");
        assertThrows(CredentialsExpiredException.class, () -> authServiceImpl.create(dto));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refreshToken(RefreshTokenRequest)}
     */
    @Test
    void testRefreshToken() {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtTokenUtil.getRole(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(AuthUser.ROLES.USER);
        TokenResponse tokenResponse = new TokenResponse();
        when(jwtTokenUtil.generateAccessToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any(),
                Mockito.<TokenResponse>any())).thenReturn(tokenResponse);
        when(jwtTokenUtil.getUsername(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn("janedoe");
        when(jwtTokenUtil.getExpiry(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(jwtTokenUtil.isValid(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(true);

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("ABC123");
        assertSame(tokenResponse, authServiceImpl.refreshToken(refreshTokenRequest));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
        verify(jwtTokenUtil).isValid(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).getRole(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).generateAccessToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any(),
                Mockito.<TokenResponse>any());
        verify(jwtTokenUtil).getUsername(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).getExpiry(Mockito.<String>any(), Mockito.<TokenType>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refreshToken(RefreshTokenRequest)}
     */
    @Test
    void testRefreshToken2() {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtTokenUtil.getRole(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenThrow(new UsernameNotFoundException("Msg"));
        when(jwtTokenUtil.generateAccessToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any(),
                Mockito.<TokenResponse>any())).thenThrow(new UsernameNotFoundException("Msg"));
        when(jwtTokenUtil.getUsername(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenThrow(new UsernameNotFoundException("Msg"));
        when(jwtTokenUtil.getExpiry(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenThrow(new UsernameNotFoundException("Msg"));
        when(jwtTokenUtil.isValid(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(true);

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("ABC123");
        assertThrows(UsernameNotFoundException.class, () -> authServiceImpl.refreshToken(refreshTokenRequest));
        verify(jwtTokenUtil).isValid(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).getUsername(Mockito.<String>any(), Mockito.<TokenType>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refreshToken(RefreshTokenRequest)}
     */
    @Test
    void testRefreshToken3() {
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        when(jwtTokenUtil.getRole(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(AuthUser.ROLES.USER);
        when(jwtTokenUtil.generateAccessToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any(),
                Mockito.<TokenResponse>any())).thenReturn(new TokenResponse());
        when(jwtTokenUtil.getUsername(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn("janedoe");
        when(jwtTokenUtil.getExpiry(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(jwtTokenUtil.isValid(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(true);

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("ABC123");
        assertThrows(UsernameNotFoundException.class, () -> authServiceImpl.refreshToken(refreshTokenRequest));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
        verify(jwtTokenUtil).isValid(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).getRole(Mockito.<String>any(), Mockito.<TokenType>any());
        verify(jwtTokenUtil).getUsername(Mockito.<String>any(), Mockito.<TokenType>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refreshToken(RefreshTokenRequest)}
     */
    @Test
    void testRefreshToken4() {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtTokenUtil.getRole(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(AuthUser.ROLES.USER);
        when(jwtTokenUtil.generateAccessToken(Mockito.<String>any(), Mockito.<AuthUser.ROLES>any(),
                Mockito.<TokenResponse>any())).thenReturn(new TokenResponse());
        when(jwtTokenUtil.getUsername(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn("janedoe");
        when(jwtTokenUtil.getExpiry(Mockito.<String>any(), Mockito.<TokenType>any()))
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(jwtTokenUtil.isValid(Mockito.<String>any(), Mockito.<TokenType>any())).thenReturn(false);

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("ABC123");
        assertThrows(CredentialsExpiredException.class, () -> authServiceImpl.refreshToken(refreshTokenRequest));
        verify(jwtTokenUtil).isValid(Mockito.<String>any(), Mockito.<TokenType>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#getAuthUser()}
     */
    @Test
    void testGetAuthUser() {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);
        assertSame(authUser, authServiceImpl.getAuthUser());
        verify(authUserRepository).findById(Mockito.<Long>any());
        verify(sessionUser).id();
    }

    /**
     * Method under test: {@link AuthServiceImpl#getAuthUser()}
     */
    @Test
    void testGetAuthUser2() {
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
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenThrow(new CredentialsExpiredException("Msg"));
        assertThrows(CredentialsExpiredException.class, () -> authServiceImpl.getAuthUser());
        verify(sessionUser).id();
    }

    /**
     * Method under test: {@link AuthServiceImpl#getAuthUser()}
     */
    @Test
    void testGetAuthUser3() {
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        when(sessionUser.id()).thenReturn(1L);
        assertThrows(UsernameNotFoundException.class, () -> authServiceImpl.getAuthUser());
        verify(authUserRepository).findById(Mockito.<Long>any());
        verify(sessionUser).id();
    }

    /**
     * Method under test: {@link AuthServiceImpl#update(AuthUserUpdateDTO)}
     */
    @Test
    void testUpdate() {
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
        when(authUserRepository.save(Mockito.<AuthUser>any())).thenReturn(authUser);

        AuthUserUpdateDTO dto = new AuthUserUpdateDTO();
        dto.setEmail("jane.doe@example.org");
        dto.setId(1L);
        dto.setUsername("janedoe");
        AuthUser actualUpdateResult = authServiceImpl.update(dto);
        assertEquals("janedoe", actualUpdateResult.getUsername());
        assertEquals(1L, actualUpdateResult.getId().longValue());
        assertEquals("jane.doe@example.org", actualUpdateResult.getEmail());
        verify(authUserRepository).save(Mockito.<AuthUser>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#update(AuthUserUpdateDTO)}
     */
    @Test
    void testUpdate2() {
        when(authUserRepository.save(Mockito.<AuthUser>any())).thenThrow(new CredentialsExpiredException("Msg"));

        AuthUserUpdateDTO dto = new AuthUserUpdateDTO();
        dto.setEmail("jane.doe@example.org");
        dto.setId(1L);
        dto.setUsername("janedoe");
        assertThrows(CredentialsExpiredException.class, () -> authServiceImpl.update(dto));
        verify(authUserRepository).save(Mockito.<AuthUser>any());
    }
}

