package tech.qijin.incubator.social.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;
import tech.qijin.incubator.social.service.bo.ActivityBo;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ActivityVo {
    // 活动id
    private Long id;
    // 活动名称
    private String title;
    // 活动标签
    private List<String> tags;
    // 发起人
    private ProfileVo sponsor;
    // 联系方式
    private String contact;
    // 活动开始时间
    private String startTime;
    // 活动结束时间
    private String endTime;
    // 活动地点
    private String location;
    private String lng;
    private String lat;
    // 活动介绍
    private String description;
    // 参与者
    private List<ParticipantVo> participants;
    // 是否是发起人
    @JSONField(name = "isMaster")
    private boolean isMaster;
    // 是否已经加入活动
    @JSONField(name = "isParticipant")
    private boolean isParticipant;

    private List<ImageVo> images;

    public static ActivityVo from(ActivityBo activityBo) {
        if (activityBo == null) return null;
        ActivityVo activityVo = ConvertUtil.convert(activityBo.getActivity(), ActivityVo.class);
        activityVo.setStartTime(DateUtil.formatStr(activityBo.getActivity().getStartTime(), DateUtil.YYYYMMDDHHMMSS));
        activityVo.setEndTime(DateUtil.formatStr(activityBo.getActivity().getEndTime(), DateUtil.YYYYMMDDHHMMSS));
        activityVo.setTags(Arrays.asList(activityBo.getActivity().getTags().split(",")));
        activityVo.setSponsor(ProfileVo.from(activityBo.getUserProfileMap().get(activityBo.getActivity().getSponsor())));
        if (CollectionUtils.isNotEmpty(activityBo.getParticipants())) {
            activityVo.setParticipants(activityBo.getParticipants()
                    .stream()
                    .map(participant -> ParticipantVo.builder()
                            .id(participant.getId())
                            .profile(ProfileVo.from(activityBo.getUserProfileMap().get(participant.getUserId())))
                            .contact(participant.getContact())
                            .build())
                    .collect(Collectors.toList()));
        }
        activityVo.setParticipant(activityBo.isParticipant());
        activityVo.setMaster(activityBo.isSponsor());
        if (CollectionUtils.isNotEmpty(activityBo.getActivityImages())) {
            activityVo.setImages(ImageVo.from2(activityBo.getActivityImages()));
        }
        return activityVo;
    }

    public static List<ActivityVo> from(List<ActivityBo> activityBos) {
        return activityBos.stream().map(ActivityVo::from).collect(Collectors.toList());
    }
}
