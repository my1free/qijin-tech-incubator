package tech.qijin.incubator.social.service;

import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.service.bo.HobbiesBo;
import tech.qijin.incubator.social.service.bo.MeBo;

public interface MeService {
    MeBo getMeInfo();

    MeBo getGallery();

    HobbiesBo listHobbies();

    SocialHobby addHobby(String content);

    void delHobby(String content);

    SocialLove upsertLove(LoveKind loveKind, String content);
}
