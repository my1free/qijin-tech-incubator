package tech.qijin.incubator.social.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum CardStatus implements EnumValue<Integer> {
    SHOW(1, "展示"),
    NOT_SHOW(0, "不展示"),
    ;

    private Integer value;
    private String desc;

    CardStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer value() {
        return this.value;
    }

    @Override
    public String desc() {
        return null;
    }
}
