package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.gameElement.Infrastructure;

class TestInfrastructure {
    Infrastructure infra;

    @BeforeEach
    void setUp(){
        infra = new Infrastructure(0, 1, 2, 3, 4, 5);
    }

    @Test
    void getProductionCost() {
        assertEquals(infra.getProductionCost(), 0);
    }

    @Test
    void getFood() {
        assertEquals(infra.getFood(), 1);
    }

    @Test
    void getCulture() {
        assertEquals(infra.getCulture(), 2);
    }

    @Test
    void getFaith() {
        assertEquals(infra.getFaith(), 3);
    }

    @Test
    void getScience() {
        assertEquals(infra.getScience(), 4);
    }

    @Test
    void getGold() {
        assertEquals(infra.getGold(), 5);
    }

}