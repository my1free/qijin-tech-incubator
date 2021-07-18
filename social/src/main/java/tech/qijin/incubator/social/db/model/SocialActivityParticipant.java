package tech.qijin.incubator.social.db.model;

import java.util.Date;
import tech.qijin.incubator.social.base.ActivityParticipantStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class SocialActivityParticipant {
    private Long id;

    private Channel channel;

    private Long activityId;

    private Long userId;

    private String contact;

    private ActivityParticipantStatus stauts;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public ActivityParticipantStatus getStauts() {
        return stauts;
    }

    public void setStauts(ActivityParticipantStatus stauts) {
        this.stauts = stauts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}