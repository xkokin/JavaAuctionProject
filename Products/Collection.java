package Products;


import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * Class that represents NFT item 
 */
public class Collection extends ColItem{
	
	private String name;
	
	/**
	 * object of an inner class in which timer will be set and calculated
	 */
	private Schedule sch;
	
	public void SetName (String name) {
		this.name = name;
	}
	public String GetName(){
		return this.name;
	}
	
	
	
	public String GetCollection() {
		return this.collection;
	}
	
	/**
	 * Gets timer from Schedule object 
	 */
	public int GetSchedule() {
		return sch.GetTimer();
	}
	/**
	 * Creates Schedule  object
	 * Calculates timer to set until auction is started 
	 */
	public void SetSchedule() {
		sch = new Schedule();
		TimerInterface interf = sch;
		interf.CalculateTime();
		System.out.println("Your registered auction "+GetName() +" will be started in " +sch.GetTimer()/60 + " minutes");
		
	}
	
	public void StartTimer() {
		sch.StartTimer();
	}
	public boolean IsStarted() {
		return sch.IsStarted();
	}
	public boolean IsEnded() {
		return sch.IsEnded();
	}
	
	/**
	 * class that manages timer set for certain auction for current guest 
	 * @author HlibKokin
	 *@version 13/05/2022
	 */
	private class Schedule implements TimerInterface{
		
		
		public interface Calc{
			public int Calculate(int time, int now);
		}
		
		
		private boolean started = false;
		private boolean ended = false;
		private int timer;
		private int after = 120;
		

		/**
		 * Creates and starts new Thread timer 
		 * when timer is over - notifies the guest that auction is available to start
		 * @see TimerInterface#Notify()  
		 * then start another timer fo 2 minutes, if guest won't join the auction, he will be unregistered from it
		 */
		public void StartTimer() {
			Timer AfterAuc = new Timer();
			TimerTask task2 = new TimerTask() {
				public void run() {
					Platform.runLater(new Runnable() {
						public void run() {
							if(after > 0) {
								after--;
							}
							else {
								AfterAuc.cancel();
								ended = true;
								
								return;
							}	
						}
					});
				}
			};
			Timer TillAuc = new Timer();
			TimerTask task1 = new TimerTask() {
				public void run() {
					Platform.runLater(new Runnable() {
						public void run() {
							if(timer > 0) {
								timer--;
							}
							else {
								TillAuc.cancel();
								sch.Notify();
								AfterAuc.scheduleAtFixedRate(task2, 0, 1000);
								return;
							}	
						}
					});
				}
			};
			
			TillAuc.scheduleAtFixedRate(task1, 0, 1000);
			
			started = true;
		}
		public boolean IsStarted() {
			return started;
		}
		public boolean IsEnded() {
			return ended;
		}
		
		public int GetTimer() {
			return this.timer;
		}
		
		
		/**
		 * Parses the time from string format 00:00 to number of seconds 
		 * that left from current time to scheduled time of auction 
		 * 
		 * method converts characters into numbers and substracts the current time
		 * interprets the result in seconds for the timer
		 */
		
		
		public void CalculateTime() {
			timer=0;
			
			Calc c = (time, now) -> Math.abs((time - now))-1;	//lambda vyraz 
			
			String time = new String ();
			time = GetTime();
			LocalTime t = LocalTime.now();
			String now = t.toString();
			System.out.println("now: "+now);
			String temp = new String();
			
			char hours1 = time.charAt(0);
			char hours2 = time.charAt(1);	
			if(hours2 == '0' && hours1 == now.charAt(0))hours2=':';
			char nowHrs1 = now.charAt(0);

			char nowHrs2 = now.charAt(1);
			if(nowHrs2 == '0') nowHrs2 =':';
			temp = time.substring(0, 2);
			
			if(temp.compareTo(now.substring(0,2)) != 0 ) {
				if(hours1 <nowHrs1){
					timer = 0;
					System.out.println("Auction has already been");
					return;
				}
				else if(hours1 >= nowHrs1) {
					if(hours1 != nowHrs1) {
						timer +=(c.Calculate(hours1, nowHrs1))* 10;  //pouzitie lambda vyrazu
						//System.out.println("timer for auction"+GetName() +" is upped hours1: " + timer/60 + " mins");
					}
					if(hours2>=nowHrs2) {
						if(hours2 != nowHrs2) {
							timer +=(c.Calculate(hours2, now.charAt(1)));
							timer *= 3600;
							//System.out.println("timer for auction "+GetName() +" is upped hours2: " + timer/60+ " mins");
						}
					}else {
						//can = false;
						timer = 0;
						System.out.println("Auction has already been");
						return;
					}
				}
				char mins1 = time.charAt(3);
				if(mins1 == '0') mins1 ='6';
				char mins2 = time.charAt(4);
				if(mins2 == '0')mins2=':';
				char nowMins1 = now.charAt(3);
				if(nowMins1 == '0') mins1 ='6';
				char nowMins2 = now.charAt(4);
				if(nowMins2 == '0') mins1 =':';
				
				timer += ((c.Calculate(mins1, nowMins1))* 10)*60;
				//System.out.println("timer for auction "+GetName() +" is upped mins1: " + timer/60+ " mins");
				
				timer += (c.Calculate(mins2, nowMins2))*60;
				//System.out.println("timer for auction "+GetName() +" is upped mins2: " + timer/60+ " mins");
			
				
				//can = true;
				return;
				}
			else {
				
				
				char mins1 = time.charAt(3);
				char mins2 = time.charAt(4);
				if(mins1 != now.charAt(3))mins2 += 10;
				char nowMins1 = now.charAt(3);
				char nowMins2 = now.charAt(4);
				if(nowMins2 == '0'&&mins2 == ':') nowMins2 =':';
				
				
			
				
				if(mins1 <nowMins1){
					//can = false;
					timer = 0;
					System.out.println("Auction has already been");
					return;
				}
				else if(mins1 >= nowMins1) {
					if(mins1 != nowMins1) {
						timer +=(c.Calculate(mins1, nowMins1)* 10)*60;
						//System.out.println("timer for auction "+GetName() +" is upped mins1: " + timer/60+ " mins");
					}
					
					if(mins2>=nowMins2 || nowMins1 != mins1) {
						timer +=(c.Calculate(mins2, nowMins2)*60);
						//System.out.println("timer for auction "+GetName() +" is upped mins22: " + timer/60+ " mins");
					}else {
					
						timer = 0;
						System.out.println("Auction has already been");
						return;
					}
				} 
			}
			
		}
	}
	
	
}
