package tema1;
class Heapcell
{
	public static int nr_cells;
	public static int count;
	Grupoid gr;
	int priority;
	int index;
	Heapcell left;
	Heapcell right;
	Heapcell parent;
	public String toString()
	{
		return gr.name;
	}
	
	Heapcell(Grupoid g,int p)
	{
		gr=g;
		priority=p;
		nr_cells++;
		count++;
		index=count;
	}
	public static void swap(Heapcell cell1, Heapcell cell2)
    {
    	if(cell1==null)
    		return;
    	if(cell2==null)
    		return;
    	Grupoid gr_temp;
    	int priority_temp;
    	int index_temp;
    	
    	gr_temp=cell1.gr;
    	cell1.gr=cell2.gr;
    	cell2.gr=gr_temp;
    	
    	priority_temp=cell1.priority;
    	cell1.priority=cell2.priority;
    	cell2.priority=priority_temp;
    	
    	index_temp=cell1.index;
    	cell1.index=cell2.index;
    	cell2.index=index_temp;
    }
	public static void attrib(Heapcell cell1, Heapcell cell2)
    {
    	if(cell1==null)
    		return;
    	if(cell2==null)
    		return;
    		
    	cell1.gr=cell2.gr;
    	cell1.priority=cell2.priority;
    	cell1.index=cell2.index;
    }
	public static boolean greater(Heapcell g1,Heapcell g2)
    {
    	if(g1 == null)
    		return false;
    	
    	if(g2==null)
    		return false;
    	
    	if(g1.priority < g2.priority)
    		return false;
    	
    	if(g1.priority == g2.priority)
    		return false;
    	
    	return true;
    }
	public static boolean greaterEq(Heapcell g1,Heapcell g2)
    {
    	if(g1 == null)
    		return false;
    	
    	if(g2==null)
    		return false;
    	
    	if(g1.priority < g2.priority)
    		return false;
    	
    	return true;
    }

	Heapcell getByName(String name)
    {
    	if(gr.name.equals(name))
    		return this;
    	if(this.left==null)
    		return null;
    	Heapcell aux=this.left.getByName(name);
    	if(aux!=null)
    		return aux;
    	return this.right.getByName(name);
    }

	public void append(Heapcell newCell)
	{
		if(this.left==null)
			this.left= newCell;
    	else
    		this.right= newCell;
	}

	public void self_remove()
	{
		if(this.parent==null)
			return;
		if(this.parent.right==this)
			this.parent.right=null;
    	else
    		this.parent.left=null;
	}
	public void raise()
    {
		Heapcell newCell=this;
    	while(newCell.parent!=null && Heapcell.greater(newCell,newCell.parent))
    	{
    		Heapcell.swap(newCell,newCell.parent);
    		newCell=newCell.parent;
    	}
    }
	public void decrease()
    {
		Heapcell current=this;
    	while(true)
    	{
    		if(current.left==null)
    			return;
    		
    		if(greaterEq(current, current.left) )
    		{
    			if(current.right==null)
    				return;
    			else if(greaterEq(current,current.right))
    				return;
    		}
    		if(current.right==null)
    		{
    			Heapcell.swap(current,current.left);
    	    	return;
    		}
    		else
    		{
    			if(Heapcell.greater(current.right,current.left))
    			{
    	    		Heapcell.swap(current,current.right);
    	    		current=current.right;
    			}
    			else
    			{
    	    		Heapcell.swap(current,current.left);
    	    		current=current.left;
    			}
    		}
    	}
    }
}