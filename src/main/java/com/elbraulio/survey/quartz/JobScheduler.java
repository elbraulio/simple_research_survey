package com.elbraulio.survey.quartz;

import com.elbraulio.survey.utils.BProperties;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class JobScheduler extends GenericServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        Scheduler sched;
        try {
            sched = StdSchedulerFactory.getDefaultScheduler();
            sched.start();
            BProperties properties = new BProperties();
            String schedule = properties.prop("job.schedule");
            Logger.getLogger(JobScheduler.class).info("configuring job with " +
                    "schedule " + schedule);
            JobDetail job = JobBuilder.newJob(MailSurvey.class).withIdentity("QuartzScheduler").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("QuartzScheduler").withSchedule(
                    CronScheduleBuilder.cronSchedule(schedule)
            ).startNow().build();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            Logger.getLogger(JobScheduler.class).error("error to load JOB",e);
        }
    }

    @Override
    public void service(
            ServletRequest arg0,
            ServletResponse arg1
    ) {
        throw new UnsupportedOperationException();
    }
}
