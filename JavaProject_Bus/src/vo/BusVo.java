package vo;

import lombok.Data;

@Data
public class BusVo {
	
	 private int no;
	 private String st_bus;
	 private String ed_bus;
	 private String de_time;
	 private String bus_cls;
	 private String company;
	 private int price;
	 private int seat;
	
	  /*
	   * select * from 으로 데이터 가져오고
	   * 데이터 타입을 regdate2로 바꿔주면
	   * date 쓸 수 있음
	   * 
	   * Date regdate;
	   * String regdate2;
	   * 
	   * public Date getRegdate(){
	   * 	SimpleDate
	   * 	return regdate;
	   * }
	   * 
	   * public void setRegdate(Date regdate){
	   * 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   * 	this.regdate2 = sdf.format(regdate);
	   * 	this.regdate = regdate;
	   */
	
}
