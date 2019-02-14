package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 10:28 2019/2/14
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Message {
    private Long id;
    private Long fromId;
    private Long toId;
    private String content;
    private Long createdDate;
    private boolean hasRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
