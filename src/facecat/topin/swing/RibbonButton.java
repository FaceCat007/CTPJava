package facecat.topin.swing;

import facecat.topin.core.*;
import facecat.topin.btn.*;
import facecat.topin.tab.*;
import java.util.*;

/* 
* 透明按钮
*/
public class RibbonButton extends FCButton {
	/*
	 * 创建透明按钮
	 */
	public RibbonButton() {
		setBackColor(FCColor.None);
		setBorderColor(FCColor.None);
		setFont(new FCFont("Default", 12, false, false, false));
	}

	private int m_arrowType;

	/*
	 * 获取或设置箭头类型
	 */
	public final int getArrowType() {
		return m_arrowType;
	}

	public final void setArrowType(int value) {
		m_arrowType = value;
	}

	/*
	 * 获取或设置是否选中
	 */
	public final boolean isSelected() {
		FCView parent = getParent();
		if (parent != null) {
			FCTabView tabView = (FCTabView) ((parent instanceof FCTabView) ? parent : null);
			if (tabView != null) {
				FCTabPage selectedTabPage = tabView.getSelectedTabPage();
				if (selectedTabPage != null) {
					if (this == selectedTabPage.getHeaderButton()) {
						return true;
					}
				}
			}
		}
		return false;
	}

        /*
	 * 获取要绘制的前景色
	 */
	public long getPaintingTextColor() {
		if (isEnabled()) {
			return MyColor.USERCOLOR27;
		} else {
			return MyColor.USERCOLOR17;
		}
	}

        /*
	 * 重绘背景方法
	 */
	public void onPaintBackground(FCPaint paint, FCRect clipRect) {
		FCNative inative = getNative();
		int width = getWidth(), height = getHeight();
		int mw = width / 2, mh = height / 2;
		FCRect drawRect = new FCRect(0, 0, width, height);
		int cornerRadius = 0;
		boolean isTabHeader = false;
		cornerRadius = 0;
		if (m_arrowType > 0) {
			cornerRadius = 0;
		}

		FCView parent = getParent();
		if (parent != null) {
			FCTabView tabView = (FCTabView) ((parent instanceof FCTabView) ? parent : null);
			if (tabView != null) {
				cornerRadius = 0;
				isTabHeader = true;
			}
		}
		if (isTabHeader) {
			drawRect = new FCRect(drawRect.left, 1, drawRect.right, drawRect.bottom - 1);
			if (isSelected()) {
				paint.fillRect(MyColor.USERCOLOR86, drawRect);
			} else {
				paint.fillRect(MyColor.USERCOLOR68, drawRect);
				paint.drawLine(MyColor.USERCOLOR85, 1, 0, width - 3, height / 4, width - 3, height * 3 / 4);
			}
		} else {
			//paint.fillGradientRect(FCDraw.FCCOLORS_BACKCOLOR, FCDraw.FCCOLORS_BACKCOLOR, drawRect, cornerRadius, 90);
			//paint.drawRect(FCDraw.FCCOLORS_EXCOLOR1, 1, 0, drawRect);
			cornerRadius = 0;
			if (m_arrowType > 0) {
				FCPoint point1 = new FCPoint();
				FCPoint point2 = new FCPoint();
				FCPoint point3 = new FCPoint();
				int dSize = Math.min(mw, mh) / 2;
				switch (m_arrowType) {
					case 1:
						point1.x = mw - dSize;
						point1.y = mh;
						point2.x = mw + dSize;
						point2.y = mh - dSize;
						point3.x = mw + dSize;
						point3.y = mh + dSize;
						break;
					case 2:
						point1.x = mw + dSize;
						point1.y = mh;
						point2.x = mw - dSize;
						point2.y = mh - dSize;
						point3.x = mw - dSize;
						point3.y = mh + dSize;
						break;
					case 3:
						point1.x = mw;
						point1.y = mh - dSize;
						point2.x = mw - dSize;
						point2.y = mh + dSize;
						point3.x = mw + dSize;
						point3.y = mh + dSize;
						break;
					case 4:
						point1.x = mw;
						point1.y = mh + dSize;
						point2.x = mw - dSize;
						point2.y = mh - dSize;
						point3.x = mw + dSize;
						point3.y = mh - dSize;
						break;
				}
				FCPoint[] points = new FCPoint[3];
				points[0] = point1;
				points[1] = point2;
				points[2] = point3;
				paint.fillPolygon(MyColor.USERCOLOR27, points);
			}
			boolean state = false;
			if (this == m_native.getPushedView()) {
				state = true;
				paint.fillRoundRect(MyColor.USERCOLOR58, drawRect, cornerRadius);
			} else if (this == m_native.getHoveredView()) {
				state = true;
				paint.fillRoundRect(MyColor.USERCOLOR6, drawRect, cornerRadius);
			}
			if (state) {
				if (cornerRadius > 0) {
					paint.drawRoundRect(MyColor.USERCOLOR9, 2, 0, drawRect, cornerRadius);
				} else {
					paint.drawRect(MyColor.USERCOLOR9, 1, 0, drawRect);
				}
			}
		}
	}
}