package com.qp.ddz.game.cmd;

import com.qp.ddz.define.GDF;
import com.smw.net.ICmd;
import com.smw.net.NetEncoding;
 


/**
 * �û�����
 * 
 */
public class CMD_C_OutCard implements ICmd {
	public int cmd;
	public int nCardCount; // ������Ŀ
	public int[] nCardData = new int[GDF.MAX_CARDCOUNT]; // �˿�����

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		int nIndex = pos;
		
		nIndex += NetEncoding.write4byte(data, cmd, nIndex); 
		
		data[nIndex++] = (byte) nCardCount;
		for (int i = 0; i < GDF.MAX_CARDCOUNT; i++) {
			data[nIndex++] = (byte) nCardData[i];
		}
		return nIndex - pos;
	}

}
