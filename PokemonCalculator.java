import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import customExceptions.BadLevelException;

public class PokemonCalculator {
	
	public static Map<String, Integer> mapOfCP =  new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		
		try {
			setUpMap();
		} catch(FileNotFoundException e) {
			System.out.println("Text file of Pokemon and avg CP gain not found.");
		}
		
		try {
			loadUpPokemon();
		} catch (FileNotFoundException e) {
			System.out.println("Text file of Pkm not found.");
		}
		
		//printOutPokemon();
	}
	
	public static void setUpMap() throws FileNotFoundException{
		Scanner kbd = new Scanner(new File("PokemonCPGain.txt"));
		
		while(kbd.hasNext()){
			String[] temp = kbd.nextLine().split(" ");
			int size = temp.length, cpNumber = Integer.parseInt(temp[0]);
			
			for(int x = 1; x < size; x++){
				mapOfCP.put(temp[x].toLowerCase(), cpNumber);
			}
			
		}
		
		kbd.close();
		
	}
	
	public static void loadUpPokemon() throws FileNotFoundException {
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
			int candy = 0, levelUps = 0, pkmCP = 0;
			
			
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
					} else if (counter == 3) {
						if("m".equals(temp)) {
							max = true;
							maximum = temp;
						} else {
							levelUps = Integer.parseInt(temp);
							max = false;
						}
						counter++;
					
					} else {
						
						pkmCP = Integer.valueOf(temp);
						
					}
				}
			}
			
					
			try {
			//ADDING CP
			if(max & pkmCP == 0) //If maximum evolutions and no initial CP give
				LoP.add(pkm = new Pokemon(name, lvl, candy, maximum, mapOfCP.get(name.toLowerCase())));
			else if(!max & pkmCP ==0)
				LoP.add(pkm = new Pokemon(name, lvl, candy, levelUps, mapOfCP.get(name.toLowerCase())));
			else if(max & pkmCP != 0) //If maximum evolutions and no initial CP give
				LoP.add(pkm = new Pokemon(name, lvl, candy, maximum, mapOfCP.get(name.toLowerCase()),pkmCP));
			else if(!max & pkmCP !=0)
				LoP.add(pkm = new Pokemon(name, lvl, candy, levelUps, mapOfCP.get(name.toLowerCase()),pkmCP));
			} catch (BadLevelException e) {
				System.out.println(e);
			}
	
			
		}
		
		kbd.close();
		
		try {
			LoP.printPokemon();
		} catch (IOException e) {
			
		}
		
	}
}
