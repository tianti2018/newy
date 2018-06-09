package com.zklc.weishangcheng.member.action;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.DianpuForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductsForDianpu;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.ProductVo;
import com.zklc.weishangcheng.member.service.CommentService;
import com.zklc.weishangcheng.member.service.DianpuForUserService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.ProductsForDianpuService;
import com.zklc.weishangcheng.member.service.ProductsService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.sign;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/dianpu")
@Action(value = "dianpuAction")
@Results({
		@Result(name = "gotoDianpu", location = "/WEB-INF/jsp/dianpu/products_dianpu.jsp"),
		@Result(name = "buy", location = "/buy.jsp"),
		@Result(name = "miaosha", location = "/miaosha.jsp"),
		@Result(name = "initOpenUser", location = "/open.jsp"),
		@Result(name = "initMessage", location = "/initMessage.jsp"),
		@Result(name = "gotoJifenShop", location = "/WEB-INF/jsp/jifen/jifenIndex.jsp"),
		@Result(name = "gotoHuiyuanShop", location = "/WEB-INF/jsp/huiyuan/huiyuanIndex.jsp"),
		@Result(name = "gotoMyDianpu", location = "/WEB-INF/jsp/dianpu/dianpuInfo.jsp"),
		@Result(name = "dianPuProduct", location = "/WEB-INF/jsp/dianpu/product_dianpu.jsp"),
		@Result(name = "gotoPersonalCenter", location = "/WEB-INF/jsp/personalCenter.jsp"),
		
		@Result(name = "fahongbao", type = "redirect", location = "/user/userAction!phoneFamily.action"),		
		@Result(name = "ajaxResult", type = "json", params = { "message",
				"message" }),
		@Result(name = "phoneFamily", location = "/WEB-INF/jsp/phone_family.jsp"),
		@Result(name = "viewHongbao", location = "/WEB-INF/jsp/phone_family_hongbao.jsp"),
		@Result(name = "viewPeoPage", location = "/WEB-INF/jsp/phone_family_vip.jsp"),
		@Result(name = "viewPeoPage2", location = "/WEB-INF/jsp/phone_family_vip.jsp"),
		@Result(name = "jamp", location = "/jamp.jsp"),
		@Result(name = "jampshopIndex", location = "/WEB-INF/jsp/market.jsp"),
		@Result(name = "loadRefs", location = "/WEB-INF/jsp/phone_family_ref.jsp"),
		@Result(name = "queryLiuBuy", location = "/liuliangbuy.jsp")
})
public class DianpuAction extends BaseAction {

	@Autowired
	private CommentService commentservice;
	@Autowired
	private ProductsService productsService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private DianpuForUserService dianpuForUserService;
	@Autowired
	private ProductsForDianpuService productsForDianpuService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	
	private DianpuForUser dianpu;
	private List<ProductVo> lunbos;
	private List<ProductVo> dankuans;
	private List<ProductVo> sanlies;
	private Integer type;						//商品显示样式0轮播1单款2散列
	private Integer pageNum;					//散列显示页数
	private Integer pageSize;					//散列显示每页多少
	private String dianpuName;					//店铺名称
	private String dianPuJianJie;				//店铺简介
	private Integer productId;					//商品id
	private Double dianpuPrice;					//商品自定义价格
	private Integer dianpuId;					//店铺id
	private Integer paixu;						//商品排序
	private Integer productId_dian;				//店铺商品id
	private Integer pdId;						//products_dianpu的id
	
	public String dianPuProduct(){
		if(pdId!=null){
			ProductsForDianpu prod = productsForDianpuService.findById(pdId);
			if(prod!=null){
				BigInteger count=commentservice.commentCount(prod.getProductId());
				String hql = "from ProductsForDianpu where dianpuId ="+prod.getDianpuId()+" and prodType ="+prod.getProdType();
				List<ProductsForDianpu> prodList=productsForDianpuService.findByHql(hql, null);
				request.setAttribute("prod", prod);
				request.setAttribute("count", count);
				request.setAttribute("typelist", prodList);
				request.setAttribute("typeqty", prodList.size());
				userVo = getSessionUser();
				if(userVo!=null){
					request.setAttribute("orderAddress", orderAddressService.findDefaultAddressByUserVo(userVo));
				}
				try {
					AccessToken accessToken = autosendmsgService.processAccessToken();
					String nonce_str = sign.create_nonce_str();
					String timestamp = sign.create_timestamp();
					String url = SystemMessage.getString("YUMING")+"/dianpu/dianpuAction!dianPuProduct.action?pdId="+pdId;
					request.setAttribute("appId", SystemMessage.getString("APPID"));
					request.setAttribute("nonce_str", nonce_str);
					request.setAttribute("timestamp", timestamp);
					request.setAttribute("signature", sign.getSignature(accessToken.getTicket(), url, nonce_str, timestamp));
					request.setAttribute("url", url
							
//		        		WeixinUtil.getShorUrl(accessToken.getToken(), 
//		        				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
//		        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
//		        				)
								);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		return "dianPuProduct";
	}
	
	public String gotoDianPu(){
		dianpu = dianpuForUserService.findById(dianpuId);
		if(dianpu!=null){
			lunbos = productsForDianpuService.findPagerByPropertyAndSort(0,0,0,dianpuId);
			dankuans = productsForDianpuService.findPagerByPropertyAndSort(1,0,0,dianpuId);
			sanlies = productsForDianpuService.findPagerByPropertyAndSort(2,0,0,dianpuId);
			try {
				AccessToken accessToken = autosendmsgService.processAccessToken();
				String nonce_str = sign.create_nonce_str();
				String timestamp = sign.create_timestamp();
				String url = SystemMessage.getString("YUMING")+"/dianpu/dianpuAction!gotoDianPu.action?dianpuId="+dianpuId;
				request.setAttribute("appId", SystemMessage.getString("APPID"));
				request.setAttribute("nonce_str", nonce_str);
				request.setAttribute("timestamp", timestamp);
				request.setAttribute("signature", sign.getSignature(accessToken.getTicket(), url, nonce_str, timestamp));
				request.setAttribute("url", url
						
//	        		WeixinUtil.getShorUrl(accessToken.getToken(), 
//	        				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
//	        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
//	        				)
							);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "gotoDianpu";
		
	}
	
	public void yishangAjax(){
		
		List<ProductVo> productsForDianpus = productsForDianpuService.findPagerByPropertyAndSort(null,pageNum,pageSize,dianpuId);
		try {
			jsonArrayOut(productsForDianpus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void weishangAjax(){
		List<Products> products = productsService.findWeiShangByDianId(dianpuId,pageNum,pageSize);
		try {
			jsonArrayOut(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shangjia(){
		json.put("success", false);
		if(dianpuId!=null&&productId!=null){
			List<ProductsForDianpu> productsForDianpus = productsForDianpuService.findbyDianPuIdAndProductId(dianpuId,productId);
			if(productsForDianpus==null||productsForDianpus.size()!=0){
				json.put("message", "商品不能重复上架!");
			}else{
				Products products = productsService.findById(productId);
				
				if(products!=null&&products.getStatus()==1){
					ProductsForDianpu productsForDianpu = new ProductsForDianpu();
					productsForDianpu.setProducts(products);
					productsForDianpu.setCreateDate(new Date());
					productsForDianpu.setDianpuId(dianpuId);
					productsForDianpu.setKaiguan(0);
					if(paixu==null)
						paixu=0;
					if(type==null){
						type =2;
					}
					productsForDianpu.setPaixu(paixu);
					if(dianpuPrice==null){
						dianpuPrice = products.getPrice();
					}
					
					if(products.getPrice()>dianpuPrice){
						json.put("message", "价格不能低于"+products.getPrice());
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return ;
					}
					if(products.getKetiao()!=null&&products.getKetiao()==1){
						dianpuPrice = products.getPrice();
						json.put("message", "本产品价格不可调,已修改为建议价格!");
					}
					productsForDianpu.setPrice(dianpuPrice);
					productsForDianpu.setProductId(products.getProductsId());
					productsForDianpu.setHeadUrl(products.getHeadUrl());
					productsForDianpu.setName(products.getName());
					productsForDianpu.setJianyiPrice(products.getPrice());
					productsForDianpu.setKaiguan(0);
					productsForDianpu.setType(type);
					productsForDianpu.setProdType(products.getProdType());
					productsForDianpuService.save(productsForDianpu);
					json.put("success", true);
					try {
						jsonOut(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
					
				}
				json.put("message", "商品已经被下架!");
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void xiajia(){
		json.put("success", false);
		if(pdId!=null){
			ProductsForDianpu pd = productsForDianpuService.findById(pdId);
			if(pd!=null){
				productsForDianpuService.delete(pd);
				json.put("success", true);
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String gotoMyDianpu(){
		userVo =getSessionUser();
		if(userVo!=null){
			Usery usery = userVo.getUsery();
			if(usery!=null){
				if(usery.getDianPuId()!=null){
					dianpu = dianpuForUserService.findById(usery.getDianPuId());
//					if(dianpu!=null){
//						sanlies = productsForDianpuService.findPagerByPropertyAndSort(null,1,10,dianpu.getId());
//					}
				}
			}
		}
		return "gotoMyDianpu";
	}
	
	public void saveDianPu(){
		json.put("success", false);
		userVo = getSessionUser();
		if(userVo!=null){
			Usery usery = userVo.getUsery();
			DianpuForUser dianpu = null;
			if(usery!=null){
				if(usery.getDianPuId()==null){
					dianpu = new DianpuForUser();
					dianpu.setUseryId(usery.getId());
					dianpu.setHeadUrl(usery.getHeadUrl());
					dianpu.setName(dianpuName.trim());
					dianpu.setJianjie(dianPuJianJie.trim());
					dianpuForUserService.save(dianpu);
					usery.setDianPuId(dianpu.getId());
					useryService.update(usery);
					userVo.setUsery(usery);
					session.setAttribute("loginUser", userVo);
				}else{
					dianpu = dianpuForUserService.findById(usery.getDianPuId());
					dianpu.setHeadUrl(usery.getHeadUrl());
					dianpu.setName(dianpuName.trim());
					dianpu.setJianjie(dianPuJianJie.trim());
					dianpuForUserService.update(dianpu);
				}
				json.put("success", true);
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DianpuForUser getDianpu() {
		return dianpu;
	}

	public void setDianpu(DianpuForUser dianpu) {
		this.dianpu = dianpu;
	}


	public List<ProductVo> getLunbos() {
		return lunbos;
	}

	public void setLunbos(List<ProductVo> lunbos) {
		this.lunbos = lunbos;
	}

	public List<ProductVo> getDankuans() {
		return dankuans;
	}

	public void setDankuans(List<ProductVo> dankuans) {
		this.dankuans = dankuans;
	}

	public List<ProductVo> getSanlies() {
		return sanlies;
	}

	public void setSanlies(List<ProductVo> sanlies) {
		this.sanlies = sanlies;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDianpuName() {
		return dianpuName;
	}

	public void setDianpuName(String dianpuName) {
		this.dianpuName = dianpuName;
	}

	public String getDianPuJianJie() {
		return dianPuJianJie;
	}

	public void setDianPuJianJie(String dianPuJianJie) {
		this.dianPuJianJie = dianPuJianJie;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getDianpuPrice() {
		return dianpuPrice;
	}

	public void setDianpuPrice(Double dianpuPrice) {
		this.dianpuPrice = dianpuPrice;
	}

	public Integer getDianpuId() {
		return dianpuId;
	}

	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}

	public Integer getPaixu() {
		return paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}

	public Integer getProductId_dian() {
		return productId_dian;
	}

	public void setProductId_dian(Integer productId_dian) {
		this.productId_dian = productId_dian;
	}

	public Integer getPdId() {
		return pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}


}
