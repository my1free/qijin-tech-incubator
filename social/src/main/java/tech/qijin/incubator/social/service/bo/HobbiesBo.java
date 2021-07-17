package tech.qijin.incubator.social.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialHobbyUnit;

import java.util.List;

@Data
@Builder
public class HobbiesBo {
    private List<SocialHobby> hobbies;
    private List<SocialHobbyUnit> hobbyUnits;
}
