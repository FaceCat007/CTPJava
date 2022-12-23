package facecat.topin.swing;

import facecat.topin.core.*;
import java.util.*;
import javax.swing.*;
import facecat.topin.btn.*;
import facecat.topin.grid.*;
import facecat.topin.scroll.*;
import facecat.topin.div.*;
import facecat.topin.input.*;
import facecat.topin.tab.*;
import java.awt.*;

/*
* 设备对接
*/
public class SwingHost extends FCHost implements Runnable {
    /*
    * 构造函数
    */
    public SwingHost() {

    }
    
    private long sysmtime = 0;
    
    /*
    * 裁剪区域
    */
    private ArrayList<FCRect> m_clipBounds = new ArrayList<FCRect>();

    /*
    * 鼠标坐标
    */
    private FCPoint m_mousePoint = new FCPoint();

    /*
    * 线程状态
    */
    public int m_threadState = 0;

    /*
    * 秒表信息
    */
    private HashMap<Integer, SwingTimer> m_timers = new HashMap<>();

    private boolean m_allowOperate = true;

    /*
    * 允许操作
    */
    public final boolean allowOperate() {
        return m_allowOperate;
    }

    /*
    * 设置是否允许操作
    */
    public final void setAllowOperate(boolean value) {
        m_allowOperate = value;
    }

    private boolean m_allowPartialPaint = true;

    /*
    * 是否允许局部绘图
    */
    public final boolean allowPartialpaint() {
        return m_allowPartialPaint;
    }

    /*
    * 设置是否允许局部绘图
    */
    public final void setAllowPartialPaint(boolean value) {
        m_allowPartialPaint = value;
    }
    
    /*
     * 获取触摸形状
     */
    public FCCursors getCursor()
    {
        if (m_view != null)
        {
            Cursor cursor = m_view.getCursor();
            if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.Arrow;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.AppStarting;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR))
            {
                return FCCursors.Cross;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
            {
                return FCCursors.Hand;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.Help;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR))
            {
                return FCCursors.IBeam;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.No;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.SizeAll;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR))
            {
                return FCCursors.SizeNESW;
            }
            else if (cursor ==  Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR))
            {
                return FCCursors.SizeNS;
            }
            else if (cursor ==  Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR))
            {
                return FCCursors.SizeNWSE;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR))
            {
                return FCCursors.SizeWE;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            {
                return FCCursors.UpArrow;
            }
            else if (cursor == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR))
            {
                return FCCursors.WaitCursor;
            }
        }
        return FCCursors.Arrow;
    }

    /*
     * 设置触摸形状
     */
    public void setCursor(FCCursors value)
    {
        if (m_view != null)
        {
            FCCursors csr = value;
            if (csr == FCCursors.Arrow)
            {
                 m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.AppStarting)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.Cross)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
            else if (csr == FCCursors.Hand)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else if (csr == FCCursors.Help)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.IBeam)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
            else if (csr == FCCursors.No)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.SizeAll)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.SizeNESW)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
            }
            else if (csr == FCCursors.SizeNS)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
            }
            else if (csr == FCCursors.SizeNWSE)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            }
            else if (csr == FCCursors.SizeWE)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            }
            else if (csr == FCCursors.UpArrow)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            else if (csr == FCCursors.WaitCursor)
            {
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }else{
                m_view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    private FCNative m_native = null;

    /*
    * 方法库
    */
    public final FCNative getNative() {
        return m_native;
    }

    /*
    * 设置方法库
    */
    public final void setNative(FCNative value) {
        m_native = value;
    }

    private JPanel m_view = null;

    /*
    * 获取视图
    */
    public final JPanel getView() {
        return m_view;
    }

    /*
    * 设置视图
    */
    public final void setView(JPanel value) {
        m_view = value;
        //m_handler = new Handler(getView().getContext().getMainLooper());
        Thread t = new Thread(this);
        t.start();
    }

    private boolean m_viewVisible = true;

    /*
    * 视图是否可见
    */
    public final boolean isViewVisible() {
        return m_viewVisible;
    }

    /*
    * 设置视图是否可见
    */
    public final void setViewVisible(boolean value) {
        m_viewVisible = value;
    }

    /*
    * 开始跨线程调用
    */
    public void beginInvoke(FCView view, Object args) {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              view.invoke(args);
          }
      });
    }

    /*
    * 复制到剪贴板
    */
    public final void copy(String text) {

    }
    
    /*
    * 删除资源
    */
    public void delete(){
        
    }

    /*
    * 创建内部视图
    */
    public final FCView createInternalView(FCView parent, String clsid) {
        FCSplitLayoutDiv splitLayoutDiv = (FCSplitLayoutDiv) ((parent instanceof FCSplitLayoutDiv) ? parent : null);
        if (splitLayoutDiv != null) {
            if (clsid.equals("splitter")) {
                FCButton splitter = new FCButton();
                FCSize size = new FCSize(5, 5);
                splitter.setSize(size);
                return splitter;
            }
        }
        FCScrollBar scrollBar = (FCScrollBar) ((parent instanceof FCScrollBar) ? parent : null);
        if (scrollBar != null) {
            scrollBar.setBackColor(FCColor.None);
            scrollBar.setBorderColor(FCColor.None);
            if (clsid.equals("addbutton")) {
                FCButton addButton = new FCButton();
                if ((FCHScrollBar) ((scrollBar instanceof FCHScrollBar) ? scrollBar : null) != null) {
                    FCSize size = new FCSize(0, 10);
                    addButton.setSize(size);
                } else {
                    FCSize size = new FCSize(10, 0);
                    addButton.setSize(size);
                }
                return addButton;
            } else if (clsid.equals("backbutton")) {
                FCButton button = new FCButton();
                button.setBackColor(FCColor.None);
                button.setBorderColor(FCColor.None);
                return new FCButton();
            } else if (clsid.equals("scrollbutton")) {
                FCButton scrollButton = new FCButton();
                scrollButton.setAllowDrag(true);
                return scrollButton;
            } else if (clsid.equals("reducebutton")) {
                FCButton reduceButton = new FCButton();
                if ((FCHScrollBar) ((scrollBar instanceof FCHScrollBar) ? scrollBar : null) != null) {
                    FCSize size = new FCSize(0, 10);
                    reduceButton.setSize(size);
                } else {
                    FCSize size = new FCSize(10, 0);
                    reduceButton.setSize(size);
                }
                return reduceButton;
            }
        }
        FCTabPage tabPage = (FCTabPage) ((parent instanceof FCTabPage) ? parent : null);
        if (tabPage != null) {
            if (clsid.equals("headerbutton")) {
                FCButton button = new FCButton();
                button.setAllowDrag(true);
                FCSize size = new FCSize(100, 20);
                button.setSize(size);
                return button;
            }
        }
        FCComboBox comboBox = (FCComboBox) ((parent instanceof FCComboBox) ? parent : null);
        if (comboBox != null) {
            if (clsid.equals("dropdownbutton")) {
                FCButton dropDownButton = new FCButton();
                dropDownButton.setDisplayOffset(false);
                int width = comboBox.getWidth();
                int height = comboBox.getHeight();
                FCSize size = new FCSize(25, height);
                dropDownButton.setSize(size);
                FCPoint location = new FCPoint(width - 25, 0);
                dropDownButton.setLocation(location);
                return dropDownButton;
            } else if (clsid.equals("dropdownmenu")) {
                FCComboBoxMenu comboBoxMenu = new FCComboBoxMenu();
                comboBoxMenu.setComboBox(comboBox);
                comboBoxMenu.setPopup(true);
                FCSize size = new FCSize(100, 200);
                comboBoxMenu.setSize(size);
                return comboBoxMenu;
            }
        }
        FCSpin spin = (FCSpin) ((parent instanceof FCSpin) ? parent : null);
        if (spin != null) {
            if (clsid.equals("downbutton")) {
                FCButton downButton = new FCButton();
                downButton.setDisplayOffset(false);
                FCSize size = new FCSize(16, 16);
                downButton.setSize(size);
                return downButton;
            } else if (clsid.equals("upbutton")) {
                FCButton upButton = new FCButton();
                upButton.setDisplayOffset(false);
                FCSize size = new FCSize(16, 16);
                upButton.setSize(size);
                return upButton;
            }
        }
        FCDiv div = (FCDiv) ((parent instanceof FCDiv) ? parent : null);
        if (div != null) {
            if (clsid.equals("hscrollbar")) {
                FCHScrollBar hScrollBar = new FCHScrollBar();
                hScrollBar.setVisible(false);
                FCSize size = new FCSize(10, 10);
                hScrollBar.setSize(size);
                return hScrollBar;
            } else if (clsid.equals("vscrollbar")) {
                FCVScrollBar vScrollBar = new FCVScrollBar();
                vScrollBar.setVisible(false);
                FCSize size = new FCSize(10, 10);
                vScrollBar.setSize(size);
                return vScrollBar;
            }
        }
        FCGrid grid = (FCGrid) ((parent instanceof FCGrid) ? parent : null);
        if (grid != null) {
            if (clsid.equals("edittextbox")) {
                return new FCTextBox();
            }
        }
        return null;
    }

    /*
    * 查找预处理视图
    */
    public FCView findPreviewsView(FCView view) {
        if (view.allowPreviewsEvent()) {
            return view;
        } else {
            FCView parent = view.getParent();
            if (parent != null) {
                return findPreviewsView(parent);
            } else {
                return null;
            }
        }
    }

    /*
    * 获取客户端的大小
    */
    public FCSize getClientSize() {
        FCSize size = new FCSize();
        if (m_view != null) {
            size = new FCSize(m_view.getWidth(), m_view.getHeight());
        }
        return size;
    }

    /*
    * 获取触摸位置
    */
    public final FCPoint getTouchPoint() {
        FCPoint mp = m_mousePoint.clone();
        if (m_native.allowScaleSize()) {
            FCSize clientSize = getClientSize();
            if (clientSize.cx > 0 && clientSize.cy > 0) {
                FCSize scaleSize = m_native.getScaleSize();
                mp.x = mp.x * scaleSize.cx / clientSize.cx;
                mp.y = mp.y * scaleSize.cy / clientSize.cy;
            }
        }
        return mp;
    }

    /*
    * 获取裁剪区域
    */
    public final int getIntersectRect(RefObject<FCRect> lpDestRect, RefObject<FCRect> lpSrc1Rect, RefObject<FCRect> lpSrc2Rect) {
        lpDestRect.argvalue.left = Math.max(lpSrc1Rect.argvalue.left, lpSrc2Rect.argvalue.left);
        lpDestRect.argvalue.right = Math.min(lpSrc1Rect.argvalue.right, lpSrc2Rect.argvalue.right);
        lpDestRect.argvalue.top = Math.max(lpSrc1Rect.argvalue.top, lpSrc2Rect.argvalue.top);
        lpDestRect.argvalue.bottom = Math.min(lpSrc1Rect.argvalue.bottom, lpSrc2Rect.argvalue.bottom);
        if (lpDestRect.argvalue.right > lpDestRect.argvalue.left && lpDestRect.argvalue.bottom > lpDestRect.argvalue.top) {
            return 1;
        } else {
            lpDestRect.argvalue.left = 0;
            lpDestRect.argvalue.right = 0;
            lpDestRect.argvalue.top = 0;
            lpDestRect.argvalue.bottom = 0;
            return 0;
        }
    }

    /*
    * 获取大小
    */
    public final FCSize getSize() {
        if (m_native.allowScaleSize()) {
            return m_native.getScaleSize();
        } else {
            return getClientSize();
        }
    }

    /*
    * 获取合并区域
    */
    public final int getUnionRect(RefObject<FCRect> lpDestRect, RefObject<FCRect> lpSrc1Rect, RefObject<FCRect> lpSrc2Rect) {
        return 0;
    }

    /*
    * 秒表方法
    */
    public final int invokeThread(Object lpParam) {
        if (lpParam instanceof SwingHost) {
            SwingHost androidHost = (SwingHost) lpParam;
            m_threadState = 1;
            while (m_threadState == 1)
            {
                try
                {
                    onTimer();
                    Thread.sleep(10);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            m_threadState = 3;
        }
        return 0;
    }

    /*
    * 局部绘图
    */
    public final void invalidate(FCRect rect) {
        if (m_allowPartialPaint)
        {
            FCSize displaySize = m_native.getSize();
            FCRect clipRect = new FCRect(rect.left >= 0 ? rect.left : 0, rect.top >= 0 ? rect.top : 0, rect.right <= displaySize.cx ? rect
                    .right : displaySize.cx, rect.bottom <= displaySize.cy ? rect.bottom : displaySize.cy);
            synchronized (m_clipBounds) {
                m_clipBounds.add(clipRect);
            }
            double scaleFactorX = 1, scaleFactorY = 1;
            FCSize clientSize = getClientSize();
            if (m_native.allowScaleSize()){
                if (clientSize.cx > 0 && clientSize.cy > 0){
                    FCSize scaleSize = m_native.getScaleSize();
                    scaleFactorX = (double)(clientSize.cx) / scaleSize.cx;
                    scaleFactorY = (double)(clientSize.cy) / scaleSize.cy;
                }
            }
            FCRect newRect = rect;
            if(scaleFactorX != 1 || scaleFactorY != 1){
                newRect.left = (int)Math.floor(newRect.left * scaleFactorX);
                newRect.top = (int)Math.floor(newRect.top * scaleFactorY);
                newRect.right = (int)Math.ceil(newRect.right * scaleFactorX);
                newRect.bottom = (int)Math.ceil(newRect.bottom * scaleFactorY);
            }
            m_view.repaint(newRect.left, newRect.top, newRect.right - newRect.left , newRect.bottom - newRect.top);
        } else
        {
            invalidate();
        }
    }

    /*
    * 全部绘图
    */
    public final void invalidate() {
        synchronized (m_clipBounds) {
            m_clipBounds.clear();
            m_clipBounds.add(new FCRect(0, 0, m_native.getSize().cx, m_native.getSize().cy));
        }
        m_view.repaint();
    }

    /*
    * 跨线程调用
    */
    public final void invoke(FCView view, Object args) {
        beginInvoke(view, args);
    }
    
    /*
    * Control键是否按下
    */
    public boolean m_isControl;
    
    /*
    * Shift键是否按下
    */
    public boolean m_isShift;
    
    /*
    * Alt键是否按下
    */
    public boolean m_isAlt;
    
    /*
    * 特殊键是否按下
    */
    public boolean isKeyPress(char key){
        //Control
        if(key == (char)0x11){
            return m_isControl;
        }
        //Shift
        if(key == (char)0x10){
            return m_isShift;
        }
        //Alt
        if(key == (char)0x12){
            return m_isAlt;
        }
        return false;
    }

    /*
    * 重绘方法
    */
    public final void onPaint(Graphics g) {
        FCSize displaySize = m_native.getSize();
        int clipBoundsSize = m_clipBounds.size();
        FCRect clipRect = new FCRect();
        synchronized (m_clipBounds) {
            if (clipBoundsSize > 0) {
                for (int i = 0; i < clipBoundsSize; i++) {
                    FCRect clipBounds = m_clipBounds.get(i);
                    if (i == 0) {
                        clipRect = new FCRect(clipBounds.left, clipBounds.top, clipBounds.right, clipBounds.bottom);
                    } else {
                        if (clipRect.left > clipBounds.left) {
                            clipRect.left = clipBounds.left;
                        }
                        if (clipRect.right < clipBounds.right) {
                            clipRect.right = clipBounds.right;
                        }
                        if (clipRect.top > clipBounds.top) {
                            clipRect.top = clipBounds.top;
                        }
                        if (clipRect.bottom < clipBounds.bottom) {
                            clipRect.bottom = clipBounds.bottom;
                        }
                    }
                }
            } else {
                clipRect = new FCRect(0, 0, displaySize.cx, displaySize.cy);
            }
            m_clipBounds.clear();
        }
        double scaleFactorX = 1, scaleFactorY = 1;
        FCSize clientSize = getClientSize();
        if (m_native.allowScaleSize()){
            if (clientSize.cx > 0 && clientSize.cy > 0){
                FCSize scaleSize = m_native.getScaleSize();
                scaleFactorX = (double)(clientSize.cx) / scaleSize.cx;
                scaleFactorY = (double)(clientSize.cy) / scaleSize.cy;
            }
        }
        FCPaint paint = m_native.getPaint();
        FCRect wRect = new FCRect(0, 0, clientSize.cx, clientSize.cy);
        paint.setScaleFactor(scaleFactorX, scaleFactorY);
        ((SwingPaint)paint).setGraphics(g);
        paint.beginPaint(0, wRect, clipRect);
        m_native.onPaint(clipRect);
        paint.endPaint();
    }

    /*
    * 秒表方法
    */
    public final void onTimer() {
        if (m_native != null) {
            if (m_view != null) {
                if (m_viewVisible) {
                    ArrayList<Integer> m_runningTimerIDs = new ArrayList<>();
                    synchronized (m_timers)
                    {
                        try {
                            for (SwingTimer timer : m_timers.values()) {
                                if (timer.m_tick % timer.m_interval == 0) {
                                    m_runningTimerIDs.add(timer.m_timerID);
                                }
                                timer.m_tick++;
                            }
                        }catch (Exception ex){

                        }
                    }
                   if(m_runningTimerIDs.size() > 0) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                synchronized (m_timers) {
                                    try {
                                        int runningTimerIDsSize = m_runningTimerIDs.size();
                                        for (int i = 0; i < runningTimerIDsSize; i++) {
                                            m_native.onTimer(m_runningTimerIDs.get(i));
                                        }
                                        m_runningTimerIDs.clear();
                                    } catch (Exception ex) {
                                        int a = 0;
                                        int  b =0;
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    /*
    * 粘贴数据
    */
    public final String paste() {
        return "";
    }

    /*
    * 运行线程
    */
    public void run() {
        invokeThread(this);
    }

    /*
    * 设置第二个点
    */
    public final void setTouchPoint(FCPoint mp) {
        m_mousePoint = mp.clone();
    }

    /*
    * 显示提示框
    */
    public void showToolTip(String var1, FCPoint var2) {

    }

    /*
    * 启动秒表
    */
    public final void startTimer(int timerID, int interval) {
        interval = interval / 10;
        if(interval < 1){
            interval = 1;
        }
        synchronized (m_timers) {
            if (m_timers.containsKey(timerID)) {
                SwingTimer timer = m_timers.get(timerID);
                timer.m_interval = interval;
                timer.m_tick = 0;
            } else {
                SwingTimer timer = new SwingTimer();
                timer.m_interval = interval;
                timer.m_timerID = timerID;
                m_timers.put(timerID, timer);
            }
        }
    }

    /*
    * 结束秒表
    */
    public final void stopTimer(int timerID) {
        synchronized (m_timers) {
            if (m_timers.containsKey(timerID)) {
                m_timers.remove(timerID);
            }
        }
    }
}
