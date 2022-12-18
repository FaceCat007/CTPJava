/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

public class Security {
	// 组合类型
        public String combinationType;
        //创建日
        public String createDate;
        //交割月
        public int deliveryMonth;
        //交割年份
        public int deliveryYear;
        //结束交割日
        public String endDelivDate;
        //交易所代码
        public String exchangeID;
        //合约在交易所的代码
        public String exchangeInstID;
        //到期日
        public String expireDate;
        //保留小数位数
        public int digit;
        //合约生命周期状态
        public String instLifePhase;
        //合约代码
        public String instrumentID;
        //合约名称
        public String instrumentName;
        //当前是否交易
        public String isTrading;
        //多头保证金率
        public double longMarginRatio;
        //限价单最大下单量
        public int maxLimitOrderVolume;
        //是否使用大额单边保证金算法
        public String maxMarginSideAlgorithm;
        //市价单最大下单量
        public int maxMarketOrderVolume;
        //限价单最小下单量
        public int minLimitOrderVolume;
        //市价单最小下单量
        public int minMarketOrderVolume;
        //上市日
        public String openDate;
        //期权类型
        public String optionsType;
        //持仓日期类型
        public String positionDateType;
        //持仓类型
        public String positionType;
        //最小变动价位
        public double priceTick;
        //产品类型
        public String productClass;
        //产品代码
        public String productID;
        //空头保证金率
        public double shortMarginRatio;
        //开始交割日
        public String startDelivDate;
        //执行价
        public double strikePrice;
        //基础商品代码
        public String underlyingInstrID;
        //基础商品名称
        public String underlyingInstrName;
        //合约基础商品乘数
        public double underlyingMultiple;
        //合约数量乘数
        public int volumeMultiple;

	public void getNnderlyingInstrName() {
		int size = instrumentName.length();
		underlyingInstrName = "";
		for (int i = 0; i < size; i++) {
			char sz = instrumentName.charAt(i);
			if (!(sz == '0' || sz == '1' || sz == '2' || sz == '3' || sz == '4' || sz == '5' || sz == '6' || sz == '7'
					|| sz == '8' || sz == '9')) {
				underlyingInstrName += sz;
			}
		}
	}
}
