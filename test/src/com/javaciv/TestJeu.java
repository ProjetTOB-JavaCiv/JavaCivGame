package com.javaciv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestJeu {
    String test = "";

    @BeforeEach
    void init() {
        test = "test";
    }

    @Test
    void test() {
        assertEquals("test", test);
    }

}
