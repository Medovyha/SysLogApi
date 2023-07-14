package SysLogApi.SysLogApi.Services;

import SysLogApi.SysLogApi.Entities.ApiKey;
import SysLogApi.SysLogApi.Entities.User;
import SysLogApi.SysLogApi.Repositories.ApiKeyRepository;
import SysLogApi.SysLogApi.Repositories.UserRepository;
import SysLogApi.SysLogApi.utils.GetMd5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public ApiKey createApiKey(ApiKey apiKey){
        apiKey = apiKeyRepository.save(apiKey);
        return apiKey;
    }
}

