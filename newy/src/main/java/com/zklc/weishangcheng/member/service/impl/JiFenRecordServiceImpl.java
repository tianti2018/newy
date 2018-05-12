package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.service.JiFenRecordService;

@Service
public class JiFenRecordServiceImpl extends BaseServiceImp<JiFenRecord, Integer> implements
		JiFenRecordService {


	@Override
	public List findJiFenList(Integer userId,Integer status, String date1, String date2,Integer pageNum) {
		if(pageNum==null||pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*5;
		}
		String sql="select * from jifen_record where userId = "+userId+" and status <> 0";
		if(status!=null&&!status.equals(11)){
			if(status == 69){
				sql+=" and status in(1,2)";
			}else{
				sql+=" and status="+status+"";
			}
		
		}
        if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
          sql+=" and createDate between '"+date1+"' and '"+date2+"'";
        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
        	sql+=" and createDate<='"+date2+"'";
        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
        	sql+=" and createDate>='"+date1+"'";
        }
                    
        sql+=" order by createDate desc";
        sql+=" limit "+pageNum+" ,"+5;
        System.out.println(sql);
		//List list=super.findBySql(sql, null);
        List<JiFenRecord> jifen=super.findBySql(sql, null);
		return jifen;
	}

	@Override
	public Integer findbyUserIdAndStatus(Integer userId, int status) {
		String sql = "SELECT SUM(jifen) FROM jifen_record WHERE userId = "+userId+" AND status ="+status;
		List list = findBySql(sql, null);
		if(list.size()!=0){
			Object object = list.get(0);
			if(object!=null){
				return Integer.parseInt(list.get(0).toString());
			}
		}
		return 0;
	}

	@Override
	public List countUserJifen(Integer userId,Integer type) {
		List list = new ArrayList();
		if(userId != null ){
			StringBuilder sb = new StringBuilder();
			//出于性能考虑，取消不行也要结果的查询语句
			if(type ==1){//查询总积分，
				//
				sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =1 and type <> 5");//总积分
				sb.append(" UNION ALL ");
				sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =2 and type <> 5");//消耗积分
				//sb.append(" UNION ALL ");
				//可用积分(查询type <> 5的 或者 type = null 的数据 或者type=5 并且 isenable=0的数据) - 消耗积分
				//sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =1 ");//and (type <> 5 or type is NULL)  or (type = 5 and isEnable =0)
				//sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =3");//不可用积分
				list = this.findBySql(sb.toString(), null);
				if(list != null)
					return list;
				
			}else{//查询可用积分 - 消耗积分
				  
				//保证查询的结果不为空
				 sb.append("select if(total>0,total,if(total<0,total,0)) from(");
				 //用户所有可用积分 - 已使用积分=真正可用积分
				 sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0))-(select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) yi from jifen_record where userId="+userId+" and status =2  and type <>5) total ");
				  // 查询type <> 5的 或者 type = null 的数据 或者type=5 并且 isenable=0的数据
				 sb.append("from jifen_record where userId="+userId+" and status=1  and type <>5)  f");//and (type <> 5 or type is NULL)  or (type = 5 and isEnable =0)
				 
				
				 
				 list = this.findBySql(sb.toString(), null);
					if(list != null)
						return list;
			}
		}
		return list;
	}

	@Override
	public List countNewUserJifen(Integer userId, Integer type) {
		List list = new ArrayList();
		if(userId != null ){
			StringBuilder sb = new StringBuilder();
			//出于性能考虑，取消不行也要结果的查询语句
			if(type ==1){//查询总积分，
				//
				sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =1 and type = 5");//总积分
				sb.append(" UNION ALL ");
				sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =2 and type = 5");//消耗积分
				//sb.append(" UNION ALL ");
				//可用积分(查询type <> 5的 或者 type = null 的数据 或者type=5 并且 isenable=0的数据) - 消耗积分
				//sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =1 ");//and (type <> 5 or type is NULL)  or (type = 5 and isEnable =0)
				//sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) from jifen_record where userId="+userId+" and status =3");//不可用积分
				list = this.findBySql(sb.toString(), null);
				if(list != null)
					return list;
				
			}else{//查询可用积分 - 消耗积分
				  
				//保证查询的结果不为空
				 sb.append("select if(total>0,total,if(total<0,total,0)) from(");
				 //用户所有可用积分 - 已使用积分=真正可用积分
				 sb.append("select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0))-(select if(SUM(jifen)>0,SUM(jifen),if(SUM(jifen)<0,SUM(jifen),0)) yi from jifen_record where userId="+userId+" and status =2  and type =5) total ");
				  // 查询type <> 5的 或者 type = null 的数据 或者type=5 并且 isenable=0的数据
				 sb.append("from jifen_record where userId="+userId+" and status=1 and type =5)  f");//and (type <> 5 or type is NULL)  or (type = 5 and isEnable =0)
				 
				 list = this.findBySql(sb.toString(), null);
					if(list != null)
						return list;
			}
		}
		return list;
	}
	
}
