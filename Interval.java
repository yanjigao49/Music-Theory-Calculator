package music;


public class Interval {
	
	/* major 2nd = 2
	 * major 3rd = 4
	 * perfect 4th = 5
	 * perfect 5th = 7
	 * major 6th = 9
	 * major 7th = 11
	 */
	
	//Usage: A for augmented, D for diminished, M for major, m for minor
	
	public Note interval(Note note, String interval) {
		String quality = "";
		int intvl = 0;
		
		//Storing quality
		while(!Character.isDigit(interval.charAt(0))) {
			quality.concat(interval.substring(0,1));
			interval = interval.substring(1,interval.length());
		}
		
		//Storing interval
		intvl = Integer.parseInt(interval);
		intvl = (((intvl)%8)+8)%8;
		
		if(intvl == 4 || intvl == 5) {
			//Perfect Intervals
			
		} else {
			//Major/Minor Intervals
			
		}
		
		
		
	}
}
