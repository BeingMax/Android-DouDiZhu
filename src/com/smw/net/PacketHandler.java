package com.smw.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.qp.ddz.GameActivity;
import com.qp.ddz.define.GDF;
import com.qp.ddz.game.GameClientActivity;
import com.qp.ddz.game.GameClientView;
import com.qp.ddz.game.cmd.CMD_S_BankerInfo;
import com.qp.ddz.game.cmd.CMD_S_CallScore;
import com.qp.ddz.game.cmd.CMD_S_GameConClude;
import com.qp.ddz.game.cmd.CMD_S_OutCard;
import com.qp.ddz.game.cmd.CMD_S_PassCard;
import com.qp.ddz.game.cmd.CMD_S_StatusCall;
import com.qp.ddz.game.cmd.CMD_S_StatusFree;
import com.qp.ddz.game.cmd.CMD_S_StatusPlay;
import com.qp.ddz.scene.popupwindow.HornRecord;
import com.qp.ddz.scene.popupwindow.PwdConfig;
import com.qp.ddz.scene.popupwindow.SetMaxim;
import com.qp.ddz.scene.popupwindow.SetNickName;
import com.qp.ddz.scene.rankinglist.RankingListActivity;
import com.qp.ddz.scene.server.ServerActivity;
import com.qp.ddz.scene.shop.ShopActivity;
import com.qp.ddz.scene.shop.adapter.Bill;
import com.qp.ddz.scene.shop.adapter.Product;
import com.qp.ddz.utility.CustomFaceManage;
import com.qp.ddz.utility.MsgRecordItem;
import com.qp.ddz.utility.RankingItem;
import com.qp.lib.cmd.CMD;
import com.qp.lib.help.SDCardHelp;
import com.qp.lib.tag.ServerItem;
import com.qp.lib.utility.Util;
import com.smw.cmd.game.MSG_Enter_Room_Ret;
import com.smw.cmd.game.MSG_S2C_CHAT;
import com.smw.cmd.game.MSG_S2C_TABLEINFO;
import com.smw.cmd.game.MSG_S2C_UPLOAD_IMAGE_RET;
import com.smw.cmd.game.MSG_S2C_USERINFO;
import com.smw.cmd.game.MSG_S_GameStart;
import com.smw.cmd.plazz.MSG_Login_Ret;
import com.smw.cmd.plazz.MSG_S2C_ALERT_DLG;
import com.smw.cmd.plazz.MSG_S2C_BILLS_RET;
import com.smw.cmd.plazz.MSG_S2C_IMAGE_RET;
import com.smw.cmd.plazz.MSG_S2C_PWD_CHANGE_RET;
import com.smw.cmd.plazz.MSG_S2C_RANK_LIST;
import com.smw.cmd.plazz.MSG_S2C_SET_MAXIM_RET;
import com.smw.cmd.plazz.MSG_S2C_SET_NICKNAME_RET;
import com.smw.cmd.plazz.MSG_S2C_SHOP_BUT_RET;
import com.smw.cmd.plazz.MSG_S2C_SHOP_GOODS;
import com.smw.cmd.plazz.MSG_S2C_SYSTEM_CONFIG;
import com.smw.cmd.plazz.MSG_S2C_SYSTEM_CONFIG2;
import com.smw.cmd.plazz.MSG_S2C_ServerList;
import com.smw.cmd.plazz.MSG_S2C_SystemMessage;

public class PacketHandler {
	
	/**
	*
	* ��������Դ����ϵ q344717871
	* 
	*/

	
	public static String TAG="PacketHandler";
	
	 
	public   Object   ParsePacket(byte data[],int len)
	{
		int msgid=NetEncoding.readFrom4Byte(data);
		Util.v(TAG,"ParsePacket  msgid["+ msgid+"]  len:"+len);
		
		ICmd obj=null;
		int readlen=0;
		switch (msgid)
		{
  
			case CMD.S2C_LOGIN_OK : 
			case CMD.S2C_LOGIN_FAILED :
			{
				  obj=new MSG_Login_Ret();
				  break;
			}
			case CMD.S2C_USERINFO:		
			{
				  obj=new MSG_S2C_USERINFO();
				  break;
			}
			case CMD.S2C_ALERT_DLG:		
			{
				  obj=new MSG_S2C_ALERT_DLG();
				  break;
			}
			case CMD.S2C_ENTER_ROOM_FAILED: 
			case CMD.S2C_ENTER_ROOM_OK: 
			{
				obj=new MSG_Enter_Room_Ret();
				break;
			}
			case CMD.S2C_TABLE_INFO:{
				obj=new MSG_S2C_TABLEINFO();
				break;
			}
			case CMD.S_CallScore:{
				obj=new CMD_S_CallScore();
				break;
			}
			case CMD.S2C_GameStart:{
				obj=new MSG_S_GameStart();
				break;
			}  
			case CMD.S_BANKER_INFO:{
				obj=new CMD_S_BankerInfo();
				break;
			}
			case CMD.S2C_CHAT:{
				obj=new MSG_S2C_CHAT();
				break;
			}
			case CMD.S_OUT_CARD:{
				obj=new CMD_S_OutCard();
				break;
			}
			case CMD.S_PASS_CARD:{
				obj=new CMD_S_PassCard();
				break;
			}
			case CMD.S_GAME_CONCLUDE:{
				obj=new CMD_S_GameConClude();
				break;
			}
			
			case CMD.GAME_SCENE_Status_FREE:{
				obj=new CMD_S_StatusFree();
				break;
			}
			
			case CMD.GAME_SCENE_Status_Call:{
				obj=new CMD_S_StatusCall();
				break;
			}
			
			case CMD.GAME_SCENE_Status_Play:{
				obj=new CMD_S_StatusPlay();
				break;
			}
			case CMD.S2C_RANK_LIST:{
				obj=new MSG_S2C_RANK_LIST();
				break;
			}
			case CMD.S2C_SystemMessage:{
				obj=new MSG_S2C_SystemMessage();
				break;
			}
			case CMD.S2C_UPLOAD_IMAGE_RET:{
				obj=new MSG_S2C_UPLOAD_IMAGE_RET();
				break;
			}
			case CMD.S2C_IMAGE_RET:{
				obj=new MSG_S2C_IMAGE_RET();
				break;
			}
			case CMD.S2C_PWD_CHANGE_RET:{
				obj=new MSG_S2C_PWD_CHANGE_RET();
				break;
			}
			case CMD.S2C_SET_NICKNAME_RET:{
				obj=new MSG_S2C_SET_NICKNAME_RET();
				break;
			}
			case CMD.S2C_SET_MAXIM_RET:{
				obj=new MSG_S2C_SET_MAXIM_RET();
				break;
			}
			case CMD.S2C_SHOP_GOODS:{
				obj=new MSG_S2C_SHOP_GOODS();
				break;
			}
			case CMD.S2C_BILLS_RET:{
				obj=new MSG_S2C_BILLS_RET();
				break;
			}
			case CMD.S2C_SHOP_BUT_RET:{
				obj=new MSG_S2C_SHOP_BUT_RET();
				break;
			}
			 
			 
			case CMD.S2C_SYSTEM_CONFIG:{
				obj=new MSG_S2C_SYSTEM_CONFIG();
				break;
			}
			case CMD.S2C_SYSTEM_CONFIG2:{
				obj=new MSG_S2C_SYSTEM_CONFIG2();
				break;
			}
			case CMD.S2C_ServerList:{
				obj=new MSG_S2C_ServerList();
				break;
			}
		} 
		if(obj==null){
			Util.e(TAG, "msgid=="+msgid+"  δ֪��!!!");
			return null;//δ֪��!!!!
		}
		
		readlen=obj.ReadFromByteArray(data, 0);
		if(readlen==len)
		{  
			Util.v(TAG, "msgid=="+msgid+"  �����С����");
			return obj;
		}else{
			Util.e(TAG, "msgid=="+msgid+"  �����С����! "+ readlen+"  "+len);
			return null;
		}
		 
	}
	
	//����
	public   void   ParseCommand(int msgid,Object obj)
	{ 
		Util.v(TAG, "ParseCommand  msgid=="+msgid );
		switch (msgid)
		{
		// �û�����
			//case CMD.SUB_GF_USER_CHAT :
			//	return OnSocketSubFramUserChat(data,len);

			//��½ok
			case CMD.S2C_LOGIN_OK :  	  OnS2C_LOGIN_OK((MSG_Login_Ret)obj);return;
			case CMD.S2C_LOGIN_FAILED :   OnS2C_LOGIN_FAILED((MSG_Login_Ret)obj);return;
			
			case CMD.S2C_ServerList:  OnS2C_ServerList((MSG_S2C_ServerList)obj);return;
			case CMD.S2C_ALERT_DLG:  OnS2C_ALERT_DLG((MSG_S2C_ALERT_DLG)obj);return;
			 
			
			case CMD.S2C_USERINFO:		  OnS2C_USERINFO((MSG_S2C_USERINFO)obj);return;
			case CMD.S2C_ENTER_ROOM_OK:		OnS2C_ENTER_ROOM_OK((MSG_Enter_Room_Ret)obj);return;
			case CMD.S2C_ENTER_ROOM_FAILED:		OnS2C_ENTER_ROOM_FAILED((MSG_Enter_Room_Ret)obj);return;
			case CMD.S2C_TABLE_INFO: 		OnS2C_TABLE_INFO((MSG_S2C_TABLEINFO)obj);return;
			case CMD.S_CallScore: OnS_CallScore((CMD_S_CallScore)obj);return;
			case CMD.S2C_GameStart: OnS2C_GameStart((MSG_S_GameStart)obj);return;
			
			case CMD.GAME_SCENE_Status_FREE: OnGAME_SCENE_Status_FREE((CMD_S_StatusFree)obj);return;
			case CMD.GAME_SCENE_Status_Call: OnGAME_SCENE_Status_Call((CMD_S_StatusCall)obj);return;
			case CMD.GAME_SCENE_Status_Play: OnGAME_SCENE_Status_Play((CMD_S_StatusPlay)obj);return;
			case CMD.S_BANKER_INFO: OnS_BANKER_INFO((CMD_S_BankerInfo)obj);return;
	 
	 
			case CMD.S2C_CHAT: OnS2C_CHAT((MSG_S2C_CHAT)obj);return;
			case CMD.S_GAME_CONCLUDE: OnS_GAME_CONCLUDE((CMD_S_GameConClude)obj);return;
			case CMD.S_OUT_CARD: OnS_OUT_CARD((CMD_S_OutCard)obj);return;
			case CMD.S_PASS_CARD: OnS_PASS_CARD((CMD_S_PassCard)obj);return;
			 
			
			case CMD.S2C_RANK_LIST: OnS2C_RANK_LIST((MSG_S2C_RANK_LIST)obj);return;
			  
			case CMD.S2C_SystemMessage: OnS2C_SystemMessage((MSG_S2C_SystemMessage)obj);return;
			
			
			case CMD.S2C_UPLOAD_IMAGE_RET: OnS2C_UPLOAD_IMAGE_RET((MSG_S2C_UPLOAD_IMAGE_RET)obj);return;
			case CMD.S2C_IMAGE_RET: OnS2C_IMAGE_RET((MSG_S2C_IMAGE_RET)obj);return;
			
			case CMD.S2C_PWD_CHANGE_RET: OnS2C_PWD_CHANGE_RET((MSG_S2C_PWD_CHANGE_RET)obj);return;
			case CMD.S2C_SET_NICKNAME_RET: OnS2C_SET_NICKNAME_RET((MSG_S2C_SET_NICKNAME_RET)obj);return;
		 
			case CMD.S2C_SET_MAXIM_RET: OnS2C_SET_MAXIM_RET((MSG_S2C_SET_MAXIM_RET)obj);return;
			
			case CMD.S2C_SHOP_GOODS: OnS2C_SHOP_GOODS((MSG_S2C_SHOP_GOODS)obj);return;
			case CMD.S2C_BILLS_RET: OnS2C_BILLS_RET((MSG_S2C_BILLS_RET)obj);return;
			case CMD.S2C_SHOP_BUT_RET: OnS2C_SHOP_BUT_RET((MSG_S2C_SHOP_BUT_RET)obj);return;
			
			case CMD.S2C_SYSTEM_CONFIG: OnS2C_SYSTEM_CONFIG((MSG_S2C_SYSTEM_CONFIG)obj);return;
			case CMD.S2C_SYSTEM_CONFIG2: OnS2C_SYSTEM_CONFIG2((MSG_S2C_SYSTEM_CONFIG2)obj);return;
			
			 
		}
	
		Util.e(TAG, "ParseCommand  msgid=="+msgid +" ��Ϣ�޷�����");
		
		obj=null;
		
	}
	
	 
	public boolean OnS2C_ALERT_DLG(MSG_S2C_ALERT_DLG obj) 
	{
		Util.v(TAG,"OnS2C_ALERT_DLG  " + obj.msg );
		//Toast.LENGTH_LONG 1   short=0
		Toast.makeText(GameActivity.getInstance(), obj.msg , obj.type).show();
		
		return true;
	}
	
	public boolean OnS2C_LOGIN_OK( MSG_Login_Ret obj) 
	{
		Util.v(TAG,"OnS2C_LOGIN_OK  " );
		
		GameActivity.dismissDialog();
			//ֱ�ӽ����������
		 GameActivity.getInstance().onShowScene(GDF.SCENE_SERVER);
			//-------------------------------------------------------------------------------
		//�������ֲ�������
	//	 DynamicUI.LoadBGM("bgm2");
		 
			
//			Message msg=new Message();
//			msg.what=0; //��Ϣcode
//			msg.obj=null; //��Ϣ����
//			GameActivity.getGameActivityInstance().m_handler.sendMessage(msg);
			//GameActivity.getGameActivityInstance().m_handler.sendEmptyMessage(0);
		 
		return true;
	}
	
	public boolean OnS2C_LOGIN_FAILED(MSG_Login_Ret obj) 
	{
		Util.v(TAG,"OnS2C_LOGIN_FAILED  " );
			//if(null!=ServerActivity.instance) ServerActivity.instance.to
			if(obj.errcode==2){
				Toast.makeText(GameActivity.getInstance(), "��¼ʧ��!�ظ���½!", Toast.LENGTH_SHORT).show();
				GameActivity.dismissDialog();
			}
			if(obj.errcode==1){
				Toast.makeText(GameActivity.getInstance(), "��¼ʧ��!�˺Ż��������!", Toast.LENGTH_SHORT).show();
				GameActivity.dismissDialog();
			}
			if(obj.errcode==3){
				Toast.makeText(GameActivity.getInstance(), "��Ϸ�汾����!", Toast.LENGTH_SHORT).show();
				GameActivity.dismissDialog();
			}
		return true;
	}
	
	public boolean OnS2C_USERINFO(MSG_S2C_USERINFO obj)
	{ 
		GameActivity.dismissDialog();
		
		Util.v(TAG,"OnS2C_USERINFO   nickname: "+obj.nickname);
		   
		GameActivity.getGameActivityInstance().m_meUID=obj.uid; 
 
		UserItem u=GameActivity.getGameActivityInstance().sm_getme();
		if(u!=null){
			 
		}else{
			 u=new UserItem();
		}
			 
			u.money= obj.money;
			u.rank=obj.rank;
			u.bankscore=obj.bankscore;
			u.wincount=obj.wincount;
			u.losecount=obj.losecount;
			u.escapecount=obj.escapecount;
			u.uid=obj.uid;
			u.nickname=obj.nickname;
			u.TableID=-1;
			u.UserStatus= GDF.US_FREE;
			u.SetFaceCheckSum(obj.img_checksum);
			u.maxim=obj.maxim;
			
			//������ �����
			if(GameActivity.getGameActivityInstance().sm_getme()==null) GameActivity.getGameActivityInstance().m_userlist.add(u);
			
			//����һ�� ���û�� �ͻ��Զ�����ͷ��
			u.GetFaceImage();
		 
		 
		return true;
	}
	
	public boolean OnS2C_ENTER_ROOM_OK(MSG_Enter_Room_Ret obj)
	{ 
		Util.v(TAG,"OnS2C_ENTER_ROOM_OK  table_num: "+obj.table_num);
		  
		GameActivity.getGameActivityInstance().sm_getme().TableID=obj.table_num;
		
		//ֱ�ӽ�����Ϸ���ӽ���
		 GameActivity.getInstance().onShowScene(GDF.SCENE_GAME);
 
		return true;
	}
	
	public boolean OnS2C_ENTER_ROOM_FAILED(MSG_Enter_Room_Ret obj)
	{ 
		Util.v(TAG,"OnS2C_ENTER_ROOM_FAILED    "+obj.table_num);
		 if(obj.errcode==2)
		 {
			 Toast.makeText(GameActivity.getInstance(), "��������!", Toast.LENGTH_SHORT).show();
			 
		 } 
		 if(obj.errcode==3){
			Toast.makeText(GameActivity.getInstance(), "��Ϸ�汾����!���������¿ͻ���!", Toast.LENGTH_SHORT).show();
			 
		 }
		 if(obj.errcode==4){
				Toast.makeText(GameActivity.getInstance(), "��Ϸ���Ͳ���!", Toast.LENGTH_SHORT).show();
				  
		 }
		 if(obj.errcode==5){
				Toast.makeText(GameActivity.getInstance(), "���ֲ��ڷ�Χ��!", Toast.LENGTH_SHORT).show();
				  
		 }
		 if(obj.errcode==55)
		 {
			 Toast.makeText(GameActivity.getInstance(), " �Ѿ�����!�������ظ���½!", Toast.LENGTH_SHORT).show();
		 }
		 
		 
		 
		return true;
	}
	
	public boolean OnS2C_TABLE_INFO(MSG_S2C_TABLEINFO obj)
	{
		Util.v(TAG,"OnS2C_TABLE_INFO    "  );
		
		GameClientView.playerArray[0]=0;
		GameClientView.playerArray[1]=0;
		GameClientView.playerArray[2]=0;
 
		
		 
		
		//
		for(int i=0;i<3;i++)
		{
			GameClientView.playerArray[i]=0;
			
			if(obj.uid[i]>0)
			{
				Util.v(TAG, "uid:"+obj.uid[i] + " ���: "+obj.nickname[i] +   "  money:"+ obj.money[i] );
				
				//��¼uid
				GameClientView.playerArray[i]= obj.uid[i];
				
				 
				UserItem item=GameActivity.getGameActivityInstance().sm_getuser(obj.uid[i]);
				if( item ==null)
				{
					//������,���
					UserItem u=new UserItem();
					u.money= obj.money[i] ;
					u.rank=obj.rank[i];
					u.uid=obj.uid[i];
					u.nickname=obj.nickname[i];
					u.TableID=obj.TableID;
					u.UserStatus=obj.UserStatus[i];
					u.bankscore=0;
					u.wincount=obj.wincount[i];
					u.losecount=obj.losecount[i];
					u.escapecount=obj.escapecount[i];
					u.maxim=obj.maxim[i];
					u.SetFaceCheckSum(obj.img_checksum[i]);
					u.ChairID=i;
					GameActivity.getGameActivityInstance().m_userlist.add(u);
				}else{
					//����,����
					item.money=obj.money[i] ;
					item.rank=obj.rank[i];
					item.uid=obj.uid[i];
					item.nickname=obj.nickname[i];
					item.TableID=obj.TableID;
					item.UserStatus=obj.UserStatus[i];
					item.wincount=obj.wincount[i];
					item.losecount=obj.losecount[i];
					item.escapecount=obj.escapecount[i];
					item.ChairID=i;
					item.SetFaceCheckSum(obj.img_checksum[i]);
				}
			}
			 
		}
		
		 
		//�������
		if(GameClientView.instance!=null){
			GameClientView.instance.UpdateGameView(); 
			//GameClientActivity.instance.UpdateTableStatus( obj.GameStatus );
			GameClientView.instance.postInvalidate();
		}
		 
		
		return true;
	}
	
	public boolean OnS_CallScore(CMD_S_CallScore obj)
	{
		Util.v(TAG,"OnS_CallScore    "  );
		if(GameClientActivity.instance!=null){
			GameClientActivity.instance.OnSubCallScore(obj);  
		}
		 
		return true;
	}
	
	public boolean OnS2C_GameStart(MSG_S_GameStart obj)
	{
		Util.v(TAG,"OnS2C_GameStart    "  );
		if(GameClientActivity.instance!=null){
			GameClientActivity.instance.OnSubGameStart( obj);  
		}
		 
		return true;
	}	
	public boolean OnS_BANKER_INFO(CMD_S_BankerInfo obj)
	{
		Util.v(TAG,"OnS_BANKER_INFO    "  );
		if(GameClientActivity.instance!=null){
			GameClientActivity.instance.OnSubBankerInfo( obj);  
		}
		 
		return true;
	}	
	public boolean OnS_OUT_CARD(CMD_S_OutCard obj)
	{
		Util.v(TAG,"OnS_OUT_CARD    "  );
		if(GameClientActivity.instance!=null){
			GameClientActivity.instance.OnSubOutCard( obj);  
		}
		 
		return true;
	}	
	public boolean OnS_PASS_CARD(CMD_S_PassCard obj)
	{
		Util.v(TAG,"OnS_PASS_CARD    "  );
		if(GameClientActivity.instance!=null){
			GameClientActivity.instance.OnSubPassCard( obj);  
		}
		 
		return true;
	}	
	 
	public boolean OnGAME_SCENE_Status_FREE(CMD_S_StatusFree c){
		Util.v(TAG,"OnGAME_SCENE_Status_FREE    "  );
		if(GameClientActivity.instance!=null  )
		{
			GameClientActivity.instance.OnEventSceneMessage(GDF.GAME_SCENE_FREE,c);  
		}
		return true;
	}
	public boolean OnGAME_SCENE_Status_Call(CMD_S_StatusCall c){
		Util.v(TAG,"OnGAME_SCENE_Status_Call    "  );
		if(GameClientActivity.instance!=null  )
		{
			GameClientActivity.instance.OnEventSceneMessage(GDF.GAME_SCENE_CALL,c);  
		}
		return true;
	}
	public boolean OnGAME_SCENE_Status_Play(CMD_S_StatusPlay c){
		Util.v(TAG,"OnGAME_SCENE_Status_Play    "  );
		if(GameClientActivity.instance!=null  )
		{
			GameClientActivity.instance.OnEventSceneMessage(GDF.GAME_SCENE_PLAY,c);  
		}
		return true;
	}
 
	//��Ϸ����
	public boolean OnS_GAME_CONCLUDE(CMD_S_GameConClude obj)
	{
		Util.v(TAG,"OnS_GAME_CONCLUDE    "  );
		if(GameClientActivity.instance!=null){
		 	 GameClientActivity.instance.OnSubGameConclude(obj);  
		}
		 
		return true;
	}
	 
	public boolean OnS2C_CHAT(MSG_S2C_CHAT obj)
	{
		Util.v(TAG,"OnOnS2C_CHAT    "  );
		if(GameClientActivity.instance!=null){
			if(obj.TargetUserID<0)
			{
				 
				for(int i=0;i<3;i++)
				{
					if(GameClientView.instance.playerArray2[i]==obj.SendUserID)
					{
						GameClientActivity.instance.onUserChat(i,obj.ChatString);  
					}
				}
				 
			}
			
			 
		}
		 
		return true;
	}
	 
	public boolean OnS2C_RANK_LIST(MSG_S2C_RANK_LIST obj)
	{
		Util.v(TAG,"OnS2C_RANK_LIST    "  );

		if(RankingListActivity.instance!=null){
			for(int i=0;i<10;i++){ 
				RankingItem item=new RankingItem();
				item.szScore= Integer.toString(obj.score[i]);
				item.szName=obj.nickname[i];
				item.imgchecksum=obj.imgchecksum[i];
				item.szMaxim=obj.maxim[i];
				RankingListActivity.instance.m_RankItemList.add(item);
				RankingListActivity.instance.onLoadCompelet();
			}
			 
		}
 
		
		return true;
	}
	
	public boolean OnS2C_SystemMessage(MSG_S2C_SystemMessage cmd)
	{
		Util.v(TAG,"OnS2C_SystemMessage    "  );

		//ȫ��㲥��ϵͳ��Ϣ
		if(cmd.type==0)
		{
			HornRecord.addHornInfo(MsgRecordItem.TYPE_HORN, "[ϵͳ]:" + cmd.ChatString);

			if (ServerActivity.instance != null)
				ServerActivity.instance.onSubSystemMessage("[ϵͳ]  " + cmd.ChatString, true);
			if (GameClientActivity.getInstance() != null)
				GameClientActivity.getInstance().onSubSystemMessage("[ϵͳ]  " + cmd.ChatString, true);

		}else if(cmd.type==-1){
			HornRecord.addHornInfo(MsgRecordItem.TYPE_HORN, "[����] " + cmd.ChatString);

			if (ServerActivity.instance != null)
				ServerActivity.instance.onSubSystemMessage("[����]  " + cmd.ChatString, false);
			if (GameClientActivity.getInstance() != null)
				GameClientActivity.getInstance().onSubSystemMessage("[����]  " + cmd.ChatString, false);

		}
		
	 
		
		return true;
	}
	
	
	public boolean OnS2C_UPLOAD_IMAGE_RET(MSG_S2C_UPLOAD_IMAGE_RET cmd)
	{
		Util.v(TAG,"OnS2C_UPLOAD_IMAGE_RET    "  );
		 
		 
		if(cmd.ret==-3){
			Toast.makeText(GameActivity.getInstance(), "ͼƬ��ʽ�޷�ʶ�� !", Toast.LENGTH_SHORT).show();
			  
		} 
		if(cmd.ret==-2){
			Toast.makeText(GameActivity.getInstance(), "ͼƬ���̫�� !", Toast.LENGTH_SHORT).show();
			  
		} 
		 if(cmd.ret==-1){
				Toast.makeText(GameActivity.getInstance(), "ͷ���ظ� !", Toast.LENGTH_SHORT).show();
				  
		 } 
		 if(cmd.ret==1){
				Toast.makeText(GameActivity.getInstance(), "ͷ���ϴ��ɹ�!", Toast.LENGTH_SHORT).show();

				GameActivity.getGameActivityInstance().sm_getme().SetFaceCheckSum(cmd.img_checksum);
		 }if(cmd.ret==0){
				Toast.makeText(GameActivity.getInstance(), "ͷ���ϴ�ʧ��!", Toast.LENGTH_SHORT).show();
				  
		 }
		return true;
	}
	
	//imge
	public boolean OnS2C_IMAGE_RET(MSG_S2C_IMAGE_RET cmd)
	{
		Util.v(TAG,"OnS2C_IMAGE_RET    "  );

		//SDwrite(cmd.img_checksum+"",cmd.img);

		if (CustomFaceManage.getInstance() != null) {
			if(null!=CustomFaceManage.getInstance().getBitmap( Integer.toHexString(cmd.img_checksum),cmd.img_checksum)){
				return true;//�Ѿ�������!!
			} 
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = 1;
		opts.inPreferredConfig = Bitmap.Config.ARGB_4444;//���� ��Լ�ڴ�
		//תbmp
		Bitmap bm =BitmapFactory.decodeByteArray(cmd.img, 0, cmd.img_size,opts);
		if(bm==null){
			//���ݲ���ȷ Ҳ��תʧ��!
			return false;
		}
//	//	 
//		//��ұ���  
		UserItem me=GameActivity.getGameActivityInstance().sm_getme();
		
		if(me!=null 
			&& me.GetFaceCheckSum() == cmd.img_checksum )
		{ 
		//	if(ServerActivity.instance!=null) ServerActivity.instance.FillHead();
		}
//		 
		//���뻺��
		if (CustomFaceManage.getInstance() != null) {
			CustomFaceManage.getInstance().onAddBitmap(Integer.toHexString(cmd.img_checksum),bm);//lUserID + "-" + lCustomID, bitmap);
		}
		
		//ͷ��浽sd�� 
		SDCardHelp.SaveFile( cmd.img,  cmd.img_size,  GDF.GAME_SD_PATH+"/userimg" , Integer.toHexString(cmd.img_checksum)+ ".png");
		
	//	if (GameClientActivity.getInstance() != null) {
	//		GameClientActivity.getInstance().m_ClockHandler.obtainMessage(GameClientActivity.IDI_UPDATEVIEW).sendToTarget();
	//	}
		Util.i(TAG, "�����ɹ�");
		
		return true;
	}
	//����
	public boolean OnS2C_PWD_CHANGE_RET(MSG_S2C_PWD_CHANGE_RET cmd)
	{
		Util.v(TAG,"OnS2C_PWD_CHANGE_RET    "  );
		if(cmd.ret==1){
			Toast.makeText(GameActivity.getInstance(), "�����޸ĳɹ�!", Toast.LENGTH_SHORT).show();
			
			GameActivity.m_pwd=PwdConfig.pwd;
			
		}
		if(cmd.ret==0){
			Toast.makeText(GameActivity.getInstance(), "�����޸�ʧ��!", Toast.LENGTH_SHORT).show();
			 
		}
		return true;
	}
	//�ǳ�
	public boolean OnS2C_SET_NICKNAME_RET(MSG_S2C_SET_NICKNAME_RET cmd)
	{
		Util.v(TAG,"OnS2C_SET_NICKNAME_RET    "  );
		if(cmd.ret==1){
			Toast.makeText(GameActivity.getInstance(), "�ǳ��޸ĳɹ�!", Toast.LENGTH_SHORT).show();
			
			GameActivity.getGameActivityInstance().sm_getme().nickname=SetNickName.nickname;
			
		}
		if(cmd.ret==-1){
			Toast.makeText(GameActivity.getInstance(), "���ǳ��Ѿ�����!", Toast.LENGTH_SHORT).show();
			 
		}
		if(cmd.ret==0){
			Toast.makeText(GameActivity.getInstance(), "�ǳ��޸�ʧ��!", Toast.LENGTH_SHORT).show();
			 
		}
		return true;
	}
	//ǩ��
	public boolean OnS2C_SET_MAXIM_RET(MSG_S2C_SET_MAXIM_RET cmd)
	{
		Util.v(TAG,"OnS2C_SET_MAXIM_RET    "  );
		if(cmd.ret==1){
			Toast.makeText(GameActivity.getInstance(), "ǩ���޸ĳɹ�!", Toast.LENGTH_SHORT).show();
			
			GameActivity.getGameActivityInstance().sm_getme().maxim=SetMaxim.maxim;
			
		}
		if(cmd.ret==0){
			Toast.makeText(GameActivity.getInstance(), "ǩ���޸�ʧ��!", Toast.LENGTH_SHORT).show();
			 
		}
		return true;
	}
	//���̵�
	public boolean OnS2C_SHOP_GOODS(MSG_S2C_SHOP_GOODS cmd)
	{
		Util.v(TAG,"OnS2C_SHOP_GOODS    "  );
		
		if(ShopActivity.instance!=null){
			Product o=new Product();
			o.id=cmd.id;
			o.goodsname=cmd.goodsname;
			o.goodsprice=cmd.goodsprice;
			o.desc=cmd.desc;
			
			ShopActivity.instance.m_ProductList.add(o);
			ShopActivity.instance.onLoadCompelet();
		}
		
		return true;
	}
	//��������
	public boolean OnS2C_BILLS_RET(MSG_S2C_BILLS_RET cmd)
	{
		Util.v(TAG,"OnS2C_BILLS_RET    "  );
		
		if(ShopActivity.instance!=null){
			Bill o=new Bill();
			o.billID=cmd.billID;
			o.goodsname=cmd.goodsname;
			o.buytime=cmd.buytime;
			o.pstate=cmd.pstate;
			
			ShopActivity.instance.m_BillList.add(o);
			ShopActivity.instance.onLoadCompelet();
		}
		
		return true;
	}
	//����ɹ� ���µ��ɹ�
	public boolean OnS2C_SHOP_BUT_RET(MSG_S2C_SHOP_BUT_RET cmd)
	{
		Util.v(TAG,"OnS2C_SHOP_BUT_RET    "  );
		if(cmd.ret==1)
			Toast.makeText(GameActivity.getInstance(), "�µ��ɹ�!", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(GameActivity.getInstance(), "�µ�ʧ��!", Toast.LENGTH_SHORT).show();
		
		return true;
	}
	
	//ϵͳ���� ����
	public boolean OnS2C_SYSTEM_CONFIG(MSG_S2C_SYSTEM_CONFIG cmd)
	{
		Util.v(TAG,"OnS2C_SYSTEM_CONFIG    "  );
		
		 //���⴦��
		if(cmd.scene.equals("bgm1")){
		//	DynamicUI.SaveBG(GameActivity.getInstance(), cmd.scene , true , cmd.url );
		}else{
		//	DynamicUI.SaveBG(GameActivity.getInstance(), cmd.scene , true , cmd.url );
		}
	 
		 
		return true;
	}
	
	//ϵͳ���� �ر�
	public boolean OnS2C_SYSTEM_CONFIG2(MSG_S2C_SYSTEM_CONFIG2 cmd)
	{
		Util.v(TAG,"OnS2C_SYSTEM_CONFIG2    "  );
		//�浽sd�� 
		//SDCardHelp.SaveFile( cmd.img,  cmd.img_size,  GDF.GAME_SD_PATH+"/userimg" , Integer.toHexString(cmd.img_checksum)+ ".png");
		 
		//DynamicUI.SaveBG(GameActivity.getInstance(), cmd.scene , false , "" );
		  
		return true;
	}
	
	//�������б�
	public boolean OnS2C_ServerList(MSG_S2C_ServerList cmd)
	{
		Util.v(TAG,"OnS2C_ServerList    "  );
		if(ServerActivity.instance!=null && cmd.gametype==GDF.GAME_TYPE )
		{
			ServerItem i  = new  ServerItem(); 
			i.nServerPort= cmd.port ;// 5001
			i.nServerID=cmd.gameroomid;//10
			i.szServerName= cmd.servername;//����������
			i.szServerUrl= cmd.ip;//127.0.0.1
			i.lMinEnterScore=cmd.ScoreMIN;  //100
			i.lMaxEnterScore=cmd.ScoreMAX; //99999999
			
			ServerActivity.instance.serverlist.add(i);
			ServerActivity.instance.onLoadCompelet();
			
			//ServerActivity.instance.FillHead();
		}
		  
		return true;
	}
 
}
