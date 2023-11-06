package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import oracle.net.ns.SessionAtts;
import print.Print;
import service.BusService;
import service.TicketService;
import util.JDBCUtil;
import util.ScanUtil;
import util.View;
import vo.BusVo;
import vo.TicketVo;

public class BusCon extends Print{
	static public Map<String, Object> sessionStorage = new HashMap<>();

	BusService busService = BusService.getInstance();
	TicketService ticketService = TicketService.getInstance();
	static JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public static void main(String[] args) {
		/*
		 * 버스예매조회
		 * 1. 전체 테이블 조회	READ -2,4
		 * 2. 상세 페이지 조회	READ
		 * 3. 게시판 글 신규 등록	CREATE
		 * 4. 게시판 글 수정	UPDATE
		 * 5. 게시판 글 삭제	DELETE
		 * 
		 * 
		 * 버스회사	출발지	도착지	시간		등급		가격		남은좌석
		 * 			대전		서울								6
		 * 
		 */
		
		new BusCon().start();
	}

	 void start() {
		View view = View.HOME;
		while(true){
			switch(view) {
			case HOME:
				view = home();
				break;
			case RESERVE:	//1.버스 예매
				view = reserve();
				break;
			case CHECK:		//2.예매내역 조회 및 변경
				view = ticketList();
				break;
			case CANCLE:	//3.예매취소
				view = cancle();
				break;
			case RESERVE_LIST:
				view = reserveList();
				break;
			case TICKET_CREATE:
				view = ticketCreate();
				break;
			case TICKET_LIST:
				view = ticketList();
				break;
			case REVCHANGE:	//출발지/도착지 변경
				view = revChange();
				break;
				
			}
		}
	}

	 private View revChange() {
		printRevChange();
		return null;
	}

	private View cancle() {
		// TODO Auto-generated method stub
		return null;
	}

	private View ticketList() {
		String ticketNo = (String) sessionStorage.get("ticketNo");
		List<TicketVo> ticketRevList = ticketService.ticketRevList(ticketNo);
		printTicketList(ticketRevList);
		int select  = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.REVCHANGE;
		case 2:
			return View.HOME;
		default:
			return View.TICKET_LIST;
		}
	}

	private View ticketCreate() {
		 //세션에 저장한 버스정보 꺼내서 티켓만들기
		BusVo busSelectInfo = (BusVo) sessionStorage.get("bus");
		String ticket = ticketService.ticketCreate(busSelectInfo);
		sessionStorage.put("ticketNo", ticket);
		return View.TICKET_LIST;
	}

	private View reserveList() {
		printReserveMenu();
		return null;
	}

	private View reserve() {
		 String st = ScanUtil.nextLine("출발지를 입력하세요\s");
		 String ed = ScanUtil.nextLine("도착지를 입력하세요\s");
		 List<BusVo> busList = busService.stedBus(st, ed);
		 printReserveList(busList);
		 printReserveMenu();
		 while(true) {
		 int select  = ScanUtil.nextInt("메뉴를 선택하세요\s");
		 	switch(select) {
		 	case 1:
		 		//티켓 발급 - insert
		 		int busSelectNo = ScanUtil.nextInt("예매할 버스 번호를 선택하세요\s");
		 		//for문으로 리스트 전체 비교
		 		//if문으로 번호같은지
		 		BusVo selectList = null;
		 		for(BusVo list : busList) {
		 			if(list.getNo() == busSelectNo) {
		 				selectList = list;
		 				sessionStorage.put("bus", selectList);	//클래스를 다 담아서 보냄
		 				break;
		 			}
		 		}
//		 		sessionStorage.put("busNo", busNo);
		 		return View.TICKET_CREATE;
		 	case 2:
		 		return View.RESERVE_LIST;
		 	case 3:
		 		return View.HOME;
		 	default :
		 		System.out.println("다시 입력하세요");
		 	}
		 }
	 }
	
	 

//	private View bookDel() {
//		 List<BookVo> list = bookService.bookList();
//		 printList(list);
//		 printDelete();
//		 int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
//			switch (select) {
//			case 1:
//				int delNo = ScanUtil.nextInt("삭제할 번호를 선택하세요\s");
//				bookService.bookDel(delNo);
//				//테이블에 delyn만들고 y로 업데이트, 쿼리는 is null
//				return View.HOME;	//삭제
//			case 2:
//				int pageNo = (int) sessionStorage.get("pageNo")+1;
//				sessionStorage.put("pageNo", pageNo);
//				return View.HOME;
//			case 3:
//				pageNo = (int) sessionStorage.get("pageNo")-1;
//				sessionStorage.put("pageNo", pageNo);
//				return View.HOME;
//			case 4:
//				return View.HOME;
//			default:
//				return View.HOME;
//			}
//	}
//
//	private View bookDetail() {
//		//session은 맵. 메소드끼리 옮기기 어려운 데이터는 sessionStorage에 넣는다
//		 int bookNo = (int) sessionStorage.get("bookNo");
//		 BookVo book = bookService.bookDetail(bookNo);
//		 printDetail(book);
//		 int select = ScanUtil.nextInt("메뉴를 선택하세요"); 
//		 switch (select) {
//			case 1:
//				return View.HOME;
//			default:
//				return View.HOME;
//		 }
//	}
//
//	private View bookList() {
//		 List<BookVo> list = bookService.bookList();
//		 printList(list);
//		 printListMenu();
//		 int select = ScanUtil.nextInt("메뉴를 선택하세요"); 
//		 switch (select) {
//			case 1:
//				int bookNo = ScanUtil.nextInt("책 번호를 선택하세요"); 
//				//검증 필요. 번호 잘못입력했다 추가
//				sessionStorage.put("bookNo", bookNo);
//				return View.HOME;
//			case 2:
//				return View.HOME;
//			default:
//				return View.HOME;
//			}
//	}

//	private View member() {
//		 printMember();
//		 int select = ScanUtil.nextInt("");
//			switch (select) {
//			case 1:
//				return View.HOME;
//			case 2:
//				return View.HOME;
//			default:
//				return View.MEMBER;
//			}
//	}


	View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.RESERVE;
		case 2:
			return View.CHECK;
		case 3:
			return View.CANCLE;
		default:
			return View.HOME;
		}
	}
	
}
