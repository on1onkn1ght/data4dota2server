package com.onionknight.data4dota2.async;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author :fdy
 * @Date :Created in 14:13 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class EventModel {
    private EventType type;
    private Long actorId;
    private Integer entityType;
    private Long entityId;
    private Long entityOwnerId;

    private Map<String,String> exts = new HashMap<>();


    public EventModel SetExt(String key,String value){
        exts.put(key,value);
        return this;
    }

    public String getExt(String key){
        return exts.get(key);
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public Long getActorId() {
        return actorId;
    }

    public EventModel setActorId(Long actorId) {
        this.actorId = actorId;
        return this;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(Integer entityType) {
        this.entityType = entityType;
        return this;
    }

    public Long getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public Long getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(Long entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public EventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }
}
