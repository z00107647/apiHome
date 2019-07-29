package apihome.modules.apitest.service.impl;

import apihome.modules.apitest.dao.ApiTestRequestDao;
import apihome.modules.apitest.entity.ApiTestRequestEntity;
import apihome.modules.apitest.service.ApiTestRequestService;
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
 * Created by zyanycall@gmail.com on 2019-07-29 10:30.
 */
@Service("ApiTestRequestService")
public class ApiTestRequestServiceImpl extends ServiceImpl<ApiTestRequestDao, ApiTestRequestEntity> implements ApiTestRequestService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String paramKey = (String) params.get("requestName");

        IPage<ApiTestRequestEntity> page = this.page(
                new Query<ApiTestRequestEntity>().getPage(params),
                new QueryWrapper<ApiTestRequestEntity>()
                        .like(StringUtils.isNotBlank(paramKey), "request_name", paramKey)
//                        .eq("status", 1)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRequest(ApiTestRequestEntity apiTestRequestEntity) {
        this.save(apiTestRequestEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ApiTestRequestEntity apiTestRequestEntity) {
        this.updateById(apiTestRequestEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
//        for(Long id : ids){
//            SysConfigEntity config = this.getById(id);
//            sysConfigRedis.delete(config.getParamKey());
//        }
        this.removeByIds(Arrays.asList(ids));
    }
}
