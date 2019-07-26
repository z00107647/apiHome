package apihome.modules.apitest.controller;

import apihome.modules.apitest.entity.ApiTestGroupEntity;
import apihome.modules.apitest.service.ApiTestGroupService;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接口分组信息
 * Created by zyanycall@gmail.com on 2019-07-26 15:55.
 */
@RestController
@RequestMapping("/apiTest/group")
public class ApiTestGroupController {

    @Autowired
    private ApiTestGroupService apiTestGroupService;

    /**
     * 所有项目列表
     */
    @GetMapping("/list")
    @RequiresPermissions("apiTest:group:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = apiTestGroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 项目信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("apiTest:group:info")
    public R info(@PathVariable("id") Long id){
        ApiTestGroupEntity group = apiTestGroupService.getById(id);

        return R.ok().put("group", group);
    }

    /**
     * 保存项目
     */
    @SysLog("保存项目信息")
    @PostMapping("/save")
    @RequiresPermissions("apiTest:group:save")
    public R save(@RequestBody ApiTestGroupEntity group){
        ValidatorUtils.validateEntity(group);

        apiTestGroupService.saveGroup(group);

        return R.ok();
    }

    /**
     * 修改项目
     */
    @SysLog("修改项目信息")
    @PostMapping("/update")
    @RequiresPermissions("apiTest:group:update")
    public R update(@RequestBody ApiTestGroupEntity group){
        ValidatorUtils.validateEntity(group);

        apiTestGroupService.update(group);

        return R.ok();
    }

    /**
     * 删除项目
     */
    @SysLog("删除项目信息")
    @PostMapping("/delete")
    @RequiresPermissions("apiTest:group:delete")
    public R delete(@RequestBody Long[] ids){
        apiTestGroupService.deleteBatch(ids);

        return R.ok();
    }


}
