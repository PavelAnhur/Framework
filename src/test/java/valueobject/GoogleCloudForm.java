package valueobject;

import excpetion.CommittedUsageException;
import excpetion.GPUNumberException;
import excpetion.LocalSSDNumberException;

import java.util.Objects;

public class GoogleCloudForm {

	private final int gpuNumber;
	private final String gpuTypeValue;
	private final int localSSDNumber;
	private final String datacenterLocationValue;
	private final int committedUsageNumber;

	public GoogleCloudForm(int gpuNumber, String gpuTypeValue, int localSSDNumber
			, String datacenterLocationValue, int committedUsageNumber) {
		this.gpuNumber = gpuNumber;
		this.gpuTypeValue = gpuTypeValue;
		this.localSSDNumber = localSSDNumber;
		this.datacenterLocationValue = datacenterLocationValue;
		this.committedUsageNumber = committedUsageNumber;
	}

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
}
