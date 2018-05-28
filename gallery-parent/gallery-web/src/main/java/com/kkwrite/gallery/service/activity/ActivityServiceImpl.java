package com.kkwrite.gallery.service.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.mapper.activity.GlyActivityMapper;
import com.kkwrite.gallery.pojo.activity.ActivityDTO;
import com.kkwrite.gallery.pojo.activity.ActivityQuery;
import com.kkwrite.gallery.pojo.activity.GlyActivityDO;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private GlyActivityMapper glyActivityMapper;

	@Override
	public ActivityDTO getActivityById(ActivityQuery activityQuery) {
		List<ActivityDTO> activityDTOs = getActivities(activityQuery);
		if (activityDTOs == null || activityDTOs.size() <= 0) {
			return null;
		}
		
		return activityDTOs.get(0);
	}

	@Override
	public List<ActivityDTO> getActivities(ActivityQuery activityQuery) {
		List<GlyActivityDO> activities = glyActivityMapper.selectSelective(activityQuery);
		if (activities == null) {
			return null;
		}
		
		List<ActivityDTO> activityDTOs = new ArrayList<ActivityDTO>(activities.size());
		for (GlyActivityDO glyActivityDO: activities) {
			ActivityDTO activityDTO = new ActivityDTO();
			activityDTO.setActivityId(glyActivityDO.getActivityId());
			activityDTO.setActivityName(glyActivityDO.getActivityName());
			activityDTO.setActivityUrl(glyActivityDO.getActivityUrl());
			activityDTO.setActivityBeginTime(glyActivityDO.getActivityBeginTime());
			activityDTO.setActivityEndTime(glyActivityDO.getActivityEndTime());
			activityDTO.setActivityPictureFileName(glyActivityDO.getActivityPictureFileName());
			activityDTOs.add(activityDTO);
		}
		return activityDTOs;
	}

}
