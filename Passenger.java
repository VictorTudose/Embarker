package tema1;

public class Passenger {
	String nume;
	char category;
	int age;
	char type;
	boolean priority;
	boolean special_needs;

	public int getPriority()
	{
		int pr=0;
		
		if(age<2)
			pr+=20;
		if(age>=2 && age<5)
			pr+=10;
		if(age>=5 && age<10)
			pr+=5;
		if(age>=60)
			pr+=15;
		
		if(type=='b')
			pr+=35;
		if(type=='p')
			pr+=20;
		
		if(priority==true)
			pr+=30;
		if(special_needs==true)
			pr+=100;
		
		return pr;
	}
	
	Passenger(String name,int age,char category,char type,boolean priority,boolean special_needs)
	{
		this.nume=name;
		this.age=age;
		this.category=category;
		this.type=type;
		this.priority=priority;
		this.special_needs=special_needs;
	}
}
