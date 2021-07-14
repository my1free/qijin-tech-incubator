package tech.qijin.incubator.social.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;
import tech.qijin.incubator.social.helper.ActivityHelper;
import tech.qijin.incubator.social.service.ActivityService;
import tech.qijin.incubator.social.service.bo.ActivitiesBo;
import tech.qijin.incubator.social.service.bo.ActivityBo;

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
                .map(activity -> ActivityBo.builder()
                        .activity(activity)
                        .activityImages(activityImagesMap.get(activity.getId()))
                        .participants(activityParticipantsMap.get(activity.getId()))
                        .userProfileMap(cellUserProfileService.mapProfile(userIds))
                        .build())
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
                .build();
    }
}
