package com.qp.lib.cmd.Bank;

import com.qp.lib.interface_ex.net.ICmd;
import com.qp.lib.utility.NetEncoding;

public class CMD_GP_UserInsureSuccess implements ICmd {

	/** �û�ID **/
	public long		lUserID;
	/** �û���� **/
	public long		lUserScore;
	/** ���н�� **/
	public long		lUserInsure;
	/** ������Ϣ **/
	public String	szDescibeString;
	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		lUserID = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		lUserID = NetEncoding.read8Byte(data, nIndex);
		nIndex += 8;
		lUserID = NetEncoding.read8Byte(data, nIndex);
		nIndex += 8;
		szDescibeString = NetEncoding.wcharUnicodeBytesToString(data, nIndex, 0);
		nIndex += 128 * 2;
		return nIndex - pos;
	}

}
