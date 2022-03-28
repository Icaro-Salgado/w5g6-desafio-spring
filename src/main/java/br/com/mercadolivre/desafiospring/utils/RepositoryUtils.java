package br.com.mercadolivre.desafiospring.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RepositoryUtils {

    public static <T> void substituteValues(List<T> modelList, Map<String, Object> values) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (T model : modelList) {
            for (var value : values.entrySet()) {

                Method[] mtd = model.getClass().getDeclaredMethods();
                Method mtdTo = Arrays.stream(mtd)
                        .filter(m -> m.getName().equals("set" + StringUtils.capitalize(value.getKey())))
                        .findFirst()
                        .orElseThrow(NoSuchMethodException::new);
                mtdTo.invoke(model, value.getValue());
            }
        }
    }

}
