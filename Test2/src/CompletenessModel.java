import java.sql.Date;

public class CompletenessModel {
		private int measure_amt;
		private String run_sttd;
		private String qualityMetricTypeName;
		private String validationQualityRuleComment;
		
		public void setDate(String value){
			
			this.run_sttd=value;
		}
		
		public String getDate(){
			
			return this.run_sttd;
		}
		
		public void setMeasureAmt(int value){
			
			this.measure_amt=value;
		}
		public int getMeasureAmt(){
			
			return this.measure_amt;
		}
		
	    public void setValidationRuleComment(String value){
			
			 this.validationQualityRuleComment=value;
		}
		
		public String getValidationRuleComment(){
			
			return this.validationQualityRuleComment;
		}
		public void setQualityMetricType(String value){
			this.qualityMetricTypeName=value;
		}
		public String getQualityMetricType(){
			return this.qualityMetricTypeName;
		}
}
