package org.example.mongo.controller;

import io.swagger.annotations.ApiOperation;
import org.example.mongo.request.JourneyEntity;
import org.example.mongo.request.ProxyCallBackRequest;
import org.example.mongo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class JourneyController {

    @Autowired
    private JourneyService journey;

    public static final String JOURNEY_ID = "journeyId";

    public static final String USER_ID = "userId";

    public static final String USER_NAME = "userName";

    @ApiOperation("start journey")
    @PostMapping("/journey/{journeyId}/start")
    public void journeySchedulerStart(@PathVariable(JOURNEY_ID) String journeyId) {
        System.out.println("journeyId:"+journeyId);
    }

    @ApiOperation("suspend all instances belongs to the journey")
    @PutMapping("/journey/{journeyId}/suspend")
    public void suspendJourney(@PathVariable(JOURNEY_ID) String journeyId,
                                               @RequestHeader(USER_ID) String userId,
                                               @RequestHeader(USER_NAME) String userName) {
        System.out.println("journeyId:"+journeyId+"-userId："+userId+"-userName："+userName);
    }

    @RequestMapping("/journeyAdd")
    @ResponseBody
    public String journeyAdd(@RequestBody JourneyEntity journeyRequest) {
        return journey.journeyAdd(journeyRequest);
    }

    @RequestMapping("/journeyAddDocument")
    @ResponseBody
    public String journeyAddDocument(@RequestBody JourneyEntity journeyRequest) {
        return journey.journeyAddDocument(journeyRequest);
    }

    @RequestMapping("/journeyQuery")
    @ResponseBody
    public String journeyQuery(@RequestBody JourneyEntity journeyRequest) {
        return journey.journeyQuery(journeyRequest);
    }

    @RequestMapping("/journeySchedulerBegin")
    @ResponseBody
    public String journeySchedulerBegin(@RequestBody JourneyEntity journeyRequest) {
        return journey.journeySchedulerBegin(journeyRequest);
    }


    @RequestMapping("/journeyProxyCallback")
    @ResponseBody
    public String journeyProxyCallback(@RequestBody ProxyCallBackRequest request) {
        return journey.journeyProxyCallback(request);
    }


}


