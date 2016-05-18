package com.qp.ddz.game.cmd;
 
import com.qp.lib.utility.NetEncoding;
import com.qp.ddz.define.GDF;
import com.smw.net.ICmd;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * �û�����
 * 
 */
public class CMD_S_OutCard implements ICmd {
	public int cmd; // S_OUT_CARD
	public int		nCardCount;								// ������Ŀ
	public int		nCurrentUser;								// ��ǰ���
	public int		nOutCardUser;								// �������
	public int[]	nCardData	= new int[GDF.MAX_CARDCOUNT];	// �˿��б�

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		
		nCardCount = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nOutCardUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		for (int i = 0; i < nCardCount; i++) {
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
