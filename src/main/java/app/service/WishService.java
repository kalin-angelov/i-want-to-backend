package app.service;

import app.model.Wish;
import app.repository.WishRepository;
import app.web.dto.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public List<Wish> getAllWishes() {
        return wishRepository.findAll();
    }

    public Wish createWish(WishRequest wishRequest) {

        Wish wish = Wish.builder()
                .description(wishRequest.getWish())
                .createdOn(LocalDateTime.now())
                .build();

        wishRepository.save(wish);
        return wish;
    }

    public Wish getWishById(UUID id) {
        return wishRepository.findById(id).orElseThrow();
    }

    public Wish editWish(UUID id, WishRequest wishRequest) {

        Wish wish = getWishById(id);

        wish.setDescription(wishRequest.getWish());
        wish.setUpdatedOn(LocalDateTime.now());

        wishRepository.save(wish);
        return wish;
    }

    public void deleteWish(UUID id) {

        wishRepository.deleteById(id);
    }
}
