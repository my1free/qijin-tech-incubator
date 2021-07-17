package tech.qijin.incubator.social.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.service.CellUserImageService;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialHobbyUnit;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.helper.LoveHelper;
import tech.qijin.incubator.social.service.MeService;
import tech.qijin.incubator.social.service.bo.HobbiesBo;
import tech.qijin.incubator.social.service.bo.MeBo;
import tech.qijin.satellites.user.auth.UserUtil;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class MeServiceImpl implements MeService {
    @Autowired
    private LoveHelper loveHelper;
    @Autowired
    private CellUserProfileService cellUserProfileService;
    @Autowired
    private CellUserImageService cellUserImageService;

    @Override
    public MeBo getMeInfo() {
        Long userId = UserUtil.getUserId();
        UserProfile profile = cellUserProfileService.getProfile(userId);
        List<UserImage> images = cellUserImageService.listUserImage(userId);
        List<SocialLove> loves =  loveHelper.listLove(userId);
        List<SocialHobby> hobbies = loveHelper.listHobby(userId);
        return MeBo.builder()
                .profile(profile)
                .images(images)
                .loves(loves)
                .hobbies(hobbies)
                .build();
    }

    @Override
    public MeBo getGallery() {
        Long userId = UserUtil.getUserId();
        UserProfile profile = cellUserProfileService.getProfile(userId);
        List<UserImage> images = cellUserImageService.listUserImage(userId);
        return MeBo.builder()
                .profile(profile)
                .images(images)
                .build();
    }

    @Override
    public HobbiesBo listHobbies() {
        List<SocialHobby> hobbies = loveHelper.listHobby(UserUtil.getUserId());
        List<SocialHobbyUnit> hobbyUnits = loveHelper.listHobbyUnit();
        return HobbiesBo.builder()
                .hobbies(hobbies)
                .hobbyUnits(hobbyUnits)
                .build();
    }

    @Override
    public SocialHobby addHobby(String content) {
        MAssert.notBlank(content, ResEnum.INVALID_PARAM);
        SocialHobbyUnit hobbyUnit = loveHelper.getHobbyUnit(content);
        if (hobbyUnit == null) {
            loveHelper.addHobbyUnit(content);
        }else {
            loveHelper.incrHobbyUnitCount(hobbyUnit);
        }
        SocialHobby socialHobby = loveHelper.getHobby(UserUtil.getUserId(), content);
        if (socialHobby == null) {
            return loveHelper.addHobby(UserUtil.getUserId(), content);
        }else{
            loveHelper.enableHobby(socialHobby);
            return socialHobby;
        }
    }

    @Override
    public void delHobby(String content) {
        MAssert.notBlank(content, ResEnum.INVALID_PARAM);
        loveHelper.delHobby(UserUtil.getUserId(), content);
    }

    @Override
    public SocialLove upsertLove(LoveKind loveKind, String content) {
        SocialLove socialLove = loveHelper.getLoveByKind(UserUtil.getUserId(), loveKind);
        if (socialLove == null) {
            return loveHelper.addLove(UserUtil.getUserId(), loveKind, content);
        }else{
            loveHelper.updateLove(socialLove, content);
            return socialLove;
        }
    }
}
