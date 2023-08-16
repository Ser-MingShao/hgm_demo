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
 * (MgVideoNum)实体类
 *
 * @author: SerMs
 * @Email: 1839928782@qq.com
 * @packagePath: top.serms.hgm
 * @date: 2023-08-16 12:09:19
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "MgVideoNum", value = "视频剧集内容")
public class MgVideoNum implements Serializable {
    private static final long serialVersionUID = 928585395625317792L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "小程序id")
    @TableField(value = "pid")
    private Integer pid;

    @ApiModelProperty(value = "视频id")
    @TableField(value = "video_id")
    private Integer videoId;

    @ApiModelProperty(value = "视频第几集")
    @TableField(value = "video_num")
    private Integer videoNum;


    @ApiModelProperty(value = "微信剧目id")
    @TableField(value = "media_id")
    private String mediaId;

    @TableField(value = "mp4_url")
    private String mp4Url;

    @ApiModelProperty(value = "过期时间")
    @TableField(value = "expires_time")
    private Long expiresTime;
}
