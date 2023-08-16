package top.serms.hgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.ObjectUtils;
import top.serms.hgm.pojo.MgApp;
import top.serms.hgm.mapper.MgAppMapper;
import top.serms.hgm.pojo.MgVideo;
import top.serms.hgm.pojo.MgVideoNum;
import top.serms.hgm.service.MgAppService;
import org.springframework.stereotype.Service;
import top.serms.hgm.service.MgVideoNumService;
import top.serms.hgm.service.MgVideoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MgApp)表服务实现类
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@Service
public class MgAppServiceImpl extends ServiceImpl<MgAppMapper, MgApp> implements MgAppService {

    @Resource
    private MgVideoNumService mgVideoNumService;

    @Resource
    private MgVideoService mgVideoService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public MgApp getByAppId(String appId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<MgApp>().eq(MgApp::getAppId, appId));
    }

    @Override
    public boolean removeByIdsSerMs(List<Long> id) {
        TransactionStatus txStatus = null;
        try {
            DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
            txStatus = transactionManager.getTransaction(txDef);

            // 先删除视频视频剧集
            this.mgVideoService.remove(new QueryWrapper<MgVideo>().in("pid", id));
            this.mgVideoNumService.remove(new QueryWrapper<MgVideoNum>().in("pid", id));
            baseMapper.deleteBatchIds(id);
            transactionManager.commit(txStatus);
            return true;
        } catch (Exception e) {
            if (txStatus != null) {
                transactionManager.rollback(txStatus);
            }
            throw e;
        }
    }
}

