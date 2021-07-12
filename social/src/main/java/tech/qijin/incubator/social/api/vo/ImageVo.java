package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserImage;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ImageVo {
    private Long id;
    private String url;
    private boolean selected;

    public static ImageVo from(UserImage image) {
        return ImageVo.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }

    public static List<ImageVo> fromList(List<UserImage> images) {
        return images.stream().map(ImageVo::from).collect(Collectors.toList());
    }
}
