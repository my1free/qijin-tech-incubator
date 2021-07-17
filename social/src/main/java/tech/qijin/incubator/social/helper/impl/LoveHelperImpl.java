package tech.qijin.incubator.social.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.incubator.social.db.dao.SocialHobbyDao;
import tech.qijin.incubator.social.db.dao.SocialHobbyUnitDao;
import tech.qijin.incubator.social.db.dao.SocialLoveDao;
import tech.qijin.incubator.social.db.model.*;
import tech.qijin.incubator.social.helper.LoveHelper;
import tech.qijin.util4j.utils.LogFormat;

import java.util.List;

@Slf4j
@Service
public class LoveHelperImpl implements LoveHelper {
    @Autowired
    private SocialHobbyDao socialHobbyDao;
    @Autowired
    private SocialLoveDao socialLoveDao;
    @Autowired
    private SocialHobbyUnitDao socialHobbyUnitDao;

    @Override
    public List<SocialLove> listLove(long userId) {
        SocialLoveExample example = new SocialLoveExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return socialLoveDao.selectByExample(example);
    }

    @Override
    public SocialLove getLoveByKind(long userId, LoveKind loveKind) {
        SocialLoveExample example = new SocialLoveExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindEqualTo(loveKind);
        return socialLoveDao.selectByExample(example)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public SocialLove addLove(long userId, LoveKind loveKind, String content) {
        SocialLove love = new SocialLove();
        love.setUserId(userId);
        love.setKind(loveKind);
        love.setContent(content);

        socialLoveDao.insertSelective(love);
        return love;
    }

    @Override
    public boolean updateLove(SocialLove love, String content) {
        SocialLoveExample example = new SocialLoveExample();
        example.createCriteria().andIdEqualTo(love.getId());
        SocialLove record = new SocialLove();
        record.setContent(content);
        return socialLoveDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public List<SocialHobby> listHobby(long userId) {
        SocialHobbyExample example = new SocialHobbyExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andValidEqualTo(true);
        return socialHobbyDao.selectByExample(example);
    }

    @Override
    public List<SocialHobbyUnit> listHobbyUnit() {
        SocialHobbyUnitExample example = new SocialHobbyUnitExample();
        example.setOrderByClause("count desc");
        example.createCriteria()
                .andValidEqualTo(true);
        return socialHobbyUnitDao.selectByExample(example);
    }

    @Override
    public SocialHobbyUnit getHobbyUnit(String content) {
        SocialHobbyUnitExample example = new SocialHobbyUnitExample();
        example.createCriteria().andContentEqualTo(content);
        return socialHobbyUnitDao.selectByExample(example)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public SocialHobbyUnit addHobbyUnit(String content) {
        SocialHobbyUnit record = new SocialHobbyUnit();
        record.setContent(content);
        try {
            socialHobbyUnitDao.insertSelective(record);
        } catch (DuplicateKeyException e) {
            log.warn(LogFormat.builder()
                    .message("duplicate key")
                    .put("content", content)
                    .build());
        }
        return record;
    }

    @Override
    public boolean incrHobbyUnitCount(SocialHobbyUnit hobbyUnit) {
        SocialHobbyUnitExample example = new SocialHobbyUnitExample();
        example.createCriteria()
                .andIdEqualTo(hobbyUnit.getId())
                .andValidEqualTo(true);
        SocialHobbyUnit record = new SocialHobbyUnit();
        record.setCount(hobbyUnit.getCount() + 1);
        return socialHobbyUnitDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public SocialHobby getHobby(long userId, String content) {
        SocialHobbyExample example = new SocialHobbyExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andContentEqualTo(content);
        return socialHobbyDao.selectByExample(example)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean enableHobby(SocialHobby socialHobby) {
        socialHobby.setValid(true);
        return socialHobbyDao.updateByPrimaryKeySelective(socialHobby) > 0;
    }

    @Override
    public SocialHobby addHobby(long userId, String content) {
        SocialHobby record = new SocialHobby();
        record.setUserId(userId);
        record.setContent(content);
        try {
            socialHobbyDao.insertSelective(record);
        } catch (DuplicateKeyException e) {
            log.warn(LogFormat.builder()
                    .message("duplicate key")
                    .put("userId", userId)
                    .put("content", content)
                    .build());
        }
        return record;
    }

    @Override
    public boolean delHobby(Long userId, String content) {
        SocialHobbyExample example = new SocialHobbyExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andContentEqualTo(content);
        SocialHobby record = new SocialHobby();
        record.setValid(false);
        return socialHobbyDao.updateByExampleSelective(record, example) > 0;
    }
}
