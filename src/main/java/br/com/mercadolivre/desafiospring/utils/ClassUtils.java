package br.com.mercadolivre.desafiospring.utils;

import br.com.mercadolivre.desafiospring.models.Customer;
import org.springframework.util.StringUtils;

import javax.el.PropertyNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ClassUtils {

    public static <T> List<String> getClassFields(Class<T> cls) {
        List<String> fields = new ArrayList<>();
        Field[] objects = cls.getDeclaredFields();

        for (var field : objects) {
            String[] fieldInfo = field.toString().split(" ");
            String urlType = fieldInfo[1];

            String fieldUrl = fieldInfo[2];
            String fieldName = fieldUrl.split("\\.")[fieldUrl.split("\\.").length - 1];
            fields.add(fieldName);

            if(urlType.contains(".models.")){
                try {
                    Class<?> subClass = Class.forName(urlType);

                    List<String> subFields = getClassFields(subClass);

                    fields.addAll(subFields.stream().map(f -> fieldName.concat(".").concat(f)).collect(Collectors.toList()));
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                }

            }

        }

        return fields;
    }

    public static Object invokeGetMethod(Object obj, String filterString){
        try {
            Object result = obj;
            List<String> fields = ClassUtils.getClassFields(obj.getClass());

            String[] splitPath = filterString.split("\\.");
            if(!fields.contains(filterString)){
                throw new PropertyNotFoundException();
            }

            for (String s : splitPath) {
                Method initialMethod = result.getClass().getDeclaredMethod("get" + StringUtils.capitalize(s));
                result = initialMethod.invoke(result);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> buildStringFilters(Map<String, Object> filters){
        List<String> fields = ClassUtils.getClassFields(Customer.class);

        for(var filter: filters.entrySet()){
            if(!fields.contains(filter.getKey())){
                Optional<String> optionalFilter = fields
                        .stream()
                        .filter(f -> f.contains(filter.getKey())).findFirst();

                if(optionalFilter.isPresent()){
                    filters.put(optionalFilter.get(), filter.getValue());
                    filters.remove(filter.getKey());
                }
            }
        }

        return filters;
    }
}
