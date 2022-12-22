/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

/*
* 委托回报
*/
public class OrderInfo {
	//激活时间	
        public String activeTime;
        //最后修改交易所交易员代码	
        public String activeTraderID;
        //操作用户代码	
        public String activeUserID;
        //经纪公司代码	
        public String brokerID;
        //经纪公司报单编号	
        public int brokerOrderSeq;
        //业务单元	
        public String businessUnit;
        //撤销时间	
        public String cancelTime;
        //结算会员编号	
        public String clearingPartID;
        //客户代码	
        public String clientID;
        //合约代码	
        public String code;
        //组合投机套保标志	
        public String combHedgeFlag;
        //组合开平标志	
        public String combOffsetFlag;
        //触发条件	
        public String contingentCondition;
        //买卖方向	
        public String direction;
        //交易所代码	
        public String exchangeID;
        //合约在交易所的代码	
        public String exchangeInstID;
        //强平原因	
        public String forceCloseReason;
        //前置编号	
        public int frontID;
        //GTD日期	
        public String gTDDate;
        //价格	
        public double limitPrice;
        //报单日期	
        public String insertDate;
        //委托时间	
        public String insertTime;
        //安装编号	
        public String installID;
        //投资者代码	
        public String investorID;
        //自动挂起标志	
        public int isAutoSuspend;
        //互换单标志	
        public int isSwapOrder;
        //最小成交量	
        public int minVolume;
        //报单提示序号	
        public int notifySequence;
        //本地报单编号	
        public String orderLocalID;
        //报单价格条件	
        public String orderPriceType;
        //报单引用	
        public String orderRef;
        //报单状态	
        public String orderStatus;
        //报单来源	
        public String orderSource;
        //报单提交状态	
        public String orderSubmitStatus;
        //报单编号	
        public String orderSysID;
        //报单类型	
        public String orderType;
        //会员代码	
        public String participantID;
        //相关报单	
        public String relativeOrderSysID;
        //请求编号	
        public int requestID;
        //序号	
        public int sequenceNo;
        //会话编号	
        public int sessionID;
        //结算编号	
        public int settlementID;
        //状态信息	
        public String statusMsg;
        //止损价	
        public double stopPrice;
        //挂起时间	
        public String suspendTime;
        //有效期类型	
        public String timeCondition;
        //交易所交易员代码	
        public String traderID;
        //交易日	
        public String tradingDay;
        //最后修改时间	
        public String updateTime;
        //用户强评标志	
        public int userForceClose;
        //用户代码	
        public String userID;
        //用户端产品信息	
        public String userProductInfo;
        //成交量类型	
        public String volumeCondition;
        //剩余数量	
        public int volumeTotal;
        //数量	
        public int volumeTotalOriginal;
        //今成交数量	
        public int volumeTraded;
        //郑商所成交数量	
        public int zCETotalTradedVolume;
}
