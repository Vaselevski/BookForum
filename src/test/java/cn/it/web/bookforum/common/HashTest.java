package cn.it.web.bookforum.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @Test
    void hashString() {
        long startTime = System.currentTimeMillis();
        String input ="nljsahdojfnuolasnuolncvunawldnfjln";
        Hash hash = new Hash();
        hash.hashString(input);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + " milliseconds");
    }
}