package tech.qijin.incubator.social.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HobbyAddReqVo {
    @NotNull
    private String content;
}
