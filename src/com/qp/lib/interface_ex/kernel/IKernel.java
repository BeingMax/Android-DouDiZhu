package com.qp.lib.interface_ex.kernel;

import java.util.ArrayList;
import com.qp.lib.interface_ex.user.IUserItem;
import com.qp.lib.tag.AccountsDB;
import com.qp.lib.tag.UserScore;
import com.qp.lib.tag.UserStatus;

public interface IKernel {

	public abstract void onDestroy();

	public abstract AccountsDB getLoginInfo();

	public abstract ArrayList<AccountsDB> getAccountList();

	public abstract void onUpdateAccountList();

	public abstract void onDeletAccount(int i);

	public abstract int getLoginEntrance();

	public abstract boolean sendSocketData(int i, int j, Object obj);

	public abstract boolean autoSitDown();

	public abstract boolean sendGameOption();

	/** �û���Ϣ **/
	public boolean frushUserInfo(long userid, int tablepos);

	/** ������Ϣ **/
	public boolean sendUserChairInfoReq(int table, int chair);

	/** ����׼�� **/
	public boolean sendUserReady(byte data[], int datasize);

	/** �Թۿ��� **/
	public boolean sendUserLookon(long userid, boolean allowlookon);

	/** ���ͱ��� **/
	public boolean sendUserExpression(long targetuserid, int itemindex);

	/** �������� **/
	public boolean sendUserChatMessage(long targetuserid, String Chat, long color);

	/** �������� **/
	public boolean sendUserHorn(String szinfo);

	public abstract boolean PerformStandupAction(boolean bforce);

	public abstract IUserItem getMeUserItem();

	public abstract IUserItem getTableUserItem(int i, int j);

	public abstract IUserItem searchUserByUserID(long l);

	public abstract IUserItem searchUserByGameID(long l);

	public abstract IUserItem searchUserByNickName(String s);

	public abstract IUserItem searchUserByTableInfo(int i, int j);

	public abstract IUserItem onEventUserEnter(IUserItem iuseritem, boolean flag);

	public abstract void onEventUserLeave(long l, boolean flag);

	public abstract IUserItem onEventUserScore(long l, UserScore userscore, UserScore userscore1);

	public abstract IUserItem onEventUserStatus(long l, UserStatus userstatus, UserStatus userstatus1);

	public abstract IUserItem onEventCustomFace(IUserItem iuseritem, boolean flag);

	public abstract long getUserRight();

	public abstract long getMasterRight();
}
