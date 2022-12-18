/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

import java.util.List;


/**
 * Lord CTP java 接口
 * 
 * @author todd
 *
 */
public class FCCTPIMPL implements FCCTP {

	private static IFCCTP m_ctpBridge;

	public FCCTPIMPL() {
		if (m_ctpBridge == null) {
			m_ctpBridge = new IFCCTP();
		}
	}

	public int askClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.askClose(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int askCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.askCloseToday(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int askOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.askOpen(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int bidClose(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.bidClose(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int bidCloseToday(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.bidCloseToday(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int bidOpen(int ctpID, int requestID, String code, String exchangeID, double price, int qty, char timeCondition,
			String orderRef) {
		return m_ctpBridge.bidOpen(ctpID, requestID, code, exchangeID, price, qty, timeCondition, orderRef);
	}

	public int cancelOrder(int ctpID, int requestID, String exchangeID, String orderSysID, String orderRef) {
		return m_ctpBridge.cancelOrder(ctpID, requestID, exchangeID, orderSysID, orderRef);
	}

	public int create() {
		return m_ctpBridge.create();
	}

	public int generateReqID(int ctpID) {
		return m_ctpBridge.generateReqID(ctpID);
	}
        
	public int hasNewDatas(int ctpID){
            return m_ctpBridge.hasNewDatas(ctpID);
        }

	public String getAccountData(int ctpID) {
		return m_ctpBridge.getAccountData(ctpID);
	}

	public String getBrokerID(int ctpID) {
		return m_ctpBridge.getBrokerID(ctpID);
	}
        
	public String getCommissionRate(int ctpID, String code) {
		return m_ctpBridge.getCommissionRate(ctpID, code);
	}

	public String getDepthMarketData(int ctpID) {
		return m_ctpBridge.getDepthMarketData(ctpID);
	}

	public String getInstrumentsData(int ctpID) {
		return m_ctpBridge.getInstrumentsData(ctpID);
	}

	public String getInvestorID(int ctpID) {
		return m_ctpBridge.getInvestorID(ctpID);
	}

	public String getMarginRate(int ctpID, String code) {
		return m_ctpBridge.getMarginRate(ctpID, code);
	}

	public String getOrderInfo(int ctpID) {
		return m_ctpBridge.getOrderInfo(ctpID);
	}

	public String getOrderInfos(int ctpID) {
		return m_ctpBridge.getOrderInfos(ctpID);
	}

	public String getPassword(int ctpID) {
		return m_ctpBridge.getPassword(ctpID);
	}
        
	public String getPositionData(int ctpID) {
		return m_ctpBridge.getPositionData(ctpID);
	}

	public String getPositionDetailData(int ctpID) {
		return m_ctpBridge.getPositionDetailData(ctpID);
	}

	public int getSessionID(int ctpID) {
		return m_ctpBridge.getSessionID(ctpID);
	}

	public String getTradeRecord(int ctpID) {

		return m_ctpBridge.getTradeRecord(ctpID);
	}

	public String getTradeRecords(int ctpID) {

		return m_ctpBridge.getTradeRecords(ctpID);
	}

	public String getTradingDate(int ctpID) {
		return m_ctpBridge.getTradingDate(ctpID);
	}

	public String getTradingTime(int ctpID) {
		return m_ctpBridge.getTradingTime(ctpID);
	}

	public int isClearanced(int ctpID) {
		return m_ctpBridge.isClearanced(ctpID);
	}

	public int isClearanceTime(int ctpID) {
		return m_ctpBridge.isClearanceTime(ctpID);
	}
        
        public int isDataOk(int ctpID) {
		return m_ctpBridge.isDataOk(ctpID);
	}

	public boolean isMdLogined(int ctpID) {
		return m_ctpBridge.isMdLogined(ctpID) > 0 ? true : false;
	}

	public boolean isTdLogined(int ctpID) {
		return m_ctpBridge.isTdLogined(ctpID) > 0 ? true : false;
	}

	public int isTradingTime(int ctpID) {
		return m_ctpBridge.isTradingTime(ctpID);
	}

	public int isTradingTimeExact(int ctpID, String code) {
		return m_ctpBridge.isTradingTimeExact(ctpID, code);
	}

	public int reqCommissionRate(int ctpID, String code, int requestID) {
		return m_ctpBridge.reqCommissionRate(ctpID, code, requestID);
	}

	public int reqMarginRate(int ctpID, String code, int requestID) {

		return m_ctpBridge.reqMarginRate(ctpID, code, requestID);
	}

	public int reqQrySettlementInfoConfirm(int ctpID, int requestID) {
		return m_ctpBridge.reqQrySettlementInfoConfirm(ctpID, requestID);
	}
        
        public int reqQrySettlementInfo(int ctpID, int requestID, String tradingDate) {
		return m_ctpBridge.reqQrySettlementInfo(ctpID, requestID, tradingDate);
	}

	public int start(int ctpID, int requestID, String appID, String authCode, String mdServer, String tdServer, String brokerID, String investorID,
			String password) {
		return m_ctpBridge.start(ctpID, requestID, appID, authCode, mdServer, tdServer, brokerID, investorID, password);
	}

	public int subMarketDatas(int ctpID, int requestID, String code) {
		return m_ctpBridge.subMarketDatas(ctpID, requestID, code);
	}

	public int unSubMarketDatas(int ctpID, int requestID, String codes) {
		return m_ctpBridge.subMarketDatas(ctpID, requestID, codes);
	}
}
