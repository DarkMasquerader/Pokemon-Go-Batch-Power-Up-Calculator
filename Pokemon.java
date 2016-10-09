import customExceptions.BadLevelException;

public class Pokemon {
	private final String name;
	private final int initialCandy, initialPowerUps;
	private final float initialLevel;
	
	private float level;
	private int remainingCandy, noPowerUps, accumulatedLevelUpCost = 0;
	private final boolean maxPowerUp;
	private Cost cost;

	public String toString(){
		return getName() + "\t" + 
				getInitialLevel() + " -> " + getLevel() + "\t" + 
					getInitialCandy() + " -> " + getRemainingCandy() + "\t" +
						getDustUsage();
	}
	
	public Pokemon(String name, float level, int remainingCandy, int noPowerUps) throws BadLevelException {
		this.name = name;
		maxPowerUp = false;
		
		setLevel(level);
		setRemainingCandy(remainingCandy);
		setNoPowerUps(noPowerUps);
		
		initialCandy = getRemainingCandy();
		initialPowerUps = getNoPowerUps();
		initialLevel = getLevel();
			
		runChecker();
		//Need to check for less candy than cost
	}

	public Pokemon(String name, float level, int remainingCandy, String max) throws BadLevelException {

		this.name = name;
		maxPowerUp = true;
		
		setLevel(level);
		setRemainingCandy(remainingCandy);
		
		initialCandy = getRemainingCandy();
		initialPowerUps = getNoPowerUps();
		initialLevel = getLevel();
		
		runChecker();
		//Need to work out number of power-ups from given info
	}
	
	public void runChecker() throws BadLevelException {
		determineTier();
		calculations();
	}
	
	public void calculations() {
	
		if(!isMaxPowerUp()) { //If the user entered a number of power-ups
			
			while(getNoPowerUps() > 0) { //Keep powering up as long as we have power-ups left
				setRemainingCandy(getRemainingCandy() - cost.getCandy()); //Subtracts power-up candy cost
				setLevel(getLevel() + 0.5f); //Adds half a level to a pokemon
				incDustUsage(cost.getDust()); //Adds cost of levelling up
				decNoPowerups();
				checkTier();
			}
			
		} else { //If we are powering up until we're out of candy
		
			while(getRemainingCandy() - cost.getCandy() > 0) { //Keep powering up as long as we don't get negative candy
				setRemainingCandy(getRemainingCandy() - cost.getCandy()); //Subtracts power-up candy cost
				setLevel(getLevel() + 0.5f); //Adds half a level to a pokemon
				incDustUsage(cost.getDust()); //Adds cost of levelling up
				checkTier();
		
			}
		}
	}
	
	private void determineTier() throws BadLevelException {
		boolean bigC = false, eqC = false;
		
		for(Cost tempCost : Cost.values()) {
			
			if(bigC) {
				if(new Float(getLevel()).compareTo(new Float(tempCost.getLvl())) < 0 ) {
					cost = tempCost;
					return;
				}
			bigC = false;
			} else if(eqC) {
				if(new Float(getLevel()).compareTo(new Float(tempCost.getLvl())) < 0) {
					cost = tempCost;
					return;
				}
				eqC = false;
			}
			
			if(new Float(getLevel()).compareTo(new Float(tempCost.getLvl())) > 0) { //If pkm level is bigger than tier boundary
				bigC = true;
				continue;
			} else if (new Float(getLevel()).compareTo(new Float(tempCost.getLvl())) == 0) { //If pkm level is equal to tier boundary
				eqC = true;
				continue;
			}			
		}
		throw new BadLevelException("Invalid Pokemon level entered for:\nPokemon:" + getName() + "\nLevel: " + getLevel());
		
		/*
		 * Accept Conditions:
		 * > < OR = <
		 */
	}

	private void checkTier() {
		if(new Float(getLevel()).compareTo(cost.getLvl()) < 0) //If pkm is below tier limit, we are ok
			return;
		
		else { //Move to next tier

			int ordinalMarker = cost.ordinal(); //Stores current Tier ordinal so we can move one ahead
			for(Cost tempCost : Cost.values()) {
				if(tempCost.ordinal() > ordinalMarker) { //If the Enum we're looking at is a position ahead, take it.
					cost = tempCost;
					return;
				}
			}
		}
			
			
	}
	
	public String getName() {
		return name;
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
	}

	public int getRemainingCandy() {
		return remainingCandy;
	}

	public void setRemainingCandy(int remainingCandy) {
		this.remainingCandy = remainingCandy;
	}

	public void decNoPowerups() {
		this.noPowerUps--;
	}
	
	public int getNoPowerUps() {
		return noPowerUps;
	}

	public void setNoPowerUps(int noPowerUps) {
		this.noPowerUps = noPowerUps;
	}

	public boolean isMaxPowerUp() {
		return maxPowerUp;
	}

	public float getMaxTierLevel() {
		return cost.getLvl();
	}

	public int getTierDustCost() {
		return cost.getDust();
	}

	public int getTierCandyCost() {
		return cost.getCandy();
	}

	public String getTierName() {
		return cost.name();
	}

	public int getInitialCandy() {
		return initialCandy;
	}

	public int getInitialPowerUps() {
		return initialPowerUps;
	}

	public float getInitialLevel() {
		return initialLevel;
	}

	public int getDustUsage() {
		return accumulatedLevelUpCost;
	}

	public void incDustUsage(int accumulatedLevelUpCost) {
		this.accumulatedLevelUpCost += accumulatedLevelUpCost;
	}
	
	
	

}
