package valueobject;

import exception.CommittedUsageException;
import exception.GPUNumberException;
import exception.LocalSSDNumberException;

import java.util.Objects;

public class GoogleCloudForm {
	
	private int gpuNumber;
	private String gpuTypeValue;
	private int localSSDNumber;
	private String datacenterLocationValue;
	private int committedUsageNumber;
	
	public int getGpuNumber() throws GPUNumberException {
		if (gpuNumber <= 0) {
			throw new GPUNumberException("Number of GPU can't be less than 0");
		}
		return gpuNumber;
	}
	
	public String getGpuTypeValue() {
		return gpuTypeValue;
	}
	
	public int getLocalSSDNumber() throws LocalSSDNumberException {
		if (localSSDNumber <= 0) {
			throw new LocalSSDNumberException("Number of SSD can't be less than 0");
		}
		return localSSDNumber;
	}
	
	public String getDatacenterLocationValue() {
		return datacenterLocationValue;
	}
	
	public int getCommittedUsageNumber() throws CommittedUsageException {
		if (committedUsageNumber <= 0) {
			throw new CommittedUsageException("Number of committed usage can't be less than 0");
		}
		return committedUsageNumber;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GoogleCloudForm that = (GoogleCloudForm) o;
		return gpuNumber == that.gpuNumber && localSSDNumber == that.localSSDNumber
			&& committedUsageNumber == that.committedUsageNumber && Objects.equals(gpuTypeValue, that.gpuTypeValue)
			&& Objects.equals(datacenterLocationValue, that.datacenterLocationValue);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gpuNumber, gpuTypeValue, localSSDNumber, datacenterLocationValue, committedUsageNumber);
	}
	
	public static class BuilderGoogleCloudForm {
		
		private final GoogleCloudForm newGoogleCloudForm;
		
		public BuilderGoogleCloudForm() {
			newGoogleCloudForm = new GoogleCloudForm();
		}
		
		public BuilderGoogleCloudForm withGPUNumber(int gpuNumber) {
			newGoogleCloudForm.gpuNumber = gpuNumber;
			return this;
		}
		
		public BuilderGoogleCloudForm withGPUType(String gpuTypeValue) {
			newGoogleCloudForm.gpuTypeValue = gpuTypeValue;
			return this;
		}
		
		public BuilderGoogleCloudForm withLocalSSDNumber(int localSSDNumber) {
			newGoogleCloudForm.localSSDNumber = localSSDNumber;
			return this;
		}
		
		public BuilderGoogleCloudForm withDatacenterLocation(String datacenterLocationValue) {
			newGoogleCloudForm.datacenterLocationValue = datacenterLocationValue;
			return this;
		}
		
		public BuilderGoogleCloudForm withCommittedUsageNumber(int committedUsageNumber) {
			newGoogleCloudForm.committedUsageNumber = committedUsageNumber;
			return this;
		}
		
		public GoogleCloudForm build() {
			return newGoogleCloudForm;
		}
	}
}
