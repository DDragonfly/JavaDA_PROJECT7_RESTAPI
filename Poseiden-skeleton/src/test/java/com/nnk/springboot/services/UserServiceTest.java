package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));

        List<User> users = userService.findAll();

        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void findById_shouldReturnUser() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.findById(1);

        assertSame(user, result);
        verify(userRepository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenUserNotFound() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> userService.findById(99));

        assertTrue(ex.getMessage().contains("Invalid user ID"));
        verify(userRepository).findById(99);
    }

    @Test
    void save_shouldHashPasswordAndSaveUser() {
        User user = new User();
        user.setUsername("Pinco");
        user.setPassword("Pallino123!");
        user.setFullname("Pinco Pallino");
        user.setRole("USER");

        // simulate persistence
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User saved =userService.save(user);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User toSave = captor.getValue();

        assertNotNull(toSave.getPassword());
        assertNotEquals("Pallino123!", toSave.getPassword());
        assertTrue(toSave.getPassword().startsWith("$2"));
        assertEquals("Pinco", toSave.getUsername());

        assertNotEquals("Pallino123!", saved.getPassword());
    }

    @Test
    void deleteById_shouldCallRepository() {
        userService.deleteById(5);

        verify(userRepository).deleteById(5);
    }
}
