package appliLinkAnalysis;


public class Bp {

	private String bpName;
	private String bpSource;
	private String bpCategory;
	
	
	public Bp (String bpName, String bpSource, String bpCategory)
	{
		this.bpName = bpName;
		this.bpSource = bpSource;
		this.bpCategory = bpCategory;
	}


	public String getName() {
		// TODO Auto-generated method stub
		return this.bpName;
	}


	public String getSource() {
		// TODO Auto-generated method stub
		return this.bpSource;
	}
}
