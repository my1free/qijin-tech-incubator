package tech.qijin.incubator.social.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.incubator.social.base.CardStatus;
import tech.qijin.incubator.social.db.dao.SocialCardDao;
import tech.qijin.incubator.social.db.model.SocialCard;
import tech.qijin.incubator.social.db.model.SocialCardExample;
import tech.qijin.incubator.social.helper.CardHelper;
import tech.qijin.util4j.trace.pojo.Channel;

import java.util.List;

@Slf4j
@Component
public class CardHelperImpl implements CardHelper {
    @Autowired
    private SocialCardDao socialCardDao;

    @Override
    public List<SocialCard> listAllCards(Long userId) {
        SocialCardExample example = new SocialCardExample();
        example.createCriteria()
                .andUserIdNotEqualTo(userId)
                .andStatusEqualTo(CardStatus.SHOW);
        return socialCardDao.selectByExample(example);
    }

    @Override
    public List<SocialCard> listCardsByGender(Long userId, Gender gender) {
        SocialCardExample example = new SocialCardExample();
        example.createCriteria()
                .andUserIdNotEqualTo(userId)
                .andStatusEqualTo(CardStatus.SHOW)
                .andGenderEqualTo(gender);
        return socialCardDao.selectByExample(example);
    }

    @Override
    public SocialCard getCardByUserId(long userId) {
        SocialCardExample example = new SocialCardExample();
        example.createCriteria()
                .andUserIdNotEqualTo(userId);
        return socialCardDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public boolean addCard(long userId, Gender gender) {
        SocialCard socialCard = new SocialCard();
        socialCard.setChannel(Channel.SOCIAL);
        socialCard.setUserId(userId);
        socialCard.setGender(gender);
        socialCard.setStatus(CardStatus.SHOW);
        return socialCardDao.insertSelective(socialCard) > 0;
    }

    @Override
    public boolean updateCardStatus(long id, CardStatus status) {
        SocialCard card = new SocialCard();
        card.setId(id);
        card.setStatus(status);
        return socialCardDao.updateByPrimaryKeySelective(card) > 0;
    }

    @Override
    public boolean shouldShow(UserProfile profile, List<UserImage> images) {
        if (StringUtils.isNotBlank(profile.getName())
                && profile.getGender() != null
                && profile.getBirthday() != null
                && StringUtils.isNotBlank(profile.getAvatar())
                && CollectionUtils.isNotEmpty(images)) {
            return true;
        }
        return false;
    }
}
