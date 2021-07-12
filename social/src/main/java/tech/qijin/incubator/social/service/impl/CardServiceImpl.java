package tech.qijin.incubator.social.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.service.CellUserImageService;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.incubator.social.api.vo.CardDetailVo;
import tech.qijin.incubator.social.db.model.SocialCard;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.helper.CardHelper;
import tech.qijin.incubator.social.helper.LoveHelper;
import tech.qijin.incubator.social.service.CardService;
import tech.qijin.incubator.social.service.bo.CardBo;
import tech.qijin.incubator.social.service.bo.CardDetailBo;
import tech.qijin.satellites.user.auth.UserUtil;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardHelper cardHelper;
    @Autowired
    private LoveHelper loveHelper;
    @Autowired
    private CellUserImageService cellUserImageService;
    @Autowired
    private CellUserProfileService cellUserProfileService;

    @Override
    public List<CardBo> listCard() {
        UserProfile userProfile = getCurrUserProfile();

        List<SocialCard> socialCards = cardHelper.listCardsByGender(getQueryGender(userProfile));
        List<Long> userIds = socialCards.stream()
                .map(SocialCard::getUserId)
                .collect(Collectors.toList());

        Map<Long, UserProfile> userProfileMap = cellUserProfileService.mapProfile(userIds);
        Map<Long, List<UserImage>> userImagesMap = cellUserImageService.mapUserImages(userIds);
        return userIds.stream()
                .map(uid -> CardBo.builder()
                        .profile(userProfileMap.get(uid))
                        .images(userImagesMap.get(uid))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CardDetailBo getCardDetail() {
        UserProfile profile = getCurrUserProfile();
        List<UserImage> images = cellUserImageService.listUserImage(profile.getUserId());
        List<SocialLove> loves = loveHelper.listLove(profile.getUserId());
        List<SocialHobby> hobbies = loveHelper.listHobby(profile.getUserId());
        return CardDetailBo.builder()
                .profile(profile)
                .images(images)
                .hobbies(hobbies)
                .loves(loves)
                .build();
    }

    private UserProfile getCurrUserProfile() {
        Long userId = UserUtil.getUserId();
        UserProfile userProfile = cellUserProfileService.getProfile(userId);
        MAssert.notNull(userProfile, ResEnum.UNAUTHORIZED);
        return userProfile;
    }

    private Gender getQueryGender(UserProfile userProfile) {
        Gender gender = null;
        if (Gender.FEMALE.equals(userProfile.getGender())) {
            gender = Gender.MALE;
        } else if (Gender.MALE.equals(userProfile.getGender())) {
            gender = Gender.FEMALE;
        }
        return gender;
    }
}
