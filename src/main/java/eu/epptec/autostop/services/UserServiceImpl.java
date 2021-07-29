package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.RatingDTO;
import eu.epptec.autostop.dtos.UserDTO;
import eu.epptec.autostop.model.User;
import eu.epptec.autostop.exceptions.UserNotFoundException;
import eu.epptec.autostop.repositories.PassengerRepository;
import eu.epptec.autostop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return new UserDTO(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new UserDTO(user);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userDTO.toEntity();
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> new UserDTO(user));
    }

    @Override
    public RatingDTO getRating(Long id) {return userRepository.getRating(id);}

    @Override
    public UserDTO replace(UserDTO userDTO, Long id) {
        return userRepository.findById(id)
                .map(oldUser -> {
                    oldUser.setFirstName(userDTO.getFirstName());
                    oldUser.setLastName(userDTO.getLastName());
                    oldUser.setEmail(userDTO.getEmail());
                    oldUser.setPhone(userDTO.getPhone());
                    return new UserDTO(userRepository.save(oldUser));
                })
                .orElseGet(() -> {
                    User user = userDTO.toEntity();
                    user.setId(id);
                    return new UserDTO(userRepository.save(user));
                });
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
