package kr.storekiosksystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

// 로그인(사용자(회원))하면 로그인한 회원으로

public class StoreKioskSystemMain {

	public static String userTitle; // userList.txt 첫 줄을 살리기 위해서 쓴다.
	public static String menuTitle; // menuList.txt 첫 줄을 살리기 위해서 쓴다.

	public static void main(String[] args) { // 메인함수 시작

		Scanner scan = new Scanner(System.in); // 스캐너 선언.
		Owner owner = new Owner(); // 계정관리자
		User user = null; // 지금 로그인 한 사용자(회원).

		owner.setUserList(userFileUpLoad(owner.getUserList())); // txt에 저장된 고객리스트 불러오기
		owner.setStoreMenu(menuFileUpLoad(owner.getStoreMenu())); // txt에 저장된 가게 메뉴 불러오기

		// 관리자 아이디,비번,가게명
		owner.setOwnerId("ysh0812"); // 관리자 ID
		owner.setOwnerPassword("258456"); // 관리자 PW
		owner.setStoreName("피자가게명"); // 가게명
		// FileInputStream fis = new FileInputStream("res/menuList.txt"); 파일불러오기 미완성

		// 메뉴 임시객체 3개

		// 변수선언
		boolean stopFlag = false; // 각 역할 달성 후 처음으로 돌아가기 위함
		boolean loginFlag = false; // 로그인 상태관리

		// 반복(입력, 출력, 연산) 반복문 4가지(카운트셀때 for, 계속돌때 while, 한번은 해줄때 do-while, 향상된 for문)
		while (!stopFlag) { // 메인화면 넘어가는지 안넘어가는지 무한반복으로 관리
			if (!loginFlag) {
				while (!loginFlag && !stopFlag) { // 로그인화면 넘어가는지 안넘어가는지 무한반복으로 관리
					int no = 0;
					// 프로그램 실행하면 나오는 처음 화면
					System.out.println("――――――――――" + owner.getStoreName() + "―――――――――――"); // 사용하는 가게명 출력
					System.out.println("1.로그인 "); // 사용자 로그인
					System.out.println("2.회원가입 "); // 신규사용자 가입
					System.out.println("3.키오스크 종료 ");
					System.out.println("―――――――――――――――――――――――――");
					System.out.print(">>");
					// 입력받은 숫자로 케이스 1,2로 이동하기 위해 변수 no를 선언함.
					try {
						no = Integer.parseInt(scan.nextLine()); // 1 & 2로 선택.
						// no가 1,2 이외의 경우 예외처리 작성 필
						// patternmatches없이 예외처리하기
						if (no == 1 || no == 2 || no == 3) { // 입력한 값이 정수 1 또는(||) 2이면 이 if {}안 코드를 실행한다.
							switch (no) { // case1 안의 switch문 케이스1,2
							// 로그인 기능
							case 1: // no에 1이 들어갔을 경우
								user = userLogin(owner.getUserList());
								loginFlag = true;
								break;
							// 회원가입 기능
							case 2: { // no에 2가 들어갔을 경우
								// String userId, String userPassword, String userName, String userPhoneNum 총
								// 4개의 값을 입력 받아야한다.

								System.out.print("이름을 입력해주세요: "); // 눈으로 이름 입력 요구내용을 확인하게 하기 위함.
								String userName = scan.nextLine(); // 사용자는 이름을 문자열로 입력하여 userName에 저장한다.

								boolean stopFlagId = false; // 동일한 아이디가 있는지 확인하고 무한반복문으로 다시 입력시키려는 용도.
								String userId = null; // while 무한반복문에서 입력받은 문자열값을 밖으로 빼와서 userId에 저장하기 위해서 while문 밖에 둔다.
								while (!stopFlagId) { // 다른 아이디를 입력할 때까지 무한반복문 시작
									System.out.print("아이디를 입력해주세요: "); // 눈으로 만드려는 계정을 입력하라는 요구내용을 확인하게 하기 위함.
									String userInput = scan.nextLine(); // 생성하려는 계정 아이디를 문자열로 입력해서 userInput에 저장한다.
									boolean findFlag = false; // 아이디를
									for (User user1 : owner.getUserList()) { // User 임시객체(user1)를 만들어서 유저리스트를 하나씩 빼온다.
										// user1에서 빼온 값과 생성하려는 아이디 입력값이 동일한게 있는지 equals로 비교한다.(문자열끼리 비교해서 equals 사용)
										if (user1.getUserId().equals(userInput)) { // 두 문자열을 비교해서 동일한게 있다면 {} 안의 내용을
																					// 실행한다.
											System.out.print("/n이미 생성된 아이디가 있습니다. 다시 ");
											findFlag = true; // 애초에 findFlag를 왜 썼지.
										}
									}
									if (findFlag == false) { // 아이디가 없다면 {} 안의 내용을 실행한다.
										userId = userInput; // 새로운 아이디를 생성할 수 있으니 userInput(입력한 값)을 while문 안에서 끝나지않게 하기
															// 위해서
															// userId(진짜 저장될 값)에 값을 넣는다.
										stopFlagId = true; // 더 이상 반복할 이유가 없으니 while문의 반복을 관리하는 stopFlag를 true로 만들어
															// 무한반복을
															// 해제한다.
									}
								}
								// 이하 입력값들은 동일한게 있어도 문제가 없으니 계속 입력받는다.
								System.out.print("비밀번호를 입력해주세요: "); // 눈으로 이름 입력 요구내용을 확인하게 하기 위함.
								String userPassword = scan.nextLine(); // 생성하려는 계정 비밀번호 입력.
								System.out.print("전화번호를 입력해주세요: "); // 눈으로 사용자 연락처를 입력하라는 내용을 확인하게 하기 위함.
								String userPhoneNum = scan.nextLine(); // 사용자의 연락처 입력.
								User _user = new User(userId, userPassword, userName, userPhoneNum); // user 객체에 4가지를
																										// _user객체에
																										// 보관한다.

								ArrayList<User> userList = owner.getUserList();
								userList.add(_user);
								owner.setUserList(userList);

								// owner.addUserList(_user); // 관리자(owner)의 유저리스트에 새로 생성된 사용자의 정보(_user)를 넣는다.
								userSave(owner.getUserList());

								System.out.println(_user.toString()); // 생성된 아이디의 정보를 출력해준다.
							} // case2 block end
							case 3:
								stopFlag = true;
								break;
							}// end of login swtich case문
						} else { // 1,2,3 이외의 다른 숫자를 입력했을 경우 문구만 출력 후 로그인화면 반복.
							System.out.println("다른 숫자를 입력하였습니다.");
						}
						// no = Integer.parseInt(scan.nextLine());에 숫자 이외의 문자열을 입력했을 경우에 나오는 에러를
						// Exception으로 무시하고 진행(다시 반복)한다.
					} catch (Exception e) {
						System.out.println("숫자만 입력해주세요."); // 문구 출력
					}
				} // 로그인 및 회원 가입 로직
				if (stopFlag) {	// case 3를 통해 나왔을 때 사용자메뉴라 가는걸 방지.
					System.out.println("(!)키오스크 종료");
					break;
					}
			}

			// 기본 사용자 메뉴선택
			System.out.println("――――――――――" + owner.getStoreName() + "―――――――――――");
			System.out.println("1.메뉴선택 ");
			System.out.println("2.장바구니확인");
			System.out.println("3.장바구니비우기");
			System.out.println("4.결제하기");
			System.out.println("5.관리자모드");
			System.out.println("6.로그아웃");
			System.out.println("―――――――――――――――――――――――――");
			System.out.print(">>");
			// 메뉴선택실행
			int no = Integer.parseInt(scan.nextLine()); // 로그인 성공시 나오는 숫자 입력
			// 1-7 까지 받아야 함

			switch (no) {
			case 1: // 메뉴선택기능
				choiceMenu(owner, user);

				break;

			case 2: // 장바구니 확인 기능
				if (user.getUserCart().getMenu().size() == 0) { // 담은 메뉴가 0 이라면
					System.out.println("아무것도 안담겨져 있습니다.");
				} else { // 담은 메뉴가 0이 아닐시
					System.out.println("담은 메뉴: ");
					for (Menu menu : user.getUserCart().getMenu()) { // 같은 메뉴를 담으면 또 추력을 안하고 기존에 출력된 메뉴
																		// 줄에 getCount만 늘어나고 getPrice에
																		// 합산시키려고 한다.
						System.out.printf("%s 갯수 = %d | 단품가격 = %d₩ \n", menu.getMenuName(), menu.getCount(),
								menu.getPrice());
					}
					System.out.printf("총 금액 = %d₩\n", user.getUserCart().getTotalPrice());
				}
				break;

			case 3: // 장바구니 비우기 기능
				System.out.print("제거할 메뉴를 적어주세요."); // 눈으로 사용자가 요구내용을 확인하게 하기 위함.
				String _inputMenu = scan.nextLine(); // 제거하려는 메뉴 입력 숫자로 하고싶다.

				Menu imsiMenu = null;

				for (Menu dropmenu : user.getUserCart().getMenu()) {
					if (dropmenu.getMenuName().equals(_inputMenu)) {
						imsiMenu = dropmenu;
						break;
					}
				}
				if (imsiMenu != null) {
					System.out.printf("%s가 삭제되었습니다.\n", _inputMenu);
					// imsiMenu == 빼려는 객체
					int cnt = imsiMenu.getCount(); // cnt == 빼려는 객체의 갯수
					int pri = imsiMenu.getPrice(); // pri == 빼려는 객체의 금액
					int cntMultiPri = cnt * pri; // 빼려는 객체의 갯수 곱하기 빼려는 객체의 금액

					int totalPrice = user.getUserCart().getTotalPrice(); // 장바구니의 총금액
					user.getUserCart().setTotalPrice(totalPrice - cntMultiPri); // 빼려는 객체합계 금액을 총금액에서 빼고 그값으로 설정함
					user.getUserCart().getMenu().remove(imsiMenu);

				} else {
					System.out.printf("%s가 존재하지 않습니다.\n", _inputMenu);
				}

				break;
			case 4: // 결제하기 기능
				if (user.getUserCart().getMenu().size() != 0) {
					boolean payFlag = false;
					while (!payFlag) {
						System.out.print("결제 수단을 선택해주세요. (카드(1) / 현금 (2) / 메인으로 돌아가기(-1) ): ");
						int input = Integer.parseInt(scan.nextLine());
						if (input == 1) {
							System.out.println("카드결제가 완료되었습니다.");
							owner.setDailySales(owner.getDailySales() + user.getUserCart().getTotalPrice()); // 있는 돈 + 장바구니 총액
																												// 합산
							user.getUserCart().clearCart(); // 사용자 카트 내용물 싹다 비우고 총액도 0으로 리셋.
							payFlag = true;
						} else if (input == 2) {
							System.out.println("현금결제가 완료되었습니다.");
							owner.setDailySales(owner.getDailySales() + user.getUserCart().getTotalPrice()); // 있는 돈 + 장바구니 총액
																												// 합산
							user.getUserCart().clearCart(); // 사용자 카트 내용물 싹다 비우고 총액도 0으로 리셋.
							payFlag = true;
						} else if(input == -1) { // 아무것도 안하고 무한반복문 나가기.
							payFlag = true;
						}
						else {
							System.out.println("잘못 선택하셨습니다. 다시 선택하세요.");
						}
					}
					
				} else {
					System.out.println("담아져 있는 메뉴가 없습니다."); // 장바구니에 담겨져 있는게 없으면 문구출력 후 무한반복문을 다시 실행한다.
				}
				break;
			case 5: // 관리자 메뉴 진입

				if (!owner.getOwnerId().equals(user.getUserId())) { // 현재 접속한 userId가 관리자 ownerId와 일치하는지 비교하고 다르면 들어가지
																	// 못하게 한다.
					System.out.println("관계자 외에는 사용할 수 없는 기능입니다.");
					break;
				}

				boolean _stopFlag = false; // 관리자 메뉴 무한반복 관리용
				while (!_stopFlag) { // 관리자 메뉴 무한반복문

					System.out.println("――――――――――" + owner.getOwnerId() + "관리자님―――――――――――");
					System.out.println("1.회원리스트확인 ");
					System.out.println("2.메뉴리스트확인 ");
					System.out.println("3.메뉴추가");
					System.out.println("4.메뉴삭제");
					System.out.println("5.메뉴수정");
					System.out.println("6.하루매출확인");
					System.out.println("7.관리자메뉴 나가기");
					System.out.println("―――――――――――――――――――――――――――――――");
					System.out.print(">>");
					no = Integer.parseInt(scan.nextLine());
					switch (no) {
					case 1: { // 회원리스트확인

						System.out.println("―――――――――――회원 출력――――――――――――");
						for (User _user : owner.getUserList()) {
							System.out.println(_user.toString());
						}
						break;
					}
					case 2: { // 메뉴리스트확인

						System.out.println("―――――――――――메뉴 출력――――――――――――");
						for (Menu menu : owner.getStoreMenu()) {
							System.out.println(menu.toString());
						}
						break;
					}
					case 3: { // 메뉴추가

						System.out.print("추가할 메뉴명 :"); // 상황 파악시키는 문구 (중복되는 메뉴가 있는지)
						String newMenu = scan.nextLine(); // 추가하려는 메뉴 작성
						boolean sameMenu = false;

						for (Menu data : owner.getStoreMenu()) { // 중복되는게 잇는지 하나씩 체크
							if (data.getMenuName().equals(newMenu)) { // 적은거랑 일치하는게 있는지 비교(equals)
								sameMenu = true;
								System.out.printf("%s 메뉴는 이미 있습니다.\n", data.getMenuName());
								break;
							}
						}
						if (sameMenu == false) { // 중복되는 메뉴가 없다

							System.out.print("추가할 메뉴 가격 :"); // 계속 진행하며 상황 파악시키는 문구
							int newMenuPrice = Integer.parseInt(scan.nextLine()); // 남은 가격을 마저 작성한다.

							Menu menu = new Menu(newMenu, newMenuPrice, 1); // 작성한걸 menu에 넣는다.
							owner.getStoreMenu().add(menu); // add로 리스트에 추가한다.
							menuSave(owner.getStoreMenu());
							System.out.println("메뉴가 추가되었습니다."); // 추가됐다고 알려주는 문구
							break; // 나간다.
						}
						break; // 이 케이스를 완전히 나간다.
					}
					case 4: { // 메뉴삭제

						System.out.print("삭제할 메뉴명 :");
						String deleteMenu = scan.nextLine(); // 삭제할 메뉴 타이핑

						Menu menu = null;
						boolean deleteFlag = false;
						for (Menu data : owner.getStoreMenu()) { // 임시 변수 data를 선언해서 owner 어레이리스트에 보관된 메뉴를 빼온다.
							if (data.getMenuName().equals(deleteMenu)) { // 지울 메뉴가 리스트에 있다면 {}안의 코드를 실행한다.
								deleteFlag = true;
								menu = data; // 비교하려고 꺼낸 메뉴(data)를 menu에 넣는다. (빼온 메뉴를 for문에서 끝내서 사라지지 않게하기 위해)
								break;
							}
						}
						if (deleteFlag == false) { // 지울 메뉴가 리스트에 없는 경우 {}안의 코드를 실행한다.
							System.out.println("해당 메뉴는 존재하지 않아 지울 수 없습니다.");
							break;
						}
						owner.getStoreMenu().remove(menu); // menu에 들어가있는 메뉴와 같은 이름을 가진 관리자의 메뉴리스트에서 일치하는 메뉴를
															// 제거(remove)한다.
						menuSave(owner.getStoreMenu()); // 제거한 뒤의 메뉴리스트를 저장한다.
						System.out.println("해당 메뉴가 삭제되었습니다."); // 문구 출력
						break;
					}
					case 5: { // 메뉴수정

						System.out.print("수정할 메뉴 입력: "); // 상황 파악시키는 문구
						String checkMenu = scan.nextLine(); // 일치하는게 있는지 작성한다.
						boolean checkFlag = false; // 일단 넣긴하는데 무한반복걸고 탈출도 아닌데 왜 넣는지 모르겠음
						Menu menu = null;
						for (Menu data : owner.getStoreMenu()) { // data에 owner에 메뉴들을 하나씩 넣어라? 맞나 모르겟음. 이하나씩이란 개념도
							if (data.getMenuName().equals(checkMenu)) { // 하나씩 넣는데(?) 그러다가 내가 입력한(checkMenu)와 하나씩
																		// 빼오던(data)가 일치하면(equals) 수정허가.
								checkFlag = true; // fixFlag true로 하고
								menu = data; // menu에 일치하는 data(내가 타이핑한(checkMenu) 데이터가 일치해서 들어가있음)를 넣는다. data의 역할은 여기서
												// 끝.
								break; // 나가기
							}
						}
						if (checkFlag == false) { // 아니면 걍 나간다.
							System.out.println("수정하려는 메뉴와 일치하는 이름이 없습니다."); // 상황 파악시키는 문구
							break; // 나가기
						}
						System.out.println("메뉴를 찾았습니다, 수정하세요."); // 상황 파악시키는 문구
						System.out.printf("%s > ", menu.getMenuName()); // 상황 파악시키는 문구
						String fixMenu = scan.nextLine(); // 수정할 메뉴 입력
						menu.setMenuName(fixMenu); // 기존메뉴(menu로 뽑아져 온 상태)를 방금 작성한 메뉴로 수정
						System.out.printf("%d > ", menu.getPrice()); // 상황 파악시키는 문구
						int fixPrice = Integer.parseInt(scan.nextLine()); // 수정할 가격 입력
						menu.setPrice(fixPrice); // 기존가격(menu로 뽑아져 온 상태)를 방금 작성한 가격로 수정
						menuSave(owner.getStoreMenu());
						System.out.println("메뉴가 수정되었습니다.");

						break;
					}
					case 6: { // 하루매출확인
						System.out.println("금일 매출 = " + owner.getDailySales() + "₩");
						break;
					}
					case 7: {
						_stopFlag = true;
						break;
					}
					}// end of switch (관리자 메뉴)
				}
				break;
			case 6:// 로그아웃
				System.out.print("정말 로그아웃 하시겠습니까? (y/n) :");
				String logoutInput = scan.nextLine();
				if (logoutInput.equals("y") || logoutInput.equals("Y")) {
					loginFlag = false;
					user = null;
					System.out.println("로그아웃 되었습니다.");
				} else if (logoutInput.equals("N") || logoutInput.equals("n")) {

				}
				break;
			case 7:// 사용자모드종료
				_stopFlag = true;
				System.out.println("시스템 종료");
				break;
			}
		} // end of while
	} // end of main

	public static User userLogin(ArrayList<User> userList) { // 유저 리스트 받아서 로그인 처리하고, 성공한 User 객체를 리턴하는 함수
		boolean islogin = false; // 로그인 성공 여부 저장용 플래그
		Scanner scan = new Scanner(System.in);
		User user = null; // 로그인 성공한 유저 객체 저장용

		while (!islogin) { // 로그인 성공할 때까지 무한 반복
			System.out.print("ID : ");
			String loginID = scan.nextLine(); // 아이디 입력받음
			System.out.print("PW : ");
			String loginPW = scan.nextLine(); // 비밀번호 입력받음

			for (User user1 : userList) { // 유저 리스트 전체를 하나씩 확인
				if (user1.getUserId().equals(loginID)) {
					user = user1; // 사용자가 입력한 사번에 해당하는 user를 임시로 설정함
				}
			} // end of for

			if (user != null) {
				// 사용자가 입력한 아이디가 있는 경우
				if (user.getUserPassword().equals(loginPW)) {
					// 그 사용자의 암호와 입력한 암호가 일히하면
					// while 종료
					System.out.println("로그인 성공");
					System.out.printf("[%s]님 환영합니다.\n", user.getUserName());
					islogin = true; // 플래그 true로 바꿔서 while 종료 조건 만족

					break;
				} else {
					// 그 사용자의 암호와 입력한 암호가 일히하지 않으면
					user = null;
					System.out.println("다시 입력해주세요.");
				}
			} else {
				// 사용자가 입력한 아이디가 없는 경우
				System.out.println("다시 입력해주세요.");
			}
		} // end of while

		return user; // 로그인 성공한 유저 객체 리턴 (main에서 받아서 사용)
	}

	// 메뉴선택 후 장바구니 담는 기능 함수
	@SuppressWarnings("unchecked")
	public static void choiceMenu(Owner owner, User currentUser) {
		Scanner scan = new Scanner(System.in);
		System.out.println("―――――――――――――메뉴판――――――――――――――");
		for (int i = 0; i < owner.getStoreMenu().size(); i++) {
			System.out.println((i + 1) + "번 " + owner.getStoreMenu().get(i).toString()); // 음식점 메뉴 번호 붙여서 출력
		}
		System.out.println("―――――――――――――――――――――――――――――――");
		int no = 0;
		do {
			System.out.print("장바구니에 담으실 번호를 입력해주세요: "); // 사용자가 번호를 입력해 메뉴 선택
			String noStr = scan.nextLine();
			boolean isInputCheck = true;
			try {
				no = Integer.parseInt(noStr); // 선택한 메뉴번호를 저장
			} catch (Exception e) { // 숫자로 변환 하다가 숫자가 아니라서 오류가 났을 경우
				isInputCheck = false; // isInputCheck 아무것도 입력이 안되었다는 의미
			}
			if (isInputCheck == true && (1 <= no && no <= owner.getStoreMenu().size())) { // 메뉴 사이즈보다 숫자가 넘어가면 다시요청
				break;
			}
			System.out.printf("번호선택오류발생 다시입력요청(1~%d)\n", owner.getStoreMenu().size());
		} while (true);
		Menu menu = owner.getStoreMenu().get(no - 1); // 인덱스가 0부터 시작이니까 1을 빼주고 선택한 메뉴를 메뉴 객체 생성
		Menu tmpMenu = new Menu(menu.getMenuName(), menu.getPrice());
		// 장바구니
		ArrayList<Menu> tmpMenuList = (ArrayList<Menu>) currentUser.getUserCart().getMenu().clone(); // 주소만 다르게 복제하는 역할
																										// clone(깊은 복사)
		// tmpMenuList = currentUser.getUserCart().getMenu(); // 값과 주소 모두 똑같이 복제됨 (얕은
		// 복사)

		System.out.println(menu);

		boolean findFlag = false; // 현재 선택한 메뉴가 사용자 장바구니에 있는지 없는지 그 플레그

		for (Menu menu1 : currentUser.getUserCart().getMenu()) { // menu1 == 장바구니의 n번째 물건, menu == 시용자가 선택한 물건
			// 장바구니가 비어있지 얺으면 도는데,
			if (menu1.getMenuName().equals(menu.getMenuName()) && menu1.getPrice() == menu.getPrice()) {
				// 사용자가 선택한 매뉴와 장바구니에 있는 매뉴의 이름과 가격이 같을 때 아래 실행
				int newCnt = menu1.getCount() + 1; // 2 + 1
				tmpMenu.setCount(newCnt); // 2 + 1
				tmpMenuList.remove(menu1);
				tmpMenuList.add(tmpMenu);
				findFlag = true;
			}
		}
		if (findFlag) {
			currentUser.getUserCart().setMenu(tmpMenuList);
		} else {
			currentUser.getUserCart().addMenu(menu); // 현재 사용자 장바구니에 메뉴 추가
		}
		currentUser.getUserCart().addTotalPrice(menu.getPrice()); // 현재 사용자 장바구니에 메뉴 가격을 총액에 더함
	}

	// 사용자 정보 파일을 ArrayList에 업로드
	public static ArrayList<User> userFileUpLoad(ArrayList<User> userList) {
		// 파일에서 가져오고 보조스트림을 정의한다.(Scanner)
		FileInputStream fi;
		try {
			fi = new FileInputStream("E:/kiosk_project/storekiosksystem/src/res/userList.txt");
			Scanner scan = new Scanner(fi);
			// 첫라인 없앤다.
			if (scan.hasNextLine()) {
				StoreKioskSystemMain.userTitle = scan.nextLine();
			}
			// 반복문을 통해서 모든 한라인씩을 가져와서 => String tokens
			// => 형변환시켜서 =>StudentData 객체 => ArrayList 입력
			while (true) {
				if (!scan.hasNextLine()) {
					break;
				}
				String data = scan.nextLine(); // 입력값을 문자열 data에 저장한다.
				String[] tokens = data.split(","); // ,가 찍힐때마다 분리한다
				String userId = tokens[0];
				String userPassword = tokens[1];
				String userName = tokens[2];
				String userPhoneNum = tokens[3];
				User user = new User(userId, userPassword, userName, userPhoneNum);
				userList.add(user); // user에 담겨져있는 4개의 값을 uuserList안에 add로 추가한 객체에 추가한다.
			} // end of while
				// System.out.println("파일에서 ArrayList에 로드되었습니다.");
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	// 메뉴 정보 파일을 ArrayList에 업로드
	public static ArrayList<Menu> menuFileUpLoad(ArrayList<Menu> menuList) {
		// 파일에서 가져오고 보조스트림을 정의한다.(Scanner)
		FileInputStream fi;
		try {
			// fi = new FileInputStream("res/dog.txt");
			fi = new FileInputStream("E:/kiosk_project/storekiosksystem/src/res/menuList.txt");
			Scanner scan = new Scanner(fi);
			// 첫라인 없앤다.
			if (scan.hasNextLine()) {
				StoreKioskSystemMain.menuTitle = scan.nextLine();
			}
			// 반복문을 통해서 모든 한라인씩을 가져와서 => String tokens
			// => 형변환시켜서 =>StudentData 객체 => ArrayList 입력
			while (true) {
				if (!scan.hasNextLine()) {
					break;
				}
				String data = scan.nextLine();
				String[] tokens = data.split(",");
				String menuName = tokens[0];
				int price = Integer.parseInt(tokens[1]);

				Menu menu = new Menu(menuName, price);
				menuList.add(menu);
			} // end of while
				// System.out.println("파일에서 ArrayList에 로드되었습니다.");
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	// 메뉴정보리스트를 파일에 저장
	public static void menuSave(ArrayList<Menu> menuList) {
		{
			// 파일에서 가져오고 보조스트림을 정의한다.(Scanner)
			FileOutputStream fo = null;
			PrintStream out = null;
			try {
				fo = new FileOutputStream("E:/kiosk_project/storekiosksystem/src/res/menuList.txt");
				out = new PrintStream(fo);
				// 파일 메뉴를 추가한다.
				out.printf("%s", StoreKioskSystemMain.menuTitle);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ArrayList 내용을 한개씩 가져와서 파일에 저장한다.
			for (int i = 0; i < menuList.size(); i++) {
				Menu menu = menuList.get(i);
				out.printf("\n%s,%d", menu.getMenuName(), menu.getPrice()); // 앞에 \n 필수!!
			}
			out.close();
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	// 사용자정보리스트를 파일에 저장
	public static ArrayList<User> userSave(ArrayList<User> userList) {
		{
			// 파일에서 가져오고 보조스트림을 정의한다.(Scanner)
			FileOutputStream fo = null;
			PrintStream out = null;
			try {
				fo = new FileOutputStream("E:/kiosk_project/storekiosksystem/src/res/userList.txt");
				out = new PrintStream(fo);
				// 파일 메뉴를 추가한다.
				out.printf("%s", StoreKioskSystemMain.userTitle);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ArrayList 내용을 한개씩 가져와서 파일에 저장한다.
			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);
				out.printf("\n%s,%s,%s,%s", user.getUserId(), user.getUserPassword(), user.getUserName(),
						user.getUserPhoneNum()); // 앞에 \n 필수!!
			}
			out.close();
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		return userList;
	}
} // end of class