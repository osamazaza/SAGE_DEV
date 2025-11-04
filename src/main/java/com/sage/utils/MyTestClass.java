package com.sage.utils;

import java.lang.reflect.Method;

public class MyTestClass {

    


	
    public   String getUniqueIdentifier(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return method.getDeclaringClass().getName() + "." + method.getName();
        } catch (NoSuchMethodException e) {
            return "Method not found in class: " + clazz.getName();
        }
    }

    
    
    
    
    
}
