var messageCode =[
	{code :"001001",value:"余额不足!!!!"},
	{code :"001000",value:"请先去店铺管理-->修改店铺信息!!!!"},
	{code :"010000",value:"您还没有资格进入需要关注公众号!!!!"},
	{code :"100000",value:"用户未登录!!!!"},
	{code :"001010",value:"提现金额格式不对!!!!!"},

]

function getMessageByCode(code){
	for(var i in messageCode){
		if(messageCode[i].code==code){
			return messageCode[i].value;
		}
	}
}