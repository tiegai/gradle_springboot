package org.example.jsonSchema;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestJsonController {

    //TODO do test from postman
    @PostMapping("/user")
    public String createUser(@RequestBody String json) throws ProcessingException, IOException {

        JsonNode schemaNode = JsonLoader.fromResource("/twinSchema.json");

        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);

        //解析用户提交的json数据
        JsonNode userNode = JsonLoader.fromString(json);

        //校验json是否符合规则
        ProcessingReport validate = schema.validate(userNode);

        if(validate.isSuccess()){
            return "User is created successfully";
        }else {
            return validate.toString();
        }

    }

}
