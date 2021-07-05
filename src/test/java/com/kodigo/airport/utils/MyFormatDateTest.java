package com.kodigo.airport.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFormatDateTest {

    @Test
    void splitDate() {
        assertEquals("2021-06-12", MyFormatDate.splitDate("2021-06-12 09:52:00"));
    }

    @Test
    void splitTime() {
        assertEquals("09:52:00", MyFormatDate.splitTime("2021-06-12 09:52:00"));
    }
}