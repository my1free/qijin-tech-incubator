package tech.qijin.incubator.social.service;

import tech.qijin.incubator.social.service.bo.ActivitiesBo;
import tech.qijin.incubator.social.service.bo.ActivityBo;

import java.util.List;

public interface ActivityService {
    List<ActivityBo> listActivity();

    ActivityBo getActivity(Long activityId);

}
