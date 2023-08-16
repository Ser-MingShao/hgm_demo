package top.serms.hgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.serms.hgm.pojo.MgVideo;

/**
 * (MgVideo)表数据库访问层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@Mapper
public interface MgVideoMapper extends BaseMapper<MgVideo> {
}

