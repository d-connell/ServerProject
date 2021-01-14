package com.dconnell.server.service;

import com.dconnell.server.model.user.User;
import com.dconnell.server.respository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultUserServiceTest {

    private final UserRepository mockUserRepository = mock(UserRepository.class);
    private final User mockUser = mock(User.class);
    private static final String validUsername = "username";
    private static final String unknownUsername = "unknown";
    private final DefaultUserService defaultUserService = new DefaultUserService(mockUserRepository);

    @Test
    void shouldReturnUserDetailsWhenValidNameGiven() {
        when(mockUserRepository.findUserByUsername(validUsername)).thenReturn(mockUser);
        defaultUserService.loadUserByUsername(validUsername);
        verify(mockUserRepository).findUserByUsername(validUsername);
    }

    @Test
    void throwsErrorIfNullUsernameGiven() {
        assertThrows(UsernameNotFoundException.class, this::attemptToFindNullUser);
    }

    @Test
    void throwsErrorIfUnknownUsernameGiven() {
        assertThrows(UsernameNotFoundException.class, this::attemptToFindUnknownUser);
    }

    private void attemptToFindNullUser() {
        defaultUserService.loadUserByUsername(null);
    }

    private void attemptToFindUnknownUser() {
        when(mockUserRepository.findUserByUsername(unknownUsername)).thenReturn(null);
        defaultUserService.loadUserByUsername(unknownUsername);
    }

}