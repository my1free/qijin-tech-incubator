package tech.qijin.incubator.social.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum ActivityParticipantStatus implements EnumValue<String> {
    JOIN(0, "加入"),
    QUIT(1, "退出"),
    ;

    private Integer value;
    private String desc;

    ActivityParticipantStatus(Integer value, String desc) {
        this.value = value;
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
