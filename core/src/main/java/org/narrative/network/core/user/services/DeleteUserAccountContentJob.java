package org.narrative.network.core.user.services;

import org.narrative.common.persistence.OID;
import org.narrative.network.core.quartz.AreaJob;
import org.narrative.network.core.quartz.services.QuartzJobScheduler;
import org.narrative.network.core.user.User;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.TriggerBuilder;

import static org.quartz.TriggerBuilder.*;

/**
 * User: barry
 * Date: Mar 17, 2010
 * Time: 11:36:11 AM
 */
public class DeleteUserAccountContentJob extends AreaJob {
    public static final String USER_OID = "userOid";
    public static final String USER_DISPLAY_NAME = "userDisplayName";
    public static final String USER_EMAIL = "userEmail";

    @Deprecated //Quartz Only
    public DeleteUserAccountContentJob() { }

    @Override
    protected void executeAreaJob(JobExecutionContext context) throws JobExecutionException {
        OID userOid = getOidFromContext(context, USER_OID);
        String userName = context.getMergedJobDataMap().getString(USER_DISPLAY_NAME);
        String userEmail = context.getMergedJobDataMap().getString(USER_EMAIL);
        User user = User.dao().get(userOid);
        getNetworkContext().doGlobalTask(new DeleteUserAccountContentTask(user, true, userName, userEmail));

    }

    public static void schedule(User user, String userDisplayName, String userEmail) {
        TriggerBuilder triggerBuilder = newTrigger().withIdentity(DeleteUserAccountContentJob.class.getSimpleName() + "/" + user.getOid()).usingJobData(DeleteUserAccountContentJob.USER_OID, user.getOid().toString()).usingJobData(AreaJob.AREA_OID, user.getAuthZone().getOid().toString()).usingJobData(DeleteUserAccountContentJob.USER_DISPLAY_NAME, userDisplayName).usingJobData(DeleteUserAccountContentJob.USER_EMAIL, userEmail).forJob(DeleteUserAccountContentJob.class.getSimpleName());
        QuartzJobScheduler.GLOBAL.schedule(triggerBuilder);
    }
}
