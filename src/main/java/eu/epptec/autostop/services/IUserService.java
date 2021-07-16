package eu.epptec.autostop.services;

import eu.epptec.autostop.model.RatingDTO;
import eu.epptec.autostop.model.User;

import java.util.List;

public interface IUserService {
    User findById(Long id);
    User save(User user);
    List<User> findAll();
    RatingDTO getRating(Long id);
    User replace(User user, Long id);
    void deleteById(Long id);

}
