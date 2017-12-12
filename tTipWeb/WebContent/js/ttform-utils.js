function getJSON(jqSelectorStr) {
    var arr = $(jqSelectorStr).serializeArray();
    var ret = {};
    $.each(arr, function(i, obj) {
    	var elem = document.getElementById(obj.name);
    	if (elem.type === "date") {
    		if (elem.value != null && elem.value != "") {
    			value = new Date(elem.value + "T00:00:00");
    			ret[obj.name] = value.toUTCString();
    		}
    	}
    	else {
    		ret[obj.name] = obj.value;
    	}
    	
    });
    return ret;
}

function getURLParameters() {
	var sPageURL = decodeURIComponent(window.location.search.substring(1));
	var aParams = sPageURL.split("&");
	var aParam;
	var ret = {};

	for (i = 0; i < aParams.length; i++) {
		aParam = aParams[i].split("=");
		ret[aParam[0]] = aParam[1];
    }
	
	return ret;
}

function fillForm(data) {
	
    $.each(data, function(name, value) {
    	$("#" + name).val("" + value).change();
	});
	
}