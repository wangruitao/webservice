package org.template.com.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.template.com.model.User;


@WebService
public interface UserService  {

	User loadUserByLoginName(String userName);

	@WebMethod
	List<User> findAll();
	@WebMethod
	User save(@WebParam(name = "user") User user);

	@WebMethod
	User findByUserId(@WebParam(name = "id") Long id);
	
	@WebMethod
	boolean update(@WebParam(name = "user") User user);
	
	@WebMethod
	boolean delete(@WebParam(name = "id") Long id);

	
}
