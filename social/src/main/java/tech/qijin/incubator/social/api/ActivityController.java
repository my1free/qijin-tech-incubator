package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.incubator.social.api.vo.ActivitiesVo;
import tech.qijin.incubator.social.api.vo.ActivityVo;
import tech.qijin.incubator.social.service.ActivityService;
import tech.qijin.incubator.social.service.bo.ActivityBo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/incubator/social/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/list")
    public ActivitiesVo listActivity() {
        List<ActivityBo> activityBos = activityService.listActivity();
        return ActivitiesVo.builder()
                .activities(ActivityVo.from(activityBos))
                .build();
    }

    @GetMapping("/detail")
    public ActivityVo getActivityDetail(Long activityId) {
        ActivityBo activityBo = activityService.getActivity(activityId);
        return ActivityVo.from(activityBo);
    }
}
