if (typeof(pvhitimgview) == "undefined") {
    var pvhitimgview = true;
    function pv_rport(a, c) {
        var b = a.indexOf(c);
        if (b > 0) {
            return a.substring(0, b)
        }
        return a
    }
    function getRefUrl(b) {
        if (b.indexOf("ref0") > -1) {
            var a = /(?:\&|\?)ref0=([\s\S]*?)$/i;
            b = b.match(a);
            b = encodeURI(b[1]);
            return b
        }
    }
    function getDomain() {
        var a = "";
        hn = location.hostname;
        str = hn.replace(/\.(com|net|org|cn$)\.?.*/, "");
        if (str.lastIndexOf(".") == -1) {
            a = "." + hn
        } else {
            str = str.substring(str.lastIndexOf("."));
            a = hn.substring(hn.lastIndexOf(str))
        }
        return a
    }
    function getflash() {
        var b, a;
        if (window.ActiveXObject) {
            for (b = 12; b > 0; b--) {
                try {
                    a = new ActiveXObject("ShockwaveFlash.ShockwaveFlash." + b);
                    return b + ".0"
                } catch(c) {}
            }
        } else {
            if (navigator.plugins && navigator.plugins.length) {
                for (b = 0; b < navigator.plugins.length; b++) {
                    if (navigator.plugins[b].name.indexOf("Shockwave Flash") != -1) {
                        return navigator.plugins[b].description.split(" ")[2]
                    }
                }
            }
        }
        return "Not enabled"
    }
    function readck(a) {
        var c = "";
        var b = a + "=";
        if (document.cookie.length > 0) {
            offset = document.cookie.indexOf(b);
            if (offset != -1) {
                offset += b.length;
                end = document.cookie.indexOf(";", offset);
                if (end == -1) {
                    end = document.cookie.length
                }
                c = unescape(document.cookie.substring(offset, end))
            }
        }
        return c
    }
    function writeck(d, e, a) {
        var c = "";
        var b = getDomain();
        if (a != null) {
            c = new Date((new Date()).getTime() + a * 3600000);
            c = "; expires=" + c.toGMTString()
        }
        document.cookie = d + "=" + escape(e) + c + ";domain=" + b + ";path=/; "
    }
    function randck() {
        return Math.floor(Math.random() * 256)
    }
    function getRefer() {
        var a;
        if (typeof(_zpv_document_refer) == "undefined") {
            a = document.referrer
        } else {
            if ("" == _zpv_document_refer) {
                a = document.referrer
            } else {
                a = _zpv_document_refer
            }
        }
        return a
    }
    var imgsrc = "";
    function pv_d() {
        var e = document.URL;
        if (e.indexOf("#cwmysxghgu") != -1) {
            return
        }
        var a = new Date().getTime();
        var c;
        var j = getDomain();
        if (j == ".fengniao.com") {
            var h = readck("bbuserid");
            if (h != "") {
                c = "fn_" + h
            }
        } else {
            c = readck("zol_userid")
        }
        var b = escape(a * 1000 + Math.round(Math.random() * 1000));
        if (typeof(pv_subcatid) == "undefined") {
            pv_subcatid = 0
        }
        if (top.location == self.location) {
            var i = getRefer();
            i = i.replace(/\</g, "");
            i = i.replace(/\>/g, "");
            var k = location.href;
            if (k.indexOf("#") != -1) {
                k = k.substr(0, k.indexOf("#"))
            }
            imgsrc = "http://wappv.fengniao.com/images/wap3g.gif?t=" + b + "&vuserid=" + c + "&url=" + k + "&ref=" + i
        } else {
            var l = document.referrer + "";
            l = l.substr(7);
            l = pv_rport(l, "/");
            l = pv_rport(l, ":");
            var i = getRefUrl(document.referrer);
            i = (i) ? i: document.referrer;
            i = i.replace(/\</g, "");
            i = i.replace(/\>/g, "");
            var k = location.href;
            if (k.indexOf("#") != -1) {
                k = k.substr(0, k.indexOf("#"))
            }
            imgsrc = "http://wappv.fengniao.com/images/wap3g.gif?t=" + b + "&vuserid=" + c + "&url=" + k + "&ref=" + i
        }
        if (imgsrc != "") {
            var n = readck("ip_ck");
            var g = "js.fengniao.com";
            if (null == document.getElementById("iptj")) {
                if (null == document.getElementById("pv_d")) {
                    document.write('<script type="text/javascript" id="pv_d" src="http://' + g + "/wapp.php?h=&t=" + parseInt(b / 1000) + "&c=" + n + '"><\/script>')
                } else {
                    var m = document.createElement("script");
                    m.src = "http://" + g + "/wapp.php?h=&t=" + parseInt(b / 1000) + "&c=" + n;
                    m.type = "text/javascript";
                    document.getElementsByTagName("head")[0].appendChild(m)
                }
            } else {
                html = '<script type="text/javascript" id="pv_d" src="http://' + g + "/wapp.php?h=&t=" + parseInt(b / 1000) + "&c=" + n + '"><\/script>';
                var m = document.createElement("script");
                m.src = "http://" + g + "/wapp.php?h=&t=" + parseInt(b / 1000) + "&c=" + n;
                m.type = "text/javascript";
                document.getElementById("iptj").appendChild(m)
            }
        }
    }
    pv_d()
};