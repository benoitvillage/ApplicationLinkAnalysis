package appliLinkAnalysis;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ShareVariables shareVariable = new ShareVariables();
		
		System.out.println("variables created (e.g." + shareVariable.getHost());
		
		//Create the connection
		MyConnection myConnection = new MyConnection(shareVariable);
		
		AnalysisSynchronizator as = new AnalysisSynchronizator(myConnection);
		
		as.computeAnalysisLink();
		
	}

}
