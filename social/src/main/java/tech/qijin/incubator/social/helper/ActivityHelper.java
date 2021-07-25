package tech.qijin.incubator.social.helper;

import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;

import java.util.List;
import java.util.Map;

public interface ActivityHelper {
    SocialActivity getActivity(Long activityId);

    List<SocialActivity> listActivity();

    Map<Long, List<SocialActivityParticipant>> mapActivityParticipant(List<Long> activityIds);

    boolean hasJoined(Long userId, Long activityId);

    boolean addActivityParticipant(Long activityId, Long userId);

    boolean removeActivityParticipant(Long activityId, Long userId);

    Map<Long, List<SocialActivityImage>> mapActivityImage(List<Long> activityIds);

    List<SocialActivityImage> listActivityImage(Long activityId);

    SocialActivityImage addActivityImage(Long activityId, SocialActivityImage image);

    boolean delActivityImage(Long activityId, Long imageId);

    boolean delAllActivityImage(Long activityId);

    List<SocialActivityParticipant> listActivityParticipants(Long activityId);

    SocialActivity addActivity(Long userId, SocialActivity activity);

    boolean updateActivity(Long userId, Long activityId, SocialActivity activity);

    boolean closeActivity(Long activityId, Long userId);

}
