package kr.storekiosksystem;
// 사용자 관련 클래스
public class User {
	// 변수
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhoneNum;
	private Cart userCart;

	// 생성자
	public User() {
		this(null,null,null,null);
	}

	public User(String userId, String userPassword, String userName, String userPhoneNum) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
		this.userCart = new Cart(); // 사용자 생성 시 장바구니 자동 생성
	}
	
	// 함수
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public Cart getUserCart() {
		return userCart;
	}

	@Override
	public String toString() {
		return "사용자 명 = " + userName + " / 사용자ID = " + userId
				+ " / 연락처 = " + userPhoneNum;
	}
	
}
