package tech.qijin.incubator.social.helper.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.qijin.incubator.social.base.ActivityImageStatus;
import tech.qijin.incubator.social.base.ActivityParticipantStatus;
import tech.qijin.incubator.social.base.ActivityStatus;
import tech.qijin.incubator.social.db.dao.SocialActivityDao;
import tech.qijin.incubator.social.db.dao.SocialActivityImageDao;
import tech.qijin.incubator.social.db.dao.SocialActivityParticipantDao;
import tech.qijin.incubator.social.db.model.*;
import tech.qijin.incubator.social.helper.ActivityHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ActivityHelperImpl implements ActivityHelper {
    @Autowired
    private SocialActivityDao socialActivityDao;
    @Autowired
    private SocialActivityImageDao socialActivityImagesDao;
    @Autowired
    private SocialActivityParticipantDao socialActivityParticipantDao;

    @Override
    public SocialActivity getActivity(Long activityId) {
        return socialActivityDao.selectByPrimaryKey(activityId);
    }

    @Override
    public List<SocialActivity> listActivity() {
        SocialActivityExample example = new SocialActivityExample();
        example.setOrderByClause("end_time");
        example.createCriteria()
                .andStatusEqualTo(ActivityStatus.OPENED)
                .andEndTimeGreaterThan(DateUtil.now());
        return socialActivityDao.selectByExampleWithBLOBs(example);
    }

    @Override
    public Map<Long, List<SocialActivityParticipant>> mapActivityParticipant(List<Long> activityIds) {
        if (CollectionUtils.isEmpty(activityIds)) return Collections.emptyMap();
        SocialActivityParticipantExample example = new SocialActivityParticipantExample();
        example.createCriteria()
                .andActivityIdIn(activityIds)
                .andStatusEqualTo(ActivityParticipantStatus.JOIN);
        return socialActivityParticipantDao.selectByExample(example).stream()
                .collect(Collectors.groupingBy(SocialActivityParticipant::getActivityId));
    }

    @Override
    public boolean hasJoined(Long userId, Long activityId) {
        SocialActivityParticipantExample example = new SocialActivityParticipantExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andActivityIdEqualTo(activityId)
                .andStatusEqualTo(ActivityParticipantStatus.JOIN);
        return socialActivityParticipantDao.selectByExample(example).size() > 0;
    }

    @Override
    public boolean addActivityParticipant(Long activityId, Long userId) {
        SocialActivityParticipant participant = new SocialActivityParticipant();
        participant.setActivityId(activityId);
        participant.setStatus(ActivityParticipantStatus.JOIN);
        participant.setUserId(userId);
        return socialActivityParticipantDao.insertSelective(participant) > 0;
    }

    @Override
    public boolean removeActivityParticipant(Long activityId, Long userId) {
        SocialActivityParticipantExample example = new SocialActivityParticipantExample();
        example.createCriteria()
                .andActivityIdEqualTo(activityId)
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(ActivityParticipantStatus.JOIN);
        SocialActivityParticipant record = new SocialActivityParticipant();
        record.setStatus(ActivityParticipantStatus.QUIT);
        return socialActivityParticipantDao.updateByExampleSelective(record, example) > 0;
    }
    @Override
    public Map<Long, List<SocialActivityImage>> mapActivityImage(List<Long> activityIds) {
        if (CollectionUtils.isEmpty(activityIds)) return Collections.emptyMap();
        SocialActivityImageExample example = new SocialActivityImageExample();
        example.createCriteria()
                .andActivityIdIn(activityIds)
                .andStatusEqualTo(ActivityImageStatus.NORMAL);
        return socialActivityImagesDao.selectByExample(example).stream()
                .collect(Collectors.groupingBy(SocialActivityImage::getActivityId));
    }

    @Override
    public List<SocialActivityImage> listActivityImage(Long activityId) {
        List<SocialActivityImage> images= mapActivityImage(Lists.newArrayList(activityId)).get(activityId);
        return images != null ? images : Lists.newArrayList();
    }

    @Override
    public SocialActivityImage addActivityImage(Long activityId, SocialActivityImage image) {
        MAssert.isTrue(StringUtils.isNotBlank(image.getUrl()), ResEnum.INVALID_PARAM.code, "图片url不能为空");
        image.setActivityId(activityId);
        image.setStatus(ActivityImageStatus.NORMAL);
        socialActivityImagesDao.insertSelective(image);
        return image;
    }

    @Override
    public boolean delActivityImage(Long activityId, Long imageId) {
        SocialActivityImageExample example = new SocialActivityImageExample();
        example.createCriteria()
                .andActivityIdEqualTo(activityId)
                .andIdEqualTo(imageId);
        SocialActivityImage record = new SocialActivityImage();
        record.setStatus(ActivityImageStatus.DELETED);
        return socialActivityImagesDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public boolean delAllActivityImage(Long activityId) {
        SocialActivityImageExample example = new SocialActivityImageExample();
        example.createCriteria()
                .andActivityIdEqualTo(activityId);
        SocialActivityImage record = new SocialActivityImage();
        record.setStatus(ActivityImageStatus.DELETED);
        return socialActivityImagesDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public List<SocialActivityParticipant> listActivityParticipants(Long activityId) {
        List<SocialActivityParticipant> participants = mapActivityParticipant(Lists.newArrayList(activityId)).get(activityId);
        return participants != null ? participants : Lists.newArrayList();
    }

    @Override
    public SocialActivity addActivity(Long userId, SocialActivity activity) {
        checkActivity(activity);
        activity.setSponsor(userId);
        activity.setStatus(ActivityStatus.OPENED);
        // TODO set tags
        socialActivityDao.insertSelective(activity);
        return activity;
    }

    @Override
    public boolean updateActivity(Long userId, Long activityId, SocialActivity activity) {
        checkActivity(activity);
        SocialActivityExample example = new SocialActivityExample();
        example.createCriteria()
                .andIdEqualTo(activityId)
                .andSponsorEqualTo(userId);
        return socialActivityDao.updateByExampleSelective(activity, example) > 0;
    }

    @Override
    public boolean closeActivity(Long activityId, Long userId) {
        SocialActivityExample example = new SocialActivityExample();
        example.createCriteria().andIdEqualTo(activityId)
                .andSponsorEqualTo(userId);
        SocialActivity record = new SocialActivity();
        record.setStatus(ActivityStatus.STOPPED);
        return socialActivityDao.updateByExampleSelective(record, example) > 0;
    }

    private void checkActivity(SocialActivity activity) {
        Date now = DateUtil.now();
        MAssert.isTrue(StringUtils.isNotBlank(activity.getTitle()), ResEnum.INVALID_PARAM.code, "[活动名称] 必须填写哦");
        MAssert.isTrue(StringUtils.isNotBlank(activity.getDescription()), ResEnum.INVALID_PARAM.code, "[活动简介] 必须填写哦");
        MAssert.isTrue(activity.getStartTime() != null, ResEnum.INVALID_PARAM.code, "[开始时间] 必须填写哦");
        MAssert.isTrue(activity.getEndTime() != null, ResEnum.INVALID_PARAM.code, "[结束时间] 必须填写哦");
        MAssert.isTrue(now.compareTo(activity.getEndTime()) <0, ResEnum.INVALID_PARAM.code, "[结束时间] 不能早于当前时间哦");
    }
}
