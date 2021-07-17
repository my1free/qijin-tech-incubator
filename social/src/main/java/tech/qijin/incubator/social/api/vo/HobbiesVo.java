package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.service.bo.HobbiesBo;

import java.util.List;

@Data
@Builder
public class HobbiesVo {
    private List<HobbyVo> hobbies;
    private List<HobbyUnit> hobbyUnits;

    public static HobbiesVo from(HobbiesBo hobbiesBo) {
        return HobbiesVo.builder()
                .hobbies(HobbyVo.from(hobbiesBo.getHobbies()))
                .hobbyUnits(HobbyUnit.from(hobbiesBo.getHobbyUnits()))
                .build();
    }

}
