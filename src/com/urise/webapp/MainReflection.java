package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) {
        Resume resume = new Resume();
        try {
            Method method = resume.getClass().getDeclaredMethod("toString");
            Field field = resume.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            field.set(resume, "new_uuid");
            method.setAccessible(true);
            method.invoke(resume);
            System.out.println(resume);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
