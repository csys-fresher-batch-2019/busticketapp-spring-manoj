package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.ebus.dao.UserAccountDetailsDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.model.UserAccountDetails;
import com.chainsys.ebus.util.ConnectionUtil;

public class UserAccountDetailsDAOImpl implements UserAccountDetailsDAO {
	public int addUser(UserAccountDetails a) throws DbException,SQLException {

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

		String sql1 = "select user_id from user_account where email_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql1);) {
			pst1.setString(1, emailId);
			try (ResultSet row = pst1.executeQuery();) {
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

		String sql2 = "update user_account set user_password=? where user_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst2 = con.prepareStatement(sql2);) {
			pst2.setString(1, password);
			pst2.setInt(2, userid);
			int rows = pst2.executeUpdate();
			if (rows > 0) {
				con.close();
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

		String email1 = "select email_id from user_account where email_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(email1);) {

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

		String email1 = "select email_id from user_account where user_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(email1);) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEEMAILWITHUSERID,e);

		}

	}

	public boolean validateUserId(int userId) throws DbException,SQLException {

		String sql3 = "select user_id from user_account where user_id = ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt2 = con.prepareStatement(sql3);) {
			smt2.setInt(1, userId);
			try (ResultSet row2 = smt2.executeQuery();) {
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

		String sql4 = "select user_password from user_account where user_id = ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt3 = con.prepareStatement(sql4);) {
			smt3.setInt(1, userId);
			try (ResultSet row3 = smt3.executeQuery();) {
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
