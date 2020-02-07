
package tema1;
import java.io.PrintStream;

public class MaxHeap {

	Heapcell Heap;
	PrintStream output;
	HeapList HL;
	
	public class HeapList
	{

		Heapcell[] to_list;
		HeapList(){}

		void add(Heapcell H)
		{
			if(to_list==null)
			{
				to_list=new Heapcell[1];
				to_list[0]=H;
				return;
			}
			Heapcell[] aux=new Heapcell[to_list.length+1];
			for(int i=0;i<to_list.length;i++)
				aux[i]=to_list[i];
			aux[to_list.length]=H;
			to_list=aux;
		}

		void list(PrintStream output)
		{
			for(int i=0;i<to_list.length-1;i++)
			{
				output.print(to_list[i]+" ");
			}
			output.print(to_list[to_list.length-1]);
		}

		void clear()
		{
			to_list=null;
		}
	}

	private Heapcell[] create_queue()
	{
		int nr=Heapcell.nr_cells;
		Heapcell[] H=new Heapcell[nr];
		int e1=0,e2=1;
		H[0]=Heap;
		while(e2 != nr && !(H[e1].left==null && H[e1].right==null) )
		{
			if(H[e1].left!=null)
				H[e2++]=H[e1].left;
			if(H[e1].right!=null)
				H[e2++]=H[e1].right;
			e1++;
		}
		return H;
	}

	private Heapcell left_most()
	{
		Heapcell ret;
		ret=Heap;
		while(true) {
			if(ret.left==null)
				break;
			ret=ret.left;
		}
		return ret;
	}

	private Heapcell right_most()
	{
		Heapcell ret;
		ret=Heap;
		while(true)
		{
			if(ret.right==null)
				break;
			ret=ret.right;
		}
		return ret;
	}

    private Heapcell to_append()
	{
		if(Heap.left==null || Heap.right==null) {
			return Heap;
		}
		
		int nr=Heapcell.nr_cells;
		nr++;
		nr = nr & (nr-1);
		if(nr==0)
			return left_most();
		
		nr=Heapcell.nr_cells;
		Heapcell[] H=create_queue();
		
		if(nr%2==1)
			return H[nr/2];
		else
			return H[nr/2-1];
	}

    private Heapcell last()
	{
    	
		if(Heap.left==null && Heap.right==null)
			return Heap;

		int nr=Heapcell.nr_cells;
		nr++;
		nr = nr & (nr-1);
		
		if(nr==0)
			return right_most();
		
		nr=Heapcell.nr_cells;
		Heapcell[] H=create_queue();
		
		return H[nr-1];
	}

    public void insert(Grupoid gr,int priority)
    {
    	if(Heap==null)
    	{
    		Heap=new Heapcell(gr,priority);
    		return;
    	}
    	
    	Heapcell to_append = to_append();
    	Heapcell newCell = new Heapcell(gr,priority);
    	to_append.append(newCell);
    	newCell.parent=to_append;
    	newCell.raise();
      }

    public void embark()
    {
    	Heapcell last=last();
		Heapcell.attrib(Heap,last);
    	last.self_remove();
    	Heapcell current=Heap;
    	current.decrease();
    	Heapcell.nr_cells--;
    }

    private void print(Heapcell H)
    {
    	if(H==null)
    		return;
    	HL.add(H);
    	print(H.left);
    	print(H.right);
    }

    public void list()
    {
    	print(Heap);
    	HL.list(output);
    	HL.clear();
    }

    public void delete(String name)
    {
    	Heapcell to_delete=Heap.getByName(name);
    	Heapcell last=last();
    	Heapcell.swap(last,to_delete);
    	last.self_remove();
    	to_delete.raise();
    	Heapcell.nr_cells--;
    }

    public void delete(String grupoid_name,String passenger_name)
    {
    	Heapcell to_delete=Heap.getByName(grupoid_name);
    	((Supragroup)to_delete.gr).remove(passenger_name);
    	to_delete.priority=to_delete.gr.getPriority();
    	to_delete.decrease();
    }
    
	public MaxHeap(PrintStream output){
		this.output=output;
		HL=new HeapList();
	}
	
}
