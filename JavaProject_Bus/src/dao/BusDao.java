package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.BusVo;

public class BusDao {
	 private static BusDao singleTon = null;
	
	private BusDao(){};
	
	public static BusDao getInstance() {
		if(singleTon == null) {
			singleTon = new BusDao();
		}
		return singleTon;
	}

	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	public List<BusVo> stedBus(String st, String ed) {
		String sql = "select NO, ST_BUS, ED_BUS, \r\n" + 
				"to_char(DE_TIME,'YYYY-MM-DD HH24:MI:SS') DE_TIME,\r\n" + 
				"BUS_CLS, COMPANY, PRICE, SEAT\r\n" + 
				"from bus \r\n" + 
				"where st_bus =? and ed_bus =?\r\n" + 
				"and DE_TIME between sysdate and trunc(sysdate)+1\r\n" + 
				"and NOT seat = 0";
		//파라미터가 있을 땐 list 만들어서 add
		List<Object>param = new ArrayList();
		param.add(st);
		param.add(ed);
//		System.out.println(param.get(0));
//		System.out.println(param.get(1));
		List<Map<String, Object>> list = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(list, BusVo.class);
	}

	public void updateSitNo(List<Object> param2) {
		String sql = "UPDATE BUS\r\n" + 
					"SET SEAT = ?\r\n" + 
					"WHERE NO = ?";
		jdbc.update(sql, param2);
	}
	
	
	
	
	
}

