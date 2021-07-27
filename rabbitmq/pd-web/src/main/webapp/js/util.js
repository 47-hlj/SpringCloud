//定义类
	function HashMap() {
		var myArray = new Array();
		this.put = function(key, value) {
			myArray[key] = value;
		}
		this.get = function(key) {
			var value = myArray[key];
			return value;
		}
	}
//把传过来的参数放到map里面
	function getParamMap(){
		var search = location.search;
		console.log(search);
		var map = new HashMap();
		if (search.indexOf("?") >= 0) {
			//去掉?
			search = search.substr(1);
			//console.log(search);
			var nameValues = search.split("&");
			//[0] id=1
			//[2] callback=baidu
			for (var i = 0; i < nameValues.length; i++) {
				var array = nameValues[i].split("=");
				map.put(array[0], array[1]);
			}

		}
		
		return map;

	}