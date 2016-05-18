package com.qp.ddz.game.cmd;
 
import com.qp.lib.utility.NetEncoding;
import com.smw.net.ICmd;

/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * ׯ����Ϣ
 * 
 */
public class CMD_S_BankerInfo implements ICmd {
	public int cmd;//
	public int nBankerUser; // ׯ�����
	public int nCurrentUser; // ��ǰ���
	public int nBankerScore; // ׯ�ҽз�
	public int nBankerCard[] = new int[3]; // ׯ���˿�

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		nBankerUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nBankerScore = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		for (int i = 0; i < 3; i++) {
			nBankerCard[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
		}
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
