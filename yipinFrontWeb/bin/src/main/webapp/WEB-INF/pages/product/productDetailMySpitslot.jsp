<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
	<div class="d_tc w_norm">
	  <form action="" id="mySpitslotForm">
		<div class="tc_tit"><span class="f20">Hey！你们还不够好！</span>
			<input type="hidden" id="types" name="suggest.type" value="1"/>
					<span class="tc_tip" id="spitslot" style="display: none;">您还没有登录，<a href="${_ctxPath}/show-login.htm?index=login">登录</a>后才可以吐槽哦~</span>
		</div>
		<div class="tc_con cf">
			<div class="fn_left relative IE7static">
				<textarea name="suggest.content" id="spitslotContent" cols="30" rows="10" data-default="请在这里写下您想告诉我们的任何内容。我们将尽快完善这些问题，请随时回来视察我们的工作，并查看您的积分，会有惊喜哦。"></textarea>
				<div class="IE7relative">
					<span id="spitslotContentTip"></span>
				</div>
			</div>
			<div class="fn_left">
				<a href="javascript:;" id="sendSpitslot">下达指令</a>
			</div>
		</div>
	  </form>
	</div>
