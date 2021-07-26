package tech.qijin.incubator.social.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.service.CellUserImageService;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.incubator.social.api.vo.CardDetailVo;
import tech.qijin.incubator.social.base.CardStatus;
import tech.qijin.incubator.social.db.model.SocialCard;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.helper.CardHelper;
import tech.qijin.incubator.social.helper.LoveHelper;
import tech.qijin.incubator.social.service.CardService;
import tech.qijin.incubator.social.service.bo.CardBo;
import tech.qijin.incubator.social.service.bo.CardDetailBo;
import tech.qijin.satellites.user.auth.UserUtil;
import tech.qijin.satellites.user.service.UserObserverService;
import tech.qijin.satellites.user.service.bo.UserProfileAndImageBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
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
    @Autowired
    private UserObserverService userObserverService;
    @Autowired
    private ProfileAndImageObserver profileAndImageObserver;

    @PostConstruct
    public void init() {
        userObserverService.addProfileAndImageObserver(profileAndImageObserver);
    }

    @Override
    public List<CardBo> listCard() {
        UserProfile userProfile = getCurrUserProfile();

//        List<SocialCard> socialCards = cardHelper.listCardsByGender(getQueryGender(userProfile));
        List<SocialCard> socialCards = cardHelper.listAllCards(userProfile.getUserId());
        List<Long> userIds = socialCards.stream()
                .map(SocialCard::getUserId)
                .collect(Collectors.toList());

        Map<Long, UserProfile> userProfileMap = cellUserProfileService.mapProfile(userIds);
        Map<Long, List<UserImage>> userImagesMap = cellUserImageService.mapUserImages(userIds);
        return userIds.stream()
                .map(uid -> {
                    List<UserImage> images = userImagesMap.get(uid);
                    if (CollectionUtils.isNotEmpty(images) && images.size() > 5) {
                        images = images.subList(0, 5);
                    }
                    return CardBo.builder()
                            .profile(userProfileMap.get(uid))
                            .images(images)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public CardDetailBo getCardDetail(Long userId) {
        UserProfile profile = getUserProfile(userId);
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


    private UserProfile getUserProfile(Long userId) {
        UserProfile userProfile = cellUserProfileService.getProfile(userId);
        MAssert.notNull(userProfile, ResEnum.UNAUTHORIZED);
        return userProfile;
    }

    private UserProfile getCurrUserProfile() {
        return getUserProfile(UserUtil.getUserId());
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

    @Component
    public class ProfileAndImageObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            UserProfileAndImageBo profileAndImageBo = (UserProfileAndImageBo) arg;
            UserProfile profile = profileAndImageBo.getProfile();
            List<UserImage> images = profileAndImageBo.getImages();
            boolean show = cardHelper.shouldShow(profile, images);
            log.info("ProfileAndImageObserver userId={}, show={}", profile.getUserId(), show);
            SocialCard card = cardHelper.getCardByUserId(profile.getUserId());
            if (show) {
                doShow(profile, card);
            } else {
                doHide(profile, card);
            }
        }

        private void doShow(UserProfile profile, SocialCard card) {
            if (card == null) {
                cardHelper.addCard(profile.getUserId(), profile.getGender());
                return;
            }
            if (CardStatus.SHOW.equals(card.getStatus())) {
                return;
            }
            cardHelper.updateCardStatus(card.getId(), CardStatus.SHOW);
        }

        private void doHide(UserProfile profile, SocialCard card) {
            if (card == null) {
                return;
            }
            if (CardStatus.NOT_SHOW.equals(card.getStatus())) {
                return;
            }
            cardHelper.updateCardStatus(card.getId(), CardStatus.NOT_SHOW);
        }
    }
}
