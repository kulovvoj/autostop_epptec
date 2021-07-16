package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.PassengerNotFoundException;
import eu.epptec.autostop.model.Passenger;
import eu.epptec.autostop.model.RatingDTO;
import eu.epptec.autostop.model.User;
import eu.epptec.autostop.exceptions.UserNotFoundException;
import eu.epptec.autostop.repositories.PassengerRepository;
import eu.epptec.autostop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public RatingDTO getRating(Long id) {return userRepository.getRating(id);}

    @Override
    public User replace(User user, Long id) {
        return userRepository.findById(id)
                .map(oldUser -> {
                    oldUser.setFirstName(user.getFirstName());
                    oldUser.setLastName(user.getLastName());
                    oldUser.setEmail(user.getEmail());
                    oldUser.setPhone(user.getPhone());
                    oldUser.setCars(user.getCars());
                    oldUser.setPassengerOfList(user.getPassengerOfList());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
