package tema1;

public abstract class Grupoid {
	String name;
	abstract int getPriority();
	Grupoid(){}
	void setName(String name)
	{
		this.name=name;
	}
	public String toString()
	{
		return name;
	}
}
