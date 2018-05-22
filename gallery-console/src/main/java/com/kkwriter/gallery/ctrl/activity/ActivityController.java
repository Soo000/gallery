package com.kkwriter.gallery.ctrl.activity;

import com.kkwriter.gallery.entity.activity.GlyActivity;
import com.kkwriter.gallery.result.Result;
import com.kkwriter.gallery.result.ResultUtil;
import com.kkwriter.gallery.service.activity.IGlyActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lisha on 2018/5/21 13:41.
 *
 * @author lisha
 */
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @Resource(name = "activityService")
    private IGlyActivityService activityService;

    @GetMapping(value = "/managePage")
    public ModelAndView openManagePage() {
        ModelAndView model = new ModelAndView("activity_manage");
        model.addObject("activities", activityService.queryAllActivity());
        return model;
    }

    @PostMapping(value = "/save")
    public Result saveActivity(Integer activityId, String activityName, String activityUrl, Integer activityType, String activityTime,
                               String activityDescription, Integer valid, String picture) {
        GlyActivity activity = new GlyActivity();
        activity.setActivityId(activityId);
        activity.setActivityName(activityName);
        activity.setActivityUrl(activityUrl);
        activity.setActivityType(activityType);
        activity.setActivityDescription(activityDescription);
        activity.setValid(valid);
        activityService.saveActivity(activity, activityTime, picture);
        return ResultUtil.success();
    }

    @PostMapping(value = "/delete")
    public Result deleteActivity(Integer id) {
        activityService.deleteActivity(id);
        return ResultUtil.success();
    }

}
