package model;

public class ServicePlan {
	public enum enumServicePlan{
		SILVER(1000),GOLD(2000),DIAMOND(5000),PLATINUM(10000),NONE(0);
		int plan;
		private enumServicePlan(int plan) {
			this.plan=plan;
		}
		public int getPlan() {
			return this.plan;
		}
	}
}
