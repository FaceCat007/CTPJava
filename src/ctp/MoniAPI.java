package ctp;
import facecat.topin.service.*;
import facecat.topin.core.*;

/**
 *
 * 模拟交易API
 */
public class MoniAPI {
    /*
        * 调用Http
        * url地址
        */
       public static String callAPI(String url)
       {
           FCHttpGetService getService = new FCHttpGetService();
           return getService.get("http://127.0.0.1:9962?facecatid=app_SimTrading&" + url);
       }

       /*追加资金
       accountid 账户ID
       cash 资金*/
       public static String addFund(String accountid, double cash)
       {
           return callAPI("func=addfund&accountid=" + accountid + "&cash=" + FCTran.doubleToStr(cash));
       }

       /*添加委托
       accountid 账户ID
       code 代码
       name 名称
       ordertype 委托类型
       direction 方向
       price 价格
       volume 数量
       margin 保证金
       返回 委托ID*/
       public static String addOrder(String accountid, String code, String name, String ordertype, String direction, double price, double volume, double margin)
       {
           return callAPI("func=addorder&accountid=" + accountid + "&code=" + code + "&name=" + name + "&ordertype=" + ordertype + "&direction=" + direction + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&margin=" + FCTran.doubleToStr(margin));
       }

       /*添加成交
       accountid 账户ID
       code 代码
       name 名称
       ordertype 委托类型
       orderid 委托ID
       direction 方向
       price 价格
       volume 数量
       amount 数额
       commision #手续费
       返回 成交ID*/
       public static String addTrade(String accountid, String code, String name, String ordertype, String orderid, String direction, double price, double volume, double amount, double commision)
       {
           return callAPI("func=addtrade&accountid=" + accountid + "&code=" + code + "&name=" + name + "&ordertype=" + ordertype + "&orderid=" + "&direction=" + direction + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&amount=" + FCTran.doubleToStr(amount) + "&commision=" + FCTran.doubleToStr(commision));
       }

       /*添加持仓
       accountid 账户ID
       code 代码
       name 名称
       price 价格
       volume 数量
       direction 方向
       margin 保证金
       commision 手续费*/
       public static String addPosition(String accountid, String code, String name, double price, double volume, String direction, double margin, double commision)
       {
           return callAPI("func=addposition&accountid=" + accountid + "&code=" + code + "&name=" + name + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&direction=" + direction + "&margin=" + FCTran.doubleToStr(margin) + "&commision=20" + FCTran.doubleToStr(commision));
       }

       /*买入交易
       accountid 账户ID
       code 代码
       name 名称
       price 数量
       volume 数量
       direction 方向
       margin 保证金
       commision 手续费*/
       public static String buy(String accountid, String code, String name, String ordertype, String direction, double price, double volume, double amount, double margin, double commision)
       {
           return callAPI("func=buy&accountid=" + accountid + "&code=" + code + "&name=" + name + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&ordertype=" + ordertype + "&direction=" + direction + "&margin=" + FCTran.doubleToStr(margin) + "&commision=" + FCTran.doubleToStr(commision));
       }

       /*创建账户*/
       public static String createAccount()
       {
           return callAPI("func=createaccount");
       }

       /*当前账户*/
       public static String getCurrentAccount()
       {
           return callAPI("func=getcurrentaccount");
       }

       /*指定账户
       accountid 账户ID*/
       public static String getFund(String accountid)
       {
           return callAPI("func=getfund&accountid=" + accountid);
       }

       /*所有账户*/
       public static String getAllFunds()
       {
           return callAPI("func=getallfunds");
       }

       /*持仓列表
       accountid 账户ID*/
       public static String getPositionsByAccount(String accountid)
       {
           return callAPI("func=getpositionsbyaccount&accountid=" + accountid);
       }

       /*新的委托
       accountid 账户ID*/
       public static String getNewOrders(String accountid)
       {
           return callAPI("func=getnewtrades&accountid=" + accountid);
       }

       /*新的持仓
       accountid 账户ID*/
       public static String getNewPositions(String accountid)
       {
           return callAPI("func=getnewpositions&accountid=" + accountid);
       }

       /*新的成交
       accountid 账户ID*/
       public static String getNewTrades(String accountid)
       {
           return callAPI("func=getnewtrades&accountid=" + accountid);
       }

       /*委托列表
       accountid 账户ID*/
       public static String getOrdersByAccount(String accountid)
       {
           return callAPI("func=getordersbyaccount&accountid=" + accountid);
       }

       /*成交列表
       accountid 账户ID*/
       public static String getTradesByAccount(String accountid)
       {
           return callAPI("func=gettradesbyaccount&accountid=" + accountid);
       }

       /*锁定界面
       accountid 账户ID*/
       public static String lockUI(String accountid)
       {
           return callAPI("func=lockui&accountid=" + accountid);
       }

       /*移除账户
       accountid 账户ID*/
       public static String removeAccount(String accountid)
       {
           return callAPI("func=removeaccount&accountid=" + accountid);
       }

       /*删除成交
       accountid 账户ID
       tradeid 成交ID*/
       public static String removeTrade(String accountid, String tradeid)
       {
           return callAPI("func=removetrade&accountid=" + accountid + "&tradeid=" + tradeid);
       }

       /*撤销委托
       accountid 账户ID
       orderid 委托ID*/
       public static String removeOrder(String accountid, String orderid)
       {
           return callAPI("func=removeorder&accountid=" + accountid + "&orderid=" + orderid);
       }

       /*减少持仓
       accountid 账户ID
       code 代码
       name 名称
       price 价格
       volume 数量
       direction 方向
       margin 保证金
       commision 手续费*/
       public static String reducePosition(String accountid, String code, String name, double price, double volume, String direction, double margin, double commision)
       {
           return callAPI("func=reduceposition&accountid=" + accountid + "&code=" + code + "&name=" + name + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&direction=" + direction + "&margin=" + FCTran.doubleToStr(margin) + "&commision=20" + FCTran.doubleToStr(commision));
       }

       /*卖出交易
       accountid 账户ID
       code 代码
       name 名称
       price 数量
       volume 数量
       direction 方向
       margin 保证金
       commision 手续费*/
       public static String sell(String accountid, String code, String name, String ordertype, String direction, double price, double volume, double amount, double margin, double commision)
       {
           return callAPI("func=sell&accountid=" + accountid + "&code=" + code + "&name=" + name + "&price=" + FCTran.doubleToStr(price) + "&volume=" + FCTran.doubleToStr(volume) + "&ordertype=" + ordertype + "&direction=" + direction + "&margin=" + FCTran.doubleToStr(margin) + "&commision=" + FCTran.doubleToStr(commision));
       }

       /*更新价格
       accountid 账户ID
       code 代码
       direction #方向
       marketprice #市价
       marketvalue #市值
        */
       public static String setMarketValue(String accountid, String code, String direction, double marketprice, double marketvalue)
       {
           return callAPI("func=setmarketvalue&accountid=" + accountid + "&code=" + code + "&direction=" + direction + "&marketprice=" + FCTran.doubleToStr(marketprice) + "&marketvalue=" + FCTran.doubleToStr(marketvalue));
       }

       /*解锁界面
       accountid 账户ID*/
       public static String unLockUI(String accountid)
       {
           return callAPI("func=unlockui&accountid=" + accountid);
       }
}
