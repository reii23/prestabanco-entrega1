package com.prestabanco.managment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class LoadManagmentApplicationTests {

    @Test
    void contextLoads() {
    }
}
