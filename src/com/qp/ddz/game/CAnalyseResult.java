package com.qp.ddz.game;

import com.qp.ddz.define.GDF;
/*
*
* ��������Դ����ϵ q344717871
* 
* */

/**
 * �߼������ṹ
 * 
 * @author CHENPENG
 * 
 */
public class CAnalyseResult {
	public int nFourCount; // ��������
	public int nThreeCount; // ��������
	public int nDoubleCount; // ��������
	public int nSignedCount; // ��������
	public int[] nFourCardData; // ��������
	public int[] nThreeCardData; // ��������
	public int[] nDoubleCardData; // ��������
	public int[] nSignedCardData; // ��������

	/**
	 * ���캯��
	 */
	public CAnalyseResult() {
		nFourCount = 0;
		nThreeCount = 0;
		nDoubleCount = 0;
		nSignedCount = 0;
		nFourCardData = new int[GDF.MAX_CARDCOUNT];
		nThreeCardData = new int[GDF.MAX_CARDCOUNT];
		nDoubleCardData = new int[GDF.MAX_CARDCOUNT];
		nSignedCardData = new int[GDF.MAX_CARDCOUNT];
	}

	/**
	 * ����
	 */
	public void ResetData() {
		nFourCount = 0;
		nThreeCount = 0;
		nDoubleCount = 0;
		nSignedCount = 0;
		for (int i = 0; i < GDF.MAX_CARDCOUNT; i++) {
			nFourCardData[i] = 0;
			nThreeCardData[i] = 0;
			nDoubleCardData[i] = 0;
			nSignedCardData[i] = 0;
		}
	}
}
