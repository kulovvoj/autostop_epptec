package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.RatingDTO;
import eu.epptec.autostop.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    UserDTO findById(Long id);
    UserDTO save(UserDTO userDTO);
    Page<UserDTO> findAll(Pageable pageable);
    RatingDTO getRating(Long id);
    UserDTO replace(UserDTO userDTO, Long id);
    void deleteById(Long id);

}
