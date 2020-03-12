package com.chainsys.ebus.dao;

import java.sql.SQLException;

import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.model.UserAccount;

public interface UserAccountDAO {
	public  int addUser (UserAccount a) throws DbException,SQLException ;
	public  boolean forgetPassword(int userid,String password) throws DbException,SQLException ;
    public boolean validateEmailId(String emailId) throws DbException,SQLException ;
    public boolean validateUserId(int userId) throws DbException,SQLException ;
    public boolean validateLogin(int userId,String password) throws DbException,SQLException ;
    public boolean validateEmailIdWithUserId(String emailId,int userId) throws DbException,SQLException ;



}
