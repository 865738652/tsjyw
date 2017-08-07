
//获取时间
function GetDateString(date1) {
    var today = new Date($$.Date.Format(new Date(), "yyyy-MM-dd 00:00:00"));//今天
    var date11 = new Date($$.Date.Format(date1, "yyyy-MM-dd 00:00:00"));//date1的日期部分

    var obj = $$.Date.Diff(today, date11);
    var str = "";
    switch (obj.Days) {
        case 0:
            str = "刚刚";
            break;
        case 1:
            str = "昨天";
            break;
        case 2:
            str = "前天";
            break;
        default:
            str = $$.Date.Format(date1, "yyyy-MM-dd");// HH:mm:ss
            break;
    }
    return str;
}