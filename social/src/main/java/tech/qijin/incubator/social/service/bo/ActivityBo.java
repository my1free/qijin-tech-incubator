package tech.qijin.incubator.social.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ActivityBo {
    private SocialActivity activity;
    private List<SocialActivityImage> activityImages;
    private List<SocialActivityParticipant> participants;
    private Map<Long, UserProfile> userProfileMap;

    private boolean isSponsor;
    private boolean isParticipant;
}
