package apihome.modules.apitest.service.impl;

import apihome.modules.apitest.dao.ApiTestGroupDao;
import apihome.modules.apitest.entity.ApiTestGroupEntity;
import apihome.modules.apitest.service.ApiTestGroupService;
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
 * Created by zyanycall@gmail.com on 2019-07-26 15:41.
 */
@Service("ApiTestGroupService")
public class ApiTestGroupServiceImpl extends ServiceImpl<ApiTestGroupDao, ApiTestGroupEntity> implements ApiTestGroupService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String) params.get("projectId");

        IPage<ApiTestGroupEntity> page = this.page(
                new Query<ApiTestGroupEntity>().getPage(params),
                new QueryWrapper<ApiTestGroupEntity>()
                        .like(StringUtils.isNotBlank(paramKey), "project_id", paramKey)
//                        .eq("status", 1)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGroup(ApiTestGroupEntity apiTestGroupEntity) {
        this.save(apiTestGroupEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ApiTestGroupEntity apiTestGroupEntity) {
        this.updateById(apiTestGroupEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
