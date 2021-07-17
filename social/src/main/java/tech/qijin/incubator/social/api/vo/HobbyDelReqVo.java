package tech.qijin.incubator.social.api.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HobbyDelReqVo {
    @NotBlank
    private String content;
}
