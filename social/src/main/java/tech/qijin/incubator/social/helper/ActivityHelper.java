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

    Map<Long, List<SocialActivityImage>> mapActivityImage(List<Long> activityIds);

    List<SocialActivityImage> listActivityImage(Long activityId);

    List<SocialActivityParticipant> listActivityParticipants(Long activityId);
}
