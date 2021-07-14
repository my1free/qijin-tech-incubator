package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;
import tech.qijin.incubator.social.service.bo.ActivityBo;
import tech.qijin.util4j.utils.ConvertUtil;

import java.util.Arrays;
import java.util.Date;
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
    // 活动开始时间
    private Date startTime;
    // 活动结束时间
    private Date endTime;
    // 活动地点
    private String location;
    private String lng;
    private String lat;
    // 活动介绍
    private String description;
    // 参与者
    private List<ProfileVo> participants;

    public static ActivityVo from(ActivityBo activityBo) {
        if (activityBo == null) return null;
        ActivityVo activityVo = ConvertUtil.convert(activityBo.getActivity(), ActivityVo.class);
        activityVo.setTags(Arrays.asList(activityBo.getActivity().getTags().split(",")));
        activityVo.setSponsor(ProfileVo.from(activityBo.getUserProfileMap().get(activityBo.getActivity().getSponsor())));
        if (CollectionUtils.isNotEmpty(activityBo.getParticipants())) {
            activityVo.setParticipants(ProfileVo.from(activityBo.getParticipants().stream()
                    .map(participant -> activityBo.getUserProfileMap().get(participant.getUserId()))
                    .collect(Collectors.toList())));
        }
        return activityVo;
    }

    public static List<ActivityVo> from(List<ActivityBo> activityBos) {
        return activityBos.stream().map(ActivityVo::from).collect(Collectors.toList());
    }
}
