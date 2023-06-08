import java.util.ArrayList;

import javax.swing.JOptionPane;
public class User {
	private ArrayList<String> username; 
	private ArrayList<String> password;
	
	public User() {
		username = new ArrayList<String>(); 
		password = new ArrayList<String>();
	}
	public void add(String name, String pw) throws PasswordError, UserError{
		if (name.length() == 0) throw new UserError("Username can't be empty");
		if (pw.length() != 8) throw new PasswordError("Password should be 8 letter");
		
		if(!username.contains(name)) {
			username.add(name); password.add(pw);
			JOptionPane.showMessageDialog(null,"註冊成功");
		}
		else {
			JOptionPane.showMessageDialog(null,"已註冊，請登入");
		}
		
		return;
	}
	public void checkUserExist(String name) throws UserError {
		if(username.contains(name)) {
			return;
		}
		throw new UserError("Can't find the user");
	}
	public void checkPassword(String name, String PW) throws PasswordError {
		int id = username.indexOf(name);
		if(password.get(id).equals(PW)) {
			JOptionPane.showMessageDialog(null,"登入成功");
			return;
		}
		throw new PasswordError("Password is wrong");
	}
	public ArrayList<String> getUsername() {
		return username;
	}
}
class UserError extends Exception {
	public UserError(String Error){
		super(Error);
	}
}

class PasswordError extends Exception {
	public PasswordError(String Error){
		super(Error);
	}
}
