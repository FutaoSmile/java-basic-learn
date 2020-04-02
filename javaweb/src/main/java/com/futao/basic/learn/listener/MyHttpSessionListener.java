package com.futao.basic.learn.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author futao
 * @date 2020/4/2.
 */
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("id:" + session.getId());
        System.out.println("CreationTime:" + session.getCreationTime());
        System.out.println("LastAccessedTime:" + session.getLastAccessedTime());
        System.out.println("MaxInactiveInterval:" + session.getMaxInactiveInterval());
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("id:" + session.getId());
        System.out.println("CreationTime:" + session.getCreationTime());
        System.out.println("LastAccessedTime:" + session.getLastAccessedTime());
        System.out.println("MaxInactiveInterval:" + session.getMaxInactiveInterval());
        System.out.println("sessionDestroyed");
    }
}
