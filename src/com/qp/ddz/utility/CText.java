package com.qp.ddz.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public class CText {
	/**
	 * �ض����
	 * 
	 * @param canvas
	 *            �豸
	 * @param str
	 *            ��Ϣ
	 * @param x
	 *            Ŀ��X����
	 * @param y
	 *            Ŀ��Y����
	 * @param pixel
	 *            �������
	 * @param paint
	 *            ��������
	 */

	public static final void DrawTextEllip(Canvas canvas, String str, int x, int y, float pixel, Paint paint) {
		// ������Ч
		if (str == null || str.equals("")) {
			return;
		}
		// ����ȷ��
		Paint p = paint == null ? new Paint() : paint;
		// ����ȷ��
		float strw = p.measureText(str);
		// Y��������
		float ny = y + GetTextY(p);

		// �жϽض�
		int count = 0;
		if (strw > pixel) {
			count = paint.breakText(str, true, pixel - p.measureText("..."), null);
			if (count > 0) {
				String temp = str.substring(0, count) + "...";
				canvas.drawText(temp, x, ny, p);
			}
		} else {
			canvas.drawText(str, x, ny, p);
		}
	}

	/**
	 * �ض����
	 * 
	 * @param canvas
	 * @param str
	 * @param rc
	 * @param paint
	 */
	public static final void DrawTextEllip(Canvas canvas, String str, Rect rc, Paint paint) {
		// ������Ч
		if (str == null || str.equals("") || rc == null) {
			return;
		}
		// ����ȷ��
		Paint p = paint == null ? new Paint() : paint;
		// ����ȷ��
		float strw = p.measureText(str);
		// Y��������
		float ny = rc.top + GetTextY(p);
		// �жϽض�
		int count = 0;
		if (strw > rc.width()) {
			count = paint.breakText(str, true, rc.width() - p.measureText("..."), null);
			if (count > 0) {
				String temp = str.substring(0, count) + "...";
				canvas.drawText(temp, rc.left, ny, p);
			}
		} else {
			canvas.drawText(str, rc.left, ny, p);
		}

	}

	/**
	 * ��ȡ�����
	 * 
	 * @param p
	 * @return
	 */
	public static final float GetTextHeight(Paint p) {
		FontMetrics fm = p.getFontMetrics();
		return (float) Math.ceil(fm.descent - fm.ascent);
	}

	/**
	 * ����Yλ��
	 * 
	 * @param p
	 * @param nH
	 * @return
	 */
	public static final float GetTextY(Paint p) {
		FontMetrics fm = p.getFontMetrics();
		return (GetTextHeight(p) + p.getTextSize()) / 2 - fm.bottom + fm.descent - fm.ascent + fm.top;

	}

	public static String getTimeString() {
		Date now = new Date();

		SimpleDateFormat temp = new SimpleDateFormat("hh:mm");

		String str = temp.format(now);

		return str;

	}

}
