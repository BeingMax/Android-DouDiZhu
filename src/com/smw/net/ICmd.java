package com.smw.net;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public interface ICmd
{
	public abstract int WriteToByteArray(byte data[], int pos);

	public abstract int ReadFromByteArray(byte data[], int pos);

}
