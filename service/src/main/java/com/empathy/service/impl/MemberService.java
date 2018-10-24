package com.empathy.service.impl;

import com.empathy.common.IdWorker;
import com.empathy.common.RspResult;
import com.empathy.dao.MemberDao;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.MemberRole;
import com.empathy.domain.member.bo.MemberBo;
import com.empathy.domain.user.UserMember;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IMemberService;
import com.empathy.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:18
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class MemberService extends AbstractBaseService implements IMemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult save(Member entity) {
        entity.setId(IdWorker.genId());
        entity.setPassword(EncryptUtils.encrypt(entity.getPassword()));
        memberDao.save(entity);
        addRoles(entity.getId(), entity.getRoleIds());
        return success(entity);
    }

    /**
     * 添加用户角色
     *
     * @param memberId
     * @param ids
     */
    private void addRoles(Long memberId, List<Long> ids) {
        memberDao.delRolesById(memberId);
        if (ids != null && ids.size() > 0) {
            List<MemberRole> list = new ArrayList<>();
            for (Long id : ids) {
                MemberRole mr = new MemberRole();
                mr.setMemberId(memberId);
                mr.setRoleId(id);
                list.add(mr);
            }
            memberDao.saveRoles(list);
        }
    }

    @Override
    public Member findById(Long id) {
        return memberDao.findById(id);
    }

    @Override
    public Member findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult update(Member entity) {
        memberDao.update(entity);
        addRoles(entity.getId(), entity.getRoleIds());
        return success(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult delById(Long aLong) {
        memberDao.delById(aLong);
        return success();
    }

    @Override
    public RspResult page(MemberBo bo) {
        final long count = memberDao.count(bo);
        List<Member> list = new ArrayList<>();
        if (count > 0) {
            list = memberDao.list(bo);
            if (list != null) {
                for (Member m : list) {
                    m.setRole(getRole(m.getId()));
                }
            }
        }
        return success(count, list);
    }

    /**
     * 用户拥有的角色
     *
     * @param memberId
     * @return
     */
    private String getRole(Long memberId) {
        StringBuilder sb = new StringBuilder();
        List<String> roles = memberDao.findRoleById(memberId);
        if (roles != null) {
            int i = 0;
            for (String role : roles) {
                if (i == 0) {
                    sb.append(role);
                } else {
                    sb.append(",").append(role);
                }
                i++;
            }
        }
        return sb.toString();
    }

    @Override
    public List<Long> findHadAssignRolesByUserId(Long memberId) {
        return memberDao.findHadAssignRolesByUserId(memberId);
    }


}
