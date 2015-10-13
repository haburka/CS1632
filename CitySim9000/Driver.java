import java.util.Random;

public class Driver implements Cloneable{
	private String location;
	private boolean done;
	private boolean first;
	
	public Driver(){
		this.done = false;
		this.first = true;
	}
	public int getRandomNumbers(Random gen, int n){
		return gen.nextInt(n);
	}
	public String[] getLocales(){
		String[] ret = {"Outside City","Mall" , "Bookstore" , "Coffee Shop","University"};
		return ret;
	}
	//Originally I wanted i to be a random generator but I couldn't mock it so I redesigned.
	public void chooseFirst(int i,String[] locales){
		this.location = locales[i];
	}
	public void chooseNext(int i, String[] locales) throws Exception {
		String originalLoc = this.location;
		if(!this.isDone()){
			switch (this.location) {
	        case "Outside City":
				if (this.first) {
					if (i == 1) {
						this.setLocation(locales[1]);
					} else if (i == 0) {
						this.setLocation(locales[4]);
					}
					this.first = false;
				} else {
					this.done = true;
				}
				break;
			case "Mall":
				if (i == 1) {
					this.setLocation(locales[2]);
				} else if (i == 0) {
					this.setLocation(locales[3]);
				}
				break;
			case "Bookstore":
				if (i == 1) {
					this.setLocation(locales[0]);
				} else if (i == 0) {
					this.setLocation(locales[4]);
				}
				break;
			case "University":
				if (i == 1) {
					this.setLocation(locales[3]);
				} else if (i == 0) {
					this.setLocation(locales[2]);
				}
				break;
			case "Coffee Shop":
				if (i == 1) {
					this.setLocation(locales[0]);
				} else if (i == 0) {
					this.setLocation(locales[1]);
				}
				break;
			default:
				this.setLocation("Bad Value");
				break;
			}
			this.first = true;
		}else if(!(i == 0 || i == 1)){
			throw new Exception();
		}
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isDone() {
		return done;
	}
	public String getLocation() {
		return location;
	}

	protected void setLocation(String location) {
		this.location = location;
	}
	

}
