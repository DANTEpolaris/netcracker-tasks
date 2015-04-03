package ru.ncedu.java.tasks.Reflections;

import java.lang.reflect.*;
import java.util.*;

public class ReflectionsImpl implements Reflections {

    public ReflectionsImpl() {

    }

    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        if (object == null || fieldName == null) {
            throw new NullPointerException("fieldName or object is null");
        }

        try {
            Class<?> clazz = object.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(object);

        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error", e);
        }
    }

    private void throwNullPointerException(Object object) {
        if (object == null) {
            throw new NullPointerException("Method's parameter is null..");
        }
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        throwNullPointerException(clazz);

        Set<Method> methods = new HashSet<>();
        Collections.addAll(methods, clazz.getDeclaredMethods());

        Set<String> nameMethods = new HashSet<>();
        for (Method m : methods) {
            if (Modifier.isProtected(m.getModifiers())) {
                nameMethods.add(m.getName());
            }
        }
        return nameMethods;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        throwNullPointerException(clazz);

        Set<Method> methods = new HashSet<>();
        Collections.addAll(methods, clazz.getDeclaredMethods());

        for (Class c : getExtendsHierarchy(clazz)) {
            Collections.addAll(methods, c.getDeclaredMethods());
        }

        return methods;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        throwNullPointerException(clazz);

        List<Class> superClasses = new ArrayList<>();
        while ((clazz = clazz.getSuperclass()) != null) {
            superClasses.add(clazz);
        }

        return superClasses;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        throwNullPointerException(clazz);

        Set<Class> interfaces= new HashSet<>();
        Collections.addAll(interfaces, clazz.getInterfaces());

        return interfaces;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        throwNullPointerException(method);

        List<Class> exceptions = new ArrayList<>();
        Collections.addAll(exceptions, method.getExceptionTypes());

        return exceptions;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        Class<?> clazz;
        try {
            clazz = Class.forName("ru.ncedu.java.Reflections");
            clazz = clazz.getClasses()[0];

            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            Object secretClassInstance = constructor.newInstance();

            Method method = clazz.getDeclaredMethod("foo");
            method.setAccessible(true);

            return (String) method.invoke(secretClassInstance);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class was not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor was not found", e);
        } catch (SecurityException e) {
            throw new IllegalStateException("Method is private", e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            throw new IllegalStateException("Constructor error", e);
        }
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        Class<?> clazz;
        try {
            clazz = Class.forName("ru.ncedu.java.Reflections");
            clazz = clazz.getClasses()[0];

            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);

            Object secretClassInstance = constructor.newInstance(constructorParameter);

            Method method = clazz.getDeclaredMethod("foo", String.class, Integer[].class);
            method.setAccessible(true);

            return (String) method.invoke(secretClassInstance, string, integers);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class was not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor was not found", e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Constructor error", e);
        }

    }
}
