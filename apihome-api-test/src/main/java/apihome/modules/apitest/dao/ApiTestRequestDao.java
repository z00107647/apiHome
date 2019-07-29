package apihome.modules.apitest.dao;

import apihome.modules.apitest.entity.ApiTestRequestEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zyanycall@gmail.com on 2019-07-29 10:27.
 */
@Mapper
public interface ApiTestRequestDao  extends BaseMapper<ApiTestRequestEntity> {
}
