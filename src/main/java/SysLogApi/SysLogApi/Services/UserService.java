package SysLogApi.SysLogApi.Services;

import SysLogApi.SysLogApi.Entities.User;
import SysLogApi.SysLogApi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).get();
        user.setUsername(userDetails.getUsername());

        return userRepository.save(user);
    }
}
