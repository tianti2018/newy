package com.zklc.weishangcheng.member.action;

import java.math.BigInteger;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;
import com.zklc.weishangcheng.member.service.CommentService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.ProductsService;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.sign;

@ParentPackage("json")
@Namespace("/products")
@Action(value = "productsAction")
@Results({
		@Result(name = "product", location = "/WEB-INF/jsp/products/product.jsp"),
		@Result(name = "products", location = "/WEB-INF/jsp/products/products.jsp"),
})
public class ProductsAction extends BaseAction {

	@Autowired
	private CommentService commentservice;
	@Autowired
	private ProductsService productsService;
	@Autowired
	private OrderAddressService orderAddressService;
	
	private Integer prodId;//购买产品id
	
	public String products(){
		List<Products> products_lunbo = productsService.findPagerByPropertyAndSort(0, 0, 0);
		List<Products> products_dankuan=productsService.findPagerByPropertyAndSort(1, 0, 0);
		List<Products> products_sanlie=productsService.findPagerByPropertyAndSort(2, 1, 10);
		request.setAttribute("products_lunbo", products_lunbo);
		request.setAttribute("products_dankuan", products_dankuan);
		request.setAttribute("products_sanlie", products_sanlie);
		AccessToken accessToken = autosendmsgService.processAccessToken();
		String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();
        String url = SystemMessage.getString("YUMING")+"/products/productsAction!products.action";
        request.setAttribute("appId", SystemMessage.getString("APPID"));
        request.setAttribute("nonce_str", nonce_str);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("signature", sign.getSignature(accessToken.getTicket(), url, nonce_str, timestamp));
        request.setAttribute("url", url
        		
//        		WeixinUtil.getShorUrl(accessToken.getToken(), 
//        				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
//        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
//        				)
        			);
		return "products";
	}
	
	public String product(){
		Products prod=productsService.findById(prodId);
		BigInteger count=commentservice.commentCount(prodId);
		List<Products> prodList=productsService.findByProperty("prodType", prod.getProdType());
		request.setAttribute("prod", prod);
		request.setAttribute("count", count);
		request.setAttribute("typelist", prodList);
		request.setAttribute("typeqty", prodList.size());
		userVo = getSessionUser();
		if(userVo!=null&&userVo.getUser()!=null){
			request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(userVo.getUser().getUserId()));
		}
		AccessToken accessToken = autosendmsgService.processAccessToken();
		String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();
        String url = SystemMessage.getString("YUMING")+"/products/productsAction!product.action?prodId="+prodId;
        request.setAttribute("appId", SystemMessage.getString("APPID"));
        request.setAttribute("nonce_str", nonce_str);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("signature", sign.getSignature(accessToken.getTicket(), url, nonce_str, timestamp));
        request.setAttribute("url", url
        		
//        		WeixinUtil.getShorUrl(accessToken.getToken(), 
//        				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
//        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
//        				)
        			);
		return "product";
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	
	
	
	
}
