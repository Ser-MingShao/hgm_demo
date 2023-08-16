package top.serms.hgm.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: SerMs
 * @Email: 1839928782@qq.com
 * @packagePath: top.serms.hgm.pojo.vo.MgVideoNumVo
 * @date: 2023/8/16  16:42
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgVideoNumVo {
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
