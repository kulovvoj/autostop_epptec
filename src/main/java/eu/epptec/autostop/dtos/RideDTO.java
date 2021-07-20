package eu.epptec.autostop.dtos;

import eu.epptec.autostop.model.Ride;

public class RideDTO {
        private Long id;
        private Integer capacity;

        public RideDTO() {
        }

        public RideDTO(Long id, Integer capacity) {
                this.id = id;
                this.capacity = capacity;
        }

        public RideDTO(Ride ride) {
                this.id = ride.getId();
                this.capacity = ride.getCapacity();
        }

        public Ride toEntity() {
                return new Ride(id, capacity);
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Integer getCapacity() {
                return capacity;
        }

        public void setCapacity(Integer capacity) {
                this.capacity = capacity;
        }
}
