package org.example.mongo.common;

import com.alibaba.fastjson.JSONObject;
import org.example.mongo.entity.Student;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static void main(String[] args){

        Student student=new Student();
        student.setStudentName("abc");
        student.setStudentAge(12);

//        Map<String, String> map = CommonUtils.convertToMap(student);
        Map<String, Object> map = CommonUtils.toMapByReflect(student);
        System.out.println(JSONObject.toJSONString(map));

    }

    public static <T> Map<String, Object> toMapByReflect(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();

        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Field f : fields) {
                f.setAccessible(true);
                Object val = f.get(obj);
//                if (f.getType() == Date.class) {
//                    map.put(f.getName(), sdf.format(val));
//                } else {
                    map.put(f.getName(), val);
//                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }

    /*public static Map<String, String> convertToMap(Object obj) {
        try {
            if (obj instanceof Map) {
                return (Map)obj;
            }
            Map<String, String> returnMap = BeanUtils.describe(obj);
            returnMap.remove("class");
            return returnMap;
        } catch (IllegalAccessException e1) {
            e1.getMessage();
        } catch (InvocationTargetException e2) {
            e2.getMessage();
        } catch (NoSuchMethodException e3) {
            e3.getMessage();
        }
        return new HashMap();
    }*/

}
