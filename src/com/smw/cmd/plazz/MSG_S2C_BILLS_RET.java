package com.smw.cmd.plazz;

import com.smw.net.ICmd;
import com.smw.net.NetEncoding;

public class MSG_S2C_BILLS_RET implements ICmd{
	public int cmd;	//	S2C_BILLS_RET
	public int billID; //�������
	public String goodsname;//char goodsname[32];//��Ʒ����
	public String buytime; //char buytime[20];//�µ�ʱ��
	public int pstate;//����״̬ 1δ֧�� 2���׳ɹ� 3����ȡ�� 
 
	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int index = pos;
		
		cmd = NetEncoding.read4Byte(data, index);
		index += 4;
	  
		billID = NetEncoding.read4Byte(data, index);
		index += 4;
		
		goodsname=NetEncoding.byteToString(data,index,32);
		index+=32;
		
		buytime = NetEncoding.byteToString(data, index,32);
		index +=32;
		
		pstate=NetEncoding.read4Byte(data,index);
		index+=4;
		
		 
		return index - pos;
	}
	
	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		int index = pos;


		return index - pos;
	}
	
}
