package cn.dengxin.myo2o.enums;

/**
 * Created by Dengxin on 2019/7/20 3:43 PM
 */
public enum LocalAuthStateEnum {
    LOGINFAIL(-1,"密码或账号输入有误"),SUCCESS(0,"操作成功"),
    NULL_AUTH_INFO(-1006,"注册信息为空"),ONLY_ONE_ACCOUNT(-1007,"最多只能不绑定一个本地账号");

    private int state;

    private String stateInfo;

    private LocalAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static LocalAuthStateEnum stateOf(int index) {
        for (LocalAuthStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
