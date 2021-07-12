package tech.qijin.incubator.social.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;
import tech.qijin.incubator.social.helper.LoveHelper;
import tech.qijin.incubator.social.service.MeService;
import tech.qijin.incubator.social.service.bo.MeBo;
import tech.qijin.satellites.user.auth.UserUtil;

import java.util.List;

@Slf4j
@Service
public class MeServiceImpl implements MeService {
    @Autowired
    private LoveHelper loveHelper;

    @Override
    public MeBo getMeInfo() {
        Long userId = UserUtil.getUserId();
        List<SocialLove> loves =  loveHelper.listLove(userId);
        List<SocialHobby> hobbies = loveHelper.listHobby(userId);
        return MeBo.builder()
                .loves(loves)
                .hobbies(hobbies)
                .build();
    }
}
