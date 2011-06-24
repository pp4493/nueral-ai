package nueron.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NueralNetwork {
	
	List<Nueron> network;
	
	public NueralNetwork(int num)
	{
		network = new ArrayList<Nueron>();
		Random r = new Random();
		for(int i = 0; i < num; i++)
		{
			Nueron n = new Nueron(r.nextInt(256));
			network.add(n);
		}
		adjustByGrade((byte)2);
	}
	
	public void reset()
	{
		for(int i =0; i < network.size(); i++)
		{
			network.get(i).reset();
		}
	}
	
	public void adjustByGrade(byte b)
	{
		//do something here
	}
	
	public void simplifyNetwork()
	{
		for(int i = 0; i < network.size(); i++)
		{
			network.get(i).simplifyOutput();
		}
	}
}
