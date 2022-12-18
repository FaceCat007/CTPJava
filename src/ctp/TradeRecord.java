/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

public class TradeRecord {
	//经纪公司代码
        public String brokerID;
        ///经纪公司报单编号
        public int brokerOrderSeq;
        ///业务单元
        public String businessUnit;
        ///结算会员编号
        public String clearingPartID;
        ///客户代码
        public String clientID;
        //合约代码
        public String code;
        //手续费
        public double commission;
        //买卖方向
        public String direction;
        //市场代码
        public String exchangeID;
        //合约在交易所的代码
        public String exchangeInstID;
        //投机套保标志
        public String hedgeFlag;
        //投资者代码
        public String investorID;
        //开平标志
        public String offsetFlag;
        //本地报单编号
        public String orderLocalID;
        //报单引用
        public String orderRef;
        //报单编号
        public String orderSysID;
        //会员代码
        public String participantID;
        //价格
        public double price;
        //成交价来源
        public String priceSource;
        //序号
        public int sequenceNo;
        //结算编号
        public int settlementID;
        //成交编号
        public String tradeID;
        //交易所交易员代码
        public String traderID;
        //成交时期
        public String tradeDate;
        //成交来源
        public String tradeSource;
        //成交时间
        public String tradeTime;
        //交易日
        public String tradingDay;
        //成交类型
        public String tradeType;
        //交易角色
        public String tradingRole;
        //数量
        public int volume;
        //用户代码
        public String userID;
}
