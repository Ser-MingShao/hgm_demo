package top.serms.hgm.util;

import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4326147203336606257L;

    @ApiModelProperty(value = "状态编码：200-成功，非200 -> 失败", required = true)
    private Integer code;
    @ApiModelProperty(value = "提示消息", required = true)
    private String msg;
    @ApiModelProperty(value = "响应数据", required = true)
    private T data; //数据

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 200;
        result.msg = "ok";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(T object, String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 1;
        result.data = object;
        return result;
    }

    /**
     * 是否成功
     *
     * @return
     */
    @JsonIgnore
    public Boolean isSuccess() {
        return NumberUtil.equals(this.code, 0);
    }

}
