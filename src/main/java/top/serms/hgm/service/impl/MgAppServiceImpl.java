package top.serms.hgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import top.serms.hgm.pojo.MgApp;
import top.serms.hgm.mapper.MgAppMapper;
import top.serms.hgm.service.MgAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (MgApp)表服务实现类
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@Service
public class MgAppServiceImpl extends ServiceImpl<MgAppMapper, MgApp> implements MgAppService {

    @Override
    public MgApp getByAppId(String appId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<MgApp>().eq(MgApp::getAppId, appId));
    }
}

