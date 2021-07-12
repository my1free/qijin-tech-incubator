package tech.qijin.incubator.social.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserProfile;

import java.util.List;

@Data
@Builder
public class CardBo {
    private UserProfile profile;
    private List<UserImage> images;
}
