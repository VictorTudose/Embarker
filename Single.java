package tema1;

public class Single extends Grupoid {

	Passenger traveler;
	public int getPriority()
	{
		return traveler.getPriority();
	}
	public Single(Passenger P)
	{
		this.traveler=P;
	}
}
