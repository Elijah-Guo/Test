var vm = new Vue({
    el:"#app",
    data:{
        userList:[],
        user:{}
    },
    methods:{
        getUserById:function (id) {
            axios.get("/vuejsDemo/user/"+id+".do")
                .then(function (response) {
                console.log(response);
                //服务端响应的数据
                var result = response.data;
                console.log(JSON.stringify(result));
            })
        },
        getUserList:function () {
            var _this = this;
            axios.get("/vuejsDemo/user/list.do").then(function (response) {
                _this.userList = response.data;
            })
        },
        showUserInfo:function (id) {
            var _this = this;
            axios.get("/vuejsDemo/user/"+id+".do").then(function (response) {
                //把结果展示到页面
                _this.user = response.data;
                //展示模态窗口
                $('#myModal').modal("show");
            })
        },
        updateUser:function () {
            var _this = this;
            axios.post("/vuejsDemo/user/update.do", _this.user).then(function (value) {
                var result = value.data;
                if (result.result == "OK") {
                    alert("更新成功！")
                }
            })
        }
    },
    created: function() {
        this.getUserList();
    }
})