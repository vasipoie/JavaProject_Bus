package print;

import java.util.List;

import oracle.net.aso.n;
import vo.BusVo;
import vo.TicketVo;

public class Print {
	
		public void printVar() {
			System.out.println("-----------------------------------------------");
		}
		
		//줄맞춤용도
		public void printLn(int num) {
			for(int i=0; i<num; i++)
				System.out.println();
		}

		public void printHome() {
			printVar();
			System.out.println("1. 버스 예매");
			System.out.println("2. 예매내역 조회 및 변경");
			System.out.println("3. 예매취소");
			printLn(4);
			printVar();
		}

		public void printReserveList(List<BusVo> busList) {
			printVar();
			System.out.println("No\t출발지\t도착지\t출발시간\t\t\t등급\t버스회사\t가격\t좌석수");
			for(BusVo bus: busList) {
			System.out.printf("%d\t %s\t %s\t %s\t %s\t %s\t %d\t %d\n",
					bus.getNo(), bus.getSt_bus(), bus.getEd_bus(), bus.getDe_time(),
					bus.getBus_cls(), bus.getCompany(), bus.getPrice(), bus.getSeat());
			}
			printLn(5);
			printVar();
			
		}
		
		public void printReserveMenu() {
			System.out.println("1. 예매");
			System.out.println("2. 출발지/도착지 변경");
			System.out.println("3. 홈");
			printVar();
		}
	
	
		public void printTicketList(List<TicketVo>ticketRevList) {
			printVar();
			System.out.println("예매번호\t좌석번호\t");
			for(TicketVo revTicket : ticketRevList ) {
				System.out.printf("%s\t %d\n"
						, revTicket.getTicket(), revTicket.getSit_num());
			}
			printLn(5);
			printVar();
			System.out.println("1. 출발지/도착지 변경");
			System.out.println("2. 홈");
		}
		
		public void printRevChange() {
			printVar();
			System.out.println("1. 출발지 변경");
			System.out.println("2. 도착지 변경");
			printVar();
			
		}
	
}
