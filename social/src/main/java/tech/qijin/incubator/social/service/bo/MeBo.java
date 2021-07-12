package tech.qijin.incubator.social.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;

import java.util.List;

@Data
@Builder
public class MeBo {
    private List<SocialHobby> hobbies;
    private List<SocialLove> loves;
}
