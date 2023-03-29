package org.example.mongo.service;

import org.example.mongo.request.JourneyEntity;
import org.example.mongo.request.ProxyCallBackRequest;

public interface JourneyService {

    String journeyAdd(JourneyEntity request);

    String journeyAddDocument(JourneyEntity request);

    String journeySchedulerBegin(JourneyEntity request);

    String journeySchedulerStart(String params);

    String journeyProxyCallback(ProxyCallBackRequest request);

    String journeyQuery(JourneyEntity request);

}
