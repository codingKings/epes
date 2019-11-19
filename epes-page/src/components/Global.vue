<script type="text/javascript">
  //配置文件
  const appCtx = 'http://localhost:8080';
  const userId = '';
  const performance_reporting_time = '1';//每月绩效填报时间   XX-月底
  const score_time = '4';
  const startDate='2019-01';
  const endDate = '2019-12';
  function getAppCtx () {
    return appCtx;
  }

  function getReportingTime () {
    return performance_reporting_time;
  }

  export default
  {
    appCtx,
    userId,
    performance_reporting_time,
    getAppCtx,
    getReportingTime,
    score_time,
    startDate,
    endDate
  }
</script>
