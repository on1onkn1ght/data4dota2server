package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 13:39 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Topic {
    private Long topicId;
    private Long userId;
    private Long createdDate;
    private String title;
    private String content;
    private String nickname;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
