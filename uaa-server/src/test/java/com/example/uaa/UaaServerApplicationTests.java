package com.example.uaa;

import com.example.commons.domain.TblPermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UaaServerApplicationTests {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testPasswordEncoding() {
        System.out.println(bCryptPasswordEncoder.encode("secret"));
        System.out.println(bCryptPasswordEncoder.encode("root"));
        System.out.println(bCryptPasswordEncoder.encode("oodie"));
        System.out.println(bCryptPasswordEncoder.encode("sam"));
        System.out.println(bCryptPasswordEncoder.encode("guest"));
    }
}
