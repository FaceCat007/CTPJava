package ctp;

public class IFCCTP {
	static {
		try {
			System.loadLibrary("iCTPJ");
		} catch (Exception e) {
			System.exit(1);
		}
	}

	protected native int askClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	protected native int askCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty,
			char timeCondition, String orderRef);

	protected native int askOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	protected native int bidClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	protected native int bidCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty,
			char timeCondition, String orderRef);

	protected native int bidOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef);

	protected native int cancelOrder(int ctpID, int requestID, String exchangeID, String orderSysID, String orderRef);

	protected native int create();

	protected native int generateReqID(int ctpID);
        
        protected native int hasNewDatas(int ctpID);

	protected native String getAccountData(int ctpID);

	protected native String getBrokerID(int ctpID);

	protected native String getCommissionRate(int ctpID, String code);

	protected native String getDepthMarketData(int ctpID);

	protected native String getInstrumentsData(int ctpID);

	protected native String getInvestorID(int ctpID);

	protected native String getMarginRate(int ctpID, String code);

	protected native String getOrderInfo(int ctpID);

	protected native String getOrderInfos(int ctpID);

	protected native String getPassword(int ctpID);

	protected native String getPositionData(int ctpID);

	protected native String getPositionDetailData(int ctpID);

	protected native int getSessionID(int ctpID);

	protected native String getTradeRecord(int ctpID);

	protected native String getTradeRecords(int ctpID);

	protected native String getTradingDate(int ctpID);

	protected native String getTradingTime(int ctpID);

	protected native int isClearanced(int ctpID);

	protected native int isClearanceTime(int ctpID);
        
        protected native int isDataOk(int ctpID);

	protected native int isMdLogined(int ctpID);

	protected native int isTdLogined(int ctpID);

	protected native int isTradingTime(int ctpID);

	protected native int isTradingTimeExact(int ctpID, String code);

	protected native int reqCommissionRate(int ctpID, String code, int requestID);

	protected native int reqMarginRate(int ctpID, String code, int requestID);

	protected native int reqQrySettlementInfoConfirm(int ctpID, int requestID);
        
        protected native int reqQrySettlementInfo(int ctpID, int requestID, String tradingDate);

	protected native int start(int ctpID, int requestID, String appID, String authCode, String mdServer, String tdServer, String brokerID,
			String investorID, String password);

	protected native int subMarketDatas(int ctpID, int requestID, String codes);

	protected native int unSubMarketDatas(int ctpID, int requestID, String codes);
}
