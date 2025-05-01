package kr.storekiosksystem;
// 관리자 관련 클래스
import java.util.ArrayList;

public class Owner {		//관리자
	// 변수
	private String ownerId;	// 관리자 아이디
	private String ownerPassword;	// 관리자 비번
	private String storeName;	// 가게명
	private ArrayList<User> userList;	// 사용자(회원) 보관용 어레이리스트
	private ArrayList<Menu> storeMenuList;	// 가게 메뉴 어레이리스트
	private int dailySales;	// 하루 매출

	
	// 생성자
	public Owner() {
		this(null, null, null);	// 디폴트값 관리자ID / 관리자PW / 관리자명
	}

	public Owner(String ownerId, String ownerPassword, String storeName) {
		super();
		this.ownerId = ownerId;
		this.ownerPassword = ownerPassword;
		this.storeName = storeName;
		this.userList = new ArrayList<User>();
		this.storeMenuList = new ArrayList<Menu>();
		this.dailySales = 0;
	}
	// 함수

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerPassword() {
		return ownerPassword;
	}

	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public ArrayList<Menu> getStoreMenu() {
		return storeMenuList;
	}

	public void setStoreMenu(ArrayList<Menu> menuL) {
		this.storeMenuList = menuL;
	}

	public int getDailySales() {
		return this.dailySales;
	}

	public void setDailySales(int dailySales) {
		this.dailySales = dailySales;
	}

	public ArrayList<User> getUserList() {
		return this.userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
}