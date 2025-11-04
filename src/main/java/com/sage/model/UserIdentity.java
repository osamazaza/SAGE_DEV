package com.sage.model;

import com.sage.testdata.generators.IDGenerator;

public class UserIdentity {
	private String id;
	private String mobilePhone;
	private boolean isCitizen;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public boolean isCitizen() {
		return isCitizen;
	}

	public void setCitizen(boolean isCitizen) {
		this.isCitizen = isCitizen;
	}

	@Override
	public String toString() {
		return "UserIdentity [id=" + id + ", mobilePhone=" + mobilePhone + ", isCitizen=" + isCitizen + "]";
	}

} // end of UserIdentity block