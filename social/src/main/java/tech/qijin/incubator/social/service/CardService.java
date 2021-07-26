package tech.qijin.incubator.social.service;

import tech.qijin.incubator.social.service.bo.CardBo;
import tech.qijin.incubator.social.service.bo.CardDetailBo;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface CardService {
    List<CardBo> listCard();

    CardDetailBo getCardDetail(Long userId);
}
