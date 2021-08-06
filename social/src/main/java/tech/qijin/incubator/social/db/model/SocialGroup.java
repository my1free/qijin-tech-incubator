package tech.qijin.incubator.social.db.model;

import java.util.Date;
import tech.qijin.util4j.trace.pojo.Channel;

public class SocialGroup {
    private Long id;

    private Channel channel;

    private String name;

    private Long creator;

    private String logoSmall;

    private String logoBig;

    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getLogoSmall() {
        return logoSmall;
    }

    public void setLogoSmall(String logoSmall) {
        this.logoSmall = logoSmall == null ? null : logoSmall.trim();
    }

    public String getLogoBig() {
        return logoBig;
    }

    public void setLogoBig(String logoBig) {
        this.logoBig = logoBig == null ? null : logoBig.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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