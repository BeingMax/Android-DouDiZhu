package com.qp.lib.cmd;

import com.qp.lib.interface_ex.net.ICmd;
import com.qp.lib.utility.NetEncoding;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public class CMD_GP_UserFaceInfo implements ICmd {
	public int	wFaceID;	// ͷ���ʶ
	public long	dwCustomID; // �Զ���ʶ
	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		wFaceID = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		dwCustomID = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		return nIndex - pos;
	}
}
