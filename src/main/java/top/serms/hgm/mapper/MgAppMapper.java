package top.serms.hgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.serms.hgm.pojo.MgApp;

/**
 * (MgApp)表数据库访问层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@Mapper
public interface MgAppMapper extends BaseMapper<MgApp> {

    @Select("select * from mg_app where app_id = #{appId}")
    MgApp getByAppId(String appId);
}

