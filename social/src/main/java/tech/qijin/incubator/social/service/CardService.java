package tech.qijin.incubator.social.service;

import tech.qijin.incubator.social.service.bo.CardBo;
import tech.qijin.incubator.social.service.bo.CardDetailBo;

import java.util.List;

public interface CardService {
    List<CardBo> listCard();

    CardDetailBo getCardDetail();
}
