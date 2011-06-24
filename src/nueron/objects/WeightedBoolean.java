package nueron.objects;

public class WeightedBoolean {
	private boolean signal;
	private byte weight;
	
	private Nueron out;
	
	public WeightedBoolean()
	{
		signal = false;
		weight = 1;
	}
	
	public void changeSignal()
	{
		byte output = 0;
		if(!signal)
		{
			signal = true;
			output = weight;
		}
		else
		{
			signal = false;
			output = (byte)(-1 * weight);
		}
		out.checkThreshold(output);
	}
	
	public Nueron getNueron()
	{
		return out;
	}
	public void setNueron(Nueron n)
	{
		out = n;
	}
	public byte getWeight()
	{
		return weight;
	}
	public void setWeight(byte w)
	{
		weight = w;
	}
	
	public void reset()
	{
		if(signal)
		{
			changeSignal();
		}
	}

	public void dispose() 
	{
		if(signal)
		{
			changeSignal();
		}
		weight = 0;
		out = null;
	}
}
