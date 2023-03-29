package org.example.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.mongo.dao.StudentDaoTypeOne;
import org.example.mongo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private StudentDaoTypeOne studentDaoTypeOne;

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/add")
    public String addOneStudent() {
        //        插入10行
        for (Integer count = 0; count < 10; count++) {
            Student student = new Student();
            student.setStudentId("student_" + count); //如果自己不去设置id则系统会分配给一个id
            student.setStudentName("godfery" + count);
            student.setStudentAge(count);
            student.setStudentScore(98.5 - count);
            student.setStudentBirthday(new Date());
            studentDaoTypeOne.save(student);
        }
        return "mongo-add-success";
    }

    @GetMapping("/delete")
    public String deleteOneStudentByStudentId() {
        //        删除id为student_0的学生
        studentDaoTypeOne.deleteById("student_0");
        return "mongo-delete-success";
    }

    @GetMapping("/update")
    public String updateOneStudent() {
        //        修改姓名为Godfery1的Student年龄为22
        Student student = studentDaoTypeOne.getAllByStudentName("godfery1");
        student.setStudentAge(22);
        studentDaoTypeOne.save(student);
        return "mongo-update-success";
    }

    @GetMapping("/getOne")
    public String getOneStudentByStudentId() {
        Optional<Student> student = studentDaoTypeOne.findById("student_1");
        System.out.println(student);
        return student.get().getStudentName();
    }

    @GetMapping("/getAllByCursor")
    public String getByCurosr() {
        DBObject query = new BasicDBObject();
        query.put("studentAge",6);
        MongoCursor<Document> cursor = mongoTemplate.getCollection("student").find((Bson) query).iterator();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            if (next.get("studentName").toString().equals("godfery6")) {
                return next.get("_id").toString();
            }
            return next.get("studentName").toString();
        }
        return "success";
    }

    @GetMapping("/getAll")
    public String getAllStudent() {
        List<Student> student = studentDaoTypeOne.findAll();
        student.stream().forEach(s -> {
            System.out.println(s.getStudentId() + "###" + s.getStudentName() + "###" + s.getStudentAge());
        });
        return student.toString();
    }


}
