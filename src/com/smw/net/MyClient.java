package com.smw.net;

import android.os.Message;

import com.qp.ddz.GameActivity;
import com.qp.lib.utility.Util;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

public class MyClient extends MyTcpClient
							implements ISocketEventListener
{
	 
	
	public MyClient()
	{
		 
	}
	
	public static String TAG="net";
	public int on_close_msg_id;
	
	public   void  onClose( )
	{
		  //��Ϣ����GameActivity����
	 	Message msg=new Message();
		msg.what=on_close_msg_id;  
		GameActivity.getGameActivityInstance().m_handler.sendMessage(msg);
	}
	
	public  boolean onRead(  byte data[],int len){
		
		int msgid=NetEncoding.readFrom4Byte(data);
		Util.v(TAG,"onEventSocketRead  msgid["+ msgid+"]");
	 
		Object obj= GameActivity.getGameActivityInstance().m_PacketHandler.ParsePacket(data, len);
		 if(obj!=null){
			 
			 //��Ϣ����GameActivity����
			 	Message msg=new Message();
				msg.what=1; //��Ϣcode
				msg.obj=obj; //��Ϣ����
				msg.arg1=msgid;
				GameActivity.getGameActivityInstance().m_handler.sendMessage(msg);
		 }
			/* 
			CMD_TEST obj=new CMD_TEST();
			int readlen=obj.ReadFromByteArray(data, 0);
			if(readlen==len)
			{
				//�����С����
				System.out.println("  a["+ obj.a + "] time["+ obj.time + "] name["+ obj.name +"]"
						+ "name2[" + NetEncoding.ByteArrayToString(obj.name2) +"]");
			
				 
//				byte dd[]=new byte[1024];
//				
//				//obj.a=obj.a+1;
//				obj.a=obj.a+2;
//				obj.time=555;
//				obj.name="û�ϵ�abc";
//				
//				int l=obj.WriteToByteArray(dd, 0);
//				System.out.println("send len"+l);
//				send(3,dd,l); 
				
				obj.a=1314;
				obj.time=521;
				obj.name="û��abc12";
				obj.name2[0]='S';
				
				//obj.name2="alibaba3";
				
				send(obj);
				 
			}else{
				//�����С����ȷ!
				
			} 
			 
		}*/

		return true;
	}
 
}
