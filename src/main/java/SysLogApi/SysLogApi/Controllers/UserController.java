package SysLogApi.SysLogApi.Controllers;

import SysLogApi.SysLogApi.Entities.ApiKey;
import SysLogApi.SysLogApi.Entities.User;
import SysLogApi.SysLogApi.Repositories.UserRepository;
import SysLogApi.SysLogApi.Services.ApiKeyService;
import SysLogApi.SysLogApi.Services.UserService;
import SysLogApi.SysLogApi.utils.GetMd5;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApiKeyService apiKeyService;

    @GetMapping(value="/users")
    public List<User> readEmployees() {
        return userService.getUsers();
    }

    @PostMapping(value="/admin/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @ResponseBody
    public ResponseEntity<String> createEmployee(@RequestBody User user) {
        User usr = userService.createUser(user);

        String key = UUID.randomUUID().toString().replace("-", "");
        String hashedKey = GetMd5.getMd5(key);
        ApiKey apiKey = new ApiKey();
        apiKey.setKey(hashedKey);
        apiKey.setUser(user);

        apiKey = apiKeyService.createApiKey(apiKey);
        usr.setApiKey(apiKey);

        JSONObject response = new JSONObject();
        response.put("ApiKey", user.getUsername()+":"+key);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK) ;
    }
}
