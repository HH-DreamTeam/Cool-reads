package fi.haagahelia.coolreads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.haagahelia.coolreads.model.ReadingRecommendation;

@Repository
public interface ReadingRecommendationRepository extends JpaRepository<ReadingRecommendation, Long> {
    List<ReadingRecommendation> findAllByOrderByCreatedAtDesc();
}
