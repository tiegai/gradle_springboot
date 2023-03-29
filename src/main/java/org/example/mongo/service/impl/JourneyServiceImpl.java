package org.example.mongo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.mongo.common.CommonUtils;
import org.example.mongo.entity.*;
import org.example.mongo.entity.Email.EmailNode;
import org.example.mongo.entity.audience.AudienceNode;
import org.example.mongo.entity.inbox.InboxNode;
import org.example.mongo.entity.push.PushNode;
import org.example.mongo.entity.randomChoice.RandomChoiceNode;
import org.example.mongo.entity.sms.SmsNode;
import org.example.mongo.entity.traitChoice.TraitChoiceNode;
import org.example.mongo.request.JourneyEntity;
import org.example.mongo.request.ProxyCallBackRequest;
import org.example.mongo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String journeyAddDocument(JourneyEntity request) {
        mongoTemplate.save(request);
        return "SUCCESS";
    }

    @Override
    public String journeyAdd(JourneyEntity request) {
        //1.basicInfo
        JourneyBasicInfo basicInfo = new JourneyBasicInfo();
        basicInfo.setId(request.getId());
        basicInfo.setVersion(request.getVersion());
        basicInfo.setName(request.getName());
        basicInfo.setDescription(request.getDescription());
        basicInfo.setProgramId(request.getProgramId());
        basicInfo.setCampaignId(request.getCampaignId());
        basicInfo.setSubCampaignId(request.getSubCampaignId());
        basicInfo.setPeriodicBegin(request.getPeriodicBegin());
        basicInfo.setPeriodicEnd(request.getPeriodicEnd());
        basicInfo.setPeriodicValues(request.getPeriodicValues());
        basicInfo.setPeriodicTimes(request.getPeriodicTimes());
        basicInfo.setNextStartTime(request.getNextStartTime());
        basicInfo.setAudienceId(request.getAudienceId());
        basicInfo.setAudienceName(request.getAudienceName());
        basicInfo.setStartActivityId(request.getStartActivityId());
        basicInfo.setCreatedBy(request.getCreatedBy());
        basicInfo.setCreatedTime(request.getCreatedTime());
        basicInfo.setCreatedByName(request.getCreatedByName());
        basicInfo.setUpdatedBy(request.getUpdatedBy());
        basicInfo.setUpdatedByName(request.getUpdatedByName());
        basicInfo.setUpdatedTime(request.getUpdatedTime());
        basicInfo.setApprovedBy(request.getApprovedBy());
        basicInfo.setApprovedByName(request.getApprovedByName());
        basicInfo.setApprovedTime(request.getApprovedTime());
        basicInfo.setSuspendedBy(request.getSuspendedBy());
        basicInfo.setSuspendedByName(request.getSuspendedByName());
        basicInfo.setSuspendedTime(request.getSuspendedTime());
        basicInfo.setStatus(request.getStatus());
        mongoTemplate.save(basicInfo);
        //2.notes
        Object[] journeyActivities = request.getJourneyActivities();
        for (Object journeyActivity : journeyActivities) {
            Map<String, Object> objMap = (Map<String, Object>) journeyActivity;
            String category = (String) objMap.get("category");
            if ("START".equals(category)) {
                StartNode startNode = new ObjectMapper().convertValue(journeyActivity, StartNode.class);
                mongoTemplate.save(startNode);
            } else if ("AUDIENCE".equals(category)) {
                AudienceNode audienceNode = new ObjectMapper().convertValue(journeyActivity, AudienceNode.class);
                mongoTemplate.save(audienceNode);
            }else if("TRAIT_CHOICE".equals(category)){
                TraitChoiceNode traitChoiceNode = new ObjectMapper().convertValue(journeyActivity, TraitChoiceNode.class);
                mongoTemplate.save(traitChoiceNode);
            }else if("END".equals(category)){
                EndNode endNode = new ObjectMapper().convertValue(journeyActivity, EndNode.class);
                mongoTemplate.save(endNode);
            }else if("RANDOM_CHOICE".equals(category)){
                RandomChoiceNode randomChoiceNode = new ObjectMapper().convertValue(journeyActivity, RandomChoiceNode.class);
                mongoTemplate.save(randomChoiceNode);
            }else if("EMAIL".equals(category)){
                EmailNode emailNode = new ObjectMapper().convertValue(journeyActivity, EmailNode.class);
                mongoTemplate.save(emailNode);
            }else if("SMS".equals(category)){
                SmsNode smsNode = new ObjectMapper().convertValue(journeyActivity, SmsNode.class);
                mongoTemplate.save(smsNode);
            }else if("PUSH".equals(category)){
                PushNode pushNode = new ObjectMapper().convertValue(journeyActivity, PushNode.class);
                mongoTemplate.save(pushNode);
            }else if("INBOX".equals(category)){
                InboxNode inboxNode = new ObjectMapper().convertValue(journeyActivity, InboxNode.class);
                mongoTemplate.save(inboxNode);
            }
        }
        return "SUCCESS";
    }

    @Override
    public String journeyQuery(JourneyEntity request) {
        String programId = request.getProgramId();
        Query queryBasic = new Query();
        queryBasic.addCriteria(Criteria.where("_id").is(programId));
        JourneyBasicInfo journeyBasicInfo = mongoTemplate.findOne(queryBasic, JourneyBasicInfo.class);

        //get startActivityId
        String startActivityId = journeyBasicInfo.getStartActivityId();

        //basicInfo
        request.setStartActivityId(startActivityId);

        //journeyActivities
        ArrayList journeyActivitiesList = new ArrayList<>();
        //startNode
        Query queryStartNode = new Query();
        queryStartNode.addCriteria(Criteria.where("_id").is(startActivityId));
        StartNode startNode = mongoTemplate.findOne(queryStartNode, StartNode.class);
        journeyActivitiesList.add(startNode);

        //othersNode
        String parentActivityId = startNode.getId();
        while(parentActivityId != null){
            Object object = getObjByParActId(parentActivityId);
            if(null != object){
                journeyActivitiesList.add(object);
//                Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(object));
                Map<String,Object> map = CommonUtils.toMapByReflect(object);
                parentActivityId = (String)map.get("id");
            }else{
                parentActivityId = null;
            }
        }

        request.setJourneyActivities(journeyActivitiesList.toArray());

        Gson gson = new Gson();
        String jsonString = gson.toJson(request);
        return jsonString;
    }

    @Override
    public String journeySchedulerBegin(JourneyEntity request){

        String programId = request.getProgramId();

        //get JourneyBasicInfo
        Query queryJourneyBasicInfo = new Query();
        queryJourneyBasicInfo.addCriteria(Criteria.where("programId").is(programId));
        JourneyBasicInfo journeyBasicInfo = mongoTemplate.findOne(queryJourneyBasicInfo, JourneyBasicInfo.class);
        //generate JourneyBasicInfoInstance
        /*JourneyBasicInfoInstance journeyBasicInfoInstance = new JourneyBasicInfoInstance();
        journeyBasicInfoInstance.setVersion(journeyBasicInfo.getVersion());
        journeyBasicInfoInstance.setJobName(journeyBasicInfo.getName());
        journeyBasicInfoInstance.setJobStatus(0);
        journeyBasicInfoInstance.setJourneyId(journeyBasicInfo.getProgramId());*/

        String startActivityId = journeyBasicInfo.getStartActivityId();
        Query queryStartNode = new Query();
        queryStartNode.addCriteria(Criteria.where("_id").is(startActivityId));
        StartNode startNode = mongoTemplate.findOne(queryStartNode, StartNode.class);

        //parseNode and Exe
        ArrayList<Object> nextNodeList = getNextNode(startNode.getId());
        invokeProxy(nextNodeList);

        if(startNode != null){
            return "SUCCESS";
        }else{
            return "ERROR";
        }
    }

    @Override
    public String journeySchedulerStart(String params){
        Gson gson = new Gson();
        SchedulerParam schedulerParam = gson.fromJson(params, SchedulerParam.class);
        // 在journeyBasicInfo中通过journeyid和version组合判断是否存在
        // if存在则直接用节点流程执行
        // else 通过journeyid和version，去builder中去重新拉取新的flow，并入db再执行flow

        // 每次执行需要插入一个instant记录这次执行的记录情况


        return "SUCCESS";

    }

    @Override
    public String journeyProxyCallback(ProxyCallBackRequest request){
        String parentActivityId = request.getId();
        ArrayList<Object> nextNodeList = getNextNode(parentActivityId);
        invokeProxy(nextNodeList);
        return "SUCCESS";
    }

    public ArrayList<Object> getNextNode(String parentActivityId){

        ArrayList<Object>  nextNodeList = new ArrayList<>();

        Query queryAudienceNode = new Query();
        queryAudienceNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<AudienceNode> audienceNodeList = mongoTemplate.find(queryAudienceNode, AudienceNode.class);
        if(audienceNodeList != null && audienceNodeList.size()>0){
            nextNodeList.addAll(audienceNodeList);
        }

        Query queryEmailNode = new Query();
        queryEmailNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<EmailNode> emailNodeList = mongoTemplate.find(queryEmailNode, EmailNode.class);
        if(emailNodeList != null && emailNodeList.size()>0){
            nextNodeList.addAll(emailNodeList);
        }

        Query queryInboxNode = new Query();
        queryInboxNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<InboxNode> inboxNodeList = mongoTemplate.find(queryInboxNode, InboxNode.class);
        if(inboxNodeList != null && inboxNodeList.size()>0){
            nextNodeList.addAll(inboxNodeList);
        }

        Query queryPushNode = new Query();
        queryPushNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<PushNode> pushNodeList = mongoTemplate.find(queryPushNode, PushNode.class);
        if(pushNodeList != null && pushNodeList.size()>0){
            nextNodeList.addAll(pushNodeList);
        }

        Query queryRandomNode = new Query();
        queryRandomNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<RandomChoiceNode> randomChoiceNodeList = mongoTemplate.find(queryRandomNode, RandomChoiceNode.class);
        if(randomChoiceNodeList != null && randomChoiceNodeList.size()>0){
            nextNodeList.addAll(randomChoiceNodeList);
        }

        Query querySmsNode = new Query();
        querySmsNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<SmsNode> smsNodeList = mongoTemplate.find(querySmsNode, SmsNode.class);
        if(smsNodeList != null && smsNodeList.size()>0){
            nextNodeList.addAll(smsNodeList);
        }

        Query queryTraitChoNode = new Query();
        queryTraitChoNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<TraitChoiceNode> traitChoiceNodeList = mongoTemplate.find(queryTraitChoNode, TraitChoiceNode.class);
        if(traitChoiceNodeList != null && traitChoiceNodeList.size()>0){
            nextNodeList.addAll(traitChoiceNodeList);
        }

        Query queryEndNode = new Query();
        queryEndNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<EndNode> endNodeList = mongoTemplate.find(queryEndNode, EndNode.class);
        if(endNodeList != null && endNodeList.size()>0){
            nextNodeList.addAll(endNodeList);
        }

        return nextNodeList;
    }


    public void invokeProxy(ArrayList<Object> nextNodeList){

        for(int i=0;i<nextNodeList.size();i++) {
            Object journeyActivity = nextNodeList.get(i);
            Map<String,Object> objMap = JSONObject.parseObject(JSON.toJSONString(journeyActivity));
            String category = (String) objMap.get("category");
            if ("AUDIENCE".equals(category)) {
                AudienceNode audienceNode = new ObjectMapper().convertValue(journeyActivity, AudienceNode.class);
                System.out.println(audienceNode.toString());
                // TODO httpclint:proxy
            } else if ("TRAIT_CHOICE".equals(category)) {
                TraitChoiceNode traitChoiceNode = new ObjectMapper().convertValue(journeyActivity, TraitChoiceNode.class);
                System.out.println(traitChoiceNode.toString());
                // TODO httpclint:proxy
            } else if ("END".equals(category)) {
                EndNode endNode = new ObjectMapper().convertValue(journeyActivity, EndNode.class);
                System.out.println(endNode);
                // TODO httpclint:proxy
            } else if ("RANDOM_CHOICE".equals(category)) {
                RandomChoiceNode randomChoiceNode = new ObjectMapper().convertValue(journeyActivity, RandomChoiceNode.class);
                System.out.println(randomChoiceNode);
                // TODO httpclint:proxy
            } else if ("EMAIL".equals(category)) {
                EmailNode emailNode = new ObjectMapper().convertValue(journeyActivity, EmailNode.class);
                System.out.println(emailNode);
                // TODO httpclint:proxy
            } else if ("SMS".equals(category)) {
                SmsNode smsNode = new ObjectMapper().convertValue(journeyActivity, SmsNode.class);
                System.out.println(smsNode);
                // TODO httpclint:proxy
            } else if ("PUSH".equals(category)) {
                PushNode pushNode = new ObjectMapper().convertValue(journeyActivity, PushNode.class);
                System.out.println(pushNode);
                // TODO httpclint:proxy
            } else if ("INBOX".equals(category)) {
                InboxNode inboxNode = new ObjectMapper().convertValue(journeyActivity, InboxNode.class);
                System.out.println(inboxNode);
                // TODO httpclint:proxy
            }
        }
    }

    // TODO 未完成所有的节点解析
    public Object getObjByParActId(String parentActivityId){

        Query query = new Query();
        query.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        List<AudienceNode> audienceNodeList = mongoTemplate.find(query, AudienceNode.class);
        if(audienceNodeList != null && audienceNodeList.size()>0){
            return audienceNodeList;
        }

        Query queryEmailNode = new Query();
        queryEmailNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        EmailNode emailNode = mongoTemplate.findOne(queryEmailNode, EmailNode.class);
        if(emailNode != null){
            return emailNode;
        }

        Query queryInboxNode = new Query();
        queryInboxNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        InboxNode inboxNode = mongoTemplate.findOne(queryInboxNode, InboxNode.class);
        if(inboxNode != null){
            return inboxNode;
        }

        Query queryPushNode = new Query();
        queryPushNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        PushNode pushNode = mongoTemplate.findOne(queryPushNode, PushNode.class);
        if(pushNode != null){
            return pushNode;
        }

        Query queryRandomNode = new Query();
        queryRandomNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        RandomChoiceNode randomChoiceNode = mongoTemplate.findOne(queryRandomNode, RandomChoiceNode.class);
        if(randomChoiceNode != null){
            return randomChoiceNode;
        }

        Query querySmsNode = new Query();
        querySmsNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        SmsNode smsNode = mongoTemplate.findOne(querySmsNode, SmsNode.class);
        if(smsNode != null){
            return smsNode;
        }

        Query queryTraitChoNode = new Query();
        queryTraitChoNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        TraitChoiceNode traitChoiceNode = mongoTemplate.findOne(queryTraitChoNode, TraitChoiceNode.class);
        if(traitChoiceNode != null){
            return traitChoiceNode;
        }

        Query queryEndNode = new Query();
        queryEndNode.addCriteria(Criteria.where("parentActivityId").is(parentActivityId));
        EndNode endNode = mongoTemplate.findOne(queryEndNode, EndNode.class);
        if(endNode != null){
            return endNode;
        }
        return null;
    }

}
