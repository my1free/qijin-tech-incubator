package tech.qijin.incubator.social.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.incubator.social.db.dao.SocialHobbyDao;
import tech.qijin.incubator.social.db.dao.SocialLoveDao;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialHobbyExample;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.db.model.SocialLoveExample;
import tech.qijin.incubator.social.helper.LoveHelper;

import java.util.List;

@Slf4j
@Service
public class LoveHelperImpl implements LoveHelper {
    @Autowired
    private SocialHobbyDao socialHobbyDao;
    @Autowired
    private SocialLoveDao socialLoveDao;

    @Override
    public List<SocialLove> listLove(long userId) {
        SocialLoveExample example = new SocialLoveExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return socialLoveDao.selectByExample(example);
    }

    @Override
    public List<SocialHobby> listHobby(long userId) {
        SocialHobbyExample example = new SocialHobbyExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return socialHobbyDao.selectByExample(example);
    }
}
