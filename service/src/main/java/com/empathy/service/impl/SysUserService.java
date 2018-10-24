package com.empathy.service.impl;

import com.empathy.dao.SysUserDao;
import com.empathy.service.ISysUserRoleService;
import com.empathy.service.ISysUserService;
import com.empathy.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/27.
 */
@Service
public class SysUserService implements ISysUserService {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);

    }
/*

    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");

        Page<SysUserEntity> page = this.selectPage(
                new Query<SysUserEntity>(params).getPage(),
                new EntityWrapper<SysUserEntity>()
                        .like(StringUtils.isNotBlank(username),"username", username)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

//		for(SysUserEntity sysUserEntity : page.getRecords()){
//			SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
//			sysUserEntity.setDeptName(sysDeptEntity.getName());
//		}

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        this.insert(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserEntity user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        }
        this.updateById(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }


    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }
*/


}
