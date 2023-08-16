package top.serms.hgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.serms.hgm.pojo.MgVideoNum;

/**
 * (MgVideoNum)表数据库访问层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:11
 */
@Mapper
public interface MgVideoNumMapper extends BaseMapper<MgVideoNum> {
}

