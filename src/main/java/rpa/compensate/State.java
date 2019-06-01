package rpa.compensate;

import com.alibaba.fastjson.JSON;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
public enum  State {

    DELETE("0","软删除"),
    DEFAULT("1","默认"),
    SUCCESS("2","成功"),
    FAILED("3","失败");

    private String value;

    private String description;

    State(String value, String description) {
        this.value = value;
        this.description=description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
