package com.qp.ddz.game.cmd;

 
import com.qp.lib.utility.NetEncoding;
import com.qp.ddz.define.GDF;
import com.smw.net.ICmd;
/**
*
* ��������Դ����ϵ q344717871
* 
*/

/**
 * ����״̬
 * 
 */
public class CMD_S_StatusFree implements ICmd {
	public int cmd;//CMD_S_StatusFree 
	// ��Ϸ����
	public int		lCellScore;									// ��������
	// ʱ����Ϣ
	public byte		cbTimeOutCard;									// ����ʱ��
	public byte		cbTimeCallScore;								// �з�ʱ��
	public byte		cbTimeStarGame;								// ��ʼʱ��
	public byte		cbTimeHeadOutCard;								// �׳�ʱ��

	// ��ʷ����
	public long[]	lTurnScore		= new long[GDF.GAME_PLAYER];	// ������Ϣ
	public long[]	lCollectScore	= new long[GDF.GAME_PLAYER];	// ������Ϣ

	@Override
	public int ReadFromByteArray(byte[] data, int pos) {
		int nIndex = pos;
		cmd = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;

		lCellScore = NetEncoding.read4Byte(data, nIndex);
		nIndex += 4;

		cbTimeOutCard = data[nIndex++];
		cbTimeCallScore = data[nIndex++];
		cbTimeStarGame = data[nIndex++];
		cbTimeHeadOutCard = data[nIndex++];

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
