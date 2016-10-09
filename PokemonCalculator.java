import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PokemonCalculator {
	
	public static Map<String, Integer> mapOfCP =  new HashMap<String, Integer>();
	public static void main(String[] args) throws Exception{
		

		loadUpPokemon();
		//printOutPokemon();
	}
	
	public static void loadUpPokemon() throws Exception{
		Scanner kbd = new Scanner(new File("PokemonNames.txt"));
		ListOfPokemon LoP = new ListOfPokemon();
		Pokemon pkm;
		String line;
		
		while(kbd.hasNextLine()) {
			line = kbd.nextLine();
			String[] splitLine = line.split(" ");
			
			int counter = 0;
			boolean max = false;
			
			String name = null, maximum = null;
			float lvl = 0.0f;
			int candy = 0, levelUps = 0;
			
			
			for(String temp : splitLine) { //Getting the information
				if(!temp.equals(" ")) { //This also allows for tabs & multiple spaces
					if(counter == 0) {
						name = temp;
						counter++;
					} else if (counter == 1) {
						lvl = Float.parseFloat(temp);
						counter++;
					} else if (counter == 2) {
						candy = Integer.parseInt(temp);
						counter++;
					} else {
						if("m".equals(temp)) {
							max = true;
							maximum = temp;
						} else {
							levelUps = Integer.parseInt(temp);
							max = false;
						}
					}
				}
			}
			
			if(max)
				LoP.add(pkm = new Pokemon(name, lvl, candy, maximum));
			else
				LoP.add(pkm = new Pokemon(name, lvl, candy, levelUps));
				
		
			
			
		}
		kbd.close();
		LoP.printPokemon();
	}
}
