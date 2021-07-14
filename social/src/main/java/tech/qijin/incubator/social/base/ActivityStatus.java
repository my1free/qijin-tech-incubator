package tech.qijin.incubator.social.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum ActivityStatus implements EnumValue<String> {
    OPENED(0, "开启"),
    STOPPED(1, "停止"),
    ;

    private Integer value;
    private String desc;

    ActivityStatus(Integer value, String desc) {
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
