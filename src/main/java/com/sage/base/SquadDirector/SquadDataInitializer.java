package com.sage.base.SquadDirector;

import com.sage.utils.SmartEncoder;
import com.sage.testdata.dataDriven.RunTimeAttributes.SharedTestData;
import com.sage.model.*;
public class SquadDataInitializer {

	SmartEncoder smartEncoder = new SmartEncoder();

	public Squad getSquad1Data() {

		//String projectPath = smartEncoder.decodeURL(SharedTestData.projectPath);

		
		
		String projectPath = SharedTestData.projectPath;

		System.out.println("The project path is: "  + projectPath);
		
		
		Squad squad = new Squad();

		squad.setSquadImageScreenCaptureSource(projectPath + "\\Reports\\CustomReport\\Squad1\\ImageCaptures");
		squad.setSquadVideoScreenRecordingSource(projectPath + "\\Reports\\CustomReport\\Squad1\\VideoRecordings");
		squad.setSquadMainSource(projectPath + "\\Reports\\CustomReport\\Squad1");

		if (isPublicPath()) {
			setPublicPath(squad);
		}

		// C:\Users\USER\git\gitBranch
																																															return squad;
	}

	public Squad getSquad2Data() {

		String projectPath = smartEncoder.decodeURL(SharedTestData.projectPath);

		
		System.out.println("The project path is: "  + projectPath);

		Squad squad = new Squad();

		squad.setSquadImageScreenCaptureSource(projectPath + "\\Reports\\CustomReport\\Squad2\\ImageCaptures");
		squad.setSquadVideoScreenRecordingSource(projectPath + "\\Reports\\CustomReport\\Squad2\\VideoRecordings");
		squad.setSquadMainSource(projectPath + "\\Reports\\CustomReport\\Squad2");

		if (isPublicPath()) {
			setPublicPath(squad);
		}

		// C:\Users\USER\git\gitBranch
		return squad;
	}

	public Squad getSquad3Data() {

		String projectPath = smartEncoder.decodeURL(SharedTestData.projectPath);

		Squad squad = new Squad();

		squad.setSquadImageScreenCaptureSource(projectPath + "\\Reports\\CustomReport\\Squad3\\ImageCaptures");
		squad.setSquadVideoScreenRecordingSource(projectPath + "\\Reports\\CustomReport\\Squad3\\VideoRecordings");
		squad.setSquadMainSource(projectPath + "\\Reports\\CustomReport\\Squad3");

		if (isPublicPath()) {
			setPublicPath(squad);
		}

		// C:\Users\USER\git\gitBranch
		return squad;
	}

	// HELPER FUNCTIONS

	private void setPublicPath(Squad squad) {

		System.out.println("I am in the public path");
		String projectPath = smartEncoder.decodeURL(SharedTestData.projectPath);

		squad.setSquadImageScreenCaptureSource(projectPath + "\\Reports\\CustomReport\\");
		squad.setSquadVideoScreenRecordingSource(projectPath + "\\Reports\\CustomReport\\");
		squad.setSquadMainSource(projectPath + "\\Reports\\CustomReport\\");
	}

	private boolean isPublicPath() {

		String isPublicPath = SharedTestData.isPublicPath;

		if (isPublicPath.equals("1")) {
			return true;
		}

		return false;
	}
}
