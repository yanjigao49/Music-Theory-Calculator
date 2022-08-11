package music;

public class Note implements Comparable<Note>{
	
	//List of Note Names A B C D E F G
	
	final char[] notes = {'A','B','C','D','E','F','G'};

	char name;
	int position; // Keeps track of the letter name, 0-6
	int modifier; //0 for natural, positive for sharps, negative for flats
	
	//Constructors
	public Note(char name, int modifier) {
		
		name = Character.toUpperCase(name);
		
		if(!(name+"").matches("[A-G]")) {
			System.err.println("Invalid Note Name");
			return;
		}
		
		this.name = name;
		this.modifier = modifier;
		
		//ASCII Table  A=65
		this.position = Character.toUpperCase(name)-65;
	}
	
	public Note(char name) {
		
		name = Character.toUpperCase(name);
		
		if(!(name+"").matches("[A-G]")) {
			System.err.println("Invalid Note Name");
			return;
		}
		
		this.name = name;
		this.modifier = 0;
		
		//ASCII Table  A=65
		this.position = Character.toUpperCase(name)-65;
	}
	
	//Basic Operations
	
	public void sharpen() {
		this.modifier++;
	}
	
	public void sharpen(int num) {
		this.modifier+=num;
	}
	
	public void flatten() {
		this.modifier--;
	}
	
	public void flatten(int num) {
		this.modifier-=num;
	}
	
	
	//Enharmonic Respellings
	public void EnharmUp() {
		int change = 2;
		
		if(position == 1 || position == 4) {
			change = 1;
		}
		
		position = (((position+1)%7)+7)%7;
		name = notes[position];
		modifier-=change;
	}
	
	public void EnharmDown() {
		int change = 2;
		
		if(position == 2 || position == 5) {
			change = 1;
		}
		
		position = (((position-1)%7)+7)%7;
		name = notes[position];
		modifier+=change;
	}
	
	public void EnharmRespell() {
		if(modifier == 0) return;
		
		//If sharp, stay sharp, if flat, stay flat
		if(modifier > 0) {
			//Sharp, Moving Up
			
			while(modifier > 0) {
				EnharmUp();
			}
			if(modifier!=0) EnharmDown();
			
		} else {
			//Flat, Moving Down
			
			while(modifier < 0) {
				EnharmDown();
			}
			if(modifier!=0) EnharmUp();
			
		}
	}
	//Getters and Setters
	public char getName() {
		return name;
	}
	
	public int getNoteID() {
		int ID = findNoteID(name);
		
		//return the positive value
		return (((ID+modifier)%12)+12)%12;
	}
	
	//CompareTo Method
	@Override
	public int compareTo(Note note) {
		//return 1 if not equal, return 0 if equal
		if(this.getNoteID() != note.getNoteID()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//ToString Method
	public String toString() {
		//print out name first, then add modifiers
		String result = Character.toUpperCase(name)+"";
		
		//If no modifiers, return
		if(modifier == 0) {
			return result;
		}
		
		//prevent modification of original variable
		int temp = modifier;
		
		if(temp>0) {
			//Sharps
			while (temp != 0) {
				result+="#";
				temp--;
			}
			
		} else {
			//Flats
			while (temp != 0) {
				result+="b";
				temp++;
			}
		}
		
		return result;
	}
	
	//Helper Methods
	public static int findNoteID(char name) {
		switch(Character.toUpperCase(name)-65) {
		//A
		case 0:
			return 1;
		//B
		case 1:
			return 3;
		//C
		case 2:
			return 4;
		//D
		case 3:
			return 6;
		//E
		case 4:
			return 8;
		//F
		case 5:
			return 9;
		//G
		case 6:
			return 11;
		//Error -> This should not occur
		default:
			System.err.println("Error: Unable to get NoteID");
			return -1;
		}
	}
	
	public static char findNoteByID(int NoteID) {
		switch(NoteID) {
		//A
		case 1:
			return 'A';
		//B
		case 3:
			return 'B';
		//C
		case 4:
			return 'C';
		//D
		case 6:
			return 'D';
		//E
		case 8:
			return 'E';
		//F
		case 9:
			return 'F';
		//G
		case 11:
			return 'G';
		//Error -> This should not occur
		default:
			System.err.println("Error: Unable to get Note");
			return 0;
		}
	}
}
