package tech.qijin.incubator.social.helper;

import tech.qijin.cell.user.base.Gender;
import tech.qijin.incubator.social.db.model.SocialCard;

import java.util.List;

public interface CardHelper {
    List<SocialCard> listAllCards();

    List<SocialCard> listCardsByGender(Gender gender);
}