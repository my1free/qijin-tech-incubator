package tech.qijin.incubator.social.helper;

import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.incubator.social.base.CardStatus;
import tech.qijin.incubator.social.db.model.SocialCard;

import java.util.List;

public interface CardHelper {
    List<SocialCard> listAllCards(Long userId);

    List<SocialCard> listCardsByGender(Long userId, Gender gender);

    SocialCard getCardByUserId(long userId);

    boolean addCard(long userId, Gender gender);

    boolean updateCardStatus(long id, CardStatus status);

    boolean shouldShow(UserProfile profile, List<UserImage> images);
}
