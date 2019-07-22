package cn.dengxin.myo2o.exceptions;

/**
 * Created by Dengxin on 2019/7/20 11:30 AM
 */
public class AreaOperationException extends RuntimeException {
    private static final long serialVersionUID = -1512771573934050550L;

    public AreaOperationException(String msg) {
        super(msg);
    }
}
