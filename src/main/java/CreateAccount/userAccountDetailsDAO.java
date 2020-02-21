package CreateAccount;

public interface userAccountDetailsDAO {
	public  int addUser (userAccountDetails a) throws Exception ;
	//@SqlUpdate("update user_account set user_password=? where user_id=?")
	public  boolean forgetPassword(int userid,String password) throws Exception ;
	//@SqlUpdate("select email_id from user_account where email_id=?")
    public boolean checkEmailId(String emailId) throws Exception ;
	//@SqlUpdate("select user_id from user_account where user_id = ?")
    public boolean validateLogin(int userId) throws Exception ;
	//@SqlUpdate("select user_password from user_account where user_id = ?")
    public boolean validateLogin2(int userId,String password) throws Exception ;
	//@SqlUpdate("select email_id from user_account where user_id=?")
    public boolean checkEmailId2(String emailId,int userId) throws Exception ;



}
