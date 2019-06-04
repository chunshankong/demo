package rpa.compensate;

import com.alibaba.fastjson.JSON;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
public enum BidChangeRecordStatus {


    DEFAULT("0","默认"),
    SUCCESS("1","成功"),
    FAILED("2","失败");

    private String value;

    private String description;

    BidChangeRecordStatus(String value, String description) {
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
