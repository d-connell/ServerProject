package com.dconnell.server.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    private final String username = "name";
    private final String password = "password";
    private final Role roleOne = mock(Role.class);
    private final Role roleTwo = mock(Role.class);
    private final Set<Role> roles = new HashSet<>();
    private final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private final User user = new User();

    @BeforeEach
    public void initialise() {
        user.setUsername(username);
        user.setPassword(password);
        user.setIsEnabled(true);

        roles.add(roleOne);
        roles.add(roleTwo);
        user.setRoles(roles);
        when(roleOne.getName()).thenReturn("roleOne");
        when(roleTwo.getName()).thenReturn("roleTwo");
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("user",
                () -> assertEquals(username, user.getUsername()),
                () -> assertEquals(password, user.getPassword()),
                () -> assertEquals(authorities, user.getAuthorities()),
                () -> assertTrue(user.isEnabled()),
                () -> assertTrue(user.isAccountNonExpired()),
                () -> assertTrue(user.isAccountNonLocked()),
                () -> assertTrue(user.isCredentialsNonExpired())
        );
    }

}