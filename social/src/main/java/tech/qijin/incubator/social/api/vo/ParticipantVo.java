package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantVo {
    private Long id;
    private ProfileVo profile;
    private String contact;
}
