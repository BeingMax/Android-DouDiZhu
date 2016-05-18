package com.example.ddzserver;

  

 
import com.game.ddz.DDZServer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/*
*
* ��������Դ����ϵ q344717871
* 
* */

 
public class DDZService extends Service
{
 
  private Handler objHandler = new Handler();
  
	DDZServer g;
   
	
 
  private Runnable mTasks = new Runnable() 
  {
   
    public void run()
    { 
    	 g.Update(); 
      objHandler.postDelayed(mTasks,30);
    } 
  };
  
  @Override
  public void onStart(Intent intent, int startId)
  {
	    g=new DDZServer();
		g.Start();
		  
		
    objHandler.postDelayed(mTasks, 30);
    super.onStart(intent, startId);
  }

  @Override
  public void onCreate()
  {
    // TODO Auto-generated method stub
    super.onCreate();
  }
  
  @Override
  public IBinder onBind(Intent intent)
  {
    // TODO Auto-generated method stub
    
     //IBinder��k��Service�غc�����мg����k 
    return null;
  }

  @Override
  public void onDestroy()
  {
    // TODO Auto-generated method stub
    g.Close();
    // ��A�ȵ����A����mTasks�����  
    objHandler.removeCallbacks(mTasks);
    super.onDestroy();
  }  
}
