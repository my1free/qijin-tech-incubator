package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.service.bo.MeBo;

import java.util.List;

@Data
@Builder
public class GalleryVo {
    private ProfileVo profile;
    private List<ImageVo> images;

    public static GalleryVo from(MeBo meBo) {
        return GalleryVo.builder()
                .profile(ProfileVo.from(meBo.getProfile()))
                .images(ImageVo.from(meBo.getImages()))
                .build();
    }
}
