package com.sshp.config.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 注册 hibernate 监听器
 * Created by jahv on 2016/11/14.
 */
@Component
@Lazy(false)
public class ListenerRegister {

  @Autowired
  public ListenerRegister(LogListener listener, SessionFactory sessionFactory) {
    final EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
    registry.appendListeners(EventType.PRE_INSERT, listener);
    registry.appendListeners(EventType.PRE_UPDATE, listener);
    registry.appendListeners(EventType.POST_UPDATE, listener);
    registry.appendListeners(EventType.POST_INSERT, listener);
    registry.appendListeners(EventType.POST_DELETE, listener);
  }
}
