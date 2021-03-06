package xhu.wncg.firesystem.system.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xhu.wncg.common.exception.FireException;
import xhu.wncg.common.utils.Constant;
import xhu.wncg.common.utils.Query;
import xhu.wncg.firesystem.system.mapper.SysUserMapper;
import xhu.wncg.firesystem.system.pojo.SysUser;
import xhu.wncg.firesystem.system.service.SysRoleService;
import xhu.wncg.firesystem.system.service.SysUserRoleService;
import xhu.wncg.firesystem.system.service.SysUserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BZhao
 * @version 2017/11/2.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUsername(String username) {
        return sysUserMapper.queryByUsername(username);
    }

    @Override
    public List<SysUser> queryList(Query query) {
        return sysUserMapper.queryList(query);
    }

    @Override
    public int queryTotal(Query query) {
        return sysUserMapper.queryTotal(query);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserMapper.updatePassword(map);
    }

    @Override
    public SysUser queryObject(Long userId) {
        return sysUserMapper.queryObject(userId);
    }

    @Override
    @Transactional
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        //sha256??????
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        sysUserMapper.save(user);

        //????????????????????????
        checkRole(user);

        //???????????????????????????
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserMapper.update(user);

        //????????????????????????
        checkRole(user);

        //???????????????????????????
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        sysUserMapper.deleteBatch(userIds);
    }

    /**
     * ????????????????????????
     */
    private void checkRole(SysUser user) {
        //??????????????????????????????????????????????????????????????????????????????
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //?????????????????????????????????
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //??????????????????
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new FireException("?????????????????????????????????????????????");
        }
    }
}
