package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.ebus.dao.UserAccountDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.model.UserAccount;
import com.chainsys.ebus.util.ConnectionUtil;

public class UserAccountDAOImpl implements UserAccountDAO {
	public int addUser(UserAccount a) throws DbException,SQLException {

		String sql = "insert into user_account(user_name,user_id,user_password,gender,dob,contact_number,email_id) values(?,user_id.nextval,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, a.getUserName());
			pst.setString(2, a.getPassword());
			pst.setString(3, a.getGender());
			java.sql.Date dob = java.sql.Date.valueOf(a.getDob());
			pst.setDate(4, dob);
			pst.setLong(5, a.getContactNumber());
			pst.setString(6, a.getEmailId());
			pst.executeUpdate();

			String emailId = a.getEmailId();

			int userid = showUserId(emailId);
			return userid;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.ADDUSER,e);

		}

	}

	private int showUserId(String emailId) throws DbException,SQLException {
		int userid = 0;

		String sql = "select user_id from user_account where email_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, emailId);
			try (ResultSet row = pst.executeQuery();) {
				if (row.next()) {
					userid = row.getInt("user_id");

				}

				return userid;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.USERID,e);

		}

	}

	public boolean forgetPassword(int userid, String password) throws DbException,SQLException {

		String sql = "update user_account set user_password=? where user_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, password);
			pst.setInt(2, userid);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				
				return true;
			} else {

				return false;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.FORGETPASSWORD,e);

		}
	}

	public boolean validateEmailId(String emailId) throws DbException,SQLException {

		String emailid = "select email_id from user_account where email_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(emailid);) {

			smt.setString(1, emailId);
			try (ResultSet row = smt.executeQuery();) {
				String email = "";
				if (row.next()) {
					email = row.getString("email_id");
				}
				if (email.equals(emailId)) {

					return false;
				} else {

					return true;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEEMAIL,e);

		}

	}

	public boolean validateEmailIdWithUserId(String emailId, int userId) throws DbException,SQLException {

		String emailid = "select email_id from user_account where user_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(emailid);) {
			smt.setInt(1, userId);
			try (ResultSet row = smt.executeQuery();) {
				String email = "";
				if (row.next()) {
					email = row.getString("email_id");
				}
				if (email.equals(emailId)) {

					return true;
				} else {

					return false;
				}

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEEMAILWITHUSERID,e);

		}

	}

	public boolean validateUserId(int userId) throws DbException,SQLException {

		String sql = "select user_id from user_account where user_id = ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(sql);) {
			smt.setInt(1, userId);
			try (ResultSet row2 = smt.executeQuery();) {
				int userid = 0;
				if (row2.next()) {
					userid = row2.getInt("user_id");
				}
				if (userId == userid) {

					return true;
				} else {

					return false;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEUSERID,e);

		}

	}

	public boolean validateLogin(int userId, String password) throws DbException,SQLException {

		String sql = "select user_password from user_account where user_id = ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(sql);) {
			smt.setInt(1, userId);
			try (ResultSet row3 = smt.executeQuery();) {
				if (row3.next())
					if (password.equals(row3.getString("user_password"))) {
						return true;
					} else {
						return false;
					}

				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATELOGIN,e);
		}
	}
}
