/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

import java.util.*;
import facecat.topin.core.*;

/**
 * CTP数据转换方法
 * 
 * @author todd
 *
 */
public abstract class CTPConvert {
	/**
	 * 转换CTP深度市场数据
	 * 
	 * @param result
	 * @return
	 */
	public static ArrayList<SecurityLatestData> convertToCTPDepthMarketData(String result) {
            ArrayList<SecurityLatestData> datas = new ArrayList<SecurityLatestData>();
            String[] strs = result.split("[;]");
            for(int d = 0; d < strs.length; d++){
                String[] results = strs[d].split(",");
                if (results.length >= 44) {
                        SecurityLatestData cTPDepthMarketData = new SecurityLatestData();
                        int i = 0;
                        cTPDepthMarketData.tradingDay = results[i++];
                        cTPDepthMarketData.code = results[i++];
                        cTPDepthMarketData.exchangeID = results[i++];
                        cTPDepthMarketData.exchangeInstID = results[i++];
                        cTPDepthMarketData.close = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.preSettlementPrice = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.preClose = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.preOpenInterest = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.open = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.high = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.low = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.volume = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.turnover = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.openInterest = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.lastClose = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.settlementPrice = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.upperLimit = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.lowerLimit = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.preDelta = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.currDelta = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.updateTime = results[i++];
                        cTPDepthMarketData.updateMillisec = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.bidPrice1 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.bidVolume1 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.askPrice1 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.askVolume1 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.bidPrice2 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.bidVolume2 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.askPrice2 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.askVolume2 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.bidPrice3 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.bidVolume3 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.askPrice3 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.askVolume3 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.bidPrice4 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.bidVolume4 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.askPrice4 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.askVolume4 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.bidPrice5 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.bidVolume5 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.askPrice5 = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.askVolume5 = FCTran.strToInt(results[i++]);
                        cTPDepthMarketData.averagePrice = FCTran.strToDouble(results[i++]);
                        cTPDepthMarketData.actionDay = results[i++];
                        datas.add(cTPDepthMarketData);
                }
            }
            return datas;
	}

	/**
	 * 转换CTP合约信息
	 * 
	 * @param str
	 * @return
	 */
	public static ArrayList<Security> convertToCTPInstrumentDatas(String str) {
		String[] strs = str.split(";");
		int size = strs.length;
		ArrayList<Security> cTPInstrumentDatas = new ArrayList<Security>();
		for (int i = 0; i < size; i++) {
			String[] results = strs[i].split(",");
			Security cTPInstrumentData = new Security();
			if (results.length >= 31) {
				int j = 0;
				///合约代码
                                cTPInstrumentData.instrumentID = results[j++];
                                ///交易所代码
                                cTPInstrumentData.exchangeID = results[j++];
                                ///合约名称
                                cTPInstrumentData.instrumentName = results[j++];
                                ///合约在交易所的代码
                                cTPInstrumentData.exchangeInstID = results[j++];
                                ///产品代码
                                cTPInstrumentData.productID = results[j++];
                                ///产品类型
                                cTPInstrumentData.productClass = results[j++];
                                ///交割年份
                                cTPInstrumentData.deliveryYear = FCTran.strToInt(results[j++]);
                                ///交割月
                                cTPInstrumentData.deliveryMonth = FCTran.strToInt(results[j++]);
                                ///市价单最大下单量
                                cTPInstrumentData.maxMarketOrderVolume = FCTran.strToInt(results[j++]);
                                ///市价单最小下单量
                                cTPInstrumentData.minMarketOrderVolume = FCTran.strToInt(results[j++]);
                                ///限价单最大下单量
                                cTPInstrumentData.maxLimitOrderVolume = FCTran.strToInt(results[j++]);
                                ///限价单最小下单量
                                cTPInstrumentData.minLimitOrderVolume = FCTran.strToInt(results[j++]);
                                ///合约数量乘数
                                cTPInstrumentData.volumeMultiple = FCTran.strToInt(results[j++]);
                                ///最小变动价位
                                cTPInstrumentData.priceTick = FCTran.strToDouble(results[j++]);
                                ///创建日
                                cTPInstrumentData.createDate = results[j++];
                                ///上市日
                                cTPInstrumentData.openDate = results[j++];
                                ///到期日
                                cTPInstrumentData.expireDate = results[j++];
                                ///开始交割日
                                cTPInstrumentData.startDelivDate = results[j++];
                                ///结束交割日
                                cTPInstrumentData.endDelivDate = results[j++];
                                ///合约生命周期状态
                                cTPInstrumentData.instLifePhase = results[j++];
                                ///当前是否交易
                                cTPInstrumentData.isTrading = results[j++];
                                ///持仓类型
                                cTPInstrumentData.positionType = results[j++];
                                ///持仓日期类型
                                cTPInstrumentData.positionDateType = results[j++];
                                ///多头保证金率   
                                cTPInstrumentData.longMarginRatio = FCTran.strToDouble(results[j++]);
                                ///空头保证金率
                                cTPInstrumentData.shortMarginRatio = FCTran.strToDouble(results[j++]);
                                ///是否使用大额单边保证金算法
                                cTPInstrumentData.maxMarginSideAlgorithm = results[j++];
                                ///基础商品代码
                                cTPInstrumentData.underlyingInstrID = results[j++];
                                ///执行价
                                cTPInstrumentData.strikePrice = FCTran.strToDouble(results[j++]);
                                ///期权类型
                                cTPInstrumentData.optionsType = results[j++];
                                ///合约基础商品乘数
                                cTPInstrumentData.underlyingMultiple = FCTran.strToDouble(results[j++]);
                                ///组合类型
                                cTPInstrumentData.combinationType = results[j++];
                                cTPInstrumentData.getNnderlyingInstrName();
                                cTPInstrumentDatas.add(cTPInstrumentData);
			}
		}
		return cTPInstrumentDatas;
	}

	/**
	 * 转换CTP持仓信息
	 * 
	 * @param result
	 * @return
	 */
	public static ArrayList<InvestorPosition> convertToCTPInvestorPosition(String result) {
		ArrayList<InvestorPosition> list = new ArrayList<InvestorPosition>();
		String[] strs = result.split(";");
		int len = strs.length;
		for (int p = 0; p < len; p++) {
			String[] results = strs[p].split(",");
			if (results.length >= 43) {
				InvestorPosition cTPInvestorPosition = new InvestorPosition();
				int i = 0;
				///合约代码
                                cTPInvestorPosition.code = results[i++];
                                ///经纪公司代码
                                cTPInvestorPosition.brokerID = results[i++];
                                ///投资者代码
                                cTPInvestorPosition.investorID = results[i++];
                                ///持仓多空方向
                                cTPInvestorPosition.posiDirection = results[i++];
                                ///投机套保标志
                                cTPInvestorPosition.hedgeFlag = results[i++];
                                ///持仓日期
                                cTPInvestorPosition.positionDate = results[i++];
                                ///上日持仓
                                cTPInvestorPosition.ydPosition = FCTran.strToInt(results[i++]);
                                ///今日持仓
                                cTPInvestorPosition.position = FCTran.strToInt(results[i++]);
                                ///多头冻结
                                cTPInvestorPosition.longFrozen = FCTran.strToInt(results[i++]);
                                ///空头冻结
                                cTPInvestorPosition.shortFrozen = FCTran.strToInt(results[i++]);
                                ///开仓冻结金额
                                cTPInvestorPosition.longFrozenAmount = FCTran.strToDouble(results[i++]);
                                ///开仓冻结金额
                                cTPInvestorPosition.shortFrozenAmount = FCTran.strToDouble(results[i++]);
                                ///开仓量
                                cTPInvestorPosition.openVolume = FCTran.strToInt(results[i++]);
                                ///平仓量
                                cTPInvestorPosition.closeVolume = FCTran.strToInt(results[i++]);
                                ///开仓金额
                                cTPInvestorPosition.openAmount = FCTran.strToDouble(results[i++]);
                                ///平仓金额
                                cTPInvestorPosition.closeAmount = FCTran.strToDouble(results[i++]);
                                ///持仓成本
                                cTPInvestorPosition.positionCost = FCTran.strToDouble(results[i++]);
                                ///上次占用的保证金
                                cTPInvestorPosition.preMargin = FCTran.strToDouble(results[i++]);
                                ///占用的保证金
                                cTPInvestorPosition.useMargin = FCTran.strToDouble(results[i++]);
                                ///冻结的保证金
                                cTPInvestorPosition.frozenMargin = FCTran.strToDouble(results[i++]);
                                ///冻结的资金
                                cTPInvestorPosition.frozenCash = FCTran.strToDouble(results[i++]);
                                ///冻结的手续费
                                cTPInvestorPosition.frozenCommission = FCTran.strToDouble(results[i++]);
                                ///资金差额
                                cTPInvestorPosition.cashIn = FCTran.strToDouble(results[i++]);
                                ///手续费
                                cTPInvestorPosition.margin = FCTran.strToDouble(results[i++]);
                                ///平仓盈亏
                                cTPInvestorPosition.floatProfit = FCTran.strToDouble(results[i++]);
                                ///持仓盈亏
                                cTPInvestorPosition.positionProfit = FCTran.strToDouble(results[i++]);
                                ///上次结算价
                                cTPInvestorPosition.preSettlementPrice = FCTran.strToDouble(results[i++]);
                                ///本次结算价
                                cTPInvestorPosition.settlementPrice = FCTran.strToDouble(results[i++]);
                                ///交易日
                                cTPInvestorPosition.tradingDate = results[i++];
                                ///结算编号
                                cTPInvestorPosition.settlementID = FCTran.strToInt(results[i++]);
                                ///开仓成本
                                cTPInvestorPosition.openCost = FCTran.strToDouble(results[i++]);
                                ///交易所保证金
                                cTPInvestorPosition.exchangeMargin = FCTran.strToDouble(results[i++]);
                                ///组合成交形成的持仓
                                cTPInvestorPosition.combPosition = FCTran.strToInt(results[i++]);
                                ///组合多头冻结
                                cTPInvestorPosition.combLongFrozen = FCTran.strToInt(results[i++]);
                                ///组合空头冻结
                                cTPInvestorPosition.combShortFrozen = FCTran.strToInt(results[i++]);
                                ///逐日盯市平仓盈亏
                                cTPInvestorPosition.closeProfitByDate = FCTran.strToDouble(results[i++]);
                                ///逐笔对冲平仓盈亏
                                cTPInvestorPosition.closeProfitByTrade = FCTran.strToDouble(results[i++]);
                                ///今日持仓
                                cTPInvestorPosition.todayPosition = FCTran.strToInt(results[i++]);
                                ///保证金率
                                cTPInvestorPosition.marginRateByMoney = FCTran.strToDouble(results[i++]);
                                ///保证金率(按手数)
                                cTPInvestorPosition.marginRateByVolume = FCTran.strToDouble(results[i++]);
                                ///执行冻结
                                cTPInvestorPosition.strikeFrozen = FCTran.strToInt(results[i++]);
                                ///执行冻结金额
                                cTPInvestorPosition.strikeFrozenAmount = FCTran.strToDouble(results[i++]);
                                ///放弃执行冻结
                                cTPInvestorPosition.abandonFrozen = FCTran.strToInt(results[i++]);
                                //开仓均价
                                cTPInvestorPosition.openPrice = FCTran.strToInt(results[i++]);
				list.add(cTPInvestorPosition);
			}
		}
		return list;
	}

	/**
	 * 转换CTP持仓明细信息
	 * 
	 * @param result
	 * @return
	 */
	public static ArrayList<InvestorPositionDetail> convertToCTPInvestorPositionDetail(String result) {
		ArrayList<InvestorPositionDetail> list = new ArrayList<InvestorPositionDetail>();
		String[] strs = result.split(";");
		int len = strs.length;
		for (int p = 0; p < len; p++) {
			String[] results = strs[p].split(",");
			if (results.length >= 43) {
				InvestorPositionDetail cTPInvestorPositionDetail = new InvestorPositionDetail();
				int i = 0;
				cTPInvestorPositionDetail.code = results[i++];
                                cTPInvestorPositionDetail.brokerID = results[i++];
                                cTPInvestorPositionDetail.investorID = results[i++];
                                cTPInvestorPositionDetail.hedgeFlag = results[i++];
                                cTPInvestorPositionDetail.direction = results[i++];
                                cTPInvestorPositionDetail.openDate = results[i++];
                                cTPInvestorPositionDetail.tradeID = results[i++];
                                cTPInvestorPositionDetail.volume = FCTran.strToInt(results[i++]);
                                cTPInvestorPositionDetail.openPrice = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.tradingDay = results[i++];
                                cTPInvestorPositionDetail.settlementID = FCTran.strToInt(results[i++]);
                                cTPInvestorPositionDetail.tradeType = results[i++];
                                cTPInvestorPositionDetail.combInstrumentID = results[i++];
                                cTPInvestorPositionDetail.exchangeID = results[i++];
                                cTPInvestorPositionDetail.closeProfitByDate = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.closeProfitByTrade = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.positionProfitByDate = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.positionProfitByTrade = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.margin = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.exchMargin = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.marginRateByMoney = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.marginRateByVolume = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.lastSettlementPrice = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.lastSettlementPrice = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.settlementPrice = FCTran.strToDouble(results[i++]);
                                cTPInvestorPositionDetail.closeVolume = FCTran.strToInt(results[i++]);
                                list.add(cTPInvestorPositionDetail);
			}
		}
		return list;
	}

	/**
	 * 转换CTP报单信息
	 * 
	 * @param result
	 * @return
	 */
	public static OrderInfo convertToCTPOrder(String result) {
		String[] results = result.split(",");
		OrderInfo cTPOrder = null;
		if (results.length >= 57) {
			cTPOrder = new OrderInfo();
			int i = 0;
			///经纪公司代码
                        cTPOrder.brokerID = results[i++];
                        ///投资者代码
                        cTPOrder.investorID = results[i++];
                        ///合约代码
                        cTPOrder.code = results[i++];
                        ///报单引用
                        cTPOrder.orderRef = results[i++];
                        ///用户代码
                        cTPOrder.userID = results[i++];
                        ///报单价格条件
                        cTPOrder.orderPriceType = results[i++];
                        ///买卖方向
                        cTPOrder.direction = results[i++];
                        ///组合开平标志
                        cTPOrder.combOffsetFlag = results[i++];
                        ///组合投机套保标志
                        cTPOrder.combHedgeFlag = results[i++];
                        ///价格
                        cTPOrder.limitPrice = FCTran.strToDouble(results[i++]);
                        ///数量
                        cTPOrder.volumeTotalOriginal = FCTran.strToInt(results[i++]);
                        ///有效期类型
                        cTPOrder.timeCondition = results[i++];
                        ///GTD日期
                        cTPOrder.gTDDate = results[i++];
                        ///成交量类型
                        cTPOrder.volumeCondition = results[i++];
                        ///最小成交量
                        cTPOrder.minVolume = FCTran.strToInt(results[i++]);
                        ///触发条件
                        cTPOrder.contingentCondition = results[i++];
                        ///止损价
                        cTPOrder.stopPrice = FCTran.strToDouble(results[i++]);
                        ///强平原因
                        cTPOrder.forceCloseReason = results[i++];
                        ///自动挂起标志
                        cTPOrder.isAutoSuspend = FCTran.strToInt(results[i++]);
                        ///业务单元
                        cTPOrder.businessUnit = results[i++];
                        ///请求编号
                        cTPOrder.requestID = FCTran.strToInt(results[i++]);
                        ///本地报单编号
                        cTPOrder.orderLocalID = results[i++];
                        ///交易所代码
                        cTPOrder.exchangeID = results[i++];
                        ///会员代码
                        cTPOrder.participantID = results[i++];
                        ///客户代码
                        cTPOrder.clientID = results[i++];
                        ///合约在交易所的代码
                        cTPOrder.exchangeInstID = results[i++];
                        ///交易所交易员代码
                        cTPOrder.traderID = results[i++];
                        ///安装编号
                        cTPOrder.installID = results[i++];
                        ///报单提交状态
                        cTPOrder.orderSubmitStatus = results[i++];
                        ///报单提示序号
                        cTPOrder.notifySequence = FCTran.strToInt(results[i++]);
                        ///交易日
                        cTPOrder.tradingDay = results[i++];
                        ///结算编号
                        cTPOrder.settlementID = FCTran.strToInt(results[i++]);
                        ///报单编号
                        cTPOrder.orderSysID = results[i++];
                        ///报单来源
                        cTPOrder.orderSource = results[i++];
                        ///报单状态
                        cTPOrder.orderStatus = results[i++];
                        ///报单类型
                        cTPOrder.orderType = results[i++];
                        ///今成交数量
                        cTPOrder.volumeTraded = FCTran.strToInt(results[i++]);
                        ///剩余数量
                        cTPOrder.volumeTotal = FCTran.strToInt(results[i++]);
                        ///报单日期
                        cTPOrder.insertDate = results[i++];
                        ///委托时间
                        cTPOrder.insertTime = results[i++];
                        ///激活时间
                        cTPOrder.activeTime = results[i++];
                        ///挂起时间
                        cTPOrder.suspendTime = results[i++];
                        ///最后修改时间
                        cTPOrder.updateTime = results[i++];
                        ///撤销时间
                        cTPOrder.cancelTime = results[i++];
                        ///最后修改交易所交易员代码
                        cTPOrder.activeTraderID = results[i++];
                        ///结算会员编号
                        cTPOrder.clearingPartID = results[i++];
                        ///序号
                        cTPOrder.sequenceNo = FCTran.strToInt(results[i++]);
                        ///前置编号
                        cTPOrder.frontID = FCTran.strToInt(results[i++]);
                        ///会话编号
                        cTPOrder.sessionID = FCTran.strToInt(results[i++]);
                        ///用户端产品信息
                        cTPOrder.userProductInfo = results[i++];
                        ///状态信息
                        cTPOrder.statusMsg = results[i++];
                        ///用户强评标志
                        cTPOrder.userForceClose = FCTran.strToInt(results[i++]);
                        ///操作用户代码
                        cTPOrder.activeUserID = results[i++];
                        ///经纪公司报单编号
                        cTPOrder.brokerOrderSeq = FCTran.strToInt(results[i++]);
                        ///相关报单
                        cTPOrder.relativeOrderSysID = results[i++];
                        ///郑商所成交数量
                        cTPOrder.zCETotalTradedVolume = FCTran.strToInt(results[i++]);
                        ///互换单标志
                        cTPOrder.isSwapOrder = FCTran.strToInt(results[i++]);
		}
		return cTPOrder;
	}

	/**
	 * 转换CTP报单信息
	 * 
	 * @param result
	 * @return
	 */
	public static ArrayList<OrderInfo> convertToCTPOrderList(String result) {
		ArrayList<OrderInfo> lst = new ArrayList<OrderInfo>();
		String[] results = result.split(";");
		int len = results.length;
		for (int i = 0; i < len; i++) {
			OrderInfo order = convertToCTPOrder(results[i]);
			if (order != null) {
				lst.add(order);
			}
		}
		return lst;
	}

	/**
	 * 转换CTP交易信息
	 * 
	 * @param result
	 * @return
	 */
	public static TradeRecord convertToCTPTrade(String result) {
		String[] results = result.split(",");
		TradeRecord cTPTrade = null;
		if (results.length >= 30) {
			cTPTrade = new TradeRecord();
			int i = 0;
			///经纪公司代码
                        cTPTrade.brokerID = results[i++];
                        ///投资者代码
                        cTPTrade.investorID = results[i++];
                        ///合约代码
                        cTPTrade.code = results[i++];
                        ///报单引用
                        cTPTrade.orderRef = results[i++];
                        ///用户代码
                        cTPTrade.userID = results[i++];
                        ///交易所代码
                        cTPTrade.exchangeID = results[i++];
                        ///成交编号
                        cTPTrade.tradeID = results[i++];
                        ///买卖方向
                        cTPTrade.direction = results[i++];
                        ///报单编号
                        cTPTrade.orderSysID = results[i++];
                        ///会员代码
                        cTPTrade.participantID = results[i++];
                        ///客户代码
                        cTPTrade.clientID = results[i++];
                        ///交易角色
                        cTPTrade.tradingRole = results[i++];
                        ///合约在交易所的代码
                        cTPTrade.exchangeInstID = results[i++];
                        ///开平标志
                        cTPTrade.offsetFlag = results[i++];
                        ///投机套保标志
                        cTPTrade.hedgeFlag = results[i++];
                        ///价格
                        cTPTrade.price = FCTran.strToDouble(results[i++]);
                        ///数量
                        cTPTrade.volume = FCTran.strToInt(results[i++]);
                        ///成交时期
                        cTPTrade.tradeDate = results[i++];
                        ///成交时间
                        cTPTrade.tradeTime = results[i++];
                        ///成交类型
                        cTPTrade.tradeType = results[i++];
                        ///成交价来源
                        cTPTrade.priceSource = results[i++];
                        ///交易所交易员代码
                        cTPTrade.traderID = results[i++];
                        ///本地报单编号
                        cTPTrade.orderLocalID = results[i++];
                        ///结算会员编号
                        cTPTrade.clearingPartID = results[i++];
                        ///业务单元
                        cTPTrade.businessUnit = results[i++];
                        ///序号
                        cTPTrade.sequenceNo = FCTran.strToInt(results[i++]);
                        ///交易日
                        cTPTrade.tradingDay = results[i++];
                        ///结算编号
                        cTPTrade.settlementID = FCTran.strToInt(results[i++]);
                        ///经纪公司报单编号
                        cTPTrade.brokerOrderSeq = FCTran.strToInt(results[i++]);
                        ///成交来源
                        cTPTrade.tradeSource = results[i++];
                        ///手续费
                        cTPTrade.commission = FCTran.strToDouble(results[i++]);
		}
		return cTPTrade;
	}

	/**
	 * 转换CTP交易信息列表
	 * 
	 * @param result
	 * @return
	 */
	public static ArrayList<TradeRecord> convertToCTPTradeRecords(String result) {
		ArrayList<TradeRecord> list = new ArrayList<TradeRecord>();
		String[] results = result.split(";");
		int len = results.length;
		for (int i = 0; i < len; i++) {
			TradeRecord trade = convertToCTPTrade(results[i]);
			if (trade != null) {
				list.add(trade);
			}
		}
		return list;
	}

	/**
	 * 转换成CTP资金账户
	 * 
	 * @param result
	 * @return
	 */
	public static AccountData convertToCTPTradingAccount(String result) {
		String[] results = result.split(",");
		AccountData cTPTradingAccount = null;
		if (results.length >= 46) {
			cTPTradingAccount = new AccountData();
			int i = 0;
			///经纪公司代码
                        cTPTradingAccount.brokerID = results[i++];
                        ///投资者帐号
                        cTPTradingAccount.accountID = results[i++];
                        ///上次质押金额
                        cTPTradingAccount.preMortgage = FCTran.strToDouble(results[i++]);
                        ///上次信用额度
                        cTPTradingAccount.preCredit = FCTran.strToDouble(results[i++]);
                        ///上次存款额
                        cTPTradingAccount.preDeposit = FCTran.strToDouble(results[i++]);
                        ///上次结算准备金
                        cTPTradingAccount.preBalance = FCTran.strToDouble(results[i++]);
                        ///上次占用的保证金
                        cTPTradingAccount.preMargin = FCTran.strToDouble(results[i++]);
                        ///利息基数
                        cTPTradingAccount.interestBase = FCTran.strToDouble(results[i++]);
                        ///利息收入
                        cTPTradingAccount.interest = FCTran.strToDouble(results[i++]);
                        ///入金金额
                        cTPTradingAccount.deposit = FCTran.strToDouble(results[i++]);
                        ///出金金额
                        cTPTradingAccount.withdraw = FCTran.strToDouble(results[i++]);
                        ///冻结的保证金
                        cTPTradingAccount.frozenMargin = FCTran.strToDouble(results[i++]);
                        ///冻结的资金
                        cTPTradingAccount.frozenCash = FCTran.strToDouble(results[i++]);
                        ///冻结的手续费
                        cTPTradingAccount.frozenCommission = FCTran.strToDouble(results[i++]);
                        ///当前保证金总额
                        cTPTradingAccount.currMargin = FCTran.strToDouble(results[i++]);
                        ///资金差额
                        cTPTradingAccount.cashIn = FCTran.strToDouble(results[i++]);
                        ///手续费
                        cTPTradingAccount.commission = FCTran.strToDouble(results[i++]);
                        ///平仓盈亏
                        cTPTradingAccount.closeProfit = FCTran.strToDouble(results[i++]);
                        ///持仓盈亏
                        cTPTradingAccount.positionProfit = FCTran.strToDouble(results[i++]);
                        ///期货结算准备金
                        cTPTradingAccount.balance = FCTran.strToDouble(results[i++]);
                        ///可用资金
                        cTPTradingAccount.available = FCTran.strToDouble(results[i++]);
                        ///可取资金
                        cTPTradingAccount.withdrawQuota = FCTran.strToDouble(results[i++]);
                        ///基本准备金
                        cTPTradingAccount.reserve = FCTran.strToDouble(results[i++]);
                        ///交易日
                        cTPTradingAccount.tradingDay = results[i++];
                        ///结算编号
                        cTPTradingAccount.settlementID = FCTran.strToInt(results[i++]);
                        ///信用额度
                        cTPTradingAccount.credit = FCTran.strToDouble(results[i++]);
                        ///质押金额
                        cTPTradingAccount.mortgage = FCTran.strToDouble(results[i++]);
                        ///交易所保证金
                        cTPTradingAccount.exchangeMargin = FCTran.strToDouble(results[i++]);
                        ///投资者交割保证金
                        cTPTradingAccount.deliveryMargin = FCTran.strToDouble(results[i++]);
                        ///交易所交割保证金
                        cTPTradingAccount.exchangeDeliveryMargin = FCTran.strToDouble(results[i++]);
                        ///保底期货结算准备金
                        cTPTradingAccount.reserveBalance = FCTran.strToDouble(results[i++]);
                        ///币种代码
                        cTPTradingAccount.currencyID = results[i++];
                        ///上次货币质入金额
                        cTPTradingAccount.preFundMortgageIn = FCTran.strToDouble(results[i++]);
                        ///上次货币质出金额
                        cTPTradingAccount.preFundMortgageOut = FCTran.strToDouble(results[i++]);
                        ///货币质入金额
                        cTPTradingAccount.fundMortgageIn = FCTran.strToDouble(results[i++]);
                        ///货币质出金额
                        cTPTradingAccount.fundMortgageOut = FCTran.strToDouble(results[i++]);
                        ///货币质押余额
                        cTPTradingAccount.fundMortgageAvailable = FCTran.strToDouble(results[i++]);
                        ///可质押货币金额
                        cTPTradingAccount.mortgageableFund = FCTran.strToDouble(results[i++]);
                        ///特殊产品占用保证金
                        cTPTradingAccount.specProductMargin = FCTran.strToDouble(results[i++]);
                        ///特殊产品冻结保证金
                        cTPTradingAccount.specProductFrozenMargin = FCTran.strToDouble(results[i++]);
                        ///特殊产品手续费
                        cTPTradingAccount.specProductCommission = FCTran.strToDouble(results[i++]);
                        ///特殊产品冻结手续费
                        cTPTradingAccount.specProductFrozenCommission = FCTran.strToDouble(results[i++]);
                        ///特殊产品持仓盈亏
                        cTPTradingAccount.specProductPositionProfit = FCTran.strToDouble(results[i++]);
                        ///特殊产品平仓盈亏
                        cTPTradingAccount.specProductCloseProfit = FCTran.strToDouble(results[i++]);
                        ///根据持仓盈亏算法计算的特殊产品持仓盈亏
                        cTPTradingAccount.specProductPositionProfitByAlg = FCTran.strToDouble(results[i++]);
                        ///特殊产品交易所保证金
                        cTPTradingAccount.specProductExchangeMargin = FCTran.strToDouble(results[i++]);
                        ///动态权益
                        cTPTradingAccount.dynamicBalance = FCTran.strToDouble(results[i++]);
                        ///风险度
                        cTPTradingAccount.risk = FCTran.strToDouble(results[i++]);
                        //浮动盈亏
                        cTPTradingAccount.floatProfit = FCTran.strToDouble(results[i++]);
		}
		return cTPTradingAccount;
	}
}
