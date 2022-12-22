/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

import java.util.List;

/**
 * FCCTP 用户接口
 * 
 */
public interface FCCTP {
	/*
        卖平：多单平仓
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期 
        orderRef 附加信息 
        */
	int askClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition, String orderRef);

	/*
        卖平今仓：平今天的开仓的空单
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期
        orderRef 附加信息
        */
	int askCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	/*
        卖开：空单开仓
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期
        orderRef 附加信息
        */
	int askOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition, String orderRef);

	/*
        买平：空单平仓
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期
        orderRef 附加信息
        */
	int bidClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition, String orderRef);

	/*
        买平今仓：平今天的开仓的空单
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期
        orderRef 附加信息
        */
	int bidCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	/*
        买开：多单开仓
        ctpID 唯一ID
        requestID 请求ID
        code 代码
        exchangeID 交易所ID
        price 价格
        qty 数量
        timeCondition 有效期
        orderRef 附加信息
        */
	int bidOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition, String orderRef);

	/*
        撤单
        ctpID 唯一ID
        requestID 请求ID
        exchangeID 交易所ID
        orderSysID 委托编号
        orderRef 附加信息
        */
	int cancelOrder(int ctpID, int requestID, String exchangeID, String orderSysID, String orderRef);

	/*
        创建交易
        */
	int create();

	/*
        生成ctp请求编号
        ctpID 唯一ID
        */
	int generateReqID(int ctpID);
        
        /*
        是否有最新数据
        ctpID 唯一ID
        */
	int hasNewDatas(int ctpID);

	/*
        获取资金账户信息
        ctpID 唯一ID
        data 返回数据
        */
	String getAccountData(int ctpID);

	/*
        获取经纪公司ID
        ctpID 唯一ID
        data 返回数据
        */
	String getBrokerID(int ctpID);

	/*
        获取手续费率
        ctpID 唯一ID
        code 代码
        data 返回数据
        */
	String getCommissionRate(int ctpID, String code);

	/*
        获取深度市场行情
        ctpID 唯一ID
        data 返回数据
        */
	String getDepthMarketData(int ctpID);

	/*
        获取合约数据
        ctpID 唯一ID
        data 返回数据
        */
	String getInstrumentsData(int ctpID);

	/*
        获取投资者ID
        ctpID 唯一ID
        data 返回数据
        */
	String getInvestorID(int ctpID);

	/*
        获取保证金率
        ctpID 唯一ID
        code 代码
        data 返回数据
        */
	String getMarginRate(int ctpID, String code);

	/**
	 获取最新的委托回报（上一条)
	 ctpID 唯一ID
	 */
	String getOrderInfo(int ctpID);

	/**
	 获取所有的最新委托回报（今天的所有委托）
	 ctpID 唯一ID
	 */
	String getOrderInfos(int ctpID);

	/**
	 * 得到投资者密码
	 * 
	 * @param ctpID
	 * @return
	 */
	String getPassword(int ctpID);

	/*
        获取投资者持仓数据
        ctpID 唯一ID
        data 返回数据
        */
	String getPositionData(int ctpID);

	/**
	 获取投资者持仓明细数据
        ctpID 唯一ID
        data 返回数据
	 */
	String getPositionDetailData(int ctpID);

	/**
	 获取sessionID
	 ctpID 唯一ID
	 */
	int getSessionID(int ctpID);

	/**
	 获取最新成交记录（上一条）
        ctpID 唯一ID
        data 返回数据
	 */
	String getTradeRecord(int ctpID);

	/**
	 获取最新交易记录（今天的所有交易）
        ctpID 唯一ID
        data 返回数据
	 */
	String getTradeRecords(int ctpID);

	/**
	 获取交易日期
        ctpID 唯一ID
        data 返回数据
	 */
	String getTradingDate(int ctpID);

	/**
	 获取交易时间
        ctpID 唯一ID
        data 返回数据
	 */
	String getTradingTime(int ctpID);

	/**
	 当天是否已经结算
        ctpID 唯一ID
	 */
	int isClearanced(int ctpID);

	/**
	 是否是结算时间
	 ctpID 唯一ID
	 */
	int isClearanceTime(int ctpID);
        
        /**
	 数据是否ok
	 ctpID 唯一ID
	 */
	int isDataOk(int ctpID);

	/**
	 行情数据服务器是否已经登录
	 ctpID 唯一ID
	 */
	boolean isMdLogined(int ctpID);

	/**
	 交易是否已经登录
	 ctpID 唯一ID
	 */
	boolean isTdLogined(int ctpID);

	/**
	 是否是交易时间
	 ctpID 唯一ID
	 */
	int isTradingTime(int ctpID);

	/**
	 是否是精确交易时间(去掉集合竞价时间和休息时间)
        ctpID 唯一ID
        code 代码
	 */
	int isTradingTimeExact(int ctpID, String code);

	/**
	请求手续费率
        ctpID 唯一ID
        code 代码
        requestID 请求ID
	 */
	int reqCommissionRate(int ctpID, String code, int requestID);

	/**
	 请求保证金率
        ctpID 唯一ID
        code 代码
        requestID 请求ID
	 */
	int reqMarginRate(int ctpID, String code, int requestID);
        
	/**
	 请求确认结算信息
        ctpID 唯一ID
        requestID 请求ID
	 */
	int reqQrySettlementInfoConfirm(int ctpID, int requestID);
        
        /**
	 请求结算信息
        ctpID 唯一ID
        requestID 请求ID
        tradingDate 交易日
	 */
        int reqQrySettlementInfo(int ctpID, int requestID, String tradingDate);

	/**
	 启动创建的连接(在create后执行)
        ctpID 唯一ID
        requestID 请求ID
        appID APPID
        authCode 用户ID
        mdServer 行情地址
        tdServer 交易地址
        brokerID 机构号
        investorID 投资者账号
        password 密码
	 */
	int start(int ctpID, int requestID, String appID, String authCode, String mdServer, String tdServer, String brokerID, String investorID,
			String password);

	/**
	 订阅多个合约的行情数据
        ctpID 唯一ID
        requestID 请求ID
        codes 代码列表
	 */
	int subMarketDatas(int ctpID, int requestID, String codes);

	/**
	取消订阅多个合约的行情数据
        ctpID 唯一ID
        requestID 请求ID
        codes 代码列表
	 */
	int unSubMarketDatas(int ctpID, int requestID, String codes);
}
