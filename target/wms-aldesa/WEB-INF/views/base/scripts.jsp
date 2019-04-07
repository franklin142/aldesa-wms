<%--
  Author: Lennin Hernandez
  Date: 7/18/2016
  Time: 5:25 PM
--%>

<!-- jQuery Load -->
<script src="/wms-aldesa/static/resources/js/jquery-min.js"></script>
<script src="/wms-aldesa/static/resources/js/bootstrap-datepicker.min.js"></script>
<script src="/wms-aldesa/static/resources/js/bootstrap-timepicker.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- Bootstrap JS -->
<script src="/wms-aldesa/static/resources/js/bootstrap.js"></script>
<!-- WOW JS plugin for animation -->
<script src="/wms-aldesa/static/resources/js/wow.js"></script>
<!-- All JS plugin Triggers -->
<script src="/wms-aldesa/static/resources/js/main.js"></script>
<!-- Moment.js Load -->
<script src="/wms-aldesa/static/resources/js/moment.js"></script>


<script>

    var DoNav = function(url) {
        document.location.href = url;
    };

    var getCookie = function (c_name) {
        var c_value = " " + document.cookie;
        var c_start = c_value.indexOf(" " + c_name + "=");
        if (c_start == -1) {
            c_value = null;
        }
        else {
            c_start = c_value.indexOf("=", c_start) + 1;
            var c_end = c_value.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = c_value.length;
            }
            c_value = unescape(c_value.substring(c_start,c_end));
        }
        return c_value;
    };

    var verifyAuthentication = function(){
        if(getCookie('user')==null && getCookie('useradm')==null ) document.location.href ='/wms-aldesa/rest/auth/login-web';
        if(getCookie('auth')==null && getCookie('authadm')==null ) document.location.href ='/wms-aldesa/rest/auth/login-web';
    };


	var openInNewTab = function (url) {
		var win = window.open(url, '_blank');
		win.focus();
	};
</script>