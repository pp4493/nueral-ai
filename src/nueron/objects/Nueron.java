package nueron.objects;

import java.util.ArrayList;
import java.util.List;

public class Nueron {
	private int threshold;
	private int currentSignalVal;
	
	private List<WeightedBoolean> outputs;
	
	public Nueron()
	{
		threshold = 0;
		currentSignalVal = 0;
		outputs = new ArrayList<WeightedBoolean>();
	}
	
	public Nueron(int threshold)
	{
		this.threshold = threshold;
	}
	
	public void checkThreshold(byte output) {
		if(currentSignalVal < threshold)
		{
			currentSignalVal += output;
			if(currentSignalVal >= threshold)
			{
				for(int i =0; i < outputs.size(); i++)
				{
					outputs.get(i).changeSignal();
				}
			}
		}
		else
		{
			currentSignalVal += output;
			if(currentSignalVal < threshold)
			{
				for(int i =0; i < outputs.size(); i++)
				{
					outputs.get(i).changeSignal();
				}
			}
		}
	}//end checkThreshold
	
	public void simplifyOutput()
	{
		for(int i = 0; i < outputs.size()-1; i++)
		{
			for(int j = i; j < outputs.size();j++)
			{
				if(outputs.get(i).getNueron() == outputs.get(j).getNueron())
				{
					byte sum = (byte)(outputs.get(i).getWeight() + outputs.get(j).getWeight());
					if(sum < 0)
					{
						sum = 127;
					}
					outputs.get(i).setWeight(sum);
					outputs.get(j).dispose();
					outputs.remove(j);
				}
			}
		}
	}
	
	public void dispose()
	{
		for(int i = 0; i < outputs.size(); i++)
		{
			outputs.get(i).dispose();
			outputs.remove(i);
		}
		outputs = null;
	}
	
	public int getThreshold()
	{
		return threshold;
	}
	public void setThreshold(int i)
	{
		threshold = i;
	}
	public void addOutput(Nueron n)
	{
		WeightedBoolean aBool = new WeightedBoolean();
		aBool.setNueron(n);
		outputs.add(aBool);
	}
	public boolean removeOutput(Nueron n)
	{
		int i = 0;
		for(; i < outputs.size(); i++)
		{
			if(outputs.get(i).getNueron() == n)
			{
				outputs.get(i).dispose();
				outputs.remove(i);
				break;
			}
		}
		return (i == outputs.size());
	}
	public void reset()
	{
		for(int i = 0; i < outputs.size();i++)
		{
			outputs.get(i).reset();
		}
	}
}
