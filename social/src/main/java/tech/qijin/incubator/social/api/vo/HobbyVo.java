package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.incubator.social.db.model.SocialHobby;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@Builder
public class HobbyVo {
    private Long id;
    private String content;
    private String cl;

    private static final String[] CL_LIST = new String[]{
            ".bg-cyan.light",
            ".bg-brown.light",
            ".bg-orange.light",
            ".bg-grey.light",
            ".bg-purple.light",
            ".bg-blue.light",
            ".bg-mauve.light",
    };

    public static HobbyVo from(SocialHobby hobby) {
        Random rand = new Random();
        int idx = rand.nextInt(CL_LIST.length);
        return HobbyVo.builder()
                .id(hobby.getId())
                .content(hobby.getContent())
                .cl(CL_LIST[idx])
                .build();
    }

    public static List<HobbyVo> from(List<SocialHobby> hobbies) {
        return hobbies.stream().map(HobbyVo::from).collect(Collectors.toList());
    }
}
