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
 * @packagePath: top.serms.hgm.pojo.vo.MgVideoVo
 * @date: 2023/8/16  16:50
 * @Description:
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgVideoVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信剧目id
     */
    @ApiModelProperty(value = "微信剧目id")
    @TableField(value = "drama_id")
    private String dramaId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField(value = "remark")
    private String remark;
}
