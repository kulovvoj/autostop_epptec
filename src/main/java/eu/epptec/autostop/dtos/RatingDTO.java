package eu.epptec.autostop.dtos;

import java.util.Objects;

public class RatingDTO {
    Long userId;
    Integer rating;

    public RatingDTO() {
    }

    public RatingDTO(Long userId, Integer rating) {
        this.userId = userId;
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO ratingDTO = (RatingDTO) o;
        return Objects.equals(userId, ratingDTO.userId) && Objects.equals(rating, ratingDTO.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rating);
    }
}
