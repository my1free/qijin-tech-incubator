package tech.qijin.incubator.social.api.vo;

import lombok.Data;
import tech.qijin.incubator.social.base.LoveKind;

@Data
public class LoveReqVo {
    private LoveKind loveKind;
    private String content;
}
