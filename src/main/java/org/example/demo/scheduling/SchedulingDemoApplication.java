package org.example.demo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.scheduling.events.GenericSpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SchedulingDemoApplication {

    @Autowired
    Config config;
    @Autowired
    ScheduleTaskService scheduleTaskService;

    public static void main(String[] args) {
        SpringApplication.run(SchedulingDemoApplication.class);
    }

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            config.setCron("*/1 * * * * *");
            TimeUnit.SECONDS.sleep(3);
            config.setCron("*/2 * * * * *");
            TimeUnit.SECONDS.sleep(6);
            System.exit(0);
        };
    }

    @EventListener(classes = {GenericSpringEvent.class}, condition = "#event.success")
    public void handleSuccessful(GenericSpringEvent<Config> event) {
        log.info("Handling generic event (conditional).");
        Runnable task = () -> log.info(LocalDateTime.now().toString());
        String taskId = "my_cron_task";
        scheduleTaskService.removeTaskFromScheduler(taskId);
        scheduleTaskService.addTaskToScheduler(taskId, task, config.getCron());
    }

}
