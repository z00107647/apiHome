package apihome.modules.apitest.controller;

import apihome.modules.apitest.entity.ApiTestProjectEntity;
import apihome.modules.apitest.service.ApiTestProjectService;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接口测试的项目管理信息
 * Created by zyanycall@gmail.com on 2019-07-02 14:56.
 */
@RestController
@RequestMapping("/apiTest/project")
public class ApiTestProjectController {
    @Autowired
    private ApiTestProjectService apiTestProjectService;

    /**
     * 所有项目列表
     */
    @GetMapping("/list")
    @RequiresPermissions("apiTest:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = apiTestProjectService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 项目信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("apiTest:project:info")
    public R info(@PathVariable("id") Long id){
        ApiTestProjectEntity project = apiTestProjectService.getById(id);

        return R.ok().put("project", project);
    }

    /**
     * 保存项目
     */
    @SysLog("保存项目信息")
    @PostMapping("/save")
    @RequiresPermissions("apiTest:project:save")
    public R save(@RequestBody ApiTestProjectEntity project){
        ValidatorUtils.validateEntity(project);

        apiTestProjectService.saveProject(project);

        return R.ok();
    }

    /**
     * 修改项目
     */
    @SysLog("修改项目信息")
    @PostMapping("/update")
    @RequiresPermissions("apiTest:project:update")
    public R update(@RequestBody ApiTestProjectEntity project){
        ValidatorUtils.validateEntity(project);

        apiTestProjectService.update(project);

        return R.ok();
    }

    /**
     * 删除项目
     */
    @SysLog("删除项目信息")
    @PostMapping("/delete")
    @RequiresPermissions("apiTest:project:delete")
    public R delete(@RequestBody Long[] ids){
        apiTestProjectService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 项目全景，不仅仅包含项目信息，还包含其他表的联查数据
     */
    @GetMapping("/view/{id}")
    @RequiresPermissions("apiTest:project:view")
    public R view(@PathVariable("id") Long id){
        ApiTestProjectEntity project = apiTestProjectService.getById(id);

        return R.ok().put("project", project);
    }



}
