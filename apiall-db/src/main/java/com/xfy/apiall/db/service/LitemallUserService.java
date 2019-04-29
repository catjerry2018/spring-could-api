package com.xfy.apiall.db.service;

import com.github.pagehelper.PageHelper;
import com.xfy.apiall.db.dao.LitemallUserMapper;
import com.xfy.apiall.db.domain.LitemallUser;
import com.xfy.apiall.db.domain.LitemallUserExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallUserService {
    @Resource
    private LitemallUserMapper  userMapper;

    public List<LitemallUser> queryByUid(Integer uid) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andIdEqualTo(uid).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public LitemallUser findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public LitemallUser findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public int add(LitemallUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.insertSelective(user);
    }

}
