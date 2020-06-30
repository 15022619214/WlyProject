$(function () {
    $('#startTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#endTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#startBankTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#endBankTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#startTime_del').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language:  'zh-CN',
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        minView: 0,
        minuteStep: 1
    });

    $('#endTime_del').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language:  'zh-CN',
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        minView: 0,
        minuteStep: 1
    });

    $('#enableDate').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#DZnian').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#DZyue').datetimepicker({
        format: 'mm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#Qxdznian').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#Qxdzyue').datetimepicker({
        format: 'mm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#HZBalance').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#pZstartTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#pZendTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#yWstartTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#yWendTime').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#YeDZnianH').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#YeDZyueH').datetimepicker({
        format: 'mm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#pZnoreachStart').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#pZnoreachEnd').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#wDnianwork').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#wDyuework').datetimepicker({
        format: 'mm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#yWnoreachStart').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#yWnoreachEnd').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#wDnianbank').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#wDyuebank').datetimepicker({
        format: 'mm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language:  'zh-CN'
    });

    $('#RcDzsj').datetimepicker({
        language:  'zh-CN',
        format:"yyyy-mm-dd",
        autoclose: 1,
        todayBtn:  1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

})