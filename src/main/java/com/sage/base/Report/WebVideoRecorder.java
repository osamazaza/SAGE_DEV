package com.sage.base.Report;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sage.base.TestBase;


import static org.monte.media.VideoFormatKeys.*;

public class WebVideoRecorder extends ScreenRecorder {

	// Make this instance variable non-static for proper inheritance/usage
	private static WebVideoRecorder screenRecorder;
	private String name;
	private WebDriver driver;
	// Store the movie folder passed into the constructor
	private File movieFolder;


	public WebVideoRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
							Format screenFormat, Format mouseFormat, Format audioFormat,
							File movieFolder, String name, WebDriver driver) throws IOException, AWTException {

		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
		this.driver = driver;
		this.movieFolder = movieFolder;
	}

	/**
	 * This method is called internally by ScreenRecorder.start().
	 * It should only return the File object for the video.
	 */
	@Override
	protected File createMovieFile(Format fileFormat ) throws IOException {
// this function is used by screenRecorder parent (video recording framework) to tell where the the video is, path, and the name of the video that will be recorded.
// it's required for framework, and it's being invoked before stop recording is inokved, in other words, you will define during startRecording the name and path of the video, and that function will reach to this data to tell framework about this data to sucessfully create the video file.
// you can before telling the framework about the file, to add new paths or alter name, in my case I append unique timestamp to the video name to make the video recording unique.



		// This print will now execute when startRecord() is successfully called.
		System.out.println("Create Movie file: Defining output path...");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

		// Use the name and folder already stored in the instance.
		String fileName = name + "-" + dateFormat.format(new Date()) + "."
				+ Registry.getInstance().getExtension(fileFormat);

		//x1_123ID.avi
		// teller

		System.out.println("Registry.getInstance().getExtension(fileFormat) " + Registry.getInstance().getExtension(fileFormat));
		System.out.println("File name is :" + fileName);
		System.out.println("Absolute File path :" + this.movieFolder.getAbsoluteFile());

		return new File(this.movieFolder, fileName);
	}
	// --- Recorder Lifecycle Methods ---

	/**
	 * Initializes the static screenRecorder object. This must be called BEFORE startRecord().
	 */
	public static void initializeRecorder(String testCaseID) throws Exception {

		System.out.println("Initializing Monte Recorder...");

		// 1. Define folder path based on TestBase attributes
		String folderPath = TestBase.squad.getSquadVideoScreenRecordingSource()
				+ File.separator + "Test Suites"
				+ File.separator + TestBase.getReportAttributes().getTestSuiteName()
				+ File.separator + "Test Cases"
				+ File.separator + testCaseID;

		File movieFolder = new File(folderPath);
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		}

		// 2. Define recording area and configuration
		String safeMethodName = testCaseID.replaceAll("[^a-zA-Z0-9_-]", "_");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();

		// 3. Create and assign the new WebVideoRecorder instance
		screenRecorder = new WebVideoRecorder(
				gc,
				captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(
						MediaTypeKey, MediaType.VIDEO,
						EncodingKey, ENCODING_AVI_MJPG, // Use standard MJPG codec
						CompressorNameKey, ENCODING_AVI_MJPG,
						DepthKey, 24,
						FrameRateKey, Rational.valueOf(10), // Increased frame rate for smoother video
						QualityKey, 0.2f, // Set to highest quality
						KeyFrameIntervalKey, 60

				),
				null, // No mouse cursor recording
				null, // No audio recording
				movieFolder,
				safeMethodName,
				TestBase.getDriver()
		);
	}

	/**
	 * Starts the recording. This call will internally execute createMovieFile().
	 */
	public static void startRecord(String testCaseID) throws Exception {
		if (screenRecorder == null) {
			// Ensure initialization happened before attempting to start
			initializeRecorder(testCaseID);
		}
		System.out.println("Starting Monte Recorder...");
		screenRecorder.start();
	}

	/**
	 * Stops and saves the recording file.
	 */
	public static void stopRecord() throws Exception {
		if (screenRecorder != null) {
			System.out.println("Stopping Monte recorder...");
			screenRecorder.stop();
			screenRecorder = null; // Clear the recorder instance
		}
	}
}