package com.qp.ddz.game.cmd;

import com.qp.lib.interface_ex.net.ICmd;
import com.qp.lib.utility.NetEncoding;
import com.qp.ddz.define.GDF;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * �����˿�
 * 
 */
public class CMD_S_GameStart implements ICmd {
	public int		nStartUser;								// ��ʼ���
	public int		nCurrentUser;								// ��ǰ���
	public int		nValidCardData;							// �b���˿�
	public int		nValidCardDIndex;							// �b��λ��
	public int[]	nCardData	= new int[GDF.NORMAL_COUNT];	// �˿��б�

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		nStartUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nValidCardData = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nValidCardDIndex = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		for (int i = 0; i < GDF.NORMAL_COUNT; i++) {
			nCardData[i] = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
			nIndex++;
		}
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
