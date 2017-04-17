package org.template.com.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.template.com.common.enums.CommonDisabledEnums;
import org.template.com.common.enums.UserLockedEnums;
import org.template.com.mapper.RoleMapper;
import org.template.com.mapper.UserMapper;
import org.template.com.model.User;
import org.template.com.service.UserService;

import tk.mybatis.mapper.entity.Example;

@WebService(targetNamespace="http://service.org.template.com/", endpointInterface="org.template.com.service.UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public User loadUserByLoginName(String userName) {
		User user = new User();
		Example example = new Example(User.class);
		example.createCriteria().andCondition("login_name=", userName);
		List<User> users = userMapper.selectByExample(example);
		if(users.get(0) instanceof User ) ;
		if(users != null && users.size() == 1) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		
		User user = new User();
		user.setDisabled(CommonDisabledEnums.DISABLE_FALSE.getFlag());
		return userMapper.selectAll();
	}

	@Override
	public User save(User user) {
		user.setCreateTime(new Date());
		user.setLocked(UserLockedEnums.LOCKED_FALSE.getFlag());
		user.setDisabled(CommonDisabledEnums.DISABLE_FALSE.getFlag());
		userMapper.insert(user);
		return user;
	}

	@Override
	public User findByUserId(Long id) {
		User user = new User();
		user.setId(id);
		user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public boolean update(User user) {
		
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean delete(Long id) {
		
		return userMapper.deleteByPrimaryKey(id) > 0;
	}

}
