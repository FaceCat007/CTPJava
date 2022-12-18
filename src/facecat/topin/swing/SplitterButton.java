package facecat.topin.swing;

import facecat.topin.core.*;
import facecat.topin.btn.*;
import facecat.topin.div.*;
import facecat.topin.scroll.*;
import facecat.topin.tab.*;
import java.util.*;

/*
* 分割按钮
*/
public class SplitterButton  extends FCButton {
    /*
     * 创建透明按钮
     */
    public SplitterButton()
    {
        m_status = 0;
        setBorderColor(FCColor.None);
    }

    /*
     * 状态
     */
    private int m_status = 0;

    /*
     *获取状态
     * @return
     */
    public int GetStatus()
    {
        return m_status;
    }

    /*
     * 设置状态
     * @param status
     */
    public void SetStatus(int status)
    {
        m_status = status;
    }

    /*
     重绘背景
     @param paint 绘图对象
     @param clipRect 裁剪区域
     */
    @Override
    public void onPaintBackground(FCPaint paint, FCRect clipRect)
    {
        int width = getWidth(), height = getHeight();
        int mw = width / 2, mh = height / 2;
        FCRect drawRect = new FCRect(0, 0, width, height);
        long lineColor = FCColor.rgba(101, 179, 239, 255);
        long backColor = FCColor.None;
        paint.fillRect(backColor, drawRect);
        paint.drawRect(lineColor, 1, 0, drawRect);
    }
}
