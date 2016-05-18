package com.smw.cmd.game;

import com.smw.net.ICmd;
import com.smw.net.NetEncoding;

public class MSG_Enter_Room implements ICmd{
	 
	 
		public int cmd;    //���� C2S_ENTER_ROOM
		public int VERSION;//�汾..
		public int game_type;		//GAME_TYPE У����Ϸ���� ���ԾͲ�׼��
		public int room_num;//������
		public int table_num; // 
		public String name;//   char name[20]; //�û���
		public String pwd;//  char pwd[20];  //����
		
		@Override
		public int ReadFromByteArray(byte[] data, int pos) {
			int index = pos;
			
 
			return index - pos;
		}
		
		@Override
		public int WriteToByteArray(byte[] data, int pos) {
			int index = pos;

			index += NetEncoding.write4byte(data, cmd, index); 
			index += NetEncoding.write4byte(data, VERSION, index);
			index += NetEncoding.write4byte(data, game_type, index); 
			index += NetEncoding.write4byte(data, room_num, index);
			index += NetEncoding.write4byte(data, table_num, index);
			index += NetEncoding.writeString(data, name , index, 20,"GBK");
			index += NetEncoding.writeString(data, pwd , index, 20,"GBK");
			
			return index - pos;
		}
}
