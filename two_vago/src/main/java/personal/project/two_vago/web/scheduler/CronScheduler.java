package personal.project.two_vago.web.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import personal.project.two_vago.service.UserService;

@Component
public class CronScheduler {
    private final UserService userService;

    public CronScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "19 20 * * *")
    public void loggedInScheduler(){
        userService.setLoggedIn();
    }
}
