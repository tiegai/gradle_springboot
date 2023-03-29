package org.example.es.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.es.model.ESInfo;
import org.example.es.repository.ESInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test_demo/es")
@Api(tags = "ES Test Demo")
public class ESController {

    @Autowired
    private ESInfoRepo esInfoRepo;

    @GetMapping("find/{id}")
    @ApiOperation(value = "Find ES Info")
    public String find(@PathVariable String id) {

        ESInfo byId = esInfoRepo.findById(id).get();

        System.out.println(byId.getName());

        return JSON.toJSONString(byId);

    }

    @GetMapping("find/all")
    @ApiOperation(value = "Find ES All Info")
    public String findAll() {

        Iterable<ESInfo> byId = esInfoRepo.findAll();

        return JSON.toJSONString(byId);

    }

    @PostMapping("add")
    @ApiOperation(value = "add ES All Info")
    public void add(@RequestBody ESInfo esInfo) {
       esInfoRepo.save(esInfo);
    }


}
