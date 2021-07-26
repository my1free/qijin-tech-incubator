package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.service.bo.MeBo;

import java.util.List;

@Data
@Builder
public class MeVo {
    private ProfileVo profile;
    private LoveVo love;
    private List<ImageVo> images;
    private List<HobbyVo> hobbies;
    private boolean shouldShow;

    public static MeVo from(MeBo meBo) {
        if (meBo == null) return null;
        return MeVo.builder()
                .profile(ProfileVo.from(meBo.getProfile()))
                .images(ImageVo.from(meBo.getImages()))
                .love(LoveVo.from(meBo.getLoves()))
                .hobbies(HobbyVo.from(meBo.getHobbies()))
                .shouldShow(meBo.isShouldShow())
                .build();
    }
}
