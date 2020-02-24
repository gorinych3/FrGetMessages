package ru.gorinych3.models;

public class EntityWrapper {

    private MessagesLSP entity;

    private int count;

    public EntityWrapper(MessagesLSP entity, int count) {
        this.entity = entity;
        this.count = count;
    }

    public MessagesLSP getEntity() {
        return entity;
    }

    public void setEntity(MessagesLSP entity) {
        this.entity = entity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
