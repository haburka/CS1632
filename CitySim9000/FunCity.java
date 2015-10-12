import java.util.Random;
import java.util.Vector;

public class FunCity {
	
	private Vector<Driver> drivers;
	public FunCity(){
		this.drivers = new Vector<Driver>();
	}
	
	public boolean addDriver(Driver newDriver){ 
		if(drivers.size() < 4){
			drivers.add(newDriver);
			return true;
		}else{
			return false;
		}
	}
	public String iterate(int seed){
		int activeDrivers = 0;
		if(drivers.size() != 4){
			return "need more drivers.";
		}else{
			String[] locales = drivers.lastElement().getLocales();
			Random gen = new Random(seed);
			String answer = new String();
			Boolean over = false;
			int i = 0;
			do{//every program needs a do while loop ^_^
				answer += "Step "+i+":\n";
				for(int j = 0; j < activeDrivers; ++j){
					drivers.get(j).chooseNext(gen.nextInt(4),locales);
					answer += "Driver "+j+" goes to the "+drivers.get(j).getLocation()+".\n";
					if(drivers.get(j).getLocation().equals("Outside City")){
						over = true;
						answer += "Driver "+j+" has left.\n";
					}
				}
				if(i < 4 && !over){
					drivers.get(i).chooseFirst(gen.nextInt(4), locales);
					answer += "Driver "+i+" starts in the "+drivers.get(i).getLocation()+".\n";
					activeDrivers++;
				}
				i++;
			}while(!over);
			return answer + "Simulation end.\n";
		}
	}
	
}
