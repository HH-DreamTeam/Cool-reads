package fi.haagahelia.coolreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.haagahelia.coolreads.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
