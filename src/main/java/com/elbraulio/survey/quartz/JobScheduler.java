package com.elbraulio.survey.quartz;

import com.elbraulio.survey.utils.BProperties;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.*;

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
            JobDetail job = JobBuilder.newJob(MailSurvey.class).withIdentity("JobScheduler").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JobScheduler").withSchedule(
                    CronScheduleBuilder.cronSchedule(schedule)
            ).startNow().build();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            Logger.getLogger(JobScheduler.class).error(e);
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
