package com.pc.android.program.sdk.internal.activity;


import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PermissionAwareActivityProxy implements InvocationHandler {

    private Activity activity;

    public PermissionAwareActivityProxy(Activity activity){
        this.activity = activity;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       return method.invoke(activity, args);
    }


    public static Activity instance(Activity activity){
        return (Activity) Proxy.newProxyInstance(
                PermissionAwareActivityProxy.class.getClassLoader(),
                new Class[] { Activity.class },
                new PermissionAwareActivityProxy(activity));
    }


}
