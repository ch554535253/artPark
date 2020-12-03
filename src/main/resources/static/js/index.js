jQuery(document).ready(function($) {

	// If firefox
//	if(navigator.userAgent.toLowerCase().match(/firefox/)) {
//		$('.browser-warning').removeClass('hidden');
//		setTimeout(function() {
//			$('.browser-warning').addClass('hidden');
//		}, 6*1000);
//	}

	// Display window (and start animation) when document is ready
	// This mininizes the risk of firefox messing up
	$('#window').attr('style', '');

	initAnimation();
	$(document).on('click', '.trigger-anim-replay', resetAnimation);
	
	$(document).on('click', '#submit', function(){
		login();
	});
	$(document).keydown(function(event){
		if(event.keyCode == '13'){
			login();
		}
	})
	
	function login(){
		$('#submit').addClass('loading');
		var data = new Object();
		data.userId = $("#userId")[0].value;
		data.password = md5($("#password")[0].value);
		$.ajax({
			url:contextPath+"/login",
			type:"post",
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(data),
			success:function (result) {
				console.log(result);
				if(SUCCESS == result.code){
					// alert(result.obj);
					setTimeout(function() {
						$('#submit').addClass('done').closest('#window').addClass('flip');
					}, 500);
				}else{
					$('#submit').removeClass('loading');
					alert("login failure!!")
				}
			},
			error:function (e) {
				$('#submit').removeClass('loading');
				alert("login failure!!")
			}
		})
	}
	function initAnimation() {
		setTimeout(function() {
			fyll.go('focInp userId', function() {
//				$('#submit').addClass('loading');
//				setTimeout(function() {
//					$('#submit').addClass('done').closest('#window').addClass('flip');
//				}, 1500);
			});
		}, 3*1000);
	}

	function resetAnimation() {
		var win = $('#window');

		win.stop().fadeOut(500, function() {

			// Reset things
			win.attr('style', '');
			win.find('input[type=text], input[type=password]').val('');
			win.find('.load-btn.loading').removeClass('loading done');

			// Clone and re-create window element to trigger animation restart
			win.removeClass('flip');
			win.before(win.clone(true)).remove();

			// Restart animation
			initAnimation();
		});
	}

});