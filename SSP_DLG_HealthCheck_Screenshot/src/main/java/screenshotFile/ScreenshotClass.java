package screenshotFile;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class ScreenshotClass {

	@Test
	public void convertFile() throws IOException, InterruptedException {

		String apiKey = "5e8eff9af6605e7650d4ac0cfa7b7cf6cf9dcc07";
		String uploadEndPoint = "https://sandbox.zamzar.com/v1/jobs";
		String downloadFileName = "\\\\UBUNTU\\Anonymous\\DailyStatus.jpg";// Path
																			// of
																			// report
																			// file
																			// (change
																			// it)

		File sourceFile = new File(
				"\\\\VM-W7-IJ-T21\\Users\\insurej.tester\\Jenkins Slave\\workspace\\DLG-DailyReport\\TempStatusFile\\DailyStatus.xlsx");

		ValidatableResponse response = RestAssured.given().auth().basic(apiKey, "").multiPart("source_file", sourceFile)
				.multiPart("target_format", "jpg").when().post(uploadEndPoint).then();

		int jobId = response.extract().path("id");

		String jobsEndPoint = "https://sandbox.zamzar.com/v1/jobs/" + jobId;

		String idStatus = (RestAssured.given().auth().basic(apiKey, "").get(jobsEndPoint).then().extract()
				.path("status"));

		while (!idStatus.equalsIgnoreCase("successful")) {
			Thread.sleep(2000);
			idStatus = (RestAssured.given().auth().basic(apiKey, "").get(jobsEndPoint).then().extract().path("status"));

		}

		Thread.sleep(2000);

		int fileId = RestAssured.given().auth().basic(apiKey, "").get(jobsEndPoint).then().extract()
				.path("target_files.id[0]");

		String downloadEndPoint = "https://sandbox.zamzar.com/v1/files/" + fileId + "/content";

		byte[] convertedFile = RestAssured.given().auth().basic(apiKey, "").get(downloadEndPoint).asByteArray();

		FileUtils.writeByteArrayToFile(new File(downloadFileName), convertedFile);

	}

}
