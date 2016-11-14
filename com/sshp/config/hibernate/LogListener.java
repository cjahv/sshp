package com.sshp.config.hibernate;

import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

/**
 * hibernate 实体日志监听器
 * Created by jahv on 2016/11/14.
 */
@Component
class LogListener implements PostUpdateEventListener, PostInsertEventListener, PostDeleteEventListener, PreUpdateEventListener, PreInsertEventListener {
  @Override
  public void onPostDelete(PostDeleteEvent postDeleteEvent) {
    postDeleteEvent.getEntity();
  }

  @Override
  public void onPostInsert(PostInsertEvent postInsertEvent) {

  }

  @Override
  public void onPostUpdate(PostUpdateEvent postUpdateEvent) {

  }

  @Override
  public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
    return false;
  }

  @Override
  public boolean onPreInsert(PreInsertEvent preInsertEvent) {
    return false;
  }

  @Override
  public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
    return false;
  }
}
