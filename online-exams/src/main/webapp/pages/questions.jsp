<html>

<head>

	<script>

			// var keyword is used to declare variable in javascript
	
			function saveResponse()
			{
					var qno=document.questionForm.qno.value;

					var question=document.questionForm.question.value;

					var submittedAnswer=document.questionForm.option.value;

					var data="qno="+qno+"&question="+question+"&submittedAnswer="+submittedAnswer;

					//alert(data);

					var xmlhttp=new XMLHttpRequest();

					xmlhttp.open("get","saveResponse?"+data);

					xmlhttp.send();

			}

	</script>

</head>

<body>

welcome ${username}

<div>

<form name="questionForm">
		
	<input type="text" name="qno" value="${question.qno}" readonly><br><br>
	
	<textarea  rows=3 cols=50 name="question" readonly> ${question.question} </textarea><br><br>
		
	<input  type="radio" name="option" value="${question.option1}" onclick="saveResponse()">  <span> ${question.option1} </span><br><br>
	
	<input  type="radio" name="option" value="${question.option2}" onclick="saveResponse()">  <span> ${question.option2} </span> <br><br>
		
	<input  type="radio" name="option" value="${question.option3}" onclick="saveResponse()"> <span> ${question.option3} </span> <br><br>
	
	<input  type="radio" name="option" value="${question.option4}" onclick="saveResponse()"> <span> ${question.option4} </span> <br><br>
		
	<input type="submit" value="next" formaction="next">
	<input  type="submit" value="previous" formaction="previous">
	<input  type="submit" value="end exam" formaction="endExam">

<br><br>

<input  style="border:none;display:none" type="text" name="previousAnswer" value="${previousAnswer}"><br><br>

</form>
</div>

<span> ${message} </span><br>

</body>

</html> 