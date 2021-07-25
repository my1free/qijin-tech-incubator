package tech.qijin.incubator.social.api.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivityReqVo {
    private Long activityId;
    private String title;
    private String contact;
    private Date startTime;
    private Date endTime;
    private String location;
    private String description;
    private List<ImageReqVo> images;
    private List<String> tags;
}
