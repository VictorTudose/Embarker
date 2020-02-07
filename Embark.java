package tema1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintStream;

public class Embark {
	MaxHeap Hp;
	static NameTable NT;
	int size;
	Scanner keyboard;
	PrintStream output;
	boolean newline=false;
	
	public class NameTable{

		Grupoid members[];
		int n;

		public void add(Grupoid g)
		{
			Grupoid members2[]= new Grupoid[n+1];
			for(int i=0;i< n;i++)
				members2[i]=members[i];
			members2[n]=g;
			members=members2;
			n++;
		}

		public Grupoid getByName(String name)
		{
			for(int i=0;i<n ;i++) {
				if(members[i].name.equals(name))
					return members[i];
			}
			return null;
		}
		/**
		 * Constructor for NameTable
		 */
		public NameTable()
		{
			members = new Grupoid[1];
			n=0;
		}
	}

	public void insertInTable(Passenger P,String grupoid_name)
	{
		if(grupoid_name.charAt(0)=='s')
		{
			Single sg=new Single(P);
			sg.name=grupoid_name;
			NT.add(sg);
			return;
		}
		
		Grupoid gr=NT.getByName(grupoid_name);
		if(gr!=null)
		{
			switch(grupoid_name.charAt(0))
			{
			case 'f':
				((Family)gr).add(P);
				break;
			case 'g':
				((Group)gr).add(P);
				break;
			default:
				break;
			}
		}
		else {
			switch(grupoid_name.charAt(0))
			{
			case 'f':
				Family fam=new Family(P);
				fam.name=grupoid_name;
				NT.add(fam);
				break;
			case 'g':
				Group grp=new Group(P);
				grp.name=grupoid_name;
				NT.add(grp);
				break;
			default:
				break;
			}
		}
	}

	public void insertInHeap(String name)
	{
		Grupoid gr=NT.getByName(name);
		Hp.insert(gr,gr.getPriority());
	}

	public void loadPassenger()
	{ 
		String grupoid_name = keyboard.next();
		String name=keyboard.next();
		int age=keyboard.nextInt();
		String type_s=keyboard.next();
		boolean priority=keyboard.nextBoolean();
		boolean special_needs=keyboard.nextBoolean();
		
		char type=type_s.charAt(0);
		
		Passenger P=new Passenger(name,age,grupoid_name.charAt(0),type,priority,special_needs);
		insertInTable(P,grupoid_name);
	}

	public void unbindKeyboard()
	{
		keyboard.close();
	}

	public void list()
	{
		Hp.list();
	}

	public void embark()
	{
		Hp.embark();
	}

	private void newLine()
	{
		if(newline==false)
		{
			newline=true;
		}
		else
		{
			output.println();
		}
	}

	public void exec()
	{
		String command=keyboard.nextLine();
		StringTokenizer ST= new StringTokenizer(command);
		if(ST.hasMoreTokens() == false)
			return;
		String to_do = ST.nextToken();
		if(to_do.equals("insert"))
		{
			to_do = ST.nextToken();
			insertInHeap(to_do);
		}
		if(to_do.equals("embark"))
		{
			embark();
		}
		if(to_do.equals("list"))
		{
			newLine();
			list();
		}
		if(to_do.equals("delete"))
		{
			to_do = ST.nextToken();
			if(ST.hasMoreTokens())
			{
				String name=ST.nextToken();
				Hp.delete(to_do,name);
			}
			else
				Hp.delete(to_do);
		}
		
	}

	public void exec_File()
	{
		int nr=keyboard.nextInt();
		for(int i=0;i<nr;i++)
			this.loadPassenger();
		while(keyboard.hasNext())
			this.exec();
		this.unbindKeyboard();
	}

	public Embark(String input_path,String output_path) throws FileNotFoundException
	{
		File in_file = new File(input_path);
		File out_file= new File(output_path);
        keyboard = new Scanner(in_file);
        output= new PrintStream(out_file);
        NT=new NameTable();
		Hp=new MaxHeap(output);
	}
	
}