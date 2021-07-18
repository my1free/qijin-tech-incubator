package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.incubator.social.api.vo.ActivitiesVo;
import tech.qijin.incubator.social.api.vo.ActivityReqVo;
import tech.qijin.incubator.social.api.vo.ActivityVo;
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.service.ActivityService;
import tech.qijin.incubator.social.service.bo.ActivityBo;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.pojo.ResultVo;

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

    @PostMapping("/update")
    public ResultVo addOrUpdateActivity(@RequestBody ActivityReqVo activityReqVo) {
        SocialActivity socialActivity = ConvertUtil.convert(activityReqVo, SocialActivity.class);
        List<SocialActivityImage> images = ConvertUtil.convertList(activityReqVo.getImages(), SocialActivityImage.class);
        if (NumberUtil.gtZero(activityReqVo.getActivityId())) {
            activityService.updateActivity(activityReqVo.getActivityId(), socialActivity, images);
        }else{
            activityService.addActivity(socialActivity, images);
        }
        return ResultVo.instance().success();
    }
}
