package fi.haagahelia.coolreads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class ReadingRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Link cannot be blank")
    @Pattern(regexp = "https?://.*", message = "Link must start with http:// or https://")
    private String link;

    @NotBlank(message = "Description cannot be blank")
    @Column(length = 1000)
    private String description;

    public ReadingRecommendation() {
    }

    public ReadingRecommendation(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReadingRecommendation [id=" + id + ", title=" + title + ", link=" + link + ", description="
                + description + "]";
    }

}