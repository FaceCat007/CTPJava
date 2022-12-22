/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

/*
* 持仓数据
*/
public class InvestorPosition {
	//放弃执行冻结
        public int abandonFrozen;
        // 经纪公司代码
        public String brokerID;
        // 资金差额
        public double cashIn;
        // 平仓金额
        public double closeAmount;
        // 平仓盈亏
        public double closeProfit;
        // 逐日盯市平仓盈亏
        public double closeProfitByDate;
        // 逐笔对冲平仓盈亏
        public double closeProfitByTrade;
        // 平仓量
        public int closeVolume;
        // 合约代码
        public String code;
        // 组合多头冻结
        public int combLongFrozen;
        // 组合成交形成的持仓
        public int combPosition;
        // 组合空头冻结
        public int combShortFrozen;
        // 手续费
        public double commission;
        // 交易所保证金
        public double exchangeMargin;
        //浮动盈亏
        public double floatProfit;
        // 冻结的资金
        public double frozenCash;
        // 冻结的手续费
        public double frozenCommission;
        // 冻结的保证金
        public double frozenMargin;
        // 投机套保标志
        public String hedgeFlag;
        // 投资者代码
        public String investorID;
        // 多头冻结
        public int longFrozen;
        // 多头冻结金额
        public double longFrozenAmount;
        //保证金
        public double margin;
        // 保证金率
        public double marginRateByMoney;
        // 保证金率(按手数)
        public double marginRateByVolume;
        // 开仓金额
        public double openAmount;
        // 开仓成本
        public double openCost;
        //开仓价格
        public double openPrice;
        // 开仓量
        public int openVolume;
        // 今日持仓
        public int position;
        // 持仓日期
        public String positionDate;
        // 持仓多空方向
        public String posiDirection;
        // 持仓成本
        public double positionCost;
        // 持仓盈亏
        public double positionProfit;
        // 上次占用的保证金
        public double preMargin;
        // 上次结算价
        public double preSettlementPrice;
        // 结算编号
        public int settlementID;
        // 本次结算价
        public double settlementPrice;
        // 空头冻结
        public int shortFrozen;
        // 空头冻结金额
        public double shortFrozenAmount;
        // 执行冻结
        public int strikeFrozen;
        // 执行冻结金额
        public double strikeFrozenAmount;
        // 今日持仓
        public int todayPosition;
        //交易日
        public String tradingDate;
        //占用的保证金
        public double useMargin;
        //上日持仓
        public int ydPosition;

	// 获取可用仓位
	int getAvailablePosition() {
		int position = 0;
		position = position - getFrozenAmount();
		return position;
	};

	int getFrozenAmount() {
		int position = 0;
		if (posiDirection.equals("多")) {
			position = longFrozen;
		} else if (posiDirection.equals("空")) {
			position = shortFrozen;
		}
		return position;
	};
}
