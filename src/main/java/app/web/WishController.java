package app.web;

import app.model.Wish;
import app.service.WishService;
import app.web.dto.WishRequest;
import app.web.dto.WishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/wishes")
public class WishController {

    @Autowired
    private WishService wishService;

    @GetMapping
    public ResponseEntity<List<Wish>> getWishes() {

        List<Wish> wishList = wishService.getAllWishes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wishList);
    }

    @PostMapping
    public ResponseEntity<WishResponse> createNewWish(@RequestBody WishRequest wishRequest) {

        Wish wish = wishService.createWish(wishRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WishResponse.builder()
                        .status(HttpStatus.CREATED.value())
                        .successful(true)
                        .wish(wish)
                        .message("Successfully created wish")
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishResponse> updateWish(@PathVariable UUID id, @RequestBody WishRequest wishRequest) {

        Wish updatedWish = wishService.editWish(id, wishRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(WishResponse.builder()
                        .status(HttpStatus.OK.value())
                        .successful(true)
                        .wish(updatedWish)
                        .message("Successfully updated wish")
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WishResponse> removeWish (@PathVariable UUID id) {

        wishService.deleteWish(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(WishResponse.builder()
                        .status(HttpStatus.OK.value())
                        .successful(true)
                        .message("Successfully removed wish")
                        .build());
    }
}
