package rpa.compensate;

/**
 * @author yangsiguo
 * @date 2019/6/3
 * @desc TODO add description in here
 */
public enum ENV {

    DEV("dev"),
    TEST("test"),
    PRO("pro");

    private String value;

    ENV(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ENV getByValue(String value){
        for (ENV env : ENV.values()) {
            if (env.getValue().equals(value)){
                return env;
            }
        }
        return DEV;
    }


}
