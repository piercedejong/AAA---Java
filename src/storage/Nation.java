package storage;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import util.ColorUtil;

public class Nation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int bank;
	private int income;
	private int position;
	private Color color;
	private Color colorLight;
	private ImageIcon roundel;
	private List<Objective> nationalObj;
	private List<Boolean> research;
	
	public Nation(String name, Color color, Color colorLight, int position, int bank, int income, List<Objective> nationalObj, List<Boolean> research){
		this.name = name;
		this.color = color;
		this.colorLight = colorLight;
		this.position = position;
		this.bank = bank;
		this.income = income;
		this.roundel = new ImageIcon("data/roundels/"+name+".png");
		this.nationalObj = nationalObj;
		this.research = research;
	}
	
	private Nation(Nation n){
		name = n.name;
		bank = n.bank;
		income = n.income;
		position = n.position;
		color = n.color;
		colorLight = n.colorLight;
		roundel = n.roundel;
		nationalObj = n.nationalObj;
		research = n.research;
	}
	
	public Nation copy(){
		return new Nation(this);
	}
	/**
	 * SETTERS
	 */
	public void setBank(int bank){
		this.bank = bank;
	}
	
	public void setIncome(int income){
		this.income = income;
	}
	
	public void endTurn(){
		bank = bank + getTotalIncome();
	}
	
	public void changeBank(int change){
		bank = bank + change;
		if(bank<0){
			bank = 0;
		}
	}
	
	public void changeIncome(int change){
		income = income + change;
		if(income<0){
			income = 0;
		}
	}
	/**
	 * GETTERS
	 */
	public String getName(){
		return name;
	}
	
	public Integer getBank(){
		return bank;
	}
	
	public Integer getIncome(){
		return income;
	}
	
	public Integer getPosition(){
		return position;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Color getColorLight(){
		return colorLight;
	}
	
	public ImageIcon getRoundel(){
		return roundel;
	}
	
	public List<Objective> getObjectives(){
		List<Objective> clone = new ArrayList<>(nationalObj);
		return clone;
	}
	
	public List<Boolean> getResearch(){
		return research;
	}
	
	/**
	 * @returns sum off all natObj's that are enabled
	 */
	public Integer getNatObjIncome(){
		int i = 0;
		for(Objective o:nationalObj){
			if(o.isEnabled()){
				i = i + o.getAmount();
			}
		}
		return i;
	}
	
	public Integer getTotalIncome(){
		return income + getNatObjIncome();
	}
	@Override
	public String toString(){
		return "position: "+(position+1)+
				"\ncolor: "+color()+
				"\nbank: "+bank+
				"\nincome: "+income+
				"\nresearch: "+getResearchS()+
				"\n"+getObjectivesString();
	}
	private String color(){
		String colorS = ColorUtil.colorMap.get(color).toLowerCase();
		if(colorS.contains("_l")){
			colorS+="ight";
		}else if(colorS.contains("_d")){
			colorS+="ark";
		}return colorS;
	}
	private String getResearchS(){
		String s = "";
		for(Boolean b: research){
			s+= b.toString()+" ";
		}return s;
	}
	private String  getObjectivesString(){
		String s = "";
		for(Objective o: nationalObj){
			s+=o.save()+"\n";
		}return s;
	}
}
