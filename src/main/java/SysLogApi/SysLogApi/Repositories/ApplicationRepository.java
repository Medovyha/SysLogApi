package SysLogApi.SysLogApi.Repositories;

import SysLogApi.SysLogApi.Entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
}
