package com.empathy.web.admin;

import com.empathy.config.R;
import com.empathy.domain.sys.SysMenuEntity;
import com.empathy.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

/**
 * Created by MI on 2018/4/27.
 */
@Controller
@RequestMapping("/sys/menu")
public class SysController extends AbstractController {

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/nav")
    public R nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        return R.ok().put("menuList", menuList);
    }


}
