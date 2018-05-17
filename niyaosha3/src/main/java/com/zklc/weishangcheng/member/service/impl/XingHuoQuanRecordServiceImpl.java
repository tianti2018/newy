package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.XingHuoQuanRecord;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;

@Service
public class XingHuoQuanRecordServiceImpl extends BaseServiceImp<XingHuoQuanRecord, Integer>
		implements XingHuoQuanRecordService {

	@Override
	public Integer findAllMoneyByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "SELECT SUM(xinghuoquan) FROM xinghuoquan_record WHERE userId = "+userId+" AND `status` = "+status;
		List list = findBySql(sql, null);
		if(list.size()>0){
			System.out.println(list.get(0));
			Object object = list.get(0);
			if(object==null){
				return 0;
			}else {
				return Integer.parseInt(list.get(0).toString());
				
			}
		}
		return 0;
	}
	@Override
	public List findXingHuoQuanList(Integer userId, String date1, String date2,Integer pageNum) {
		if(pageNum==null||pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*5;
		}
		String sql="select * from xinghuoquan_record where userId = "+userId+" and status <> 0 ";
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
        List<XingHuoQuanRecord> xinghuoquan=super.findBySql(sql, null);
		return xinghuoquan;
	}
	
}
