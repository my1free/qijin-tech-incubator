package tech.qijin.incubator.social.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum ActivityImageStatus implements EnumValue<String> {
    NORMAL(0, "正常"),
    DELETED(1, "删除"),
    ;

    private Integer value;
    private String desc;

    ActivityImageStatus(Integer value, String desc) {
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
