package app.repository;

import app.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WishRepository extends JpaRepository<Wish, UUID> {
}
