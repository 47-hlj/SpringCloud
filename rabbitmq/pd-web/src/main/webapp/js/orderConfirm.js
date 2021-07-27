 $(".go_pay").click(function () {
       // location.href = "payment.html";
       //$("#itemForm").submit();//提交form
    	//读取用户的输入——表单序列化
        var inputData = $('#itemForm').serialize();
        //异步提交请求，进行验证
        $.ajax({
            type: 'POST',
            url: '/order/submitOrder.html',
            data: inputData,
            dataType:"json",
            success: function(txt, msg, xhr){
                if(txt.status==200){  //成功
                	//window.alert("成功");
                var orderId=txt.data;
                location.href="/order/toPayment.html?orderId="+orderId;
                }else{ //失败
                	window.alert("失败");
                }
            }
        });

    })