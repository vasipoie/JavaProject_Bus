package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.BusDao;
import dao.TicketDao;
import vo.BusVo;
import vo.TicketVo;

public class TicketService {

	private static TicketService singleTon = null;
	
	private TicketService(){};
	
	public static TicketService getInstance() {
		if(singleTon == null) {
			singleTon = new TicketService();
		}
		return singleTon;
	}
	
	TicketDao ticketDao = TicketDao.getInstance();
	BusDao busDao = BusDao.getInstance();

	public String ticketCreate(BusVo bus) {
		//좌석번호 생성, 예매번호 만들기
		int no = bus.getNo();
		int sit_no = 7-bus.getSeat();
//		랜덤으로 문자열 발급해주는 UUID
//		String ticket = UUID.randomUUID().toString();
		String ticket = "TI-"+no+"-"+sit_no;
		List<Object> param = new ArrayList();
		param.add(ticket);
		param.add(sit_no);
		param.add(no);
		ticketDao.ticketCreate(param);
		//버스 좌석수 줄이기
		List<Object> param2 = new ArrayList<Object>();
		param2.add(bus.getSeat()-1);
		param2.add(no);
		busDao.updateSitNo(param2);
		return ticket;
	}

	public List<TicketVo> ticketRevList(String ticketNo) {
		return ticketDao.ticketRevList(ticketNo);
	}

	





}

