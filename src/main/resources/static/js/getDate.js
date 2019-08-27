/**
 * 时间转换
 */
function getDate(format,creatTime) {
			  var now = creatTime;
			  var year = now.getFullYear(); //得到年份
			  var month = now.getMonth();//得到月份
			  var date = now.getDate();//得到日期
			  var day = now.getDay();//得到周几
			  var hour = now.getHours();//得到小时
			  var minu = now.getMinutes();//得到分钟
			  var sec = now.getSeconds();//得到秒
			  month = month + 1;
			  if (month < 10) month = "0" + month;
			  if (date < 10) date = "0" + date;
			  if (hour < 10) hour = "0" + hour;
			  if (minu < 10) minu = "0" + minu;
			  if (sec < 10) sec = "0" + sec;
			  var time = "";
			  //精确到天
			  if(format==1){
				time = year + "-" + month + "-" + date;
			  }
			  //精确到秒
			  else if(format==2){
				time = year + "-" + month + "-" + date+ " " + hour + ":" + minu + ":" + sec;
			  }
			  //精确到分
			  else if(format==3){
				time = year + "-" + month + "-" + date+ " " + hour + ":" + minu;
			  }
			  return time;
			}


//获取两个日期相差的月份
function getIntervalMonth(startDate,endDate){
	var startMonth = startDate.getMonth();
    var endMonth = endDate.getMonth();
    var intervalMonth = (startDate.getFullYear()*12+startMonth) - (endDate.getFullYear()*12+endMonth);
    return intervalMonth;
}

function getAgeMonth(i) {
	var ageAndMonth = "";
	if (i==0) {
		ageAndMonth = "刚出生";
	}else{
		if (i%6==0) {
			if (parseInt(i/6)==1) {
				ageAndMonth = "半岁";
			}else{
				if (i%12==0) {
					ageAndMonth = parseInt(i/12)+"岁";
				}else{
					ageAndMonth = parseInt(i/12)+"岁半";
				}
			}
		}else{
			if(i<12){
				ageAndMonth = i+"个月";
			}else{
				ageAndMonth = parseInt(i/12)+"岁"+(i%12)+"个月";
			}
		}
	}
	return ageAndMonth;
}


