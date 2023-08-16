package top.serms.hgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.serms.hgm.pojo.MgVideoNum;
import top.serms.hgm.mapper.MgVideoNumMapper;
import top.serms.hgm.service.MgVideoNumService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * (MgVideoNum)表服务实现类
 *
 * @author SerMs
 * @since 2023-08-16 12:13:11
 */
@Service
public class MgVideoNumServiceImpl extends ServiceImpl<MgVideoNumMapper, MgVideoNum> implements MgVideoNumService {
    @Override
    public void delByVid(Serializable id) {
        int delete = baseMapper.delete(new LambdaQueryWrapper<MgVideoNum>().eq(MgVideoNum::getVideoId, id));
    }
}

