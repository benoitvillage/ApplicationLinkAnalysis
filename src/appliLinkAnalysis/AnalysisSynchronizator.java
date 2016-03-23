package appliLinkAnalysis;

import java.util.ArrayList;

public class AnalysisSynchronizator {

	private MyConnection myConnection;

	public AnalysisSynchronizator(MyConnection myConnection)
	{
		this.myConnection = myConnection;
	}

	public void computeAnalysisLink() {
		// TODO Auto-generated method stub
		//Get all application present in bp_category
		ArrayList <Bp> bpCategoryList;
		ArrayList <Bp> highBpList;
		ArrayList <Bp> bottomBpList;
		
		bpCategoryList = this.myConnection.getAllBpCategory();
		
		//truncate bp_global_analysis_table
		this.myConnection.truncateBPLinksAnalysis();
		
		for(int i = 0; i < bpCategoryList.size(); i++){
			
			highBpList = new ArrayList <Bp>();
			bottomBpList = new ArrayList <Bp>();
			
			String bpName = bpCategoryList.get(i).getName();
			String bpSource = bpCategoryList.get(i).getSource();
			highBpList = this.myConnection.getAllHighBpList(bpName, bpSource);
			bottomBpList = this.myConnection.getAllBottomBpList(bpName, bpSource);
			
			for(int j = 0; j < highBpList.size(); j++) {
				for(int k = 0; k < bottomBpList.size(); k++) {
				
					String highBPName = highBpList.get(j).getName();
					String bottomBPName = bottomBpList.get(k).getName();
					String highBPSource = highBpList.get(j).getSource();
					String bottomBpSource = bottomBpList.get(k).getSource();
					this.myConnection.insertBPLinksAnalysis(highBPName,highBPSource,
							bottomBPName, bottomBpSource, bpName, bpSource);					
				}
			}
			
		}
			
		
	}

}
