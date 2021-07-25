package tech.qijin.incubator.social.service;

import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.service.bo.ActivityBo;

import java.util.List;

public interface ActivityService {
    List<ActivityBo> listActivity();

    ActivityBo getActivity(Long activityId);

    Long addActivity(SocialActivity socialActivity, List<SocialActivityImage> images);

    boolean updateActivity(Long activityId, SocialActivity socialActivity, List<SocialActivityImage> images);

    /**
     * 加入活动
     * @param activityId
     * @return
     */
    boolean joinActivity(Long activityId);

    /**
     * 取消加入活动
     * @param activityId
     * @return
     */
    boolean cancelActivity(Long activityId);

    /**
     * 关闭活动
     * @param activityId
     * @return
     */
    boolean closeActivity(Long activityId);

}
