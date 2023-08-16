package top.serms.hgm.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: SerMs
 * @Email: 1839928782@qq.com
 * @packagePath: top.serms.hgm.pojo.vo.MgAppVo
 * @date: 2023/8/16  13:36
 * @Description:
 **/
@Data
@AllArgsConstructor
@ApiModel(description = "MgApp", value = "小程序实体VO")
@NoArgsConstructor
public class MgAppVo {
    private static final long serialVersionUID = -35807319396443210L;


    @ApiModelProperty(value = "微信小程序ID")
    @TableField(value = "app_id")
    private String appId;

    @ApiModelProperty(value = "小程序唯一凭证密钥，即 AppSecret")
    @TableField(value = "app_secret")
    private String appSecret;
}
