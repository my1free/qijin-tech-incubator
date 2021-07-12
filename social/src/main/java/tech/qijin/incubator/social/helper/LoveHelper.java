package tech.qijin.incubator.social.helper;

import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;

import java.util.List;

public interface LoveHelper {
    List<SocialLove> listLove(long userId);

    List<SocialHobby> listHobby(long userId);
}
