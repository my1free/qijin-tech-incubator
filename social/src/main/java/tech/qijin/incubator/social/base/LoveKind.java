package tech.qijin.incubator.social.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum LoveKind implements EnumValue<String> {
    SELF_INFO("个人信息"),
    INTEREST("兴趣爱好"),
    FAMILY("家庭背景"),
    EXPECTED("对另一半的期望"),
    ;

    private String desc;

    LoveKind(String desc) {
        this.desc = desc;
    }

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return null;
    }
}
