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
 * (MgVideo)实体类
 *
 * @author: SerMs
 * @Email: 1839928782@qq.com
 * @packagePath: top.serms.hgm
 * @date: 2023-08-16 12:09:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "MgVideo", value = "视频实体")
public class MgVideo implements Serializable {
    private static final long serialVersionUID = -75489522497233839L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 小程序id
     */
    @ApiModelProperty(value = "小程序id")
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 微信剧目id
     */
    @ApiModelProperty(value = "微信剧目id")
    @TableField(value = "drama_id")
    private String dramaId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField(value = "remark")
    private String remark;
}
