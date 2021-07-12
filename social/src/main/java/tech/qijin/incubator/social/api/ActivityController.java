package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.incubator.social.api.vo.ActivitiesVo;

@Slf4j
@RestController
@RequestMapping("/incubator/social/activity")
public class ActivityController {
    @GetMapping("/list")
    public ActivitiesVo listActivity() {
        return new ActivitiesVo();
    }
}
