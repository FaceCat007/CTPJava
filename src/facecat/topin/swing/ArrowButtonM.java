package facecat.topin.swing;

import facecat.topin.core.*;
import facecat.topin.btn.*;

/*
* 箭头按钮
*/
public class ArrowButtonM extends FCButton
{
    /**
     创建透明按钮
     */
    public ArrowButtonM()
    {
        setBorderColor(FCColor.None);
    }

    private int m_arrowType;

    /*
     获取箭头类型
     */
    public final int getArrowType()
    {
        return m_arrowType;
    }

    /*
    * 设置箭头类型
    */
    public final void setArrowType(int value)
    {
        m_arrowType = value;
    }

    /**
     重绘背景
     @param paint 绘图对象
     @param clipRect 裁剪区域
     */
    @Override
    public void onPaintBackground(FCPaint paint, FCRect clipRect) {
        String text = getText();
        int width = getWidth(), height = getHeight();
        int mw = width / 2, mh = height / 2 + 2;
        FCRect drawRect = new FCRect(0, 0, width, height);
        if (m_arrowType > 0) {
            FCPoint[] points = new FCPoint[3];
            int ts = Math.min(mw, mh) / 2 + 2;
            switch (m_arrowType) {
                //向左
                case 1:
                    points[0] = new FCPoint(mw - ts, mh);
                    points[1] = new FCPoint(mw + ts, mh - ts);
                    points[2] = new FCPoint(mw + ts, mh + ts);
                    break;
                //向右
                case 2:
                    points[0] = new FCPoint(mw + ts, mh);
                    points[1] = new FCPoint(mw - ts, mh - ts);
                    points[2] = new FCPoint(mw - ts, mh + ts);
                    break;
                //向上
                case 3:
                    points[0] = new FCPoint(mw, mh - ts);
                    points[1] = new FCPoint(mw - ts, mh + ts);
                    points[2] = new FCPoint(mw + ts, mh + ts);
                    break;
                //向下
                case 4:
                    points[0] = new FCPoint(mw, mh + ts - 3);
                    points[1] = new FCPoint(mw - ts + 3, mh - ts + 3);
                    points[2] = new FCPoint(mw + ts - 3, mh - ts  + 3);
                    break;
            }
            paint.fillPolygon(FCColor.rgba(54, 138, 212, 255), points);
        }
    }
}
