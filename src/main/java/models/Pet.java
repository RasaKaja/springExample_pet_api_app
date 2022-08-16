package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Pet {
    private UUID id;
    private String name;
    private int age;
    private String type;
    private String ownerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Pet() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Pet(String name, int age, String type, String ownerName) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.type = type;
        this.ownerName = ownerName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}