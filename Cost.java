
public enum Cost {
	TIER_1(3, 200, 1), TIER_2(4, 400, 1), TIER_3(7, 600, 1), TIER_4(9, 800, 1), TIER_5(11, 100, 1), TIER_6(13, 1300, 2), 
		TIER_7(15, 1600, 2), TIER_8(17, 1900, 2), TIER_9(19, 2200, 2),TIER_10(21, 2500, 2),TIER_11(23, 3000, 3),
			TIER_12(25, 3500, 3),TIER_13(27, 4000, 3), TIER_14(29, 4500, 3), TIER_15(31, 5000, 3),
				TIER_16(33, 6000, 4), TIER_17(35, 7000, 4), TIER_18(37, 8000, 4), 
					TIER_19(39, 9000, 4), TIER_20(40, 10000, 4);
				
	
	float level;
	private int stardust, candy;
	
	private Cost(int lvl, int star, int candy){
		level = (float)lvl;
		stardust = star;
		this.candy = candy;
	}
	
	public float getLvl(){
		return level;
	}
	
	public int getDust(){
		return stardust;
	}
	
	public int getCandy(){
		return candy;
	}
}
