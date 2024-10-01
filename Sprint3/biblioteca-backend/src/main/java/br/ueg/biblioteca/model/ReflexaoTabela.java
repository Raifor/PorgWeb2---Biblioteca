package br.ueg.biblioteca.model;

import br.ueg.biblioteca.Util;
import jakarta.persistence.Id;

import java.lang.reflect.Field;

public class ReflexaoTabela {

    private static Field getIdField(GenericTabela genericTabela) {
        validarParametroTab(genericTabela);

        Class<?> clazz = genericTabela.getClass();
        Field idField = null;

        while (clazz != GenericTabela.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    idField = field;
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        }

        if (idField == null) {
            throw new RuntimeException(
                    "Classe: " + clazz.getName() + " não tem nenhum atributo anotado com @Id");
        }
        return idField;
    }

    public static Object getIdValue(GenericTabela genericTabela) {
        Field idField = getIdField(genericTabela);
        return Util.invokeGetMethod(genericTabela, idField.getName());
    }

    public static void validarParametroTab(GenericTabela genericTabela) {
        if (genericTabela == null) {
            throw new RuntimeException("Erro: objeto não pode ser nulo.");
        }
    }
}
