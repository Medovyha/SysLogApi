package SysLogApi.SysLogApi.Repositories;

import SysLogApi.SysLogApi.Entities.ApiKey;
import SysLogApi.SysLogApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository  extends JpaRepository<ApiKey, Integer> {
    Optional<ApiKey> findOneByKeyAndUser(String key, User user);
}
