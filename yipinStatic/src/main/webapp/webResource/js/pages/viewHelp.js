$(".helpName").on("click", function(){
	var $this = $(this);
	var id = $this.attr("data-id"),
	    helpCategoryName = $this.closest("dl").find("dt").text(),
		helpName = $this.text();
	$('#categoryName').text(helpCategoryName);
	$('#title').text(helpName);
	 $.ajax({
	    url : _ctxPath + "/help/getContentByHelpId.htm",
	    type : "GET",
	    data : {'help.helpId' : id},
	 	dataType : "json",
	    success : function(data) {
	        $("dd.on").removeClass("on");
	       	$this.parent().addClass("on");
	        $('#content').html(data.info);
	    }
	 });
});
