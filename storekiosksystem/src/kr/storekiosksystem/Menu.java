package kr.storekiosksystem;
// 메뉴 관련 클래스
public class Menu {
	// 변수
	private String menuName;	// 메뉴명 예) 망고바나나프라푸치노
	private int price;			// 메뉴가격 에) 7900
	private int count;			// 메뉴 갯수 세는 용도 예) 1

	// 기본생성자
	public Menu() { // 매개변수 개수 0개
		this(null, 0, 0);
	}
	// 오버로딩 된 생성자1
	public Menu(String menuName, int price) { // 매개변수 개수 2개
		this(menuName, price, 1);
	}
	// 오버로딩 된 생성자2
	public Menu(String menuName, int price, int count) { // 매개변수 개수 3개
		this.menuName = menuName;
		this.price = price;
		this.count = count;
	}
	// 함수
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() { // int ==> 리턴타입
		return count; // count ==> 리턴밸류
	}

	public void setCount(int count) {
		if(count>0) {
			this.count = count;
		}
	}

	@Override
	public String toString() {
		return "메뉴명 = " + menuName + " | 갯수 = "+ count + " | 가격 = "+ price+"₩";
	}

	/*
	 * @Override public String toString() { return "메뉴명 = " + menuName + " / 가격 = "
	 * + price+"₩"; }
	 */
	
	
}
