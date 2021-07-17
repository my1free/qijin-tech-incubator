package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.incubator.social.db.model.SocialHobbyUnit;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class HobbyUnit {
    private Long id;
    private String content;
    private Integer count;

    public static HobbyUnit from(SocialHobbyUnit socialHobbyUnit) {
        return HobbyUnit.builder()
                .id(socialHobbyUnit.getId())
                .content(socialHobbyUnit.getContent())
                .count(socialHobbyUnit.getCount())
                .build();
    }

    public static List<HobbyUnit> from(List<SocialHobbyUnit> hobbyUnits) {
        return hobbyUnits.stream().map(HobbyUnit::from).collect(Collectors.toList());
    }

}
