/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

public class MarginRate {
	//经纪公司代码
        public String brokerID;
        //收费方式
        public String calculateMode;
        //看涨看跌标示
        public String callOrPutFlag;
        //合约代码
        public String code;
        //代码
        public String commodityNo;
        //类型
        public String commodityType;
        //合约
        public String contractNo;
        //投机套保标志
        public String hedgeFlag;
        public double initialMargin;
        //投资者代码
        public String investorID;
        //多头保证金率
        public double longMarginRatioByMoney;
        //多头保证金费
        public double longMarginRatioByVolume;
        //投资者范围
        public String investorRange;
        //是否相对交易所收取
        public int isRelativel;
        public double lockMargin;
        public double maintenanceMargin;
        public double sellInitialMargin;
        public double sellMaintenanceMargin;
        //空头保证金率
        public double shortMarginRatioByMoney;
        //空头保证金费
        public double shortMarginRatioByVolume;
        //
        public String strikePrice;
}
