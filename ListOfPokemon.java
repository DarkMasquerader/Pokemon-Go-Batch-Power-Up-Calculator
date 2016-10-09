import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfPokemon {


	List<Pokemon> listOfPkm = new ArrayList<>();
	

	public int getNoPokemon() {
		return listOfPkm.size();
	}
	
	public void add(Pokemon pkm) {
		listOfPkm.add(pkm);
	}
	
	public void printPokemon() throws IOException {
		FileWriter fw = new FileWriter("PokemonResults.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		int totalDust = 0;
		for(Pokemon p : listOfPkm) {
			bw.write(p.toString());
			bw.newLine();
			totalDust += p.getDustUsage();
		}
		bw.write("\t\t\t\tTotal Dust Used: " + totalDust);
		bw.newLine();
		bw.close();
			
		
	}

}
