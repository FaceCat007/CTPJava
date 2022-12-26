package facecat.topin.swing;
import facecat.topin.core.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

/*
* 绘图类
*/
public class SwingPaint extends FCPaint{
    /*
    * 是否双倍缓冲
    */
    private boolean m_doubleBufferd = true;
    
    /*
    * 缓存图片
    */
    private BufferedImage m_cacheImage;

    /*
    * 绘图对象
    */
    private Graphics2D m_g;
    
    /*
    * 图片缓存
    */
    private HashMap<String, Image> m_images = new HashMap<String, Image>();
    
    /*
    * 是否裁剪
    */
    private boolean m_isClip;

    /*
    * 是否路径开始
    */
    private boolean m_isPathStart;
    
    /*
    * 我的颜色
    */
    public MyColor m_myColor = new MyColor();

    /*
    * 横向偏移
    */
    public int m_offsetX;

    /*
    * 纵向偏移
    */
    public int m_offsetY;

    /*
    * 透明度
    */
    public float m_opacity = 1;

    /*
    * 绘制区域
    */
    public FCRect m_pRect;

    /*
    * 资源路径
    */
    public String m_resourcePath;

    /*
    * 旋转角度
    */
    public int m_rotateAngle;

    /*
    * 横向缩放
    */
    public double m_scaleFactorX = 1;

    /*
    * 纵向缩放
    */
    public double m_scaleFactorY = 1;
    
    /*
    * 画布大小
    */
    public FCRect m_wRect;
    
    /**
     * 添加曲线
     *
     * @param rect 矩形区域
     * @param startAngle 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）
     * @param sweepAngle 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
     */
    public void addArc(FCRect rect, float startAngle, float sweepAngle){
    }

    /**
     * 添加贝赛尔曲线
     *
     * @param point1 坐标1
     * @param point2 坐标2
     * @param point3 坐标3
     * @param point4 坐标4
     */
    public void addBezier(FCPoint[] points){
        
    }

    /**
     * 添加曲线
     *
     * @param points 点阵
     */
    public void addCurve(FCPoint[] points){
        
    }

    /**
     * 添加椭圆
     *
     * @param rect 矩形
     */
    public void addEllipse(FCRect rect){
        
    }

    /**
     * 添加直线
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     */
    public void addLine(int x1, int y1, int x2, int y2){
        
    }

    /**
     * 添加矩形
     *
     * @param rect 区域
     */
    public void addRect(FCRect rect){
        
    }

    /**
     * 添加扇形
     *
     * @param rect 矩形区域
     * @param startAngle 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）
     * @param sweepAngle 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
     */
    public void addPie(FCRect rect, float startAngle, float sweepAngle){
        
    }

    /**
     * 添加文字
     *
     * @param text 文字
     * @param font 字体
     * @param rect 区域
     */
    public void addText(String text, FCFont font, FCRect rect, int width){
        
    }

    /*
    * 自动适应缩放
    */
    public void affectScaleFactor(RefObject<FCRect> andriodRect)
    {
        if (m_scaleFactorX != -1 || m_scaleFactorY != -1)
        {
            andriodRect.argvalue.left = (int) (andriodRect.argvalue.left * m_scaleFactorX);
            andriodRect.argvalue.top = (int) (andriodRect.argvalue.top * m_scaleFactorX);
            andriodRect.argvalue.right = (int) (andriodRect.argvalue.right * m_scaleFactorX);
            andriodRect.argvalue.bottom = (int) (andriodRect.argvalue.bottom * m_scaleFactorX);
        }
    }
    
    /**
     * 开始绘图
     *
     * @param hdc HDC
     * @param wRect 窗体区域
     * @param pRect 刷新区域
     */
    public void beginPaint(int canvas, FCRect wRect, FCRect pRect){
        m_isClip = false;
        m_opacity = 1;
        m_resourcePath = null;
        m_wRect = wRect.clone();
        m_pRect = pRect.clone();
        m_g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        m_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 开始一段路径
     */
    public void beginPath(){
        
    }

    /**
     * 清除缓存
     */
    public void clearCaches(){
        HashMap<String, Image> hashMap = new HashMap<String, Image>();
        for (String key : hashMap.keySet()){
            Image value = hashMap.get(key);
        }
        m_images.clear();
    }

    /**
     * 裁剪路径
     */
    public void clipPath(){
        
    }

    /**
     * 闭合路径
     */
    public void closeFigure(){
        
    }

    /**
     * 结束一段路径
     */
    public void closePath(){
        
    }

    /**
     * 删除对象
     */
    public void delete(){
        clearCaches();
        if (m_cacheImage != null)
        {
            m_cacheImage = null;
        }
    }

    /**
     * 绘制弧线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param rect 矩形区域
     * @param startAngle 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）
     * @param sweepAngle 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
     */
    public void drawArc(long dwPenColor, float width, int style, FCRect rect, float startAngle, float sweepAngle){
        
    }

    /**
     * 设置贝赛尔曲线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param points 坐标阵
     */
    public void drawBezier(long dwPenColor, float width, int style, FCPoint[] points){
        
    }

    /**
     * 绘制曲线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param points 坐标阵
     */
    public void drawCurve(long dwPenColor, float width, int style, FCPoint[] points){
        
    }

    /**
     * 绘制矩形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param rect 矩形区域
     */
    public void drawEllipse(long dwPenColor, float width, int style, FCRect rect){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawOval(newRect.left, newRect.top, newRect.right - newRect.left, newRect.bottom - newRect.top);
    }

    /**
     * 绘制矩形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param left 左侧坐标
     * @param top 顶部左标
     * @param right 右侧坐标
     * @param bottom 底部坐标
     */
    public void drawEllipse(long dwPenColor, float width, int style, int left, int top, int right, int bottom){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(left + m_offsetX, top + m_offsetY, right + m_offsetX, bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawOval(newRect.left, newRect.top, newRect.right - newRect.left, newRect.bottom - newRect.top);
    }

    /**
     * 绘制图片
     *
     * @param imagePath 图片路径
     * @param rect 绘制区域
     */
    public void drawImage(String imagePath, FCRect rect){
        String imageKey = imagePath;
        Image drawImage = null;
        int rw = rect.right - rect.left;
        if (rw < 1) rw = 1;
        int rh = rect.bottom - rect.top;
        if (rh < 1) rh = 1;
        if (m_images.containsKey(imageKey))
        {
            drawImage = m_images.get(imageKey);
        }else{
            try{
                File sourceimage = new File(imageKey); 
                drawImage = ImageIO.read(sourceimage);
                m_images.put(imageKey, drawImage);
            }catch(Exception ex){
                
            }
        }
        if (drawImage != null)
        {
            FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
            RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
            affectScaleFactor(refAndriodRect);
            newRect = refAndriodRect.argvalue;
            m_g.drawImage(drawImage, newRect.left, newRect.top, newRect.right - newRect.left + 1, newRect.bottom - newRect.top + 1, null);
        }
    }

    /**
     * 绘制直线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     */
    public void drawLine(long dwPenColor, float width, int style, int x1, int y1, int x2, int y2){
        if (dwPenColor == FCColor.None) return;
        float lx1 = x1 + m_offsetX;
        float ly1 = y1 + m_offsetY;
        float lx2 = x2 + m_offsetX;
        float ly2 = y2 + m_offsetY;
        if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
        {
            lx1 = (float) (m_scaleFactorX * lx1);
            ly1 = (float) (m_scaleFactorY * ly1);
            lx2 = (float) (m_scaleFactorX * lx2);
            ly2 = (float) (m_scaleFactorY * ly2);
        }
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawLine((int)lx1, (int)ly1, (int)lx2, (int)ly2);
    }

    /**
     * 绘制直线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param x 第一个点坐标
     * @param y 第二个点的坐标
     */
    public void drawLine(long dwPenColor, float width, int style, FCPoint x, FCPoint y){
        drawLine(dwPenColor, width, style, x.x, x.y, y.x, y.y);
    }

    /**
     * 绘制路径
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     */
    public void drawPath(long dwPenColor, float width, int style){
        
    }

    /**
     * 绘制弧线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param rect 矩形区域
     * @param startAngle 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）
     * @param sweepAngle 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
     */
    public void drawPie(long dwPenColor, float width, int style, FCRect rect, float startAngle, float sweepAngle){

    }

    /**
     * 绘制多边形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param points 点的数组
     */
    public void drawPolygon(long dwPenColor, float width, int style, FCPoint[] points){
        if (dwPenColor == FCColor.None) return;
        int cpt = points.length;
        int []xPoints = new int[cpt];
        int []yPoints = new int[cpt];
        for (int i = 0; i < cpt; i++)
        {
            int x = points[i].x + m_offsetX;
            int y = points[i].y + m_offsetY;
            if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
            {
                x = (int) (m_scaleFactorX * x);
                y = (int) (m_scaleFactorY * y);
            }
            xPoints[i] = x;
            yPoints[i] = y;
         }
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawPolygon(xPoints, yPoints, cpt);
    }

    /**
     * 绘制大量直线
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param points 点集
     */
    public void drawPolyline(long dwPenColor, float width, int style, FCPoint[] points){
        if (dwPenColor == FCColor.None) return;
        int cpt = points.length;
        int []xPoints = new int[cpt];
        int []yPoints = new int[cpt];
        for (int i = 0; i < cpt; i++)
        {
            int x = points[i].x + m_offsetX;
            int y = points[i].y + m_offsetY;
            if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
            {
                x = (int) (m_scaleFactorX * x);
                y = (int) (m_scaleFactorY * y);
            }
            xPoints[i] = x;
            yPoints[i] = y;
         }
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawPolyline(xPoints, yPoints, cpt);
        //m_g.drawPolygon(xPoints, yPoints, cpt);
    }

    /**
     * 绘制矩形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param rect 矩形区域
     */
    public void drawRect(long dwPenColor, float width, int style, FCRect rect){
        drawRect(dwPenColor, width, style, rect.left, rect.top, rect.right, rect.bottom);
    }

    /**
     * 绘制矩形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param left 左侧坐标
     * @param top 顶部左标
     * @param right 右侧坐标
     * @param bottom 底部坐标
     */
    public void drawRect(long dwPenColor, float width, int style, int left, int top, int right, int bottom){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(left + m_offsetX, top + m_offsetY, right + m_offsetX, bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawRect(newRect.left, newRect.top, newRect.right - newRect.left, newRect.bottom - newRect.top);
    }

    /**
     * 绘制圆角矩形
     *
     * @param dwPenColor 颜色
     * @param width 宽度
     * @param style 样式
     * @param rect 矩形区域
     * @param cornerRadius 边角半径
     */
    public void drawRoundRect(long dwPenColor, float width, int style, FCRect rect, int cornerRadius){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.setStroke(new BasicStroke(width));
        m_g.drawRoundRect(newRect.left, newRect.top, newRect.right - newRect.left, newRect.bottom - newRect.top, cornerRadius, cornerRadius);
    }

    /**
     * 绘制矩形
     *
     * @param text 文字
     * @param dwPenColor 颜色
     * @param font 字体
     * @param rect 矩形区域
     */
    public void drawText(String text, long dwPenColor, FCFont font, FCRect rect, int width){
        if (dwPenColor == FCColor.None || text == null) return;
        if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
        {
            FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
            RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
            affectScaleFactor(refAndriodRect);
            newRect = refAndriodRect.argvalue;
            float fontSize = (float) (font.m_fontSize * (m_scaleFactorX + m_scaleFactorY) / 2);
            FCFont newFont = new FCFont(font.m_fontFamily, fontSize, font.m_bold, font.m_underline, font.m_italic, font.m_strikeout);
            Font tFont = getFont(newFont);
            m_g.setFont(getFont(newFont));
            m_g.setColor(getSwingColor(dwPenColor));
            FontMetrics fontMetrics = m_g.getFontMetrics(tFont);
            m_g.drawString(text, newRect.left, newRect.top + fontMetrics.getAscent());
        } else
        {
             Font tFont = getFont(font);
            m_g.setFont(tFont);
            m_g.setColor(getSwingColor(dwPenColor));
            FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
             FontMetrics fontMetrics = m_g.getFontMetrics(tFont);
            m_g.drawString(text, newRect.left, newRect.top + fontMetrics.getAscent());
        }
    }

    /**
     * 绘制矩形
     *
     * @param text 文字
     * @param dwPenColor 颜色
     * @param font 字体
     * @param rect 矩形区域
     */
    public void drawText(String text, long dwPenColor, FCFont font, FCRectF rect, int width){
        if (dwPenColor == FCColor.None || text == null) return;
        if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
        {
            FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
            RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
            affectScaleFactor(refAndriodRect);
            newRect = refAndriodRect.argvalue;
            float fontSize = (float) (font.m_fontSize * (m_scaleFactorX + m_scaleFactorY) / 2);
            FCFont newFont = new FCFont(font.m_fontFamily, fontSize, font.m_bold, font.m_underline, font.m_italic, font.m_strikeout);
            Font tFont = getFont(newFont);
            m_g.setFont(tFont);
            m_g.setColor(getSwingColor(dwPenColor));
             FontMetrics fontMetrics = m_g.getFontMetrics(tFont);
            m_g.drawString(text, newRect.left, newRect.top + fontMetrics.getAscent());
        } else
        {
             Font tFont = getFont(font);
            m_g.setFont(tFont);
             m_g.setColor(getSwingColor(dwPenColor));
             FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
            FontMetrics fontMetrics = m_g.getFontMetrics(tFont);
            m_g.drawString(text, newRect.left, newRect.top + fontMetrics.getAscent());
        }
    }

    /**
     * 绘制自动省略结尾的文字
     *
     * @param text 文字
     * @param dwPenColor 颜色
     * @param font 字体
     * @param rect 矩形区域
     */
    public void drawTextAutoEllipsis(String text, long dwPenColor, FCFont font, FCRect rect){
        FCSize tSize = textSize(text, font, -1);
        if(tSize.cx < rect.right - rect.left){
            drawText(text, dwPenColor, font, rect, -1);
        }else{
            if(tSize.cx > 0){
                int subLen = 3;
                while(true){
                    int newLen = text.length() - subLen;
                    if(newLen > 0){
                        String newText = text.substring(0, newLen) + "...";
                        tSize = textSize(newText, font, -1);
                        if(tSize.cx < rect.right - rect.left){
                            drawText(newText, dwPenColor, font, rect, -1);
                            break;
                        }else{
                            subLen += 3;
                        }
                    }else{
                        break;
                    }
                }
            }
        }
    }

    /**
     * 结束导出
     */
    public void endPaint(){
        m_isClip = false;
        m_offsetX = 0;
        m_offsetY = 0;
        m_resourcePath = "";
    }

    /**
     * 结束绘图
     */
    public void excludeClipPath(){
        
    }

    /**
     * 填充椭圆
     *
     * @param dwPenColor 颜色
     * @param rect 矩形区域
     */
    public void fillEllipse(long dwPenColor, FCRect rect){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.fillOval(newRect.left, newRect.top, newRect.right - newRect.left + 1, newRect.bottom - newRect.top + 1);
    }

    /**
     * 填充椭圆
     *
     * @param dwPenColor 颜色
     * @param left 左侧坐标
     * @param top 顶部左标
     * @param right 右侧坐标
     * @param bottom 底部坐标
     */
    public void fillEllipse(long dwPenColor, int left, int top, int right, int bottom){
        FCRect newRect = new FCRect(left, top, right, bottom);
        fillEllipse(dwPenColor, newRect);
    }

    /**
     * 绘制渐变椭圆
     *
     * @param dwFirst 开始颜色
     * @param dwSecond 结束颜色
     * @param rect 矩形
     * @param angle 角度
     */
    public void fillGradientEllipse(long dwFirst, long dwSecond, FCRect rect, int angle){
        fillEllipse(dwFirst, rect);
    }

    /**
     * 填充渐变路径
     *
     * @param dwFirst 开始颜色
     * @param dwSecond 结束颜色
     * @param points 点的集合
     * @param angle 角度
     */
    public void fillGradientPath(long dwFirst, long dwSecond, FCRect rect, int angle){
        
    }

    /**
     * 绘制渐变的多边形
     *
     * @param dwFirst 开始颜色
     * @param dwSecond 结束颜色
     * @param points 点的集合
     * @param angle 角度
     */
    public void fillGradientPolygon(long dwFirst, long dwSecond, FCPoint[] points, int angle){
        fillPolygon(dwFirst, points);
    }

    /**
     * 绘制渐变矩形
     *
     * @param dwFirst 开始颜色
     * @param dwSecond 结束颜色
     * @param rect 矩形
     * @param cornerRadius 圆角半径
     * @param angle 角度
     */
    public void fillGradientRect(long dwFirst, long dwSecond, FCRect rect, int cornerRadius, int angle){
        FCRect newRect = new FCRect(rect.left, rect.top, rect.right, rect.bottom);
        fillRect(dwFirst, newRect);
    }

    /**
     * 填充路径
     *
     * @param dwPenColor 颜色
     */
    public void fillPath(long dwPenColor){
        
    }

    /**
     * 绘制扇形
     *
     * @param dwPenColor 颜色
     * @param rect 矩形区域
     * @param startAngle 从 x 轴到弧线的起始点沿顺时针方向度量的角（以度为单位）
     * @param sweepAngle 从 startAngle 参数到弧线的结束点沿顺时针方向度量的角（以度为单位）
     */
    public void fillPie(long dwPenColor, FCRect rect, float startAngle, float sweepAngle){
        
    }

    /**
     * 填充椭圆
     *
     * @param dwPenColor 颜色
     * @param points 点的数组
     */
    public void fillPolygon(long dwPenColor, FCPoint[] points){
        if (dwPenColor == FCColor.None) return;
        int cpt = points.length;
        int []xPoints = new int[cpt];
        int []yPoints = new int[cpt];
        for (int i = 0; i < cpt; i++)
        {
            int x = points[i].x + m_offsetX;
            int y = points[i].y + m_offsetY;
            if (m_scaleFactorX != 1 || m_scaleFactorY != 1)
            {
                x = (int) (m_scaleFactorX * x);
                y = (int) (m_scaleFactorY * y);
            }
            xPoints[i] = x;
            yPoints[i] = y;
         }
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.fillPolygon(xPoints, yPoints, cpt);
    }

    /**
     * 填充矩形
     *
     * @param dwPenColor 颜色
     * @param rect 矩形区域
     */
    public void fillRect(long dwPenColor, FCRect rect){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.fillRect(newRect.left, newRect.top, newRect.right - newRect.left + 1, newRect.bottom - newRect.top + 1);
    }

    /**
     * 填充矩形
     *
     * @param text 文字
     * @param dwPenColor 颜色
     * @param font 字体
     * @param rect 矩形区域
     */
    public void fillRect(long dwPenColor, int left, int top, int right, int bottom){
        FCRect newRect = new FCRect(left, top, right, bottom);
        fillRect(dwPenColor, newRect);
    }

    /**
     * 填充圆角矩形
     *
     * @param dwPenColor 颜色
     * @param rect 矩形区域
     * @param cornerRadius 边角半径
     */
    public void fillRoundRect(long dwPenColor, FCRect rect, int cornerRadius){
        if (dwPenColor == FCColor.None) return;
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setColor(getSwingColor(dwPenColor));
        m_g.fillRoundRect(newRect.left, newRect.top, newRect.right - newRect.left + 1, newRect.bottom - newRect.top + 1, cornerRadius, cornerRadius);
    }

    /**
     * 获取颜色
     *
     * @param dwPenColor 输入颜色
     * @returns 输出颜色
     */
    public long getColor(long dwPenColor){
        return m_myColor.getUserColor(dwPenColor);
    }

    /*
    获取字体
    */
    public Font getFont(FCFont font){
        String fontFamily = font.m_fontFamily;
        if(fontFamily.equals("Default")){
            fontFamily = "宋体";
        }
        if (font.m_strikeout) {
                if (font.m_bold && font.m_underline && font.m_italic) {
                    return new Font(fontFamily, Font.BOLD | Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_bold && font.m_underline) {
                    return new Font(fontFamily, Font.BOLD, (int)font.m_fontSize);
                }
                else if (font.m_bold && font.m_italic) {
                    return new Font(fontFamily, Font.BOLD | Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_underline && font.m_italic) {
                    return new Font(fontFamily, Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_bold) {
                    return new Font(fontFamily, Font.BOLD, (int)font.m_fontSize);
                }
                else if (font.m_underline) {
                    return new Font(fontFamily, Font.PLAIN, (int)font.m_fontSize);
                }
                else if (font.m_italic) {
                    return new Font(fontFamily, Font.PLAIN, (int)font.m_fontSize);
                }
                else {
                    return new Font(fontFamily, Font.PLAIN, (int)font.m_fontSize);
                }
            }
            else {
                if (font.m_bold && font.m_underline && font.m_italic) {
                    return new Font(fontFamily, Font.BOLD | Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_bold && font.m_underline) {
                    return new Font(fontFamily, Font.BOLD, (int)font.m_fontSize);
                }
                else if (font.m_bold && font.m_italic) {
                    return new Font(fontFamily, Font.BOLD | Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_underline && font.m_italic) {
                    return new Font(fontFamily, Font.ITALIC, (int)font.m_fontSize);
                }
                else if (font.m_bold) {
                    return new Font(fontFamily, Font.BOLD, (int)font.m_fontSize);
                }
                else if (font.m_underline) {
                    return new Font(fontFamily, Font.PLAIN, (int)font.m_fontSize);
                }
                else if (font.m_italic) {
                    return new Font(fontFamily,  Font.ITALIC, (int)font.m_fontSize);
                }
                else {
                    return new Font(fontFamily, Font.PLAIN, (int)font.m_fontSize);
                }
            }
    }
    
    /**
     * 获取要绘制的颜色
     *
     * @param dwPenColor 输入颜色
     * @returns 输出颜色
     */
    public long getPaintColor(long dwPenColor){
        return getColor(dwPenColor);
    }

    /**
     * 获取偏移
     */
    public FCPoint getOffset(){
        return new FCPoint(m_offsetX, m_offsetY);
    }
    
    /*
    获取缩放比例
    */
    public void getScaleFactor(RefObject<Double> scaleFactorX, RefObject<Double> scaleFactorY){
        scaleFactorX.argvalue = m_scaleFactorX;
        scaleFactorY.argvalue = m_scaleFactorY;
    }
    
    public Color getSwingColor(long dwPenColor){
        dwPenColor = getPaintColor(dwPenColor);
        int a = 0, r = 0, g = 0, b = 0;
        RefObject<Integer> refA = new RefObject<Integer>(a);
        RefObject<Integer> refR = new RefObject<Integer>(r);
        RefObject<Integer> refG = new RefObject<Integer>(g);
        RefObject<Integer> refB = new RefObject<Integer>(b);
        FCColor.toRgba(this, dwPenColor, refR, refG, refB, refA);
        a = refA.argvalue;
        r = refR.argvalue;
        g = refG.argvalue;
        b = refB.argvalue;
        return new Color(r, g, b, (int) (a * m_opacity));
    }
    

    /**
     * 旋转角度
     *
     * @param op 圆心坐标
     * @param mp 点的坐标
     * @param angle 角度
     * @returns 结果坐标
     */
    public FCPoint rotate(FCPoint op, FCPoint mp, int angle){
        float PI = 3.14159265f;
        FCPoint pt = new FCPoint();
        pt.x = (int) ((mp.x - op.x) * Math.cos(angle * PI / 180) - (mp.y - op.y) * Math.sin(angle * PI / 180) + op.x);
        pt.y = (int) ((mp.x - op.x) * Math.sin(angle * PI / 180) + (mp.y - op.y) * Math.cos(angle * PI / 180) + op.y);
        return pt;
    }

    /**
     * 设置裁剪区域
     *
     * @param rect 区域
     */
    public void setClip(FCRect rect){
        FCRect newRect = new FCRect(rect.left + m_offsetX, rect.top + m_offsetY, rect.right + m_offsetX, rect.bottom + m_offsetY);
        RefObject<FCRect> refAndriodRect = new RefObject<FCRect>(newRect);
        affectScaleFactor(refAndriodRect);
        newRect = refAndriodRect.argvalue;
        m_g.setClip(newRect.left, newRect.top, newRect.right - newRect.left + 1, newRect.bottom - newRect.top + 1);
    }
    
    /*
    * 设置绘图对象
    */
    public void setGraphics(Graphics g){
        m_g = (Graphics2D)g;
    }

    /**
     * 设置直线两端的样式
     *
     * @param startLineCap 开始的样式
     * @param endLineCap 结束的样式
     */
    public void setLineCap(int startLineCap, int endLineCap){
        
    }

    /**
     * 设置偏移
     *
     * @param mp 偏移坐标
     */
    public void setOffset(FCPoint mp){
        m_offsetX = mp.x;
        m_offsetY = mp.y;
    }

    /**
     * 设置透明度
     *
     * @param opacity 透明度
     */
    public void setOpacity(float opacity){
        m_opacity = opacity;
    }

    /**
     * 设置资源的路径
     *
     * @param resourcePath 资源的路径
     */
    public void setResourcePath(String resourcePath){
        m_resourcePath = resourcePath;
    }

    /**
     * 设置旋转角度
     *
     * @param rotateAngle 旋转角度
     */
    public void setRotateAngle(int rotateAngle){
        m_rotateAngle = rotateAngle;
    }

    /**
     * 设置缩放因子
     *
     * @param scaleFactorX 横向因子
     * @param scaleFactorY 纵向因子
     */
    public void setScaleFactor(double scaleFactorX, double scaleFactorY){
        m_scaleFactorX = scaleFactorX;
        m_scaleFactorY = scaleFactorY;
    }

    /**
     * 设置是否支持透明色
     *
     * @returns 是否支持
     */
    public boolean supportTransparent(){
        return true;
    }

    /**
     * 获取文字大小
     *
     * @param text 文字
     * @param font 字体
     * @returns 字体大小
     */
    public FCSize textSize(String text, FCFont font, int width){
        FontMetrics fontMetrics =  m_g.getFontMetrics(getFont(font));
        FCSize size = new FCSize(fontMetrics.stringWidth(text), fontMetrics.getHeight());
        return size;
    }

    /**
     * 获取文字大小
     *
     * @param text 文字
     * @param font 字体
     * @returns 字体大小
     */
    public FCSizeF textSizeF(String text, FCFont font, int width){
        FontMetrics fontMetrics =  m_g.getFontMetrics(getFont(font));
        FCSizeF size = new FCSizeF(fontMetrics.stringWidth(text), fontMetrics.getHeight());
        return size;
    }
}
