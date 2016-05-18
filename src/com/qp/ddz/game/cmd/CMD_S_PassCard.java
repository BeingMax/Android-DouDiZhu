package com.qp.ddz.game.cmd;

 
import com.qp.lib.utility.NetEncoding;
import com.smw.net.ICmd;

/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * ��������
 * 
 */
public class CMD_S_PassCard implements ICmd {
	public int cmd; //
	public int nTurnOver; // һ�ֽ���
	public int nCurrentUser; // ��ǰ���
	public int nPassCardUser; // �������

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		nTurnOver = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nPassCardUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
