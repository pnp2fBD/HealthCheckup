package cucumber.cc.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import cucumber.api.java.en.And;

import screens.AgentDashboard;
import utility.EnvironmentStatusUtils;

public class AgentDashboardStepsDefinition {

	String environment = System.getProperty("environment");
	String errorMessage = "NA";
	int testcase = 2;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	AgentDashboard agentDashboard = DLG.getAgentDashboard();

	@And("^clicks on Take Call$")
	public void clicks_on_Take_Call() throws IOException, DLGHealthCheckException {
		try {
			agentDashboard.hasScreenLoaded();
			agentDashboard.takeCall();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			EnvironmentStatusUtils status = new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

}
