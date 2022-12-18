/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

public class InvestorPositionDetail {
	//经纪公司代码
        public String brokerID;
        //平仓金额
        public double closeAmount;
        //平仓盈亏
        public double closeProfit;
        //逐日盯市持仓盈亏
        public double closeProfitByDate;
        //逐笔对冲持仓盈亏
        public double closeProfitByTrade;
        //平仓量
        public double closeVolume;
        //合约代码
        public String code;
        //组合合约代码
        public String combInstrumentID;
        //买卖
        public String direction;
        //交易所代码
        public String exchangeID;
        //交易所保证金
        public double exchMargin;
        //浮动盈亏
        public double floatProfit;
        //投机套保标志
        public String hedgeFlag;
        //投资者代码
        public String investorID;
        //昨收盘
        public double lastPrice;
        //昨结算价
        public double lastSettlementPrice;
        //投资者保证金
        public double margin;
        //保证金率
        public double marginRateByMoney;
        //保证金率(按手数)
        public double marginRateByVolume;
        //内部编号
        public String orderRef;
        //开仓日期
        public String openDate;
        //开仓价
        public double openPrice;
        //开仓量
        public int openVolume;
        //本地持仓号，服务器编写
        public String positionNo;
        //持仓盈亏
        public double positionProfit;
        //逐日盯市持仓利润
        public double positionProfitByDate;
        //逐笔对冲持仓利润
        public double positionProfitByTrade;
        //持仓流号
        public int positionStreamId;
        //昨结算价
        public double preSettlementPrice;
        //持仓盈亏基准价
        public double profitBasePrice;
        //结算编号
        public int settlementID;
        //结算价
        public double settlementPrice;
        //成交日期
        public String tradingDay;
        //成交编号
        public String tradeID;
        //成交类型
        public String tradeType;
        //数量
        public int volume;
}
