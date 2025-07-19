package app;

import app.model.Wish;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class TestBuilder {

    public static Wish aRandomWish() {

        return Wish.builder()
                .id(UUID.randomUUID())
                .description("My wish is...")
                .createdOn(LocalDateTime.now())
                .build();
    }

    public static Wish aRandomEditWish() {

        return Wish.builder()
                .id(UUID.randomUUID())
                .description("My new wish is...")
                .updatedOn(LocalDateTime.now())
                .build();
    }
}
