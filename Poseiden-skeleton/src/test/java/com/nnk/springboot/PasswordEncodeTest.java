package com.nnk.springboot;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 09/03/2019
 * Time: 11:26 AM
 */


public class PasswordEncodeTest {
    @Test
    public void shouldEncodeAndMatchPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String raw = "Abcdef1!";
        String hash = encoder.encode(raw);

        assertNotNull(hash);
        assertTrue(hash.startsWith("$2"));
        assertTrue(encoder.matches(raw, hash));
        assertFalse(encoder.matches("wrong", hash));
    }
}
