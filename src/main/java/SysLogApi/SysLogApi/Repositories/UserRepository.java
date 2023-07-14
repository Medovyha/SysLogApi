package SysLogApi.SysLogApi.Repositories;

import SysLogApi.SysLogApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getOneByUsername(String username);
 }
