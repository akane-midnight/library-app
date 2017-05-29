package ru.vsu.va;

import java.util.UUID;

public class IdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString(); //TODO improve
    }
}
