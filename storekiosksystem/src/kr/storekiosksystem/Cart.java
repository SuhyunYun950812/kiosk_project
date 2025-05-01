package kr.storekiosksystem;

import java.util.ArrayList;

//장바구니 관련 클래스
public class Cart {
	// 변수
	private ArrayList<Menu> menuList;	
	private int totalPrice;		// 총가격

	// 생성자
	public Cart() {
		this.menuList = new ArrayList<Menu>();
	}

	public ArrayList<Menu> getMenu() {
		return menuList;
	}
	
	public void setMenu(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void addMenu(Menu menu) {
		this.menuList.add(menu);
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int price) {
		this.totalPrice = price;
	}

	public void addTotalPrice(int price) {
		this.totalPrice += price;
	}
	
	public void clearCart() {
		menuList.clear();
		totalPrice = 0;
	}
	
	//addMenu	/addTotalPrice 이 함수들은 Cart클래스 안에 함수로 선택 메뉴를 장바구니에 있는
	//메뉴리스트에 더하는 함수랑  선택한 메뉴 가격을 받아 총액에 더하는 함수입니다!
	
	
	
}