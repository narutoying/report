var Zauth = {
    removeApp: function(appName){
        if (confirm("确认要删除应用" + appName + "吗？")) {
            $("form#" + appName).submit();
        }
    },
    removeUserFromApp: function(appName, userName, aLink){
        if (confirm("确认要删除此管理员[" + userName + "]吗？")) {
            $.post("removeUserFromApp.json", {
                appName: appName,
                userName: userName
            }, function(backData){
                if (backData.success) {
                    $(aLink).parent().parent().remove();
                }
                else {
                    alert(backData.resultMessage);
                }
            }, 'json');
        }
    }
}
