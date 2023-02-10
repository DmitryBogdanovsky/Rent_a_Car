$('.sd').click(function(){
	$('.hero, .content').addClass('scrolled');
});

$('.hero').mousewheel(function(e){
	if( e.deltaY < 0 ){
		$('.hero, .content').addClass('scrolled');
		return false;
	}
});
$(window).mousewheel(function(e){
	if( $('.hero.scrolled').length ){
		if( $(window).scrollTop() == 0 && e.deltaY > 0 ){
			$('.hero, .content').removeClass('scrolled');
		}
	}
});