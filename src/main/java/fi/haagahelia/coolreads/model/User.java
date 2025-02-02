package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuser")
public class User {
    @Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@JsonIgnore
	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private String role;

    @JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<ReadingRecommendation> recommendations;

    public User() {
	}
    
    public User(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public List<ReadingRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRecommendations(List<ReadingRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    
}
