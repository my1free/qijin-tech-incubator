package tech.qijin.incubator.social.db.model;

import java.util.Date;
import tech.qijin.incubator.social.base.ActivityImageStatus;
import tech.qijin.util4j.trace.pojo.Channel;

public class SocialActivityImage {
    private Long id;

    private Channel channel;

    private Long activityId;

    private String url;

    private ActivityImageStatus status;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public ActivityImageStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityImageStatus status) {
        this.status = status;
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