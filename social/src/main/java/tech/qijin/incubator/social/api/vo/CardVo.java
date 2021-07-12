package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.service.bo.CardBo;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CardVo {
    private Long userId;
    private ProfileVo profile;
    private List<ImageVo> images;

    public static CardVo from(CardBo cardBo) {
        return CardVo.builder()
                .userId(cardBo.getProfile().getUserId())
                .profile(ProfileVo.from(cardBo.getProfile()))
                .images(ImageVo.fromList(cardBo.getImages()))
                .build();
    }

    public static List<CardVo> fromList(List<CardBo> cardBos) {
        return cardBos.stream().map(CardVo::from).collect(Collectors.toList());
    }
}
