package service;

import java.util.List;

import dao.BusDao;
import util.JDBCUtil;
import vo.BusVo;

public class BusService {
	 private static BusService singleTon = null;
	
	private BusService(){};
	
	public static BusService getInstance() {
		if(singleTon == null) {
			singleTon = new BusService();
		}
		return singleTon;
	}

	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	BusDao busDao = BusDao.getInstance();


	public List<BusVo> stedBus(String st, String ed) {
		return busDao.stedBus(st, ed); //서비스에서 컨트롤로 보냄
	}

	
	
	
	
	
	
	
}
