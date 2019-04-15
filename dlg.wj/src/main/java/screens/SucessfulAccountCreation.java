package screens;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;

public class SucessfulAccountCreation extends DLG {
	public YourDetails clickOk() throws DLGHealthCheckException, NumberFormatException, InterruptedException {
		Common.interactButton("btnOk");
		YourDetails yourDetails = new YourDetails();
		return yourDetails;
	}
}