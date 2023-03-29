package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.chain.Execute;
import org.example.chain.Util.ListUtil;
import org.example.chain.Util.ThreadUtil;
import org.example.chain.entity.MessageEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.chain.Util.ToolUtil.getDayOfWeekWithinDateInterval;
import static org.example.chain.Util.ToolUtil.httpURLConnection;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @GetMapping("/name")
    public String getActorNAme(){
        return "jerry-test";
    }

    @GetMapping("/execute1")
    public String getChainResp() {
        // 组建多条消息体
        List<MessageEntity> entityList1 = new ArrayList<>();
        MessageEntity entity = new MessageEntity();
        entity.setGender("1");
        entity.setAge(35);
        entity.setVip("0");
        entityList1.add(entity);

        MessageEntity entity2 = new MessageEntity();
        entity2.setGender("0");
        entity2.setAge(25);
        entity2.setVip("1");
        entityList1.add(entity2);

        // 模拟实例节点顺序
        List nodeList = new ArrayList<Integer>();
        nodeList.add(1);
        nodeList.add(2);

        // TODO 大批量数据，进行1万条为单位进行一次获取责任链模版并处理，增加多线程
        Execute execute = new Execute();
        execute.execute(entityList1,nodeList);
        return "finish1...";
    }

    @RequestMapping(value="/postTest", method = RequestMethod.POST)
    public String reqBodysObjectPOST(@RequestBody String id) {
        System.out.println(new Date() + "===接受参数："+id+"===");
        return "返回-传入参数："+id+"================";
    }

    @RequestMapping(value="/postTestParam", method = RequestMethod.POST)
    public String reqBodysObjectPOSTParam(@RequestBody String params) {
        System.out.println(new Date() + "===接受参数："+params+"===");
        return "返回-传入参数："+params+"================";
    }

    /**
     * 模拟engine输入开始-结束时间，调用XXL_job创建执行器的定时任务
     * @param params
     * @return
     */
    @RequestMapping(value="/createTimer", method = RequestMethod.POST)
    public String createTimer(@RequestBody String params) {
        //1、接受页面传输json格式的数据
        String str = "{\"upmid\":\"test_upmid\",\"beginDate\":\"2022-02-10\",\"beginTime\":\"15:20:20\",\"endDate\":\"2022-12-10\",\"endTime\":\"13:22:30\",\"weekDays\":\"1\"}";
        //2、解析json
        JSONObject jsonObject = JSON.parseObject(str);
        //获取当前嵌套下的属性
        String beginDate = jsonObject.getString("beginDate");
        String beginTime = jsonObject.getString("beginTime");
        String endDate = jsonObject.getString("endDate");
        String endTime = jsonObject.getString("endTime");
        String pid = jsonObject.getString("pid");
        String weekDays = jsonObject.getString("weekDays");
        String upmid = jsonObject.getString("upmid");

        getDayOfWeekWithinDateInterval(beginDate,endDate,1);

        String url = "http://localhost:8080/xxl-job-admin/jobinfo/addAndStart";
        String method = "POST";
        String data = "upmid="+upmid+"";
        Boolean flag = httpURLConnection(url,data,method);

        return "true";
    }

    @RequestMapping("/execute2")
    public String getChainResp2(String params) {
        System.out.println("传入参数："+params+"================");
        // 组建多条消息体
        List<MessageEntity> entityList2 = new ArrayList<>();
        MessageEntity entity = new MessageEntity();
        entity.setName("陈1");
        entity.setGender("1");
        entity.setAge(35);
        entity.setVip("0");
        entityList2.add(entity);

        MessageEntity entity2 = new MessageEntity();
        entity2.setName("陈2");
        entity2.setGender("0");
        entity2.setAge(25);
        entity2.setVip("1");
        entityList2.add(entity2);

        MessageEntity entity3 = new MessageEntity();
        entity3.setName("陈3");
        entity3.setGender("1");
        entity3.setAge(36);
        entity3.setVip("0");
        entityList2.add(entity3);

        MessageEntity entity4 = new MessageEntity();
        entity4.setName("陈4");
        entity4.setGender("0");
        entity4.setAge(26);
        entity4.setVip("1");
        entityList2.add(entity4);

        MessageEntity entity5 = new MessageEntity();
        entity5.setName("陈5");
        entity5.setGender("1");
        entity5.setAge(46);
        entity5.setVip("0");
        entityList2.add(entity5);

        // 模拟实例节点顺序
        List nodeList = new ArrayList<Integer>();
        nodeList.add(1);
        nodeList.add(3);
        nodeList.add(1);
        nodeList.add(2);
        nodeList.add(3);

        // N条按一定长度切割，多线程执行分割后的list,每个分割后的list解析一次模版
        List<List<MessageEntity>> listArray = ListUtil.splitList(entityList2,3);
        ThreadUtil  threadUtil = new ThreadUtil();
        threadUtil.doProcessList(listArray,nodeList);
        return "finish2..."+"传入参数："+params+"================";
    }
}
