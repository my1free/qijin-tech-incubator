package tech.qijin.incubator.social.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.incubator.social.base.CardStatus;
import tech.qijin.incubator.social.db.dao.SocialCardDao;
import tech.qijin.incubator.social.db.model.SocialCard;
import tech.qijin.incubator.social.db.model.SocialCardExample;
import tech.qijin.incubator.social.helper.CardHelper;

import java.util.List;

@Slf4j
@Component
public class CardHelperImpl implements CardHelper {
    @Autowired
    private SocialCardDao socialCardDao;

    @Override
    public List<SocialCard> listAllCards() {
        SocialCardExample example = new SocialCardExample();
        example.createCriteria()
                .andStatusEqualTo(CardStatus.SHOW);
        return socialCardDao.selectByExample(example);
    }

    @Override
    public List<SocialCard> listCardsByGender(Gender gender) {
        SocialCardExample example = new SocialCardExample();
        example.createCriteria()
                .andStatusEqualTo(CardStatus.SHOW)
                .andGenderEqualTo(gender);
        return socialCardDao.selectByExample(example);
    }
}
