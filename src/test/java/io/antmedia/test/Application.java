package io.antmedia.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.antmedia.AntMediaApplicationAdapter;
import io.antmedia.datastore.db.types.Broadcast;
import io.antmedia.muxer.IAntMediaStreamHandler;

public class Application extends AntMediaApplicationAdapter implements IAntMediaStreamHandler {

	public static List<String> id = new ArrayList<>();
	public static List<File> file = new ArrayList<>();
	public static List<Long> duration = new ArrayList<>();
	public static List<Long> startTime = new ArrayList<>();

	public static List<String> notifyHookAction = new ArrayList<>();
	public static List<String> notitfyURL = new ArrayList<>();
	public static List<String> notifyId = new ArrayList<>();
	public static List<String> notifyStreamName = new ArrayList<>();
	public static List<String> notifyCategory = new ArrayList<>();
	public static List<String> notifyVodName = new ArrayList<>();

	public static boolean enableSourceHealthUpdate = false;
	public static List<String> notifyVodId = new ArrayList<>();;
	

	
	
	@Override
	public void muxingFinished(Broadcast broadcast, String streamId, File file, long startTime, long duration, int resolution, String previewPath, String vodId) {
		super.muxingFinished(broadcast, streamId, file, startTime, duration, resolution, previewPath, vodId);
		Application.id.add(broadcast.getStreamId());
		Application.file.add(file);
		Application.duration.add(duration);
		Application.startTime.add(startTime);
	}
	

	public static void resetFields() {
		Application.id.clear();
		Application.file.clear();
		Application.duration.clear();
		Application.startTime.clear();
		notifyHookAction.clear();
		notitfyURL.clear();
		notifyId.clear();
		notifyStreamName.clear();
		notifyCategory.clear();
		notifyVodName.clear();

	}

	@Override
	public void notifyHook(String url, String id, String mainTrackId, String action, String streamName, String category,
                           String vodName, String vodId, String metadata, String subscriberId, Map<String, String> parameters) {
		logger.info("notify hook action: {}", action);
		notifyHookAction.add(action);
		notitfyURL.add(url);
		notifyId.add(id);
		notifyStreamName.add(streamName);
		notifyCategory.add(category);
		notifyVodName.add(vodName);
		notifyVodId.add(vodId);

	}

	@Override
	public void setQualityParameters(String id, String quality, double speed, int pendingPacketSize, long updateTime) {
		if (enableSourceHealthUpdate) {
			super.setQualityParameters(id, quality, speed, pendingPacketSize, updateTime);
		}
	}
	
}
