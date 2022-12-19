package facecat.topin.swing;

import javax.swing.JPanel;
import facecat.topin.core.*;
import facecat.topin.btn.*;
import facecat.topin.div.*;
import facecat.topin.xml.*;
import java.awt.*;
import java.awt.event.*;

/*
* 视图基类
*/
public class FCUIView extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ComponentListener, KeyListener {
    private SwingHost m_host = null;
    
    /*
    * 获取设备库
    */
    public SwingHost getHost (){
        return m_host;
    }
    
    private FCNative m_native = null;

    /*
    * 获取方法库
    */
    public FCNative getNative ()
    {
        return m_native;
    }

    private SwingPaint m_paint = null;

    /*
    * 获取绘图库
    */
    public SwingPaint getPaint ()
    {
        return m_paint;
    }
    
    /*
    * 主界面
    */
    public UIXmlEx m_mainFrame;
    
    /*
    * 初始化
    */
    public void onLoad (){
        m_host = new SwingHost();
        m_native = new FCNative();
        m_paint = new SwingPaint();
        m_native.setHost(m_host);
        m_native.setPaint(m_paint);
        m_host.setNative(m_native);
        m_host.setView(this);
        m_native.setSize(new FCSize(getWidth(), getHeight()));
        //m_native.setAllowScaleSize(true);
        
        m_mainFrame = new MainFrame();
        m_mainFrame.setNative(m_native);
        String content = "";
        RefObject<String> refContent = new RefObject<String>(content);
        String dir = System.getProperty("user.dir") + "\\MainFrame2.xml";
        FCFile.read(dir, refContent);
        content = refContent.argvalue;
        m_mainFrame.load(content);
        m_mainFrame.resetScaleSize(new FCSize(getWidth(), getHeight()));
        m_native.update();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addComponentListener(this);
        this.addKeyListener(this);
        m_native.invalidate();
    }
    
    public FCSize m_lastSize = new FCSize();
    
    /*
    * 绘图方法
    */
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        if(m_host != null){
            FCSize nowSize = new FCSize(getWidth(), getHeight());
            if(nowSize.cx != m_lastSize.cx || nowSize.cy != m_lastSize.cy){
                m_mainFrame.resetScaleSize(nowSize);
            }
            m_host.onPaint(graphics);
            m_lastSize = nowSize;
        }
    }
    
    /*
    * 鼠标点击
    */
    public void mouseClicked(MouseEvent e){
    }

    public int m_lastButton;
    
    /*
    * 鼠标按下
    */
    public void mousePressed(MouseEvent e){
        if (m_host.allowOperate())
        {
            m_host.setTouchPoint(new FCPoint(e.getX(), e.getY()));
            FCTouchInfo newTouchInfo = new FCTouchInfo();
            newTouchInfo.m_firstPoint = m_native.getTouchPoint();
            newTouchInfo.m_secondPoint = m_native.getTouchPoint();
            if(e.getButton() == 1){
                newTouchInfo.m_firstTouch = true;
            }else if(e.getButton() == 2){
                newTouchInfo.m_secondTouch = true;
            }
            m_lastButton = e.getButton();
            newTouchInfo.m_clicks = e.getClickCount();
            m_native.onTouchBegin(newTouchInfo);
        }
    }

    /*
    * 鼠标释放
    */
    public void mouseReleased(MouseEvent e){
        if (m_host.allowOperate())
        {
            m_host.setTouchPoint(new FCPoint(e.getX(), e.getY()));
            FCTouchInfo newTouchInfo = new FCTouchInfo();
            newTouchInfo.m_firstPoint = m_native.getTouchPoint();
            newTouchInfo.m_secondPoint = m_native.getTouchPoint();
            if(e.getButton() == 1){
                newTouchInfo.m_firstTouch = true;
            }else if(e.getButton() == 2){
                newTouchInfo.m_secondTouch = true;
            }
            newTouchInfo.m_clicks = e.getClickCount();
            m_native.onTouchEnd(newTouchInfo);
            m_lastButton = 0;
        }
    }

    /*
    * 鼠标离开
    */
    public void mouseExited(MouseEvent e){
    }
    
    /*
    * 鼠标进入
    */
    public void mouseEntered(MouseEvent e){
        
    }
    
   /*
    * 鼠标拖拽
    */
    public void mouseDragged(MouseEvent e){
        if (m_host.allowOperate())
        {
            m_host.setTouchPoint(new FCPoint(e.getX(), e.getY()));
            FCTouchInfo newTouchInfo = new FCTouchInfo();
            newTouchInfo.m_firstPoint = m_native.getTouchPoint();
            newTouchInfo.m_secondPoint = m_native.getTouchPoint();
            if(m_lastButton == 1){
                newTouchInfo.m_firstTouch = true;
                newTouchInfo.m_clicks = 1;
            }else if(m_lastButton == 2){
                newTouchInfo.m_secondTouch = true;
                newTouchInfo.m_clicks = 1;
            }else{
                newTouchInfo.m_clicks = e.getClickCount();
            }
            m_native.onTouchMove(newTouchInfo);
        }
    }

    /*
    * 鼠标移动
    */
    public void mouseMoved(MouseEvent e){
        if (m_host.allowOperate())
        {
            m_host.setTouchPoint(new FCPoint(e.getX(), e.getY()));
            FCTouchInfo newTouchInfo = new FCTouchInfo();
            newTouchInfo.m_firstPoint = m_native.getTouchPoint();
            newTouchInfo.m_secondPoint = m_native.getTouchPoint();
            if(m_lastButton == 1){
                newTouchInfo.m_firstTouch = true;
                newTouchInfo.m_clicks = 1;
            }else if(m_lastButton == 2){
                newTouchInfo.m_secondTouch = true;
                newTouchInfo.m_clicks = 1;
            }else{
                newTouchInfo.m_clicks = e.getClickCount();
            }
            m_native.onTouchMove(newTouchInfo);
        }
    }
    
    /*
    * 鼠标滚动
    */
    public void mouseWheelMoved(MouseWheelEvent e){
        if (m_host.allowOperate())
        {
            m_host.setTouchPoint(new FCPoint(e.getX(), e.getY()));
            FCTouchInfo newTouchInfo = new FCTouchInfo();
            newTouchInfo.m_firstPoint = m_native.getTouchPoint();
            newTouchInfo.m_secondPoint = m_native.getTouchPoint();
            newTouchInfo.m_delta = -e.getWheelRotation();
            if(m_lastButton == 1){
                newTouchInfo.m_firstTouch = true;
                newTouchInfo.m_clicks = 1;
            }else if(m_lastButton == 2){
                newTouchInfo.m_secondTouch = true;
                newTouchInfo.m_clicks = 1;
            }else{
                newTouchInfo.m_clicks = e.getClickCount();
            }
            m_native.onTouchWheel(newTouchInfo);
        }
    }
    
    /*
    * 窗体界面改变
    */
    public void componentResized(ComponentEvent e){
         m_mainFrame.resetScaleSize(new FCSize(getWidth(), getHeight()));
         m_native.invalidate();
    }

    public void componentMoved(ComponentEvent e){
        
    }

    public void componentShown(ComponentEvent e){
        
    }

    public void componentHidden(ComponentEvent e){
        
    }
    
    /*
    * 键盘按下
    */
    public void keyTyped(KeyEvent e){
        m_host.m_isAlt = false;
        m_host.m_isControl = false;
        m_host.m_isShift = false;
        if((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0){
            m_host.m_isControl = true;
        }
        if((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0){
            m_host.m_isAlt = true;
        }
        if((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0){
            m_host.m_isShift = true;
        }
        m_native.onKeyDown((char)e.getKeyCode());
        m_native.onChar(e.getKeyChar());
    }

    /*
    * 键盘按住
    */
    public void keyPressed(KeyEvent e){
         m_host.m_isAlt = false;
        m_host.m_isControl = false;
        m_host.m_isShift = false;
        if((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0){
            m_host.m_isControl = true;
        }
        if((e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0){
            m_host.m_isAlt = true;
        }
        if((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0){
            m_host.m_isShift = true;
        }
        m_native.onKeyDown((char)e.getKeyCode());
    }

    /*
    * 键盘释放
    */
    public void keyReleased(KeyEvent e){
        m_native.onKeyUp((char)e.getKeyCode());
    }
}
