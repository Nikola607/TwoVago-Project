package personal.project.two_vago.web.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import personal.project.two_vago.service.UserService;

import java.util.logging.Logger;

@Component
public class CronScheduler {
    private final UserService userService;
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(CronScheduler.class));

    public CronScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "*/59 00 00 * * *")
    public void loggedInScheduler(){
        userService.setLoggedIn();
    }
}
