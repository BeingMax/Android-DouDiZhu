package com.qp.ddz.game.cmd;

 
import com.qp.lib.utility.NetEncoding;
import com.smw.net.ICmd;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * �û��з�
 * 
 * @author CHENPENG
 * 
 */
public class CMD_S_CallScore implements ICmd {
	public int cmd;;
	public int nCurrentUser; // ��ǰ���
	public int nCallScoreUser; // �з����
	public int nCurrentScore; // ��ǰ�з�
	public int nUserCallScore; // �ϴνз�

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nCallScoreUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nCurrentScore = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		nUserCallScore = (data[nIndex] < 0
				? (data[nIndex] + 256)
				: data[nIndex]);
		nIndex++;
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
