(function () {
    window.jWolf = window.$$ = {};

    $$.Base = {
        //获取元素，兼容传id和传元素
        GetNode: function (node) {
            if ($.type(node) == 'string') {
                return $("#" + node);
            } else {
                return $(node);
            }
        },
        //反射绑定  @{}
        Format: function (htm, data) {
            var reg, result, propertyName, propertyValue;

            for (reg = new RegExp("@{[a-z,A-Z,0-9,_]+}", "g") ; result = reg.exec(htm) ; reg = new RegExp("@{[a-z,A-Z,0-9,_]+}", "g")) {
                propertyName = result[0].substr(2, result[0].length - 3);
                propertyValue = eval("data." + propertyName);
                //alert(propertyName);
                if (propertyValue != undefined) {
                    htm = htm.replace(result[0], propertyValue);
                } else {
                    htm = htm.replace(result[0], "@@!!!!^====" + propertyName + "!!!!^!!!!@");//当匹配不到值时，加些乱码，这样做避免了一直匹配死循环
                }
            }

            htm = htm.replace("@@!!!!^====", "@{");//把乱码替换回来
            htm = htm.replace("!!!!^!!!!@", "}");
            return htm;
        }
    }
    $$.Browser = {
        /* 关闭浏览器滚动条  */
        CloseScroolBall: function () {
            document.body.parentNode.style.overflow = "hidden";
        },
        /* 打开浏览器滚动条 */
        OpenScroolBall: function () {
            document.body.parentNode.style.overflow = "scroll";
        },
        //获得URl参数值
        GetQueryString: function (name) {
            //构造一个含有目标参数的正则表达式对象
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            //从问号 (?) 开始的 URL（查询部分）
            var searchString = window.location.search.substr(1);
            //匹配目标参数
            var r = searchString.match(reg);
            //返回参数值
            return r != null ? unescape(r[2]) : null;
        },
        //刷新本页面
        ReloadWindow: function () {
            var url = window.location.href;
            if (url.indexOf('#') > -1) {
                window.location = url.substring(0, url.indexOf('#'));
            }
            else {
                window.location = url;
            }
        }
    }
    $$.Message = {
        jWolfTimer: null,
        index: 1,
        title: document.title,

        BeginTwinkle: function (str) {
            /// <summary>标题栏闪烁</summary>
            /// <param name="str" type="String">标题字符串</param>
            $$.Message.index++;
            document.title = $$.Message.index % 2 == 0 ? str : $$.Message.title;
            $$.Message.jWolfTimer = setTimeout(function () {
                $$.Message.BeginTwinkle(str);
            }, 800);
        },
        EndTwinkle: function () {
            /// <summary>移除闪烁</summary>
            document.title = $$.Message.title;
            clearTimeout($$.Message.jWolfTimer);
        }
    }
    $$.Date = {
        ParseJsonDate: function (str) {
            /// <summary>字符串转换成js Data对象</summary>         
            /// <param name="str" type="String">json日期</param>
            try {
                var date = eval(str.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"));
                return date;
            } catch (e) {
                return undefined;
            }
        },
        // 将 Date 转化为指定格式的String   
        // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
        // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
        // 例子：   
        // $$.Date.Format(date,"yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423   
        // $$.Date.Format(date,"yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18   
        Format: function (date, fmt) {  
            var o = {
                "M+": date.getMonth() + 1,               //月份   
                "d+": date.getDate(),                    //日   
                "H+": date.getHours(),                   //小时   
                "m+": date.getMinutes(),                 //分   
                "s+": date.getSeconds(),                 //秒   
                "q+": Math.floor((date.getMonth() + 3) / 3), //季度   
                "s": date.getMilliseconds()             //毫秒   
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        },
        //俩个时间相减
        Diff: function (date1, date2) {
            var date3 = date1.getTime() - date2.getTime()  //时间差的毫秒数

            var days = Math.floor(date3 / (24 * 60 * 60 * 1000))//计算出相差天数
            var leave1 = date3 % (24 * 60 * 60 * 1000)    //计算天数后剩余的毫秒数 

            var hours = Math.floor(leave1 / (60 * 60 * 1000));
            var leave2 = leave1 % (60 * 60 * 1000)        //计算小时数后剩余的毫秒数  

            var minutes = Math.floor(leave2 / (60 * 1000))//计算相差分钟数  
            var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数  

            var seconds = Math.round(leave3 / 1000)//计算相差秒数  

            return { "Days": days, "Hours": hours, "Minutes": minutes, "Seconds": seconds, "Milliseconds": date3 };
        }

    }
    $$.String = {
        PixelToInt: function (strPx) {
            /// <summary>像素值转换成整数</summary>         
            /// <param name="strPx" type="String">像素字符串</param>
            return parseInt(strPx.replace("px", ""));
        },
        ToInt: function (str) {
            /// <summary>字符串转换成整数</summary>         
            /// <param name="str" type="String">数字字符串</param>
            return parseInt(str);
        },
        ToJson: function (str) {
            /// <summary>字符串转换成Json对象</summary>         
            /// <param name="str" type="String">json字符串</param>
            try {
                return eval("(" + str + ")");
            } catch (e) {
                return [];
            }
        },
        Format: function () {
            /// <summary>
            /// 格式化字符串
            /// <para>字符串,obj1,obj2,obj3,obj4....</para>
            /// <para>字符串,[obj1,obj2,obj3,obj4....]</para>
            /// </summary>      
            var str = arguments[0];
            var arg = arguments[1];
            if (arguments.length == 1) {
                return str;
            }
            if (arguments.length == 2 && $$.Collection.IsArray(arg)) {
                for (var i = 0; i < arg.length; i++) {
                    var reg = new RegExp("\\{" + i + "\\}", "gm");
                    str = str.replace(reg, arg[i]);
                }
            } else {
                for (var i = 0; i < arguments.length - 1; i++) {
                    var reg = new RegExp("\\{" + i + "\\}", "gm");
                    str = str.replace(reg, arguments[i + 1]);
                }
            }

            return str;
        },
        GetValue: function (id) {
            /// <summary>根据id获取值，并去掉 双引号、单引号、回车</summary>
            /// <param name="id" type="String">dom节点的id</param>
            var txt = $("#" + id).val();
            txt = txt.replace(/\"/g, "");
            txt = txt.replace(/\n/g, "<br/>");
            txt = txt.replace(/\'/g, "");
            return txt;
        },
        /*
        ================================================================================
        XMLEncode(string):对字符串进行XML编码
        ================================================================================
        */
        XMLEncode: function (str) {
            str = Trim(str);
            str = str.replace("&", "&amp;");
            str = str.replace("<", "&lt;");
            str = str.replace(">", "&gt;");
            str = str.replace("'", "&apos;");
            str = str.replace("\"", "&quot;");
            return str;
        },
        //把字符串解码转换成数组
        StringtoArray: function (strStringLine) {
            if (strStringLine == undefined) {
                throw new Error(L_NullArgumentsException);
            }
            var index = strStringLine.lastIndexOf('@');
            if (index == -1) {
                if (strStringLine.length == 0) {
                    return [];
                }
                else {
                    var result = new Array(1);
                    result[0] = strStringLine;
                    return result;
                }
            }
            var strLengthLine = strStringLine.substring(index + 1);
            var aryLength = strLengthLine.split(':');
            var list = new Array(aryLength.length);
            var start = 0;
            for (var i = 0 ; i < list.length ; ++i) {
                var length = parseInt(aryLength[i]);
                list[i] = strStringLine.substr(start, length);
                start += length;
            }
            return list;
        },
        //把数组组装为字符串 
        ArraytoString: function () {
            if (arguments.length == 0) {
                throw new Error('Must have parameters for call me.');
            }
            var args = arguments;
            if (args.length == 1) {
                args = arguments[0];
                if (args.length == 0) {
                    return '';
                }
            }
            var strLengthLine = '';
            var strStringLine = '';
            for (var i = 0 ; i < args.length ; i++) {
                var str = '' + args[i];
                strLengthLine += ':' + str.length;
                strStringLine += str;
            }
            return strStringLine + '@' + strLengthLine.substring(1);
        },
        IsEmpty: function (str) {
            if (!str || !str.length) {
                return true;
            }
            return str.length == 0;
        },

        HtmlEncode: function (original) {
            if (!document.htmlEncoder) {
                document.htmlEncoder = document.createElement('SPAN');
            }
            document.htmlEncoder.innerText = original;
            return document.htmlEncoder.innerHTML;
        },

        QuotEscape: function (original) {
            return original.replace(/%/g, '%25').replace(/"/g, '%22').replace(/'/g, '%27');
        },

        QuotUnescpe: function (escaped) {
            return escaped.replace(/%27/g, "'").replace(/%22/g, '"').replace(/%25/g, '%');
        },
        //Trim(string):去除前后空格
        Trim: function (str) {
            if (str) {
                return str.replace(/(^\s*)|(\s*$)/g, '');
            }
            else {
                return str;
            }
        },
        //TrimStart(string):去除左边的空格
        TrimStart: function (str) {
            if (str) {
                return str.replace(/(^\s*)/g, '');
            }
            else {
                return str;
            }
        },
        //TrimEnd(string):去除右边的空格
        TrimEnd: function (str) {
            if (str) {
                return str.replace(/(\s*$)/g, '');
            }
            else {
                return str;
            }
        }
    }

    $$.Collection = {
        IsArray: function (o) {
            return Object.prototype.toString.call(o) === '[object Array]';
        },
        //查找array中的对象，查找条件 该对象的指定属性与指定参数相匹配，并返回该对象在数组中的索引号
        GetArrayIndex: function (array, classProperty, obj) {
            for (var i = 0; i < array.length; i++) {
                if (eval("array[i]" + classProperty) == obj) {
                    return i;
                }
            }
            return -1;
        },

        //移除数组中的指定元素
        Remove: function (array, index) {
            var aryReturn = new Array();
            for (var i = 0; i < array.length; i++) {
                if (i != index) {
                    aryReturn.push(array[i]);
                }
            }
            return aryReturn;
        },

        //转换为序列
        ToXl: function (array) {
            var str = "";
            for (var i = 0; i < array.length; i++) {
                if (i > 0) {
                    str += ",";
                }
                str += array[i];
            }
            return str;
        },

        //var my=new $$.Collection.HashTable(); 
        //my.Add("name","blueKnight"); 
        //my.Add("age",'24'); 
        //my.Add("sex","boy"); 
        //alert(my.Count());
        //alert(my.ContainsValue("boy"));
        //alert(my.GetValue("name"))
        //for(var i in my.Items) 
        //{ 
        //  alert("Key:"+my.Items[i].Key+"--Value:"+my.Items[i].Value); 
        //} 
        //my.Remove("age"); 
        //alert(my.Keys()+'-'+my.Values()+'\n\r');  
        HashTable: function () {
            this.Items = [];
            this.Count = function () { return this.Items.length; }; //长度                 
            this.DictionaryEntry = function (key, value) {
                this.Key = key || null;
                this.Value = value || null;
            }
            this.Add = function (key, value) { this.Items.push(new this.DictionaryEntry(key, value)); }
            this.Clear = function () { this.Items.length = 0; }
            this.Remove = function (key) {
                var index = this.GetIndexWithKey(key);
                if (index > -1)
                    this.Items.splice(index, 1);
            }
            this.GetValue = function (key) {
                var index = this.GetIndexWithKey(key);
                if (index > -1)
                    return this.Items[index].Value;
            }
            this.ContainsKey = function (key) {
                if (this.GetIndexWithKey(key) > -1)
                    return true;
                return false;
            }
            this.ContainsValue = function (value) {
                if (this.GetIndexWithValue(value) > -1)
                    return true;
                return false;
            }
            this.Keys = function () {
                var iLen = this.Count();
                var resultArr = [];
                for (var i = 0; i < iLen; i++)
                    resultArr.push(this.Items[i].Key);
                return resultArr;
            }
            this.Values = function () {
                var iLen = this.Count();
                var resultArr = [];
                for (var i = 0; i < iLen; i++)
                    resultArr.push(this.Items[i].Value);
                return resultArr;
            }
            this.IsEmpty = function () { return this.Count() == 0; }
            this.GetIndexWithKey = function (key) {
                var iLen = this.Count();
                for (var i = 0; i < iLen; i++)
                    if (this.Items[i].Key === key)
                        return i;
                return -1;
            }
            this.GetIndexWithValue = function (value) {
                var iLen = this.Count();
                for (var i = 0; i < iLen; i++)
                    if (this.Items[i].Value === value)
                        return i;
                return -1;
            }
        }
    }
    $$.Control = { //为textbox注册鼠标放上去清空当前内容的事件
        //node：元素本身(dom元素)
        //text:元素上默认显示的文本
        Add001: function (node, text) {
            node = $$.Base.GetNode(node);
            $(node).focus(function () {
                if ($(node).val() == text) {
                    $(this).val("");
                }
            });
        },

        //为textbox注册鼠标放上去清空当前内容的事件
        //node：元素本身(dom元素)
        //text:元素上默认显示的文本
        Add002: function (node, text) {
            node = $$.Base.GetNode(node);
            if ($(node).val() == '') {
                $(node).val(text);
            }

            $(node).focus(function () {
                if ($(node).val() == text) {
                    $(this).val("");
                }
            }).blur(function () {
                if ($(this).val() == '') {
                    $(this).val(text);
                }
            });
        },

        //绑定select的数据
        BindSelect: function (id, list, valueColumn, textColumn) {
            var htmlTemp = "<option value='@{" + valueColumn + "}'>@{" + textColumn + "}</option>";
            var html = "";

            for (var i = 0; i < list.length; i++) {
                html += $$.Base.Format(htmlTemp, list[i]);
            }

            $("#" + id).html(html);
        }
    }
    $$.Check = {
        /*IsHanZi(string):检测是否是汉字*/
        IsHanZi: function (value) {
            var reg = new RegExp("^[\u4E00-\u9FA5]*$", "g");

            return reg.test(value);
        },
        /*
        IsInt(string,string,int or string):(测试字符串,+ or - or empty,empty or 0)
        功能：判断是否为整数、正整数、负整数、正整数+0、负整数+0
        */
        IsInt: function (objStr, sign, zero) {
            var reg;
            var bolzero;

            if (Trim(objStr) == "") {
                return false;
            }
            else {
                objStr = objStr.toString();
            }

            if ((sign == null) || (Trim(sign) == "")) {
                sign = "+-";
            }

            if ((zero == null) || (Trim(zero) == "")) {
                bolzero = false;
            }
            else {
                zero = zero.toString();
                if (zero == "0") {
                    bolzero = true;
                }
                else {
                    alert("检查是否包含0参数，只可为(空、0)");
                }
            }

            switch (sign) {
                case "+-":
                    //整数
                    reg = /(^-?|^\+?)\d+$/;
                    break;
                case "+":
                    if (!bolzero) {
                        reg = /^\+?[0-9]*[1-9][0-9]*$/;//正整数
                    }
                    else {
                        //正整数+0
                        //reg=/^\+?\d+$/;
                        reg = /^\+?[0-9]*[0-9][0-9]*$/;
                    }
                    break;
                case "-":
                    if (!bolzero) {
                        //负整数
                        reg = /^-[0-9]*[1-9][0-9]*$/;
                    }
                    else {
                        //负整数+0
                        //reg=/^-\d+$/;
                        reg = /^-[0-9]*[0-9][0-9]*$/;
                    }
                    break;
                default:
                    alert("检查符号参数，只可为(空、+、-)");
                    return false;
                    break;
            }
            var r = objStr.match(reg);
            if (r == null) {
                return false;
            }
            else {
                return true;
            }
        },

        /*
        IsFloat(string,string,int or string):(测试字符串,+ or - or empty,empty or 0)
        功能：判断是否为浮点数、正浮点数、负浮点数、正浮点数+0、负浮点数+0
        */
        IsFloat: function (objStr, sign, zero) {
            var reg;
            var bolzero;

            if (Trim(objStr) == "") {
                return false;
            }
            else {
                objStr = objStr.toString();
            }

            if ((sign == null) || (Trim(sign) == "")) {
                sign = "+-";
            }

            if ((zero == null) || (Trim(zero) == "")) {
                bolzero = false;
            }
            else {
                zero = zero.toString();
                if (zero == "0") {
                    bolzero = true;
                }
                else {
                    alert("检查是否包含0参数，只可为(空、0)");
                }
            }

            switch (sign) {
                case "+-":
                    //浮点数
                    reg = /^((-?|\+?)\d+)(\.\d+)?$/;
                    break;
                case "+":
                    if (!bolzero) {
                        //正浮点数
                        reg = /^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
                    }
                    else {
                        //正浮点数+0
                        reg = /^\+?\d+(\.\d+)?$/;
                    }
                    break;
                case "-":
                    if (!bolzero) {
                        //负浮点数
                        reg = /^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
                    }
                    else {
                        //负浮点数+0
                        reg = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
                    }
                    break;
                default:
                    alert("检查符号参数，只可为(空、+、-)");
                    return false;
                    break;
            }

            var r = objStr.match(reg);
            if (r == null) {
                return false;
            }
            else {
                return true;
            }
        },

        CheckInteger: function (e) {
            if ((e.keyCode > 7 && e.keyCode < 10) || (e.keyCode > 27 && e.keyCode < 30) || e.keyCode > 47 && e.keyCode < 58) {
                e.returnValue = e.keyCode;
            }
            else {
                e.cancelBubble = true;
                e.returnValue = false;
            }
        },
        CheckEmail: function (address) {
            if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(address)) {
                return (false)
            }
            return (true)
        },
        //手机验证
        CheckMobile: function (tel) {
            var m = false;
            var reg0 = /^1\d{10}$/;   //130--139。至少7位
            //var reg1=/^15\d{9}$/;  //联通153。至少7位
            //var reg2=/^18\d{9}$/;  //移动159。至少7位
            if (reg0.test(tel)) m = true;
            //if (reg1.test(tel))m=true;
            //if (reg2.test(tel))m=true;

            return m;
        },
        //身份证验证
        CheckIdentityCard: function (idcard) {
            var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" }
            var idcard, Y, JYM;
            var S, M;
            var idcard_array = idcard.split("");
            //地区检验
            if (area[parseInt(idcard.substr(0, 2))] == null)
                return "身份证地区非法!";
            //身份号码位数及格式检验
            switch (idcard.length) {
                case 15:
                    if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
                    } else {
                        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
                    }
                    if (ereg.test(idcard)) {
                        return "验证通过!";
                    } else {
                        return "身份证号码出生日期超出范围或含有非法字符!";
                    }
                    break;
                case 18:
                    //18位身份号码检测
                    //出生日期的合法性检查
                    //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
                    //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
                    if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                        ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
                    } else {
                        ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
                    }
                    if (ereg.test(idcard)) {//测试出生日期的合法性
                        //计算校验位
                        S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                        + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                        + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                        + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                        + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                        + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                        + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                        + parseInt(idcard_array[7]) * 1
                        + parseInt(idcard_array[8]) * 6
                        + parseInt(idcard_array[9]) * 3;
                        Y = S % 11;
                        M = "F";
                        JYM = "10X98765432";
                        M = JYM.substr(Y, 1);//判断校验位
                        if (M == idcard_array[17]) {
                            return "验证通过!"; //检测ID的校验位
                        } else {
                            return "身份证号码校验错误!";
                        }
                    } else {
                        return "身份证号码出生日期超出范围或含有非法字符!";
                    }
                    break;
                default:
                    return "身份证号码位数不对!";
                    break;
            }
        },
        //验证护照
        GetCheckPassportNumber: function (number) {
            //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
            var Expression = /(P\d{7})|(G\d{8})/;
            var objExp = new RegExp(Expression);
            if (objExp.test(number) == true) {
                return true;
            } else {
                return false;
            }
        },
        //是否为日期字符串
        IsDate: function () {
            if (!str.match(/^\d{4}\-\d\d?\-\d\d?$/)) { return false; }
            var ar = str.replace(/\-0/g, "-").split("-");
            ar = new Array(parseInt(ar[0]), parseInt(ar[1]) - 1, parseInt(ar[2]));
            var d = new Date(ar[0], ar[1], ar[2]);
            return d.getFullYear() == ar[0] && d.getMonth() == ar[1] && d.getDate() == ar[2];
        },
        //判断字符串是否全为数字
        IsNum: function (value) {
            var reg = /^\d+$/g;
            return reg.test(value);
        }
    }
})();


//-------------JQuery插件类------------------------------
$.fn.setCursorPosition = function (position) {
    if (this.lengh == 0) return this;
    return $(this).setSelection(position, position);
}

$.fn.setSelection = function (selectionStart, selectionEnd) {
    if (this.lengh == 0) return this;

    var input = this[0];

    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd);
    }

    return this;
}

$.fn.focusEnd = function () {
    this.setCursorPosition(this.val().length);
}