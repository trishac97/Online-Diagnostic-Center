$('#toggle_message').click(
		
		             function()
		             {
	                    var value = $('#toggle_message').attr('value');
	                    $('#message').toggle('fast');
	                    
	                    if(value == 'Hide')
	                    {
	                    	$('#toggle_message').attr('value','Show');
	                    }
	                    else if(value == 'Show')
	                    {
	                    	$('#toggle_message').attr('value','Hide');
	                    }
		             }
		             
                          );