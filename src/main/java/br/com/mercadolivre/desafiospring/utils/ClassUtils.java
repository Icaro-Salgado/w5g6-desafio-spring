package br.com.mercadolivre.desafiospring.utils;

import org.springframework.util.StringUtils;

import javax.el.PropertyNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {

    public static <T> List<String> getClassFields(Class<T> cls){
        List<String> fields = new ArrayList<>();
        Field[] objects = cls.getDeclaredFields();

        for(var field:objects){
            String[] fieldInfo = field.toString().split(" ");

            String fieldUrl = fieldInfo[2];
            String fieldName = fieldUrl.split("\\.")[fieldUrl.split("\\.").length - 1];
            fields.add(fieldName);

        }

        return fields;
    }

    public static Object invokeGetMethod(Object obj, String filterString){
        try {
            String[] splitPath = filterString.split("\\.");
            if(!getClassFields(obj.getClass()).contains(splitPath[0])){
                throw new PropertyNotFoundException();
            }

            Method initialMethod = obj.getClass().getDeclaredMethod("get" + StringUtils.capitalize(splitPath[0]));
            Object result = initialMethod.invoke(obj);

            if(splitPath.length > 1){
                String subField = splitPath[1];
                if(!getClassFields(result.getClass()).contains(subField)){
                    throw new PropertyNotFoundException();
                }
                Method subMtd = result.getClass().getDeclaredMethod("get" + StringUtils.capitalize(subField));
                result = subMtd.invoke(result);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
