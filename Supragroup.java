package tema1;

public abstract class Supragroup extends Grupoid {
	Passenger members[];
	int n;
	int pr;
	public void resize(int n2)
	{
		Passenger[] members2=new Passenger[n2];
		if(n2>n)
			n2=n;
		for(int i=0;i<n2;i++)
			members2[i]=members[i];
		members=members2;
	}
	public int getByName(String name)
	{
		for(int i=0;i<n;i++)
			if(members[i].nume.equals(name))
				return i;
		return -1;
	}
	public void remove(String name)
	{
		int i=getByName(name);
		for(int j=i;j<n-1;j++)
			members[j]=members[j+1];
		resize(n-1);
		n--;
	}
	public void add(Passenger P) {
		resize(n+1);
		members[n++]=P;
	}

	public String toString()
	{
		return this.name;
	}
	int getPriority()
	{
		int pr=this.pr;
		for(int i=0; i<n ; i++ )
			pr+=members[i].getPriority();
		return pr;
	}
	Supragroup()
	{
		members = new Passenger[1];
		n=0;
	}
	
}