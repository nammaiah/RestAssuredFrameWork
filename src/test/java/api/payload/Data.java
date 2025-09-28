	package api.payload;

public class Data {
		int year;
		double price;
		String cpuModel;
		String hardDiskSize;

		public int getYear() {
			return year;
		}
		
		public void setYear(int year) {
			this.year = year;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getCpuModel() {
			return cpuModel;
		}

		public void setCpuModel(String cpuModel) {
			this.cpuModel = cpuModel;
		}

		public String getHardDiskSize() {
			return hardDiskSize;
		}

		public void setHardDiskSize(String hardDiskSize) {
			this.hardDiskSize = hardDiskSize;
		}

}
