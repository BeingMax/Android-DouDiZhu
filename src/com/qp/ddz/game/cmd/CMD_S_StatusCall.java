package com.qp.ddz.game.cmd;

 
import com.qp.lib.utility.NetEncoding;
import com.qp.ddz.define.GDF;
import com.smw.net.ICmd;

/**
*
* ��������Դ����ϵ q344717871
* 
*/

/***
 * �з�״̬
 * 
 */
public class CMD_S_StatusCall implements ICmd {
	public int cmd;	//GAME_SCENE_Status_Call
	
	// ʱ����Ϣ
	public byte cbTimeOutCard; // ����ʱ��
	public byte cbTimeCallScore; // �з�ʱ��
	public byte cbTimeStarGame; // ��ʼʱ��
	public byte cbTimeHeadOutCard; // �׳�ʱ��

	// ��Ϸ��Ϣ
	public long lCellScore; // ��Ԫ����
	public int nCurrentUser; // ��ǰ���
	public int nBankerScore; // ׯ�ҽз�
	public int[] nScoreInfo = new int[GDF.GAME_PLAYER]; // �з���Ϣ
	public int[] nHandCardData = new int[GDF.NORMAL_COUNT]; // �����˿�

	public long[] lTurnScore = new long[GDF.GAME_PLAYER]; // ������Ϣ
	public long[] lCollectScore = new long[GDF.GAME_PLAYER]; // ������Ϣ

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		
		cbTimeOutCard = data[nIndex++];
		cbTimeCallScore = data[nIndex++];
		cbTimeStarGame = data[nIndex++];
		cbTimeHeadOutCard = data[nIndex++];

		lCellScore = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;
		nCurrentUser = NetEncoding.read2Byte(data, nIndex);
		nIndex += 2;
		nBankerScore = (data[nIndex] < 0 ? (data[nIndex] + 256) : data[nIndex]);
		nIndex++;
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			nScoreInfo[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
		}
		for (int i = 0; i < GDF.NORMAL_COUNT; i++) {
			nHandCardData[i] = (data[nIndex] < 0
					? (data[nIndex] + 256)
					: data[nIndex]);
			nIndex++;
		}
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			lTurnScore[i] = NetEncoding.read8Byte(data, nIndex);
			nIndex += 8;
		}
		for (int i = 0; i < GDF.GAME_PLAYER; i++) {
			lCollectScore[i] = NetEncoding.read8Byte(data, nIndex);
			nIndex += 8;
		}
		return nIndex - pos;
	}

	@Override
	public int WriteToByteArray(byte[] data, int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
