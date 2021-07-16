package eu.epptec.autostop.model;

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
}
