package SysLogApi.SysLogApi.Services;

import SysLogApi.SysLogApi.Entities.Application;
import SysLogApi.SysLogApi.Repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(Application application){
        return applicationRepository.save(application);
    }

    public List<Application> getApplications(){return applicationRepository.findAll();};

}
