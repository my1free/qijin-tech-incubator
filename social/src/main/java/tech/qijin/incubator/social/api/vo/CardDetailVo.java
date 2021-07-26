package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.service.bo.CardDetailBo;

import java.util.List;

@Data
@Builder
public class CardDetailVo {
    private ProfileVo profile;
    private List<ImageVo> images;
    private LoveVo love;
    private List<HobbyVo> hobbies;
    private boolean shouldShow;

    public static CardDetailVo from(CardDetailBo cardDetailBo) {
        return CardDetailVo.builder()
                .profile(ProfileVo.from(cardDetailBo.getProfile()))
                .images(ImageVo.from(cardDetailBo.getImages()))
                .love(LoveVo.from(cardDetailBo.getLoves()))
                .hobbies(HobbyVo.from(cardDetailBo.getHobbies()))
                .build();
    }

}
