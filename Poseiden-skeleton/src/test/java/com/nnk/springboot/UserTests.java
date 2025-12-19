package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void userTest(){
        User user = new User();
        user.setUsername("user.test");
        user.setPassword("P1@assword.test");
        user.setFullname("User Test");
        user.setRole("USER");

        // Save
        user = userRepository.save(user);
        assertNotNull(user.getId());
        assertEquals("user.test",user.getUsername());

        // Update
        user.setFullname("User Test Update");
        user = userRepository.save(user);
        assertEquals("User Test Update",user.getFullname());

        // Find
        List<User> list = userRepository.findAll();
        assertTrue(list.size()>0);

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> deleted = userRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}
