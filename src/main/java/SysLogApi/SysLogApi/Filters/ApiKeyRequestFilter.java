package SysLogApi.SysLogApi.Filters;
import SysLogApi.SysLogApi.Entities.ApiKey;
import SysLogApi.SysLogApi.Entities.User;
import SysLogApi.SysLogApi.Repositories.ApiKeyRepository;
import SysLogApi.SysLogApi.Repositories.UserRepository;
import SysLogApi.SysLogApi.utils.GetMd5;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@Component
public class ApiKeyRequestFilter extends GenericFilterBean {
    @Autowired
    private ApiKeyRepository apiKeyRepository;
    @Autowired
    private UserRepository userRepository;

//    public ApiKeyRequestFilter(ApiKeyRepository apiKeyRepository){
//        this.apiKeyRepository = apiKeyRepository;
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if(path.startsWith("/api") == false){
            chain.doFilter(request, response);
            return;
        }

        String auth = req.getHeader("ApiKey") == null ? "" : req.getHeader("ApiKey");
        String[] values = auth.split(":");
        String username = values[0];
        String key = values[1];
        User user = userRepository.getOneByUsername(username);
        System.out.println(GetMd5.getMd5(key));
        Optional<ApiKey> apiKeyOptional = this.apiKeyRepository.findOneByKeyAndUser(GetMd5.getMd5(key), user);
        if(apiKeyOptional.isPresent()){
            if(!path.startsWith("/api/admin")){
                chain.doFilter(request, response);
            }
            else if(apiKeyOptional.get().getUser().isAdmin()){
                chain.doFilter(request, response);
            }
            else {
                HttpServletResponse resp = (HttpServletResponse) response;
                String error = "Restricted";

                resp.reset();
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentLength(error .length());
                response.getWriter().write(error);
            }

        } else{
            HttpServletResponse resp = (HttpServletResponse) response;
            String error = "Invalid API KEY";

            resp.reset();
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentLength(error .length());
            response.getWriter().write(error);
        }

    }

}