package com.qp.ddz.game.cmd;
  
import com.smw.net.ICmd;
import com.smw.net.NetEncoding;

/**
 * �û��з�
 * 
 */ 
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public class CMD_C_CallScore implements ICmd {
	public int		cmd;//	C2S_CallScore
	public int nCallScore; // �з���Ŀ

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		int nIndex = pos;

		nIndex += NetEncoding.write4byte(data, cmd, nIndex); 
 
		data[nIndex++] = (byte) nCallScore;
		return nIndex - pos;
	}

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
