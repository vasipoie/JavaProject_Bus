package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.TicketVo;

public class TicketDao {
	private static TicketDao singleTon = null;
	
	private TicketDao(){};
	
	public static TicketDao getInstance() {
		if(singleTon == null) {
			singleTon = new TicketDao();
		}
		return singleTon;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public void ticketCreate(List<Object> param) {
		String sql = "INSERT INTO TICKET\r\n" + 
					"VALUES(?,?,?)";
		jdbc.update(sql, param);
	}
	
//	public TicketVo ticketCreate(List<Object> param) {
//	String sql = "INSERT INTO TICKET\r\n" + 
//				"VALUES(?,?,?)";
//	Map<String, Object> map = jdbc.selectOne(sql, param);
//	return ConvertUtils.convertToVo(map, TicketVo.class);
//}

	public List<TicketVo> ticketRevList(String ticketNo) {
		String sql = "select ticket,sit_num \r\n" + 
					"from ticket\r\n" + 
					"where ticket ='"+ticketNo+"'";
		List<Map<String, Object>> list = jdbc.selectList(sql);	//왕복으로 예매할 수도 있으니까
		return ConvertUtils.convertToList(list, TicketVo.class);
	}


	
	
}
