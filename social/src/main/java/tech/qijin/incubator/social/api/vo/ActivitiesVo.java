package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActivitiesVo {
    private List<ActivityVo> activities;
}
