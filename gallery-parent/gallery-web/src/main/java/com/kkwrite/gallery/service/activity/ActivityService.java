package com.kkwrite.gallery.service.activity;

import java.util.List;

import com.kkwrite.gallery.pojo.activity.ActivityDTO;
import com.kkwrite.gallery.pojo.activity.ActivityQuery;

public interface ActivityService {

	ActivityDTO getActivityById(ActivityQuery activityQuery);
	
	List<ActivityDTO> getActivities(ActivityQuery activityQuery);
}
