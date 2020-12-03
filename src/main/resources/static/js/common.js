const SUCCESS = '0000';
var pathName = document.location.pathname;
var index = pathName.substr(1).indexOf("/");
const contextPath = pathName.substr(0,index+1);
document.write('<script type="text/javascript" src="'+contextPath+'/webjars/jquery/3.5.1/dist/jquery.min.js"></script>');