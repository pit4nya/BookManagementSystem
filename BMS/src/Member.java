//Member Class
public class Member {

	int num;
	String name;
	String tel;
	String addr;
	String email;
	String id;
	String password;

	public Member() {
	}

	public Member(int _num, String _name, String _tel, String _addr, String _email, String _id, String _password) {
		this.num = _num;
		this.name = _name;
		this.tel = _tel;
		this.addr = _addr;
		this.email = _email;
		this.id = _id;
		this.password = _password;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}
}
