package top.serms.hgm.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * (MgApp)实体类
 *
 * @author: SerMs
 * @Email: 1839928782@qq.com
 * @packagePath: top.serms.hgm
 * @date: 2023-08-16 12:09:18
 * @Description:
 */
@Data
@AllArgsConstructor
@ApiModel(description = "MgApp", value = "小程序实体")
@NoArgsConstructor
public class MgApp implements Serializable {
    private static final long serialVersionUID = -35807319396443210L;

    @ApiModelProperty(value = "id")
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    @ApiModelProperty(value = "微信小程序ID")
    @TableField(value = "app_id")
    private String appId;

    @ApiModelProperty(value = "小程序唯一凭证密钥，即 AppSecret")
    @TableField(value = "app_secret")
    private String appSecret;

    @ApiModelProperty(value = "accessToken")
    @TableField(value = "access_token")
    private String accessToken;

    @ApiModelProperty(value = "过期时间")
    @TableField(value = "expires_time")
    private Long expiresTime;
}
