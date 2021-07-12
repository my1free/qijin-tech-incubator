package tech.qijin.incubator.social.db.model;

import java.util.Date;
import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.util4j.trace.pojo.Channel;

public class SocialLove {
    private Long id;

    private Channel channel;

    private Long userId;

    private LoveKind kind;

    private String content;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LoveKind getKind() {
        return kind;
    }

    public void setKind(LoveKind kind) {
        this.kind = kind;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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