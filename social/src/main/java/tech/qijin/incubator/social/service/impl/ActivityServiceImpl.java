package tech.qijin.incubator.social.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.incubator.social.base.ActivityStatus;
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;
import tech.qijin.incubator.social.helper.ActivityHelper;
import tech.qijin.incubator.social.service.ActivityService;
import tech.qijin.incubator.social.service.bo.ActivityBo;
import tech.qijin.satellites.user.auth.UserUtil;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityHelper activityHelper;
    @Autowired
    private CellUserProfileService cellUserProfileService;

    @Override
    public List<ActivityBo> listActivity() {
        List<SocialActivity> activities = activityHelper.listActivity();
        List<Long> activityIds = activities.stream()
                .map(SocialActivity::getId)
                .collect(Collectors.toList());
        Map<Long, List<SocialActivityImage>> activityImagesMap = activityHelper.mapActivityImage(activityIds);
        Map<Long, List<SocialActivityParticipant>> activityParticipantsMap = activityHelper.mapActivityParticipant(activityIds);
        List<Long> sponsorUserIds = activities.stream()
                .map(SocialActivity::getSponsor)
                .distinct()
                .collect(Collectors.toList());
        List<Long> participantUserIds = activityParticipantsMap.values()
                .stream()
                .flatMap(List::stream)
                .map(SocialActivityParticipant::getUserId)
                .distinct()
                .collect(Collectors.toList());
        List<Long> userIds = ListUtils.union(sponsorUserIds, participantUserIds);

        return activities.stream()
                .map(activity -> {
                    List<SocialActivityParticipant> participants = activityParticipantsMap.get(activity.getId());
                    int participantCount = CollectionUtils.size(participants);
                    if (CollectionUtils.isNotEmpty(participants) && participants.size() > 6) {
                        participants = participants.subList(0, 6);
                    }
                    return ActivityBo.builder()
                            .activity(activity)
                            .isSponsor(UserUtil.getUserId().equals(activity.getSponsor()))
                            .isParticipant(CollectionUtils.isNotEmpty(participantUserIds) && participantUserIds.contains(UserUtil.getUserId()))
                            .activityImages(activityImagesMap.get(activity.getId()))
                            .participants(participants)
                            .participantCount(participantCount)
                            .userProfileMap(cellUserProfileService.mapProfile(userIds))
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public ActivityBo getActivity(Long activityId) {
        SocialActivity activity = activityHelper.getActivity(activityId);
        if (activity == null) return null;
        List<SocialActivityImage> images = activityHelper.listActivityImage(activityId);
        List<SocialActivityParticipant> participants = activityHelper.listActivityParticipants(activityId);
        List<Long> sponsorUserIds = Lists.newArrayList(activity.getSponsor());
        List<Long> participantUserIds = participants.stream()
                .map(SocialActivityParticipant::getUserId)
                .distinct()
                .collect(Collectors.toList());
        List<Long> userIds = ListUtils.union(sponsorUserIds, participantUserIds);
        return ActivityBo.builder()
                .activity(activity)
                .activityImages(images)
                .participants(participants)
                .userProfileMap(cellUserProfileService.mapProfile(userIds))
                .isSponsor(UserUtil.getUserId().equals(activity.getSponsor()))
                .isParticipant(CollectionUtils.isNotEmpty(participantUserIds) && participantUserIds.contains(UserUtil.getUserId()))
                .build();
    }

    @Override
    public Long addActivity(SocialActivity socialActivity, List<SocialActivityImage> images) {
        activityHelper.addActivity(UserUtil.getUserId(), socialActivity);
        if (CollectionUtils.isNotEmpty(images)) {
            images.stream().forEach(image -> activityHelper.addActivityImage(socialActivity.getId(), image));
        }
        return socialActivity.getId();
    }

    @Override
    public boolean updateActivity(Long activityId, SocialActivity socialActivity, List<SocialActivityImage> images) {
        activityHelper.delAllActivityImage(activityId);
        boolean res = activityHelper.updateActivity(UserUtil.getUserId(), activityId, socialActivity);
        if (CollectionUtils.isNotEmpty(images)) {
            images.stream().forEach(image -> activityHelper.addActivityImage(activityId, image));
        }
        return res;
    }

    @Override
    public boolean joinActivity(Long activityId) {
        SocialActivity activity = getAvailableActivity(activityId);
        if (activityHelper.hasJoined(UserUtil.getUserId(), activityId)) return true;
        return activityHelper.addActivityParticipant(activityId, UserUtil.getUserId());
    }

    @Override
    public boolean cancelActivity(Long activityId) {
        SocialActivity activity = getAvailableActivity(activityId);
        return activityHelper.removeActivityParticipant(activityId, UserUtil.getUserId());
    }

    @Override
    public boolean closeActivity(Long activityId) {
        SocialActivity activity = getAvailableActivity(activityId);
        return activityHelper.closeActivity(activityId, UserUtil.getUserId());
    }

    private SocialActivity getAvailableActivity(Long activityId) {
        SocialActivity activity = activityHelper.getActivity(activityId);
        checkActivity(activity);
        return activity;
    }

    private void checkActivity(SocialActivity activity) {
        MAssert.isTrue(activity != null, ResEnum.INVALID_PARAM.code, "活动不存在");
        MAssert.isTrue(ActivityStatus.OPENED.equals(activity.getStatus()), ResEnum.INVALID_PARAM.code, "活动已取消");
        MAssert.isTrue(DateUtil.now().compareTo(activity.getEndTime()) <= 0, ResEnum.INVALID_PARAM.code, "活动已结束");
    }
}
