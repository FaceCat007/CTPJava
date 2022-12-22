/*基于捂脸猫FaceCat框架 v1.0
上海卷卷猫信息技术有限公司
 */

package ctp;

/*
* 资金账号
*/
public class AccountData {
	 // 投资者帐号
        public String accountID = "";
        // 可用资金
        public double available;
        // 期货结算准备金
        public double balance;
        // 经纪公司代码
        public String brokerID = "";
        // 资金差额
        public double cashIn;
        // 平仓盈亏
        public double closeProfit;
        // 手续费
        public double commission;
        // 信用额度
        public double credit;
        // 币种代码
        public String currencyID = "";
        // 当前保证金总额
        public double currMargin;
        // 投资者交割保证金
        public double deliveryMargin;
        // 入金金额
        public double deposit;
        // 动态权益(新增)
        public double dynamicBalance;
        // 交易所交割保证金
        public double exchangeDeliveryMargin;
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
        // 货币质押余额
        public double fundMortgageAvailable;
        // 货币质入金额
        public double fundMortgageIn;
        // 货币质出金额
        public double fundMortgageOut;
        // 利息收入
        public double interest;
        // 利息基数
        public double interestBase;
        // 质押金额
        public double mortgage;
        // 可质押货币金额
        public double mortgageableFund;
        // 持仓盈亏
        public double positionProfit;
        // 上次结算准备金
        public double preBalance;
        // 上次信用额度
        public double preCredit;
        // 上次存款额
        public double preDeposit;
        // 上次货币质入金额
        public double preFundMortgageIn;
        // 上次货币质出金额
        public double preFundMortgageOut;
        // 上次占用的保证金
        public double preMargin;
        // 上次质押金额
        public double preMortgage;
        // 基本准备金
        public double reserve;
        // 保底期货结算准备金
        public double reserveBalance;
        // 风险度(新增)
        public double risk;
        // 结算编号
        public int settlementID;
        // 特殊产品平仓盈亏
        public double specProductCloseProfit;
        // 特殊产品手续费
        public double specProductCommission;
        // 特殊产品交易所保证金
        public double specProductExchangeMargin;
        // 特殊产品冻结手续费
        public double specProductFrozenCommission;
        // 特殊产品冻结保证金
        public double specProductFrozenMargin;
        // 特殊产品占用保证金
        public double specProductMargin;
        // 特殊产品持仓盈亏
        public double specProductPositionProfit;
        // 根据持仓盈亏算法计算的特殊产品持仓盈亏
        public double specProductPositionProfitByAlg;
        // 交易日
        public String tradingDay = "";
        // 出金金额
        public double withdraw;
        // 可取资金
        public double withdrawQuota;
}
