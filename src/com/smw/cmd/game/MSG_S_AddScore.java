package com.smw.cmd.game;

import com.qp.lib.utility.NetEncoding;
import com.smw.net.ICmd;

public class MSG_S_AddScore implements ICmd{
	public int cmd; //  S2C_AddScore
	public int		 AddScoreUser;						//��ע�û�
	public int			 AddScoreCount;						//��ע��Ŀ
	
	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int index = pos;
		cmd = NetEncoding.read4Byte(data, index);
		index += 4;
		AddScoreUser = NetEncoding.read4Byte(data, index);
		index += 4;
		AddScoreCount = NetEncoding.read4Byte(data, index);
		index += 4;
		return index - pos;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		int index = pos;

 	
		return index - pos;
	}
	
}
