package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.incubator.social.db.model.SocialActivityImage;

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

    public static ImageVo from(SocialActivityImage image) {
        return ImageVo.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }

    public static List<ImageVo> from(List<UserImage> images) {
        return images.stream().map(ImageVo::from).collect(Collectors.toList());
    }

    public static List<ImageVo> from2(List<SocialActivityImage> images) {
        return images.stream().map(ImageVo::from).collect(Collectors.toList());
    }
}
