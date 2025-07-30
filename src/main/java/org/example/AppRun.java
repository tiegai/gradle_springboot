package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.es.model.ESInfo;
import org.example.es.repository.ESInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component("appRun")
@Slf4j
public class AppRun implements ApplicationRunner {

    @Autowired
    private ESInfoRepo esInfoRepo;

    /*private final EventHandler eventHandler;

    private final EventService eventService;


    public AppRun(EventHandler eventReceiver, EventService eventService) {
        this.eventHandler = eventReceiver;
        this.eventService = eventService;
    }*/

    public void run(ApplicationArguments args) throws Exception {
       // log.info("============Hello, World!");

        ESInfo byId = esInfoRepo.findById("4").get();

        log.info("AppRun提前执行id为:" + byId.getName());

        DaemonThread daemonThread = new DaemonThread();
        daemonThread.run();


        /*log.info("do handler");
        int concurrency = 10;
        eventHandler.handle(concurrency, eventService::doService);*/


    }
}
