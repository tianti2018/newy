package com.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zklc.weixin.util.JdbcUtil;


public class RemoveRepeatData {

	public static void main(String[] args) {

		Connection conn = JdbcUtil.getConnection();
		
		String sqlForRepeatOpenId = "SELECT wxOpenid from users GROUP BY wxOpenid HAVING COUNT(wxOpenid)>1";
		try {
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(sqlForRepeatOpenId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String openId = rs.getString("wxOpenid");
				String sqlForUsers = "select * from users where wxOpenid = ?";
				pst = conn.prepareStatement(sqlForUsers);
				pst.setString(1, openId);
				ResultSet rs1 = pst.executeQuery();
				int referId = 0;
				String qrcode = null;
				String ticket = null;
				List<Integer> userIds = new ArrayList<Integer>();
				while(rs1.next()){
					userIds.add(rs1.getInt("userId"));
					String qr = rs1.getString("qrCode");
					String ti = rs1.getString("ticket");
					Integer rId = rs1.getInt("referrerId");
					if(qr != null && !"".equals(qr)){
						qrcode = qr;
					}
					if(ti != null && !"".equals(ti)){
						ticket = ti;
					}
					if(null != rId && !"".equals(rId)&&rId !=0){
						referId = rId;
					}
				}
				String sqlUpdateUser = "update users set referrerId=? , ticket=?,qrCode=? where userId = ?";
				String uIds = "";
				pst = conn.prepareStatement(sqlUpdateUser);
				for(Integer id:userIds){
					if(referId != 0 ){
						pst.setInt(1, referId);
					}else {
						pst.setInt(1, (Integer) null);
					}
					if(ticket != null){
						pst.setString(2, ticket);
					}else {
						pst.setString(2, null);
					}
					if(qrcode != null){
						pst.setString(3, qrcode);
					}else {
						pst.setString(3, null);
					}
					pst.setInt(4, id);
					pst.addBatch();
					uIds +=id+",";
				}
				pst.executeBatch();
				List<Integer> userIdlist = new ArrayList<Integer>();
				if(uIds !=null &&uIds.endsWith(",")&&uIds.length()>1){
					uIds = uIds.substring(0,uIds.length()-1);
					String sqlOrders = "select * from ordery where userId in ("+uIds+")";
					pst = conn.prepareStatement(sqlOrders);
					ResultSet rs3 = pst.executeQuery();
					String sqldelete = "delete from ordery where ordersId=?";
					PreparedStatement ps1 = conn.prepareStatement(sqldelete);
					while(rs3.next()){
						String order_stat = rs3.getString("order_status");
						if(order_stat!=null&&!order_stat.isEmpty()){
							if(!order_stat.equals("0")){
								userIdlist.add(rs3.getInt("userId"));
							}else{
								ps1.setInt(1, rs3.getInt("ordersId"));
								ps1.addBatch();
							}
						}
					}
					ps1.executeBatch();
				}
				if(userIdlist.size()>0){
					userIds.removeAll(userIdlist);
				}else
					userIds.remove(0);
				String sqldeleteUser = "delete from users where userId =?";
				pst = conn.prepareStatement(sqldeleteUser);
				for(Integer id:userIds){
					pst.setInt(1, id);
					System.out.println(id);
					pst.addBatch();
				}
				pst.executeBatch();
			}
			conn.commit();
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
