package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.incubator.social.api.vo.CardDetailVo;
import tech.qijin.incubator.social.api.vo.CardVo;
import tech.qijin.incubator.social.api.vo.CardsVo;
import tech.qijin.incubator.social.service.CardService;
import tech.qijin.incubator.social.service.bo.CardBo;
import tech.qijin.incubator.social.service.bo.CardDetailBo;

import java.util.List;

/**
 * @author michealyang
 * @date 2019-12-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/incubator/social/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/recommend")
    public CardsVo recommendCards() {
        List<CardBo> cardBos =  cardService.listCard();
        return CardsVo.builder()
                .cards(CardVo.fromList(cardBos))
                .build();
    }

    @GetMapping("/detail")
    public CardDetailVo cardDetail(Long userId) {
        CardDetailBo cardDetailBo = cardService.getCardDetail(userId);
        return CardDetailVo.from(cardDetailBo);
    }
}
