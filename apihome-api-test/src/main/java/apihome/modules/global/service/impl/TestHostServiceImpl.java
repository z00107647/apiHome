package apihome.modules.global.service.impl;

import apihome.modules.global.dao.TestHostDao;
import apihome.modules.global.entity.TestHostEntity;
import apihome.modules.global.service.TestHostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-25 20:13.
 */
@Service("TestHostService")
public class TestHostServiceImpl extends ServiceImpl<TestHostDao, TestHostEntity> implements TestHostService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String) params.get("hostName");

        IPage<TestHostEntity> page = this.page(
                new Query<TestHostEntity>().getPage(params),
                new QueryWrapper<TestHostEntity>()
                        .like(StringUtils.isNotBlank(paramKey), "host_name", paramKey)
//                        .eq("status", 1)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHost(TestHostEntity testHostEntity) {
        this.save(testHostEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TestHostEntity testHostEntity) {
        this.updateById(testHostEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
