// updates the tweet counter field with the number of characters the user has left

$(document).ready(function () {
  var textMax = 140;
  $('#charCounter').html(textMax);

  $('#tweetContent').keydown(function () {
    var textLength = $('#tweetContent').val().length;
    var textRemaining = textMax - textLength;

    $('#charCounter').html(textRemaining);
  });
});

/*
document.getElementById('tweetContent').addEventListener('onKeyUp', function () {
  var tweetcount = document.getElementById('tweetContent').innerHTML.length;
  console.log('listener triggered');
  document.getElementById('charCounter').innerHTML = (140 - tweetcount).toString();
});
*/

/*
 $('#tweetContent').addEventListener('onKeyUp', function () {
 var tweetcount = $('#tweetContent').innerHTML.length;
 console.log('listener triggered');
 $('#charCounter').innerHTML = (140 - tweetcount).toString();
 });
 */
