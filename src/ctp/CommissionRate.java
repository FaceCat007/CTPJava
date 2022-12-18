/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

public class CommissionRate {
	//经纪公司代码
        public String brokerID;
        //收费方式
        public String calculateMode;
        //平仓手续费率
        public double closeRatioByMoney;
        //平仓手续费
        public double closeRatioByVolume;
        //平今手续费率
        public double closeTodayRatioByMoney;
        //平今手续费
        public double closeTodayRatioByVolume;
        //平今费
        public double closeTodayFee;
        //合约代码
        public String code;
        //代码
        public String commodityNo;
        //类型
        public String commodityType;
        //交易所编号
        public String exchangeNo;
        //投资者代码
        public String investorID;
        //投资者范围
        public String investorRange;
        //来源
        public String matchSource;
        //开平费
        public double openCloseFee;
        //开仓手续费率
        public double openRatioByMoney;
        //开仓手续费
        public double openRatioByVolume;
}
