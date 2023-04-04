## BatchJobMonitoringInOKM

This project is helpful to automate Batch Job Monitoring in Oracle Knowledge Management (InQuira) application.

### Problem : 
There are twelve batch jobs (can be more if custom jobs are configured) in InfoManager which run from a frequency of 1 min to 24 hrs and perform different task like send subscription email, content batch monitoring, import/export content etc. As of now there is no monitoring mechanism available in out of the box Oracle code to keep an eye on the job execution and take action in case of job failure/hung/stuck situations. Application admins have to manually monitor the job on a regular interval or take action after business reports any issue related to batch jobs.

### Solution: 
This automation will check the job execution as per our schedule time and send a consolidated report to designated application admins/teams. This way admins can proactively monitor the jobs without logging into InfoManager and take corrective actions before business reports any issue. The report will consist following details :

•	Job Name
•	Last Execution Date Time
•	Frequency of the job
•	Current System Date Time
•	Status

From Last Execution Date Time and Status, we will get to know if the last job execution was success or error and  from the combination of Last Execution Date Time, Frequency of the job and Current System Date Time we will get to know that if the job is running as per the schedule or it is stuck/hung due to any technical issue.  Based on these data, admins can decide their next course of action.
