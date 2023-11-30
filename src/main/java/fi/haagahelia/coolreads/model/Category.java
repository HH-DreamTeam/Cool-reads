package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ReadingRecommendation> recommendations;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public List<ReadingRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<ReadingRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }

}
