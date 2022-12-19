/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facecat.topin.swing;
import ctp.TradeRecord;
import ctp.SecurityLatestData;
import ctp.InvestorPositionDetail;
import ctp.InvestorPosition;
import ctp.Security;
import ctp.OrderInfo;
import ctp.AccountData;
import facecat.topin.core.*;
import facecat.topin.grid.*;
import facecat.topin.chart.*;
import facecat.topin.btn.*;
import java.util.*;
import ctp.*;

/**
 *
 * @author taode
 */
public class MainFrame extends UIXmlEx implements FCTouchEventCallBack, FCTimerEventCallBack, FCGridCellTouchEventCallBack {
    /*
    * 交易接口
    */
    private FCCTPIMPL m_ctp = new FCCTPIMPL();
 
    /*
    * 交易ID
    */
    private int m_ctpID;
    
    /*
    * 秒表ID
    */
    public int m_timerID = FCView.getNewTimerID();
    
    /*
    * 当前代码
    */
    public String m_currentCode = "";
    
    /*
    * 表格点击方法
    */
    public void callGridCellTouchEvent(String eventName, Object sender, FCGridCell cell, FCTouchInfo touchInfo, Object invoke)
    {
        FCGrid grid = (FCGrid)sender;
        String gridName = grid.getName();
        if (gridName.equals("gridLatestData"))
        {
            FCChart chart = getChart("chart");
            chart.clear();
            chart.update();
            String code = cell.getRow().getCell(0).getString();
            m_currentCode = code;
            String price = cell.getRow().getCell(2).getString();
            FCView txtIssueCode = findView("txtIssueCode");
            FCView spinPrice = findView("spinPrice");
            FCView spinVolume = findView("spinVolume");
            txtIssueCode.setText(code);
            spinPrice.setText(price);
            spinVolume.setText("1");
            getNative().invalidate();
        }
        else if (gridName.equals("gridInvestorPosition"))
        {
            String code = cell.getRow().getCell(0).getString();
            FCView txtIssueCode = findView("txtIssueCode");
            FCView spinPrice = findView("spinPrice");
            FCView spinVolume = findView("spinVolume");
            String price = "";
            if (m_allDatas.containsKey(code))
            {
                price = FCTran.doubleToStr(m_allDatas.get(code).close);
            }
            txtIssueCode.setText(code);
            spinPrice.setText(price);
            spinVolume.setText("1");
            getNative().invalidate();
        }
        else if (gridName.equals("gridInvestorPositionDetail"))
        {
            String code = cell.getRow().getCell(1).getString();
            FCView txtIssueCode = findView("txtIssueCode");
            FCView spinPrice = findView("spinPrice");
            FCView spinVolume = findView("spinVolume");
            String price = "";
            if (m_allDatas.containsKey(code))
            {
                price = FCTran.doubleToStr(m_allDatas.get(code).close);
            }
            txtIssueCode.setText(code);
            spinPrice.setText(price);
            spinVolume.setText("1");
            getNative().invalidate();
        }
    }
    
    /**
     * 秒表事件
     *
     * @param sender 发送者
     * @param timerID 秒表ID
     */
    public void callTimerEvent(String eventName, Object sender, int timerID, Object invoke){
        if(timerID == m_timerID){
            checkCTPData();
        }
    }
    
    /*
    * 运行方法
    */
    public void checkCTPData()
    {
        while (m_ctp.hasNewDatas(m_ctpID) > 0)
        {
            String str = m_ctp.getDepthMarketData(m_ctpID);
            if(str.length() > 1){
                ArrayList<SecurityLatestData> latestDatas = CTPConvert.convertToCTPDepthMarketData(str);
                onSecurityLatestDataCallBack(latestDatas, m_ctpID);
                continue;
            }
            str = m_ctp.getAccountData(m_ctpID);
            if (str.length() > 1)
            {
                AccountData accountData = CTPConvert.convertToCTPTradingAccount(str);
                onAccountDataCallBack(accountData, m_ctpID);
                continue;
            }
            str = m_ctp.getPositionData(m_ctpID);
            if (str.length() > 1)
            {
                ArrayList<InvestorPosition> investorPositions = CTPConvert.convertToCTPInvestorPosition(str);
                onInvestorPositionCallBack(investorPositions, m_ctpID);
                continue;
            }
            str = m_ctp.getPositionDetailData(m_ctpID);
            if (str.length() > 1)
            {
                ArrayList<InvestorPositionDetail> investorPositionDetails = CTPConvert.convertToCTPInvestorPositionDetail(str);
                onInvestorPositionDetailCallBack(investorPositionDetails, m_ctpID);
                continue;
            }
            str = m_ctp.getTradeRecord(m_ctpID);
            if (str.length() > 1)
            {
                TradeRecord tradeRecord = CTPConvert.convertToCTPTrade(str);
                onTradeRecordCallBack(tradeRecord, m_ctpID);
                continue;
            }
            str = m_ctp.getOrderInfo(m_ctpID);
            if (str.length() > 1)
            {
                OrderInfo orderInfo = CTPConvert.convertToCTPOrder(str);
                onOrderInfoCallBack(orderInfo, m_ctpID);
                continue;
            }
        }
    }
    
    /*
    * 登陆到CTP
    */
    public void loginCTP(String appID, String authCode, String mdServer, String tdServer, String brokerID, String investorID, String password)
    {
        try
        {
            m_ctpID = m_ctp.create();
            int reqID = m_ctp.generateReqID(m_ctpID);
            m_ctp.start(m_ctpID, reqID, appID, authCode, mdServer, tdServer, brokerID, investorID, password);
            while (m_ctp.isDataOk(m_ctpID) <= 0)
            {
                try{
                    Thread.sleep(1);
                }catch(Exception ex){
                    
                }
            }
            String str = m_ctp.getDepthMarketData(m_ctpID);
            str = m_ctp.getInstrumentsData(m_ctpID);
            if (str.length() > 1)
            {
                ArrayList<Security> instrumentDatas = CTPConvert.convertToCTPInstrumentDatas(str);
                onSecurityCallBack(instrumentDatas, m_ctpID);
            }
            str = m_ctp.getOrderInfos(m_ctpID);
            if (str.length() > 1)
            {
                ArrayList<OrderInfo> orderInfos = CTPConvert.convertToCTPOrderList(str);
                onOrderInfosCallBack(orderInfos, m_ctpID);
            }
            str = m_ctp.getTradeRecords(m_ctpID);
            if (str.length() > 1)
            {
                ArrayList<TradeRecord> tradeRecords = CTPConvert.convertToCTPTradeRecords(str);
                onTradeRecordsCallBack(tradeRecords, m_ctpID);
            }
            reqID = m_ctp.generateReqID(m_ctpID);
            m_ctp.subMarketDatas(m_ctpID, reqID, "cu2301,cu2302,cu2303,rb2301,rb2302,rb2304,ru2301,ru2302,ru2303");
            FCChart chart = getChart("chart");
            chart.addEvent(this, FCEventID.Timer, this);
            chart.startTimer(m_timerID, 1);
        }
        catch (Exception e)
        {
        }
    }
    
    /*
    * 加载xml
    */
    public void load(String xml){
        super.load(xml);
        
        findView("cbInvestorPosition").addEvent(this, FCEventID.Click, this);
        findView("cbInvestorPositionDetail").addEvent(this, FCEventID.Click, this);
        findView("btnOrder").addEvent(this, FCEventID.Click, this);
        findView("btnCancelOrder2").addEvent(this, FCEventID.Click, this);

        getGrid("gridLatestData").addEvent(this, FCEventID.GridCellClick, this);
        getGrid("gridInvestorPosition").addEvent(this, FCEventID.GridCellClick, this);
        getGrid("gridInvestorPositionDetail").addEvent(this, FCEventID.GridCellClick, this);

        FCChart chart = getChart("chart");
        chart.setRightVScaleWidth(80);
        chart.setLeftVScaleWidth(0);

        ChartDiv candleDiv = chart.addDiv(60);
        candleDiv.getHScale().setHeight(0);
        candleDiv.getHScale().setVisible(false);
        candleDiv.setBackColor(FCColor.Back);
        ChartDiv volDiv = chart.addDiv(40);
        volDiv.getHScale().setHeight(20);
        volDiv.getHScale().setVisible(true);
        volDiv.setBackColor(FCColor.Back);
        candleDiv.getTitleBar().m_titles.add(new ChartTitle(0, "CLOSE", FCColor.rgb(255, 255, 255), 0, true));
        volDiv.getTitleBar().m_titles.add(new ChartTitle(1, "VOL", FCColor.rgb(255, 255, 255), 0, true));

        FCDataTable dataSource = chart.getDataSource();
        dataSource.addColumn(0);
        dataSource.addColumn(1);

        PolylineShape ps = new PolylineShape();
        ps.setFieldName(0);
        ps.setFieldText("CLOSE");
        candleDiv.addShape(ps);

        BarShape bs = new BarShape();
        bs.setFieldName(1);
        bs.setFieldText("VOL");
        volDiv.addShape(bs);
        loginCTP("simnow_client_test", "0000000000000000", "180.168.146.187:10212", "180.168.146.187:10202", "9999", "021739", "123456");
    }
    
    /*
    * 点击事件
    */
    public void callTouchEvent(String eventName, Object sender, FCTouchInfo touchInfo, Object invoke)
    {
        if (touchInfo.m_firstTouch && touchInfo.m_clicks == 1)
        {
            FCView control = (FCView)sender;
            String name = control.getName();
            if (name.equals("cbInvestorPosition"))
            {
                FCGrid gridInvestorPosition = getGrid("gridInvestorPosition");
                FCGrid gridInvestorPositionDetail = getGrid("gridInvestorPositionDetail");
                gridInvestorPosition.m_visible = true;
                gridInvestorPositionDetail.m_visible = false;
                gridInvestorPosition.update();
                gridInvestorPosition.invalidate();
            }
            else if (name.equals("cbInvestorPositionDetail"))
            {
                FCGrid gridInvestorPosition = getGrid("gridInvestorPosition");
                FCGrid gridInvestorPositionDetail = getGrid("gridInvestorPositionDetail");
                gridInvestorPosition.m_visible = false;
                gridInvestorPositionDetail.m_visible = true;
                gridInvestorPositionDetail.update();
                gridInvestorPositionDetail.invalidate();
            }
            else if (name.equals("btnOrder"))
            {
                FCView txtIssueCode = findView("txtIssueCode");
                FCView spinVolume = findView("spinVolume");
                FCView spinPrice = findView("spinPrice");
                FCRadioButton rbBuy = getRadioButton("rbBuy");
                FCRadioButton rbSell = getRadioButton("rbSell");
                FCRadioButton rbOpen = getRadioButton("rbOpen");
                FCRadioButton rbCloseToday = getRadioButton("rbCloseToday");
                FCRadioButton rbClose = getRadioButton("rbClose");
                String issueCode = txtIssueCode.getText();
                if (m_allCodes.containsKey(issueCode))
                {
                    String exchangeID = m_allCodes.get(issueCode).exchangeID;
                    int volume = FCTran.strToInt(spinVolume.getText());
                    double price = FCTran.strToDouble(spinPrice.getText());
                    String bCode = issueCode;
                    String bExchangeID = exchangeID;
                    double bPrice = price;
                    int bVolume = volume;
                    char bCondition = '3';
                    if (rbBuy.isChecked())
                    {
                        if (rbOpen.isChecked())
                        {
                            m_ctp.bidOpen(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                        else if (rbCloseToday.isChecked())
                        {
                            m_ctp.bidCloseToday(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                        else if (rbClose.isChecked())
                        {
                            m_ctp.bidClose(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                    }
                    else
                    {
                        if (rbOpen.isChecked())
                        {
                            m_ctp.askOpen(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                        else if (rbCloseToday.isChecked())
                        {
                            m_ctp.askCloseToday(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                        else if (rbClose.isChecked())
                        {
                            m_ctp.askClose(m_ctpID, m_ctp.generateReqID(m_ctpID), bCode, bExchangeID, bPrice, bVolume, bCondition, "");
                        }
                    }
                }
            }else if (name.equals("btnCancelOrder2"))
            {
                FCGrid gridOrder = getGrid("gridOrder");
                ArrayList<FCGridRow> selectedRows = gridOrder.getSelectedRows();
                if (selectedRows.size() > 0)
                {
                    String orderSysID = selectedRows.get(0).getCell(0).getString();
                    String exchangeID = selectedRows.get(0).getCell(16).getString();
                    m_ctp.cancelOrder(m_ctpID, m_ctp.generateReqID(m_ctpID), exchangeID, orderSysID, "");
                }
            }
        }
    }
    
    /*
    * 资金账户信息回调
    */
    public void onAccountDataCallBack(AccountData data, int ctpID)
    {
        FCGrid gridTradeAccount = getGrid("gridTradeAccount");
        gridTradeAccount.m_headerHeight = 30;
        gridTradeAccount.m_showHScrollBar = true;
        gridTradeAccount.getRowStyle().setSelectedBackColor(FCColor.None);
        gridTradeAccount.setGridLineColor(FCColor.None);
        for (int i = 0; i < gridTradeAccount.m_columns.size(); i++)
        {
            gridTradeAccount.m_columns.get(i).m_allowSort = false;
            gridTradeAccount.m_columns.get(i).setWidth(140);
        }
        FCGridRow row = null;
        if (gridTradeAccount.m_rows.size() == 0)
        {
            row = new FCGridRow();
            row.setHeight(30);
            gridTradeAccount.addRow(row);
            for (int i = 0; i < gridTradeAccount.m_columns.size(); i++)
            {
                FCGridStringCell cell1 = new FCGridStringCell();
                row.addCell(i, cell1);
            }
        }
        gridTradeAccount.m_rows.get(0).m_cells.get(0).setString(FCTran.getValueByDigit(data.balance, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(1).setString(FCTran.getValueByDigit(data.closeProfit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(2).setString(FCTran.getValueByDigit(data.floatProfit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(3).setString(FCTran.getValueByDigit(data.positionProfit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(4).setString(FCTran.getValueByDigit(data.dynamicBalance, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(5).setString(FCTran.getValueByDigit(data.currMargin, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(6).setString(FCTran.getValueByDigit(data.frozenCash, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(7).setString(FCTran.getValueByDigit(data.available, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(8).setString(FCTran.getValueByDigit(data.risk * 100, 2) + "%");
        gridTradeAccount.m_rows.get(0).m_cells.get(9).setString(FCTran.getValueByDigit(data.frozenMargin, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(10).setString(FCTran.getValueByDigit(data.frozenCommission, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(11).setString(FCTran.getValueByDigit(data.commission, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(12).setString(FCTran.getValueByDigit(data.preBalance, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(13).setString(FCTran.getValueByDigit(data.preCredit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(14).setString(FCTran.getValueByDigit(data.preMortgage, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(15).setString(FCTran.getValueByDigit(data.mortgage, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(16).setString(FCTran.getValueByDigit(data.withdraw, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(17).setString(FCTran.getValueByDigit(data.deposit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(18).setString(FCTran.getValueByDigit(data.credit, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(19).setString(FCTran.getValueByDigit(data.reserveBalance, 0));
        gridTradeAccount.m_rows.get(0).m_cells.get(20).setString(FCTran.getValueByDigit(data.withdrawQuota, 0));
        gridTradeAccount.update();
        gridTradeAccount.invalidate();
    }
    
    /*
    * 持仓数据回调
    */
    public void onInvestorPositionCallBack(ArrayList<InvestorPosition> data, int ctpID)
    {
        FCGrid gridInvestorPosition = getGrid("gridInvestorPosition");
        gridInvestorPosition.setGridLineColor(FCColor.None);
        for (int i = 0; i < data.size(); i++)
        {
            FCGridRow row = null;
            for (int j = 0; j < gridInvestorPosition.m_rows.size(); j++)
            {
                if ((gridInvestorPosition.m_rows.get(j).m_cells.get(0).getString() + gridInvestorPosition.m_rows.get(j).m_cells.get(1).getString()).equals(data.get(i).code + data.get(i).posiDirection))
                {
                    row = gridInvestorPosition.m_rows.get(j);
                    break;
                }
            }
            if (row == null)
            {
                row = new FCGridRow();
                row.setHeight(30);
                gridInvestorPosition.addRow(row);
                for (int j = 0; j < gridInvestorPosition.m_columns.size(); j++)
                {
                    FCGridStringCell cell1 = new FCGridStringCell();
                    row.addCell(j, cell1);
                }
            }
            row.m_cells.get(0).setString(data.get(i).code);
            row.m_cells.get(1).setString(data.get(i).posiDirection);
            row.m_cells.get(2).setString(FCTran.intToStr(data.get(i).ydPosition + data.get(i).todayPosition));
            row.m_cells.get(3).setString(FCTran.intToStr(data.get(i).ydPosition));
            row.m_cells.get(4).setString(FCTran.intToStr(data.get(i).todayPosition));
            row.m_cells.get(5).setString("0");
            row.m_cells.get(6).setString(FCTran.getValueByDigit(data.get(i).positionCost, 0));
            row.m_cells.get(7).setString(FCTran.getValueByDigit(data.get(i).positionProfit, 0));
            row.m_cells.get(8).setString(FCTran.getValueByDigit(data.get(i).margin, 0));
            row.m_cells.get(9).setString(data.get(i).hedgeFlag);
            row.m_cells.get(10).setString(data.get(i).code);
            row.m_cells.get(11).setString("0");
            row.m_cells.get(12).setString("0");
            row.m_cells.get(13).setString("0");
            row.m_cells.get(14).setString("0");
            row.m_cells.get(15).setString("0");
            row.m_cells.get(16).setString("0");
            row.m_cells.get(17).setString("0");
            row.m_cells.get(18).setString("0");
            row.m_cells.get(19).setString("0");
            row.m_cells.get(20).setString("0");
            row.m_cells.get(21).setString("0");
            row.m_cells.get(22).setString("0");
            row.m_cells.get(23).setString("0");
            row.m_cells.get(24).setString("0");
            row.m_cells.get(25).setString("0");
            row.m_cells.get(26).setString("0");
            row.m_cells.get(27).setString("0");
            row.m_cells.get(28).setString("0");
            row.m_cells.get(29).setString("0");
            row.m_cells.get(30).setString("0");
        }
        int rowsSize = gridInvestorPosition.m_rows.size();
        for (int i = 0; i < rowsSize; i++)
        {
            FCGridRow row = gridInvestorPosition.m_rows.get(i);
            if (row.getCell(2).getString().equals("0"))
            {
                gridInvestorPosition.removeRow(row);
                row.delete();
                i--;
                rowsSize--;
            }
        }
        gridInvestorPosition.update();
        gridInvestorPosition.invalidate();
    }

    /*
    * 持仓数据回调
    */
    public void onInvestorPositionDetailCallBack(ArrayList<InvestorPositionDetail> data, int ctpID)
    {
        FCGrid gridInvestorPositionDetail = getGrid("gridInvestorPositionDetail");
        gridInvestorPositionDetail.setGridLineColor(FCColor.None);
        for (int i = 0; i < data.size(); i++)
        {
            FCGridRow row = null;
            for (int j = 0; j < gridInvestorPositionDetail.m_rows.size(); j++)
            {
                if (gridInvestorPositionDetail.m_rows.get(j).m_cells.get(0).getString().equals(data.get(i).tradeID))
                {
                    row = gridInvestorPositionDetail.m_rows.get(j);
                    break;
                }
            }
            if (row == null)
            {
                row = new FCGridRow();
                row.setHeight(30);
                gridInvestorPositionDetail.addRow(row);
                for (int j = 0; j < gridInvestorPositionDetail.m_columns.size(); j++)
                {
                    FCGridStringCell cell1 = new FCGridStringCell();
                    row.addCell(j, cell1);
                }
            }
            row.m_cells.get(0).setString(data.get(i).tradeID);
            row.m_cells.get(1).setString(data.get(i).code);
            row.m_cells.get(2).setString(data.get(i).direction);
            row.m_cells.get(3).setString(FCTran.getValueByDigit(data.get(i).volume, 0));
            row.m_cells.get(4).setString(FCTran.getValueByDigit(data.get(i).openPrice, 0));
            row.m_cells.get(5).setString(FCTran.getValueByDigit(data.get(i).margin, 0));
            row.m_cells.get(6).setString(data.get(i).tradeType);
            row.m_cells.get(7).setString(data.get(i).hedgeFlag);
            row.m_cells.get(8).setString(data.get(i).openDate);
            row.m_cells.get(9).setString(FCTran.getValueByDigit(data.get(i).positionProfit, 0));
            row.m_cells.get(10).setString(FCTran.getValueByDigit(data.get(i).closeProfitByTrade, 0));
            row.m_cells.get(11).setString(data.get(i).exchangeID);
            row.m_cells.get(12).setString("普通持仓");
            row.m_cells.get(13).setString(FCTran.getValueByDigit(data.get(i).preSettlementPrice, 0));
            row.m_cells.get(14).setString(FCTran.getValueByDigit(data.get(i).closeVolume, 0));
            row.m_cells.get(15).setString(FCTran.getValueByDigit(data.get(i).floatProfit, 0));
            row.m_cells.get(16).setString(FCTran.getValueByDigit(data.get(i).openPrice, 0));
            row.m_cells.get(17).setString(data.get(i).combInstrumentID);
        }
        int rowsSize = gridInvestorPositionDetail.m_rows.size();
            for (int i = 0; i < rowsSize; i++)
            {
                FCGridRow row = gridInvestorPositionDetail.m_rows.get(i);
                if (row.getCell(3).getString().equals("0"))
                {
                    gridInvestorPositionDetail.removeRow(row);
                    row.delete();
                    i--;
                    rowsSize--;
                }
            }
        gridInvestorPositionDetail.update();
        gridInvestorPositionDetail.invalidate();
    }


    /*
    * 推送的委托回报回调
    */
    public void onOrderInfoCallBack(OrderInfo data, int ctpID)
    {
        if (data.orderStatus.equals("未知"))
        {
            return;
        }
        FCGrid gridOrder = getGrid("gridOrder");
        gridOrder.setGridLineColor(FCColor.None);
        FCGridRow row = null;
        for (int i = 0; i < gridOrder.m_rows.size(); i++)
        {
            if (gridOrder.m_rows.get(i).m_cells.get(0).getString().equals(data.orderSysID))
            {
                row = gridOrder.m_rows.get(i);
                break;
            }
        }
        if (row == null)
        {
            row = new FCGridRow();
            row.setHeight(30);
            gridOrder.insertRow(0, row);
            for (int j = 0; j < gridOrder.m_columns.size(); j++)
            {
                FCGridStringCell cell1 = new FCGridStringCell();
                row.addCell(j, cell1);
            }
        }
        row.m_cells.get(0).setString(data.orderSysID);
        row.m_cells.get(1).setString(data.code);
        row.m_cells.get(2).setString(data.direction);
        row.m_cells.get(3).setString(data.combOffsetFlag);
        row.m_cells.get(4).setString(data.orderStatus);
        row.m_cells.get(5).setString(FCTran.getValueByDigit(data.limitPrice, 0));
        row.m_cells.get(6).setString(FCTran.doubleToStr(data.volumeTotalOriginal));
        row.m_cells.get(7).setString(FCTran.doubleToStr(data.volumeTotal));
        row.m_cells.get(8).setString(FCTran.doubleToStr(data.volumeTotal));
        row.m_cells.get(9).setString(FCTran.doubleToStr(data.volumeTraded));
        row.m_cells.get(10).setString(data.insertTime);
        row.m_cells.get(11).setString(data.updateTime);
        row.m_cells.get(12).setString(FCTran.getValueByDigit(data.limitPrice, 0));
        row.m_cells.get(13).setString("0");
        row.m_cells.get(14).setString("0");
        row.m_cells.get(15).setString(data.combHedgeFlag);
        row.m_cells.get(16).setString(data.exchangeID);
        row.m_cells.get(17).setString(data.orderPriceType);
        row.m_cells.get(18).setString(data.timeCondition);
        row.m_cells.get(19).setString(data.orderType);
        row.m_cells.get(20).setString(FCTran.doubleToStr(data.userForceClose));
        row.m_cells.get(21).setString(data.forceCloseReason);
        row.m_cells.get(22).setString("");
        row.m_cells.get(23).setString("");
        gridOrder.update();
        gridOrder.invalidate();
    }

    /*
    * 主动查询的委托回报回调
    */
    public void onOrderInfosCallBack(ArrayList<OrderInfo> data, int ctpID)
    {
         FCGrid gridOrder = getGrid("gridOrder");
        gridOrder.setGridLineColor(FCColor.None);
        for (int i = 0; i < data.size(); i++)
        {
            if (data.get(i).orderStatus.equals("未知"))
            {
                return;
            }
            FCGridRow row = null;
            for (int j = 0; j < gridOrder.m_rows.size(); j++)
            {
                if (gridOrder.m_rows.get(j).m_cells.get(i).getString().equals(data.get(i).orderSysID))
                {
                    row = gridOrder.m_rows.get(j);
                    break;
                }
            }
            if (row == null)
            {
                row = new FCGridRow();
                row.setHeight(30);
                gridOrder.insertRow(0, row);
                for (int j = 0; j < gridOrder.m_columns.size(); j++)
                {
                    FCGridStringCell cell1 = new FCGridStringCell();
                    row.addCell(j, cell1);
                }
            }
            row.m_cells.get(0).setString(data.get(i).orderSysID);
            row.m_cells.get(1).setString(data.get(i).code);
            row.m_cells.get(2).setString(data.get(i).direction);
            row.m_cells.get(3).setString(data.get(i).combOffsetFlag);
            row.m_cells.get(4).setString(data.get(i).orderStatus);
            row.m_cells.get(5).setString(FCTran.getValueByDigit(data.get(i).limitPrice, 0));
            row.m_cells.get(6).setString(FCTran.doubleToStr(data.get(i).volumeTotalOriginal));
            row.m_cells.get(7).setString(FCTran.doubleToStr(data.get(i).volumeTotal));
            row.m_cells.get(8).setString(FCTran.doubleToStr(data.get(i).volumeTotal));
            row.m_cells.get(9).setString(FCTran.doubleToStr(data.get(i).volumeTraded));
            row.m_cells.get(10).setString(data.get(i).insertTime);
            row.m_cells.get(11).setString(data.get(i).updateTime);
            row.m_cells.get(12).setString(FCTran.getValueByDigit(data.get(i).limitPrice, 0));
            row.m_cells.get(13).setString("0");
            row.m_cells.get(14).setString("0");
            row.m_cells.get(15).setString(data.get(i).combHedgeFlag);
            row.m_cells.get(16).setString(data.get(i).exchangeID);
            row.m_cells.get(17).setString(data.get(i).orderPriceType);
            row.m_cells.get(18).setString(data.get(i).timeCondition);
            row.m_cells.get(19).setString(data.get(i).orderType);
            row.m_cells.get(20).setString(FCTran.doubleToStr(data.get(i).userForceClose));
            row.m_cells.get(21).setString(data.get(i).forceCloseReason);
            row.m_cells.get(22).setString("");
            row.m_cells.get(23).setString("");
        }
        gridOrder.update();
        gridOrder.invalidate();

    }

    public HashMap<String, Security> m_allCodes = new HashMap<String, Security>();
    
    public HashMap<String, SecurityLatestData> m_allDatas = new HashMap<String, SecurityLatestData>();

    /*
    * 码表回调
    */
    public void onSecurityCallBack(ArrayList<Security> data, int ctpID)
    {
        FCGrid contractGrid = getGrid("gridContracts");
        contractGrid.setGridLineColor(FCColor.None);
        for (int i = 0; i < data.size(); i++)
        {
            m_allCodes.put(data.get(i).instrumentID, data.get(i));
            FCGridRow row = new FCGridRow();
            row.setHeight(30);
            contractGrid.addRow(row);
            FCGridStringCell cell1 = new FCGridStringCell();
            cell1.setString(data.get(i).productID);
            row.addCell(0, cell1);
            FCGridStringCell cell2 = new FCGridStringCell();
            cell2.setString(data.get(i).instrumentID);
            row.addCell(0, cell2);
            FCGridStringCell cell3 = new FCGridStringCell();
            cell3.setString(data.get(i).instrumentName);
            row.addCell(0, cell3);
            FCGridStringCell cell4 = new FCGridStringCell();
            cell4.setString(data.get(i).exchangeID);
            row.addCell(0, cell4);
            FCGridStringCell cell5 = new FCGridStringCell();
            cell5.setString(FCTran.doubleToStr(data.get(i).volumeMultiple));
            row.addCell(0, cell5);
            FCGridStringCell cell6 = new FCGridStringCell();
            cell6.setString(FCTran.doubleToStr(data.get(i).priceTick));
            row.addCell(0, cell6);
            FCGridStringCell cell7 = new FCGridStringCell();
            cell7.setString("期货");
            row.addCell(0, cell7);
            FCGridStringCell cell8 = new FCGridStringCell();
            cell8.setString(data.get(i).expireDate);
            row.addCell(0, cell8);
            FCGridStringCell cell9 = new FCGridStringCell();
            cell9.setString(FCTran.doubleToStr(data.get(i).longMarginRatio));
            row.addCell(0, cell9);
            FCGridStringCell cell10 = new FCGridStringCell();
            cell10.setString(FCTran.doubleToStr(data.get(i).shortMarginRatio));
            row.addCell(0, cell10);
            FCGridStringCell cell11 = new FCGridStringCell();
            cell11.setString("0");
            row.addCell(0, cell11);
            FCGridStringCell cell12 = new FCGridStringCell();
            cell12.setString("0");
            row.addCell(0, cell12);
            FCGridStringCell cell13 = new FCGridStringCell();
            cell13.setString("0");
            row.addCell(0, cell13);
            FCGridStringCell cell14 = new FCGridStringCell();
            cell14.setString("0");
            row.addCell(0, cell14);
            FCGridStringCell cell15 = new FCGridStringCell();
            cell15.setString("0");
            row.addCell(0, cell15);
            FCGridStringCell cell16 = new FCGridStringCell();
            cell16.setString("0");
            row.addCell(0, cell16);
            FCGridStringCell cell17 = new FCGridStringCell();
            cell17.setString(FCTran.doubleToStr(data.get(i).maxMarketOrderVolume));
            row.addCell(0, cell17);
            FCGridStringCell cell18 = new FCGridStringCell();
            cell18.setString(FCTran.doubleToStr(data.get(i).maxLimitOrderVolume));
            row.addCell(0, cell18);
        }
        contractGrid.invalidate();
        contractGrid.invalidate();
    }

    public void setCellStyle2(FCGridCell cell, Object value1, Object value2)
    {
        double num1 = 0;
        if (value1 != null)
        {
            num1 = FCTran.strToDouble(value1.toString());
        }
        double num2 = 0;
        if (value2 != null)
        {
            num2 = FCTran.strToDouble(value2.toString());
        }
        long color = FCColor.rgb(255, 255, 255);
        if (num1 > num2)
        {
            color = FCColor.rgb(255, 0, 0);
        }
        else if (num1 < num2)
        {
            color = FCColor.rgb(0, 255, 0);
        }
        FCGridCellStyle style = cell.m_style;
        if (style == null)
        {
            style = new FCGridCellStyle();
            cell.setStyle(style);
        }
        style.m_textColor = color;
    }


    /*
    * 最新数据回调
    */
    public void onSecurityLatestDataCallBack(ArrayList<SecurityLatestData> data, int ctpID)
    {
        FCGrid gridLatestData = getGrid("gridLatestData");
        gridLatestData.setGridLineColor(FCColor.None);
        FCChart chart = getChart("chart");
        FCDataTable dataSource = chart.getDataSource();
        for (int d = 0; d < data.size(); d++)
        {
            FCGridRow row = null;
            for (int i = 0; i < gridLatestData.m_rows.size(); i++)
            {
                if (gridLatestData.m_rows.get(i).m_cells.get(0).getString().equals(data.get(d).code))
                {
                    row = gridLatestData.m_rows.get(i);
                    break;
                }
            }
            if (row == null)
            {
                row = new FCGridRow();
                row.setHeight(30);
                gridLatestData.addRow(row);
                for (int j = 0; j < gridLatestData.m_columns.size(); j++)
                {
                    FCGridStringCell cell1 = new FCGridStringCell();
                    row.addCell(j, cell1);
                }
            }
            row.m_cells.get(0).setString(data.get(d).code);
            double newVol = 0;
            if (m_allDatas.containsKey(data.get(d).code))
            {
                newVol = data.get(d).volume - m_allDatas.get(data.get(d).code).volume;
            }
            m_allDatas.put(data.get(d).code, data.get(d));
            int digit = 0;
            if (m_allCodes.containsKey(data.get(d).code))
            {
                row.m_cells.get(1).setString(m_allCodes.get(data.get(d).code).instrumentName);
                digit = m_allCodes.get(data.get(d).code).digit;
            }
            setCellStyle2(row.m_cells.get(2), row.m_cells.get(2).getString(), data.get(d).close);
            row.m_cells.get(2).setString(FCTran.getValueByDigit(data.get(d).close, digit));
            double diff = data.get(d).close - data.get(d).preSettlementPrice;
            setCellStyle2(row.m_cells.get(3), diff, 0);
            row.m_cells.get(3).setString(FCTran.getValueByDigit(diff, digit));
            setCellStyle2(row.m_cells.get(4), row.m_cells.get(4).getString(), data.get(d).bidPrice1);
            row.m_cells.get(4).setString(FCTran.getValueByDigit(data.get(d).bidPrice1, digit));
            row.m_cells.get(5).setString(FCTran.doubleToStr(data.get(d).bidVolume1));
            setCellStyle2(row.m_cells.get(6), row.m_cells.get(6).getString(), data.get(d).askPrice1);
            row.m_cells.get(6).setString(FCTran.getValueByDigit(data.get(d).askPrice1, digit));
            row.m_cells.get(7).setString(FCTran.doubleToStr(data.get(d).askVolume1));
            row.m_cells.get(8).setString(FCTran.doubleToStr(data.get(d).volume));
            row.m_cells.get(9).setString(FCTran.getValueByDigit(data.get(d).openInterest, digit));
            row.m_cells.get(10).setString(FCTran.getValueByDigit(data.get(d).upperLimit, digit));
            row.m_cells.get(11).setString(FCTran.getValueByDigit(data.get(d).lowerLimit, digit));
            setCellStyle2(row.m_cells.get(12), row.m_cells.get(12).getString(), data.get(d).open);
            row.m_cells.get(12).setString(FCTran.getValueByDigit(data.get(d).open, digit));
            row.m_cells.get(13).setString(FCTran.getValueByDigit(data.get(d).preSettlementPrice, digit));
            setCellStyle2(row.m_cells.get(14), row.m_cells.get(14).getString(), data.get(d).high);
            row.m_cells.get(14).setString(FCTran.getValueByDigit(data.get(d).high, digit));
            setCellStyle2(row.m_cells.get(15), row.m_cells.get(15).getString(), data.get(d).low);
            row.m_cells.get(15).setString(FCTran.getValueByDigit(data.get(d).low, digit));
            row.m_cells.get(16).setString(FCTran.doubleToStr(data.get(d).bidVolume1));
            double rangeValue = 0;
            if (data.get(d).preSettlementPrice != 0)
            {
                rangeValue = 100 * (data.get(d).close - data.get(d).preSettlementPrice) / data.get(d).preSettlementPrice;
            }
            row.m_cells.get(17).setString(FCTran.getValueByDigit(rangeValue, 2) + "%");
            setCellStyle2(row.m_cells.get(17), rangeValue, 0);
            row.m_cells.get(18).setString(FCTran.getValueByDigit(data.get(d).preClose, digit));
            row.m_cells.get(19).setString(FCTran.doubleToStr(data.get(d).turnover));
            row.m_cells.get(20).setString(data.get(d).exchangeID);
            row.m_cells.get(21).setString(data.get(d).updateTime);
            row.m_cells.get(22).setString(FCTran.getValueByDigit(data.get(d).preOpenInterest, digit));
            row.m_cells.get(23).setString(FCTran.getValueByDigit(data.get(d).close, digit));
            row.m_cells.get(24).setString(FCTran.getValueByDigit(data.get(d).settlementPrice, digit));
            row.m_cells.get(25).setString(FCTran.getValueByDigit(data.get(d).averagePrice, digit));
            row.m_cells.get(26).setString("0");
            row.m_cells.get(27).setString("0");
            row.m_cells.get(28).setString("0");
            row.m_cells.get(29).setString("0");
            row.m_cells.get(30).setString("0");
            row.m_cells.get(31).setString("0");
            if (newVol > 0)
            {
                if (data.get(d).code.equals(m_currentCode))
                {
                    int rowsSize = dataSource.getRowsCount();
                    dataSource.set(rowsSize + 1, 0, data.get(d).close);
                    dataSource.set(rowsSize + 1, 1, newVol);
                    chart.update();
                    chart.invalidate();
                }
            }
        }
        gridLatestData.update();
        gridLatestData.invalidate();
    }

    /*
    * 推送的交易信息回调
    */
    public void onTradeRecordCallBack(TradeRecord data, int ctpID)
    {
        FCGrid gridTradeRecord = getGrid("gridTradeRecord");
        gridTradeRecord.setGridLineColor(FCColor.None);
        FCGridRow row = new FCGridRow();
        row.setHeight(30);
        gridTradeRecord.insertRow(0, row);
        FCGridStringCell cell1 = new FCGridStringCell();
        cell1.setString(data.tradeID);
        row.addCell(0, cell1);
        FCGridStringCell cell2 = new FCGridStringCell();
        cell2.setString(data.code);
        row.addCell(1, cell2);
        FCGridStringCell cell3 = new FCGridStringCell();
        cell3.setString(data.direction);
        row.addCell(2, cell3);
        FCGridStringCell cell4 = new FCGridStringCell();
        cell4.setString(data.offsetFlag);
        row.addCell(3, cell4);
        FCGridStringCell cell5 = new FCGridStringCell();
        cell5.setString(FCTran.getValueByDigit(data.price, 0));
        row.addCell(4, cell5);
        FCGridStringCell cell6 = new FCGridStringCell();
        cell6.setString(FCTran.getValueByDigit(data.volume, 0));
        row.addCell(5, cell6);
        FCGridStringCell cell7 = new FCGridStringCell();
        cell7.setString(data.tradeTime);
        row.addCell(6, cell7);
        FCGridStringCell cell8 = new FCGridStringCell();
        cell8.setString(data.orderSysID);
        row.addCell(7, cell8);
        FCGridStringCell cell9 = new FCGridStringCell();
        cell9.setString("普通成交");
        row.addCell(8, cell9);
        FCGridStringCell cell10 = new FCGridStringCell();
        cell10.setString(data.hedgeFlag);
        row.addCell(9, cell10);
        FCGridStringCell cell11 = new FCGridStringCell();
        cell11.setString(data.exchangeID);
        row.addCell(10, cell11);
        FCGridStringCell cell12 = new FCGridStringCell();
        cell12.setString(FCTran.doubleToStr(data.commission));
        row.addCell(11, cell12);
        gridTradeRecord.update();
        gridTradeRecord.invalidate();
    }

    /*
    * 主动查询的交易信息回调
    */
    public void onTradeRecordsCallBack(ArrayList<TradeRecord> data, int ctpID)
    {
        FCGrid gridTradeRecord = getGrid("gridTradeRecord");
        gridTradeRecord.setGridLineColor(FCColor.None);
        for (int i = 0; i < data.size(); i++)
        {
            FCGridRow row = new FCGridRow();
            row.setHeight(30);
            gridTradeRecord.insertRow(0, row);
            FCGridStringCell cell1 = new FCGridStringCell();
            cell1.setString(data.get(i).tradeID);
            row.addCell(0, cell1);
            FCGridStringCell cell2 = new FCGridStringCell();
            cell2.setString(data.get(i).code);
            row.addCell(1, cell2);
            FCGridStringCell cell3 = new FCGridStringCell();
            cell3.setString(data.get(i).direction);
            row.addCell(2, cell3);
            FCGridStringCell cell4 = new FCGridStringCell();
            cell4.setString(data.get(i).offsetFlag);
            row.addCell(3, cell4);
            FCGridStringCell cell5 = new FCGridStringCell();
            cell5.setString(FCTran.getValueByDigit(data.get(i).price, 0));
            row.addCell(4, cell5);
            FCGridStringCell cell6 = new FCGridStringCell();
            cell6.setString(FCTran.getValueByDigit(data.get(i).volume, 0));
            row.addCell(5, cell6);
            FCGridStringCell cell7 = new FCGridStringCell();
            cell7.setString(data.get(i).tradeTime);
            row.addCell(6, cell7);
            FCGridStringCell cell8 = new FCGridStringCell();
            cell8.setString(data.get(i).orderSysID);
            row.addCell(7, cell8);
            FCGridStringCell cell9 = new FCGridStringCell();
            cell9.setString("普通成交");
            row.addCell(8, cell9);
            FCGridStringCell cell10 = new FCGridStringCell();
            cell10.setString(data.get(i).hedgeFlag);
            row.addCell(9, cell10);
            FCGridStringCell cell11 = new FCGridStringCell();
            cell11.setString(data.get(i).exchangeID);
            row.addCell(10, cell11);
            FCGridStringCell cell12 = new FCGridStringCell();
            cell12.setString(FCTran.doubleToStr(data.get(i).commission));
            row.addCell(11, cell12);
        }
        gridTradeRecord.update();
        gridTradeRecord.invalidate();
    }
}
