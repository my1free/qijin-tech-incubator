package tech.qijin.incubator.social.api.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProfileVo {
    private Long userId;
    // 姓名
    private String name;
    // 身高
    private String height;
    // 性别
    private Gender gender;
    // 头像
    private String avatar;
    // 年龄
    private Integer age;
    // 生日
    private String birthday;
    // 籍贯
    private String bornCity;
    // 居住城市
    private String liveCity;
    // 学校
    private String edu;
    // 学历
    private String eduDegree;
    // 工作
    private String job;
    // 星座
    private String constellation;

    public static ProfileVo from(UserProfile profile) {
        ProfileVo profileVo = ConvertUtil.convert(profile, ProfileVo.class);
        if (profile.getBirthday() != null) {
            profileVo.setBirthday(DateUtil.formatStr(profile.getBirthday(), DateUtil.DATE_FORMAT));
        }
        profileVo.setAge(DateUtil.getAgeByBirth(profile.getBirthday()));
        profileVo.setConstellation(DateUtil.getConstellation(profile.getBirthday()));
        return profileVo;
    }

    public static List<ProfileVo> from(List<UserProfile> profiles) {
        return profiles.stream().map(ProfileVo::from).collect(Collectors.toList());
    }
}
