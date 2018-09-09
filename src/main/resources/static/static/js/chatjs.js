var chatUser;
var stompClient;
$(function() {
	disconnect();
	connect();
////////////////////////////////socket/////////////////////////////////////////////////
	
	$("body").bind('keyup', function(event) {
		if (event.keyCode == 13) {
			sendMsg();
		}
	});
	$("#btn_send").click(function() {
		sendMsg();
	})

	for (i = 0; i < userArray.length; i++) {
		newUser('.ulist', userArray[i]);
	}
	$("#texterea").focus(function(){
		$("#texterea").val("");
		});
})

	function sibHide(tag) {
		$(tag).show(function() {
			$(".my_show").scrollTop($(".con_box").height() - $(".my_show").height());
		}).siblings().hide();
	}
	function newConWin(user, id) {
		if ($("#user_con" + id).length < 1) {
			$(".con_box").append('<div id="user_con' + id + '"></div>');
			$("#user_con" + id).hide();
		}
		sibHide("#user_con" + id);
	}
	
	function titleUser(id, user) {
		if ($("#" + id).length < 1) {
			$("#mid_top") .html('<li class="active" data-id=' + id+ '><a href="#"  class="glyphicon glyphicon-user">' + user[1] + '</a></li>');
		}
		chatUser = user;// 当前用户
		newConWin(user, id);
		if (user[3].indexOf("QL") > -1) {
			$("#mid_top li a").removeClass("glyphicon-user").addClass("glyphicon-tower");
		}
		$("#mid_top li a").click(function() {
			titleUser(id, user);
			$(this).addClass("active").siblings().removeClass("active");
			loadChatRecord(user[3]);
		});
	}

function sendMsg() {
		var t = new Date().toLocaleTimeString();// 当前时间
		if ($("#texterea").val()) {
			var msg=trim($("#texterea").val()) ;
			var chatUserId = chatUser[0];
			postMsg(chatUserId,msg)
			$("#user_contitle_user" + chatUser[0]).append('<div   style="text-align:right;padding-right:50px;font-family:Georgia;">' + loginUser.username + t + "<p>" + msg + '</p></div>');
			$("#texterea").val("");
			$(".my_show").scrollTop($(".con_box").height() - $(".my_show").height());// 让滚动滚到最底端
			if ($("#user" + chatUserId).length < 1) {
				newUser('.ulist', chatUser, chatUserId);
			} else {
				modChatUser('.ulist', chatUser);
			}
		}
		$("#texterea").focus();// 光标焦点
	}
	

function newGroup(tag, user,chating){
		var id = "user";
		var frag='<li id="' + id + user[0]+ '"><a href="#" class="glyphicon glyphicon-tower" style="color: #333;">' + user[1] + '</a></li>';
		if (chating != undefined) {
			$(tag).prepend(frag);
			$('#' + id + user[0]).click(function() {
				titleUser('title_' + id + chating, user);
				$(this).addClass("active").siblings().removeClass("active");
				loadChatRecord(user[3]);
			});
		} else {// 创建好友
			$(tag).append(frag);
			$('#' + id + user[0]).click(function() {
				titleUser('title_' + id + user[0], user);
				$(this).addClass("active").siblings().removeClass("active");
				loadChatRecord(user[3]);
			});// 给按钮加事件
			if(i==0){
				titleUser('title_' + id + user[0], user);
				$('#' + id + user[0]).addClass("active");
				loadChatRecord(user[3]);
			}
		}
		hoverUser('#' + id + user[0]);
	}


function loadChatRecord(partCode){
	$.post("/latestrecord",  {},  function(data,status){
		data.data.forEach(function( val, index ) {
			
			if(data.data.from.id!=loginUser.id){
				if (data.data.to.usercode.indexOf("QL") > -1) {
					
				}else if (data.data.to.usercode.indexOf("YH") > -1) {
					modChatUser('.ulist',new Array(data.data.from.id,data.data.from.username, '/static/img/0.jpg',data.data.from.usercode));
				}
				$("#user_contitle_user" + data.data.from.id).append('<div   style="text-align:left;font-family:Georgia;">' + data.data.from.username + data.data.fromTime + "<p>" + data.data.content + '</p></div>');
			}
			var g=new Array(val.id,val.groupName, '/static/img/0.jpg',val.groupCode);
			if(index==0){
				chatUser=g;
			}
			newGroup('.ulist', g);
			stompClient.subscribe('/talk/'+val.groupCode, function (result) {
		        	showContent(JSON.parse(result.body));
		        });
		});
	});
	
}

	function clearChatLeft(){
		$('.ulist').empty();
	}
	
	function trim(content) {
		try {
			content = content.replace(/ /g, "&nbsp;")
			content = content.replace(/\r\n/g, "<br />")
			content = content.replace(/\n/g, "<br />");
		} catch (e) {
			alert(e.message);
		}
		return content;
	}

	function hoverUser(tag) {
		$(tag).hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		});
	}

	function newUser(tag, user,chating) {
		var id = "user";
		var frag='<li id="' + id + user[0]+ '"><a href="#" class="glyphicon glyphicon-user" style="color: #333;">' + user[1] + '</a></li>';
		if (chating != undefined) {
			$(tag).prepend(frag);
			$('#' + id + user[0]).click(function() {
				titleUser('title_' + id + chating, user);
				$(this).addClass("active").siblings().removeClass("active");
				loadChatRecord(user[3]);
			});
		} else {// 创建好友
			$(tag).append(frag);
			$('#' + id + user[0]).click(function() {
				titleUser('title_' + id + user[0], user);
				$(this).addClass("active").siblings().removeClass("active");
				loadChatRecord(user[3]);
			});// 给按钮加事件
			if(i==0){
				titleUser('title_' + id + user[0], user);
				$('#' + id + user[0]).addClass("active");
				loadChatRecord(user[3]);
			}
		}
		hoverUser('#' + id + user[0]);
		if (user[3].indexOf("QL") > -1) {
			$("#" + id + user[0]).children().removeClass("glyphicon-user").addClass("glyphicon-tower");
		}
	}
	function modChatUser(tag, user, chating) {
				var id = "user";
				var frag = '<li id="' + id + user[0] + '"><a href="#" class="glyphicon glyphicon-user" style="color: #333;">' + user[1] + '</a></li>';
				$("#" + id + user[0]).remove();
				$(tag).prepend(frag);
				$('#' + id + user[0]).click(function() {
					titleUser('title_' + id + user[0], user);
					$(this).addClass("active").siblings().removeClass("active");
					loadChatRecord(user[3]);
				});
				hoverUser('#' + id + user[0]);
				$("#" + id + user[0]).addClass("active").siblings().removeClass("active");
				if (user[3].indexOf("QL") > -1) {
					$("#" + id + user[0]).children().removeClass("glyphicon-user").addClass("glyphicon-tower");
				}
}
	
	//////////////////////////////////socket/////////////////////////////
	function connect() {
	    var socket = new SockJS('/skywspoint'); 
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {	
	        stompClient.subscribe('/talk/'+loginUser.usercode, function (result) {
	        	var data=JSON.parse(result.body);
	        	// 3加好友.4删好友5.加群6退群7拉入群8踢出群
	        	var userId=data.fromId;
	        	var fromName=data.fromName;
	        	var fromCode=data.from;
	        	switch (data.type)
	        	{
	        	case 3:
	      		showWarn({"fromName":fromName,"msg":"加你为好友"});
	        	  break;
	        	case 4:
	      		showWarn({"fromName":fromName,"msg":"把你删除好友"});
	        	  break;
	        	case 5:
	      		showWarn({"fromName":fromName,"msg":"加入了你的群"});
	        	  break;
	        	case 6:
		      	showWarn({"fromName":fromName,"msg":"退出了你的群"});
	        	  break;
	        	case 7:
		      	showWarn({"fromName":fromName,"msg":"拉你进群"});
		          break;
	        	case 8:
		      	showWarn({"fromName":fromName,"msg":"踢我出群"});
		          break;
	        	default:
		        showContent(data);
		          break;
	        	}
	        	
	        });
	    });
	}

	function showWarn(obj) {
	$('#myModal').modal();
	 $("#addInfo").html("<font>{<strong> "+obj.fromName+"</strong>}"+obj.msg+"</font>");
	}

	function disconnect() {
	    if (stompClient !== null&&stompClient) {
	        stompClient.disconnect();
	    }
	}

	function postMsg(friendId,msg) {
	    stompClient.send("/app/singleChat", {}, JSON.stringify({'content': msg,'from':loginUser.usercode,'to':chatUser[3]}));
	}

	function showContent(body) {
		var t = new Date().toLocaleTimeString();// 当前时间
		if(body.fromId!=loginUser.id){
			if (body.to.indexOf("QL") > -1) {
				
			}else if (body.to.indexOf("YH") > -1) {
				modChatUser('.ulist',new Array(body.fromId,body.fromName, '/static/img/0.jpg',body.from));
			}
			$("#user_contitle_user" + chatUser[0]).append('<div   style="text-align:left;font-family:Georgia;">' + body.fromName + t + "<p>" + body.content + '</p></div>');
		}
	}
	