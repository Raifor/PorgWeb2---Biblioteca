package br.ueg.biblioteca;

import br.ueg.biblioteca.model.GenericTabela;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Util {

    public static String getUCFirst(String name) {
        return (name == null || name.isEmpty()) ? name : Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public static Object invokeMethod(GenericTabela genericTabela, String methodName, Object... args) {
        try {
            Method method = getMethod(genericTabela.getClass(), methodName, args);
            return method.invoke(genericTabela, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Erro ao executar o método: " + methodName, e);
        }
    }

    private static Method getMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException {
        if (args == null || args.length == 0) {
            return cls.getMethod(methodName);
        } else {
            Class<?>[] paramTypes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
            return cls.getMethod(methodName, paramTypes);
        }
    }

    public static Object invokeGetMethod(GenericTabela genericTabela, String fieldName) {
        String methodName = "get" + getUCFirst(fieldName);
        return invokeMethod(genericTabela, methodName);
    }

    public static void invokeSetMethod(GenericTabela genericTabela, String fieldName, Object value) {
        String methodName = "set" + getUCFirst(fieldName);
        invokeMethod(genericTabela, methodName, value);
    }
}
