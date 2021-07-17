package tech.qijin.incubator.social.helper;

import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialHobbyUnit;
import tech.qijin.incubator.social.db.model.SocialLove;

import java.util.List;

public interface LoveHelper {
    List<SocialLove> listLove(long userId);

    SocialLove getLoveByKind(long userId, LoveKind loveKind);

    SocialLove addLove(long userId, LoveKind loveKind, String content);

    boolean updateLove(SocialLove love, String content);

    List<SocialHobby> listHobby(long userId);

    List<SocialHobbyUnit> listHobbyUnit();

    SocialHobbyUnit getHobbyUnit(String content);

    SocialHobbyUnit addHobbyUnit(String content);

    boolean incrHobbyUnitCount(SocialHobbyUnit hobbyUnit);

    SocialHobby getHobby(long userId, String content);

    boolean enableHobby(SocialHobby socialHobby);

    SocialHobby addHobby(long userId, String content);

    boolean delHobby(Long userId, String content);
}
