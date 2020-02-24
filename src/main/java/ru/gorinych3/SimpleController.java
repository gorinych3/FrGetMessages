package ru.gorinych3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gorinych3.services.FrService;

import java.util.concurrent.ScheduledFuture;

@RestController
public class SimpleController {

    @Autowired
    private FrService service;

    @Autowired
    private TaskScheduler taskScheduler;

    ScheduledFuture<?> scheduledFuture;

    public static final long FIXED_RATE = 1000;

    @PostMapping(value = "/start")
    public void startApp(){
        //service.getAllNMessages();
        //service.getAllMes();
        service.newMethod();
        //service.sched();
    }

    @PostMapping(value = "/startScheduler")
    ResponseEntity<Void> startScheduler(){
        scheduledFuture = taskScheduler.scheduleAtFixedRate(startNewMethod(), FIXED_RATE);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/stopScheduler")
    ResponseEntity<Void> stopScheduler() {
        scheduledFuture.cancel(false);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    private Runnable startNewMethod() {
        return () -> service.newMethod();
    }
}
