<script type="text/javascript">
  //配置文件
  const appCtx = 'http://localhost:8080';
  const userId = '';
  const performance_reporting_time = '5';//每月绩效填报时间   XX-月底
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
    getReportingTime
  }
</script>
