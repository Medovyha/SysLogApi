package SysLogApi.SysLogApi.Controllers;

import SysLogApi.SysLogApi.Entities.Application;
import SysLogApi.SysLogApi.Services.ApplicationService;
import SysLogApi.SysLogApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class ApplicationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value="/applications")
    public List<Application> readApplications(){ return applicationService.getApplications();}

}
