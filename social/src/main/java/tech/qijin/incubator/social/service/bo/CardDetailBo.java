package tech.qijin.incubator.social.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialLove;

import java.util.List;

@Data
@Builder
public class CardDetailBo {
    private UserProfile profile;
    private List<UserImage> images;
    private List<SocialHobby> hobbies;
    private List<SocialLove> loves;
}
